module com.infinitops.musicaltickets {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.infinitops.musicaltickets to javafx.fxml;
    opens com.infinitops.musicaltickets.model to javafx.fxml;
    exports com.infinitops.musicaltickets;
    exports com.infinitops.musicaltickets.model;
}