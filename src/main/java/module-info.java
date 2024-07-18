module org.example.snake {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.snake to javafx.fxml;
    exports org.example.snake;
}