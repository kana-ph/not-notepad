package ph.kana.notnotepad.persistence;

import ph.kana.notnotepad.model.TextFile;

import java.nio.file.Path;

public class TextFileGateway {

    public TextFile save(TextFile textFile) {
        return textFile;
    }

    public TextFile open(Path path) {
        return null;
    }
}
