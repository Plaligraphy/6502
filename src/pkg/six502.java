package pkg;

import java.util.Scanner;

public class six502 {
    private boolean running = false;

    private final int memsize = 5;

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
            s.checkInterrupts();
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
        String runningUserInput = getUserInput();
        switch(runningUserInput) {
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

            case "stop":
                running = false;
                break;
        }
        if(runningUserInput.contains("st")) {
            String[] split = runningUserInput.split(",");
            split[0] = removeWhitespace(split[0]);
            split[1] = removeWhitespace(split[1]);
            switch(split[0]) {
                case "sta":
                    setMemory(0,Integer.parseInt(split[1]));
                    break;
                case "stx":
                    setMemory(1,Integer.parseInt(split[1]));
                    break;
                case "sty":
                    setMemory(2,Integer.parseInt(split[1]));
                    break;
            }
        }

    }
    private void setMemory(int var, int locale) {
        //var sets which register is being stored
        //locale sets which part of memory the value of said register is being stored in
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
    public void checkInterrupts() {
        if(memory[0] == 0 && memory[1] == 4 && memory[2] == 2) {
            System.out.println("Exit Code Called! Exiting now...");
            running=false;
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
    public String removeWhitespace(String pa) {
        String pt;
        pt = pa.replaceAll("\\s+","");
        return pt;
    }
}
