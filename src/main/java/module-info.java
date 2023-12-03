module com.infinitops.musicaltickets {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires org.apache.pdfbox;

    opens com.infinitops.musicaltickets to javafx.fxml;
    opens com.infinitops.musicaltickets.model to javafx.fxml;
    exports com.infinitops.musicaltickets;
    exports com.infinitops.musicaltickets.model;
}