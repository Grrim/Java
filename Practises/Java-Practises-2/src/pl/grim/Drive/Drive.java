package pl.grim.Drive;

import pl.grim.File.File;

public interface Drive {
    void addFile(File file);
    void listFlies();
    File findFile(String name);
}
