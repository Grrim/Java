package pl.grim.File.ArchiveFile;

import pl.grim.File.AbstractFile;
import pl.grim.File.FileType;

public abstract class AbstractArchiveFile extends AbstractFile implements ArchiveFile {

    protected AbstractArchiveFile(String name, int size) {
        super(name, size);
    }

    @Override
    public FileType getType() {
        return FileType.ARCHIVE;
    }
}
