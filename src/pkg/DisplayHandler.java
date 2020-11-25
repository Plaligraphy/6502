package pkg;

public class DisplayHandler {
    public final int version = 1;
    StorageHandler sh = new StorageHandler();
    CommandHandler ch = new CommandHandler();
    public void initBeginDisplay() {
        System.out.println("six5o2 Emulator ("+version+")");
        ch.init();
    }
    public void runtime() {
        //Ran every revolution
        System.out.println("\n");
        System.out.println("A: " + sh.getA());
        System.out.println("X: " + sh.getX());
        System.out.println("Y: " + sh.getY());
        System.out.println("CPU Status: " + sh.getP());
        System.out.println("Program Counter: " + sh.getPc());
        System.out.println("Mem0 = " + sh.getMem0());
        System.out.println("Mem1 = " + sh.getMem1());
        System.out.println("Mem2 = " + sh.getMem2());

    }
}
