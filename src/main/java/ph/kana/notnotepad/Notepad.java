package ph.kana.notnotepad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static javafx.scene.input.KeyCombination.keyCombination;

public class Notepad extends Application implements EventHandler<ActionEvent> {

    private Path activeFile = null;

    private Stage stage;
    private TextArea textArea = new TextArea();
    private MenuItem openMenuItem = new MenuItem("_Open");
    private MenuItem saveMenuItem = new MenuItem("_Save");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var pane = new AnchorPane();
        this.stage = stage;

        textArea.setFont(Font.font("monospaced", 14.0));
        addToAnchorPane(pane, textArea, 29.0, 0.0, 0.0, 0.0);

        var fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);

        openMenuItem.setMnemonicParsing(true);
        openMenuItem.setAccelerator(keyCombination("SHORTCUT+O"));
        openMenuItem.setOnAction(this);

        saveMenuItem.setMnemonicParsing(true);
        saveMenuItem.setAccelerator(keyCombination("SHORTCUT+S"));
        saveMenuItem.setOnAction(this);

        var exitMenuItem = new MenuItem("E_xit");
        exitMenuItem.setMnemonicParsing(true);
        exitMenuItem.setAccelerator(keyCombination("SHORTCUT+Q"));

        fileMenu.getItems()
            .addAll(openMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        var cutMenuItem = new MenuItem("Cut");
        cutMenuItem.setMnemonicParsing(false);
        cutMenuItem.setAccelerator(keyCombination("SHORTCUT+X"));

        var editMenu = new Menu("_Edit");
        var copyMenuItem = new MenuItem("_Copy");
        copyMenuItem.setMnemonicParsing(true);
        copyMenuItem.setAccelerator(keyCombination("SHORTCUT+C"));

        var pasteMenuItem = new MenuItem("_Paste");
        pasteMenuItem.setMnemonicParsing(true);
        pasteMenuItem.setAccelerator(keyCombination("SHORTCUT+V"));

        editMenu.getItems()
            .addAll(cutMenuItem, copyMenuItem, pasteMenuItem);

        var menuBar = new MenuBar();
        menuBar.getMenus()
            .addAll(fileMenu, editMenu);
        addToAnchorPane(pane, menuBar, 0.0, 0.0, null, 0.0);

        var scene = new Scene(pane, 800.0, 600.0);
        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
        stage.show();

        setActiveFile(stage, null);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == saveMenuItem) {
            if (activeFile == null) {
                var fileChooser = new FileChooser();
                fileChooser.setTitle("Save to");
                var textFileExtension = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters()
                    .add(textFileExtension);
                fileChooser.setSelectedExtensionFilter(textFileExtension);

                var saveFile = fileChooser.showSaveDialog(stage);
                if (saveFile == null) {
                    return;
                }

                var filename = saveFile.getName();

                if (!filename.endsWith(".txt")) {
                    filename += ".txt";
                }
                setActiveFile(stage, Path.of(saveFile.getParent(), filename));
            }

            try {
                Files.writeString(activeFile, textArea.getText());
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        } else if (event.getSource() == openMenuItem) {
            var fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");

            var file = fileChooser.showOpenDialog(stage);
            if (file == null) {
                return;
            }
            setActiveFile(stage, file.toPath());

            try {
                String text = Files.readString(activeFile);
                textArea.setText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setActiveFile(Stage stage, Path activeFile) {
        this.activeFile = activeFile;
        if (activeFile == null) {
            stage.setTitle("Not Notepad - New Document");
        } else {
            stage.setTitle("Not Notepad - " + activeFile);
        }
    }

    private void addToAnchorPane(AnchorPane pane, Node node,
                                 Double anchorTop, Double anchorRight, Double anchorBottom, Double anchorLeft) {
        pane.getChildren()
            .add(node);

        AnchorPane.setTopAnchor(node, anchorTop);
        AnchorPane.setRightAnchor(node, anchorRight);
        AnchorPane.setBottomAnchor(node, anchorBottom);
        AnchorPane.setLeftAnchor(node, anchorLeft);
    }
}
