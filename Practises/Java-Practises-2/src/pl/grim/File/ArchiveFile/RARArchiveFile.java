package pl.grim.File.ArchiveFile;

public class RARArchiveFile extends AbstractArchiveFile {

    public RARArchiveFile(String name, int size) {
        super(name, size);
    }

    @Override
    public void create() {
        System.out.println("Creating RAR file");
    }
}
