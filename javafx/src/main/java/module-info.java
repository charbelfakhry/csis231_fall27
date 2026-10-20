module org.example.csis231_fall27_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens org.example.csis231_fall27_2 to javafx.fxml;
    // let Jackson read/create the request & response records
    opens org.example.csis231_fall27_2.api to com.fasterxml.jackson.databind;
    exports org.example.csis231_fall27_2;
}