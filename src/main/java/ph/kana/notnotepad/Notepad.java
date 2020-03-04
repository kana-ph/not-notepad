package ph.kana.notnotepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ph.kana.notnotepad.ui.NotepadController;

public class Notepad extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var location = getClass()
            .getResource("/ph/kana/notnotepad/ui/notepad.fxml");

        var loader = new FXMLLoader();
        loader.setLocation(location);

        var root = (Parent) loader.load();
        var scene = new Scene(root, 800.0, 600.0);

        var controller = (NotepadController) loader.getController();
        controller.setStage(stage);
        controller.setHostServices(getHostServices());

        stage.setTitle("Not Notepad - New Document");
        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
        stage.show();
    }
}
