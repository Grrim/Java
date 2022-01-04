package pl.grim.File.TextFile;

import pl.grim.File.AbstractFile;
import pl.grim.File.FileType;

public abstract class AbstractTextFile extends AbstractFile implements TextFile {

    protected AbstractTextFile(String name, int size) {
        super(name, size);
    }

    @Override
    public FileType getType() {
        return FileType.TEXT;
    }
}
