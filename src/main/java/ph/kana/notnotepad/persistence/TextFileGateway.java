package ph.kana.notnotepad.persistence;

import ph.kana.notnotepad.error.NotepadException;
import ph.kana.notnotepad.model.TextFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileGateway {

    public TextFile save(TextFile textFile) {
        var path = textFile.getPath();
        if (!path.endsWith(".txt")) {
            var filename = path.getFileName()
                .toString();
            path = path.getParent()
                .resolve(filename);
        }
        textFile.setPath(path);

        try {
            Files.writeString(path, textFile.getText());
            return textFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotepadException("Failed to save file: " + path, e);
        }
    }

    public TextFile open(Path path) {
        try {
            var text = Files.readString(path);
            return new TextFile(text, path);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new NotepadException("Failed to open file: " + path, e);
        }
    }
}
