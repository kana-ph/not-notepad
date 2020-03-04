module app {
    requires javafx.controls;
    requires javafx.fxml;

    exports ph.kana.notnotepad;

    opens ph.kana.notnotepad.ui to
        javafx.fxml,
        javafx.graphics,
        javafx.controls;
}
