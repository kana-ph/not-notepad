package ph.kana.notnotepad.ui;

import javafx.application.HostServices;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.nio.file.Path;

public class DialogService {

    private final static FileChooser.ExtensionFilter TEXT_FILE_FILTER = new FileChooser
        .ExtensionFilter("Text Files (*.txt)", "*.txt");

    public Path showSaveDialog(Stage stage) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Save to");

        fileChooser.getExtensionFilters()
            .add(TEXT_FILE_FILTER);
        fileChooser.setSelectedExtensionFilter(TEXT_FILE_FILTER);

        var file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            return file.toPath();
        }
        return null;
    }

    public Path showOpenDialog(Stage stage) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        var file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            return file.toPath();
        }
        return null;
    }

    public void showAboutDialog(HostServices hostServices) {
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
}
