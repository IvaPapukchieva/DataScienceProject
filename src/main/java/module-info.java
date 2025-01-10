module com.example.project11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;
    requires jdk.compiler;

    opens com.example.project11 to javafx.fxml;
    exports com.example.project11;
    exports com.example.project11.Controllers;
    opens com.example.project11.Controllers to javafx.fxml;
    opens com.example.project11.Controllers.FilterControllers to javafx.fxml;
    exports com.example.project11.Controllers.FilterControllers to javafx.fxml;
    exports com.example.project11.Controllers.Charts;
    opens com.example.project11.Controllers.Charts to javafx.fxml;
    exports com.example.project11.Controllers.TreeVisualization to javafx.fxml;
    opens com.example.project11.Controllers.TreeVisualization to javafx.fxml;

}