package pkg;

import java.util.Scanner;

public class DisplayHandler extends StorageHandler{
    public final int version = 1;
    public boolean running = false;
    CommandHandler ch = new CommandHandler();
    public void initBeginDisplay() {
        System.out.println("six5o2 Emulator ("+version+")");
        ch.init();
        System.out.println(getP());
        runtime();

    }

}
