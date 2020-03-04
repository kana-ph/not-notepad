package ph.kana.notnotepad.model;

import java.nio.file.Path;

public class TextFile {

    private String text;
    private Path path;

    public TextFile() { }

    public TextFile(String text, Path path) {
        this.text = text;
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
