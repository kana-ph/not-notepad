package ph.kana.notnotepad.ui;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class NotepadController {

    private Stage stage;
    private HostServices hostServices;

    @FXML
    private TextArea textArea;

    @FXML
    public void openFile() {

    }

    @FXML
    public void saveFile() {

    }

    @FXML
    public void showAbout() {
        var aboutDialog = new Alert(Alert.AlertType.INFORMATION);
        aboutDialog.setTitle("About Not Notepad");
        aboutDialog.setHeaderText("This is Not Notepad but a Notepad clone!");

        var sourceCodeLink = new Hyperlink("https://github.com/kana0011/not-notepad");
        sourceCodeLink.setOnAction(event -> hostServices.showDocument("https://github.com/kana0011/not-notepad"));

        var textPane = new TextFlow();
        textPane.getChildren()
            .addAll(
                new Text("For god class demonstration.\n\n"),
                new Text("Source code at "),
                sourceCodeLink
            );
        textPane.setLineSpacing(1.5);
        aboutDialog.getDialogPane()
            .setContent(textPane);
        aboutDialog.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
}
