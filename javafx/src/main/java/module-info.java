module org.example.csis231_fall27_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.csis231_fall27_2 to javafx.fxml;
    exports org.example.csis231_fall27_2;
}