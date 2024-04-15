module org.example.demodic {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens org.example.demodic to javafx.fxml;
    exports org.example.demodic;
}