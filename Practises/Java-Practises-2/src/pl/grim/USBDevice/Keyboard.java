package pl.grim.USBDevice;

public class Keyboard implements USBDevice{
    String name;

    public Keyboard(String name) {
        this.name = name;
    }

    @Override
    public boolean connect() {
        System.out.println("Keyboard connected");
        return true;
    }

    @Override
    public boolean disconnect() {
        System.out.println("Keyboard discconected");
        return true;
    }

    public String getName() {
        return name;
    }
}
