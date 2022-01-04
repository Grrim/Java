package pl.grim.File.MusicFile;

public class MP3MusicFile extends AbstractMusicFile{
    private  int quality;

    public MP3MusicFile(String name, int size, String bandName, String title, int quality) {
        super(name, size, bandName, title);

        this.quality = quality;
    }

    @Override
    public void play() {
        System.out.println("Playing MP3 File");
    }

    public int getQuality(){
        return quality;
    }
}
