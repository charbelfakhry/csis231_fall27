package com.example.demo.university;

import com.example.demo.university.model.Gender;
import com.example.demo.university.model.User;
import com.example.demo.university.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiIntegrationTests {

    @Autowired
    private Environment environment;

    @Autowired
    private UserRepository userRepository;

    private final HttpClient client = HttpClient.newHttpClient();

    @BeforeEach
    void seedUser() {
        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("secret"));
            user.setFirstName("Ada");
            user.setLastName("Admin");
            userRepository.save(user);
        }
    }

    @Test
    void departmentAndStudentCrud() throws Exception {
        HttpResponse<String> dept = send("POST", "/api/departments",
                "{\"name\":\"Computer Science\",\"description\":\"CS dept\"}");
        assertThat(dept.statusCode()).isEqualTo(201);
        long deptId = extractId(dept.body());

        assertThat(send("GET", "/api/departments", null).body()).contains("Computer Science");
        assertThat(send("GET", "/api/departments/" + deptId, null).statusCode()).isEqualTo(200);
        assertThat(send("GET", "/api/departments/99999", null).statusCode()).isEqualTo(404);

        HttpResponse<String> student = send("POST", "/api/students",
                "{\"studentNumber\":\"S001\",\"firstName\":\"John\",\"lastName\":\"Doe\","
                        + "\"email\":\"john@uob.edu\",\"dateOfBirth\":\"2004-05-01\",\"departmentId\":" + deptId + "}");
        assertThat(student.statusCode()).isEqualTo(201);
        assertThat(student.body()).contains("Computer Science");
        long studentId = extractId(student.body());

        HttpResponse<String> updated = send("PUT", "/api/students/" + studentId,
                "{\"studentNumber\":\"S001\",\"firstName\":\"Johnny\",\"lastName\":\"Doe\","
                        + "\"email\":\"john@uob.edu\",\"departmentId\":" + deptId + "}");
        assertThat(updated.statusCode()).isEqualTo(200);
        assertThat(updated.body()).contains("Johnny");

        assertThat(send("GET", "/api/students", null).body()).contains("Johnny");
        assertThat(send("POST", "/api/students", "{\"firstName\":\"\"}").statusCode()).isEqualTo(400);

        // department still referenced by a student
        assertThat(send("DELETE", "/api/departments/" + deptId, null).statusCode()).isEqualTo(409);

        assertThat(send("DELETE", "/api/students/" + studentId, null).statusCode()).isEqualTo(204);
        assertThat(send("GET", "/api/students/" + studentId, null).statusCode()).isEqualTo(404);
        assertThat(send("DELETE", "/api/departments/" + deptId, null).statusCode()).isEqualTo(204);
    }

    @Test
    void login() throws Exception {
        HttpResponse<String> ok = send("POST", "/api/auth/login",
                "{\"username\":\"admin\",\"password\":\"secret\"}");
        assertThat(ok.statusCode()).isEqualTo(200);
        assertThat(ok.body()).contains("\"role\":\"USER\"");

        HttpResponse<String> bad = send("POST", "/api/auth/login",
                "{\"username\":\"admin\",\"password\":\"wrong\"}");
        assertThat(bad.statusCode()).isEqualTo(404);
    }

    @Test
    void registerThenLogin() throws Exception {
        String body = "{\"username\":\"jdoe\",\"password\":\"pass1234\","
                + "\"firstName\":\"John\",\"lastName\":\"Doe\","
                + "\"gender\":\"MALE\",\"dateOfBirth\":\"2004-05-01\"}";

        HttpResponse<String> created = send("POST", "/api/auth/register", body);
        assertThat(created.statusCode()).isEqualTo(201);
        assertThat(created.body()).contains("\"username\":\"jdoe\"").contains("\"role\":\"USER\"");

        User saved = userRepository.findByUsername("jdoe").orElseThrow();
        // the password is hashed, never stored as plain text
        assertThat(saved.getPassword()).isNotEqualTo("pass1234");
        // every form value is stored, nothing is filled in by the server
        assertThat(saved.getGender()).isEqualTo(Gender.MALE);
        assertThat(saved.getDateOfBirth()).isEqualTo(LocalDate.of(2004, 5, 1));

        // the new account can log in straight away
        HttpResponse<String> login = send("POST", "/api/auth/login",
                "{\"username\":\"jdoe\",\"password\":\"pass1234\"}");
        assertThat(login.statusCode()).isEqualTo(200);

        HttpResponse<String> duplicate = send("POST", "/api/auth/register", body);
        assertThat(duplicate.statusCode()).isEqualTo(409);
        assertThat(duplicate.body()).contains("Username is already taken");

        assertThat(send("POST", "/api/auth/register", "{\"username\":\"\"}").statusCode()).isEqualTo(400);
        assertThat(send("POST", "/api/auth/register", body.replace("jdoe", "future").replace("2004-05-01", "2999-01-01"))
                .statusCode()).isEqualTo(400);
    }

    private HttpResponse<String> send(String method, String path, String json) throws Exception {
        String port = environment.getProperty("local.server.port");
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create("http://localhost:" + port + path))
                .header("Content-Type", "application/json")
                .method(method, json == null
                        ? HttpRequest.BodyPublishers.noBody()
                        : HttpRequest.BodyPublishers.ofString(json));
        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    private long extractId(String body) {
        Matcher matcher = Pattern.compile("\"id\"\\s*:\\s*(\\d+)").matcher(body);
        assertThat(matcher.find()).isTrue();
        return Long.parseLong(matcher.group(1));
    }
}
