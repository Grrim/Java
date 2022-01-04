package pl.grim.File.ImageFile;

import pl.grim.File.AbstractFile;
import pl.grim.File.FileType;

public abstract class AbstractImageFile extends AbstractFile {
    protected AbstractImageFile(String name, int size) {
        super(name , size);
    }

    @Override
    public FileType getType() {
        return FileType.IMAGE;
    }
}
