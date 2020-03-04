package ph.kana.notnotepad.ui;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ph.kana.notnotepad.model.TextFile;
import ph.kana.notnotepad.persistence.TextFileGateway;

public class NotepadController {

    private Stage stage;
    private HostServices hostServices;

    private TextFile activeFile;

    private final DialogService dialogService = new DialogService();
    private final TextFileGateway textFileGateway = new TextFileGateway();

    @FXML
    private TextArea textArea;

    @FXML
    public void openFile() {
        var path = dialogService.showOpenDialog(stage);
        if (path != null) {
            var textFile = textFileGateway.open(path);
            textArea.setText(textFile.getText());
        }
    }

    @FXML
    public void saveFile() {
        if (activeFile != null) {
            activeFile.setText(textArea.getText());
        } else {
            var path = dialogService.showSaveDialog(stage);
            if (path != null) {
                var textFile = new TextFile(textArea.getText(), path);
                setActiveFile(textFile);
            }
        }
        textFileGateway.save(activeFile);
    }

    @FXML
    public void showAbout() {
        dialogService.showAboutDialog(hostServices);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    private void setActiveFile(TextFile textFile) {
        this.activeFile = textFile;
        stage.setTitle("Not Notepad - " + textFile.getPath());
        textArea.setText(textFile.getText());
    }
}
