package pl.grim.Drive;

import pl.grim.File.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HDDDrive implements Drive {
    List<File> files = new ArrayList<>();

    @Override
    public void addFile(File file) {
        files.add(file);
    }

    @Override
    public void listFlies() {
        for (File file : files){
            System.out.println(file.getName());
        }
    }

    @Override
    public File findFile(String name) {
        Optional<File> foundFile = files.stream()
                .filter(file -> file.getName().equals(name))
                .findFirst();

        return foundFile.orElseThrow();
    }
}
