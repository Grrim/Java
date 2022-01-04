package pl.grim.File.TextFile;

import pl.grim.File.FileType;

public class TXTTextFile extends AbstractTextFile{

    public TXTTextFile(String name, int size) {
        super(name, size);
    }

    @Override
    public void read() {
        System.out.println("Runing txt file");
    }
}
