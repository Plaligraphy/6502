package pkg;

import java.util.Scanner;

public class six502 {
    private boolean running = false;

    private final int memsize = 3;

    private int a = 0;
    private int x = 0;
    private int y = 0;
    private int pc = 64;
    private int p = 1;

    private int[] memory = new int[memsize];

    public static void main(String[] args) {
        six502 s = new six502();


        System.out.println("-- 6502 Emu --");
        s.running = true;
        while(s.running) {
            s.printVals();
            s.parseUserInput();
            s.pc++;
        }
        System.out.println("Closing...");
    }
    private void printVals() {
        System.out.println("A: " + Math.abs(a));
        System.out.println("X: " + Math.abs(x));
        System.out.println("Y: " + Math.abs(y));
        System.out.println("P: " + Math.abs(p));
        System.out.println("PC: " + Math.abs(pc));
        for(int i=0;i<memsize;i++) {
            System.out.println("Memory ["+i+"]: " + memory[i]);
        }
    }
    private String getUserInput() {
        Scanner in = new Scanner(System.in);
        return (in.nextLine()).toLowerCase();
    }
    private void parseUserInput() {
        switch(getUserInput()) {
            case "inx":
                x++;
                break;
            case "iny":
                y++;
                break;
            case "inc":
                a++;
                break;
            case "dex":
                x--;                                                    //Basic CMDs
                break;
            case "dey":
                y--;
                break;
            case "dec":
                a--;
                break;
            case "sta":
                setMemory(0);
                break;
            case "stx":
                setMemory(1);
                break;
            case "sty":
                setMemory(2);
                break;
















            case "stop":
                running = false;
                break;
        }
    }
    private void setMemory(int var) {           //var for selecting which register (A,X,Y)
        int locale;                             //locale for selecting memory location (changes according to memsize)
        System.out.println("Location: ");       // 0 = a    1 = x   2 = y
        six502 s = new six502();
        locale = Integer.parseInt(getUserInput());
        switch(var) {
            case 0:
                memory[locale] = getA();
                setA(0);
                break;
            case 1:
                memory[locale] = getX();
                setX(0);
                break;
            case 2:
                memory[locale] = getY();
                setY(0);
                break;

        }
    }
    public int getA() {
        return a;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setA(int a) {
        this.a = a;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
