package pl.grim;

import pl.grim.Drive.SSDDrive;
import pl.grim.File.ArchiveFile.RARArchiveFile;
import pl.grim.File.File;
import pl.grim.File.ImageFile.GIFImageFile;
import pl.grim.File.ImageFile.JPGImageFile;
import pl.grim.File.MusicFile.MP3MusicFile;
import pl.grim.File.TextFile.TXTTextFile;
import pl.grim.Program.Calculator.CalculatorProgram;
import pl.grim.Program.Game.GameProgram;
import pl.grim.USBDevice.Keyboard;
import pl.grim.USBDevice.Mouse;

public class Main {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Keyboard keyboard = new Keyboard("Logitech Keyboard");
        Mouse mouse = new Mouse("Razer Mouse");
        SSDDrive ssdDrive = new SSDDrive();
        Computer computer = new Computer(monitor, ssdDrive);

        computer.addUSBDevices(keyboard);
        computer.addUSBDevices(mouse);
        System.out.println("");

        CalculatorProgram calculatorProgram = new CalculatorProgram("Calculator", "Calculate numbers");
        calculatorProgram.calculate();
        System.out.println("");
        GameProgram gameProgram = new GameProgram("Game", "Plating video game", "Gothic");
        gameProgram.clickPlay();
        gameProgram.startGame();
        gameProgram.leaveGame();
        System.out.println("");

        TXTTextFile txtTextFile = new TXTTextFile("notatnik.txt",40);
        RARArchiveFile rarArchiveFile = new RARArchiveFile("archive.rar", 30);

        GIFImageFile gifImageFile = new GIFImageFile("nazwa1.gif", 100);
        JPGImageFile jpgImageFile = new JPGImageFile("nazwa1.jpg", 200, 80);

        MP3MusicFile mp3MusicFile = new MP3MusicFile("plik.mp3", 3000, "Linkin Park", "Numb", 100);

        ssdDrive.addFile(rarArchiveFile);
        ssdDrive.addFile(txtTextFile);
        ssdDrive.addFile(gifImageFile);
        ssdDrive.addFile(jpgImageFile);
        ssdDrive.addFile(mp3MusicFile);

        ssdDrive.listFlies();
        System.out.println("");
        File file = ssdDrive.findFile("archive.rar");
        System.out.println(file.getSize());
    }
}
