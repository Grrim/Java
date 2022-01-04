package pl.grim;

import pl.grim.Drive.Drive;
import pl.grim.USBDevice.Keyboard;
import pl.grim.USBDevice.Mouse;
import pl.grim.USBDevice.USBDevice;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private Monitor monitor;
    private Drive drive;
    private Headphones headphones;

    private List<USBDevice> usbDevices = new ArrayList<>();

    public Computer(Monitor monitor, Drive drive){
        this.monitor = monitor;
        this.drive = drive;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Headphones getHeadphones() {
        return headphones;
    }

    public void setHeadphones(Headphones headphones) {
        this.headphones = headphones;
    }

    public void setUsbDevices(List<USBDevice> usbDevices) {
        this.usbDevices = usbDevices;
    }

    public void connectKeyboard(Keyboard keyboard) {
        System.out.println("Connected to " + keyboard.getName());
    }

    public void connectMouse(Mouse mouse) {
        System.out.println("Connected to " + mouse.getName());
    }

    public void addUSBDevices(USBDevice usbDevice){
        boolean isConnected = usbDevice.connect();

        if(isConnected) {
            usbDevices.add(usbDevice);
        }
    }

    public void removeUSBDevice(USBDevice usbDevice){
        boolean isDisconnected = usbDevice.disconnect();

        if(isDisconnected) {
            usbDevices.remove(usbDevice);
        }
    }
}
