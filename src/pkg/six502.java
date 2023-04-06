package pkg;

import java.io.FileNotFoundException;
import java.util.Scanner;
/*
TO:DO
    Fix STA and STOP commands as STA does not currently store and stop currently throws error
    Rewrite substantial variable usages and consolidate code
    Prosper
 */
public class six502 {
    // Variable Declarations
    private boolean running = false; // Is the program running?
    private final int memorySize = 6;  // Sized to 6 for current ease of design. Will be increased in later builds
    private String codeInput; // Users input parsed into an understandable command.
    private int a = 0; // Accumulator Register
    private int x = 0; // X register
    private int y = 0; // Y Register
    private int pc = 64; // Program Counter
    int operation; // Operation Declaration

    private final int[] memory = new int[memorySize]; // Declares Memory Size
    public String userInput;
    public static void main(String[] args) {
        // Start of Program
        six502 s = new six502();
        System.out.println("-- 6502 Emu --");
        s.running = true;
        s.operation = 0;

        while(s.running) {
            // Main Loop
            System.out.println(s.operation);
            s.printValues();
            s.parseUserInput();
            s.pc++;
            s.checkInterrupts();
        }
        System.out.println("Closing...");
    }
    private void printValues() {
        System.out.println("A: " + Math.abs(a));
        System.out.println("X: " + Math.abs(x));
        System.out.println("Y: " + Math.abs(y));
        System.out.println("PC: " + Math.abs(pc));
        for(int i = 0; i < memorySize; i++) {
            System.out.println("Memory ["+i+"]: " + memory[i]);
        }
    }
    private String getUserInput() {
        Scanner in = new Scanner(System.in);
        return (in.nextLine()).toLowerCase();
    }
    private void parseUserInput() {
        if(operation == 0) {
            userInput = getUserInput();
        }else if(operation == 1) {
            userInput = codeInput;
            System.out.println(operation);
        }
        switch (userInput) {
            case "inx" -> x++; //Increment X Register
            case "iny" -> y++; //Increment Y Register
            case "inc" -> a++; //Increment Accumulator
            case "dex" -> x--; //Decrement X Register
            case "dey" -> y--; //Decrement Y Register
            case "dec" -> a--; //Decrement Accumulator
            case "stop" -> running = false; // Stops Program
            /*
            * Stop does not currently work, throws IndexOutOfBoundsException.
             */
            case "debug" -> { // Debugging only see Line 66 Note
                FileReader fr = new FileReader();
                try {
                    fr.readFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();    // Eventually will be used for Crash Report storage. Debugging only!
                }
            }

        }
        if(userInput.contains("st")) {
            String[] split = userInput.split(",");
            split[0] = removeWhitespace(split[0]);
            split[1] = removeWhitespace(split[1]);
            switch (split[0]) {
                case "sta" -> setMemory(0, Integer.parseInt(split[1]));
                case "stx" -> setMemory(1, Integer.parseInt(split[1]));
                case "sty" -> setMemory(2, Integer.parseInt(split[1]));
            }
        }else if(userInput.contains("ld")) {
            String[] split = userInput.split(",");
            split[0] = removeWhitespace(split[0]);
            split[1] = removeWhitespace(split[1]);
            switch (split[0]) {
                case "lda" -> {
                    setA(memory[Integer.parseInt(split[1])]);
                    memory[Integer.parseInt(split[1])] = 0;
                }
                case "ldx" -> {
                    setX(memory[Integer.parseInt(split[1])]);
                    memory[Integer.parseInt(split[1])] = 0;
                }
                case "ldy" -> {
                    setY(memory[Integer.parseInt(split[1])]);
                    memory[Integer.parseInt(split[1])] = 0;
                }
            }
        }

    }
    private void setMemory(int var, int locale) {
        //var sets which register is being stored
        //locale sets which part of memory the value of said register is being stored in
        switch (var) {
            case 0 -> {
                memory[locale] = getA();
                setA(0);
            }
            case 1 -> {
                memory[locale] = getX();
                setX(0);
            }
            case 2 -> {
                memory[locale] = getY();
                setY(0);
            }
        }
    }
    public void checkInterrupts() {
        if(memory[0] == 0 && memory[1] == 4 && memory[2] == 2) {
            System.out.println("Exit Code Called! Exiting now...");
            running=false;
        }
        if(memory[0] == 1 && memory[1] == 1) {
            //Addition interrupt when mem0 and mem1 are both equal to 1
            // Adds mem4 and mem5
            memory[3] = memory[4] + memory[5];
            System.out.println(memory[3]);
            memory[4] = 0;
            memory[5] = 0;
            memory[0] = 0;
            memory[1] = 0; //Could be sped up with for loop but eh
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

    public void setUserInput(String cmd) {
       // System.out.println(cmd);
        setOperation(1);
        codeInput = cmd;
    }
    public void setOperation(int op) {
        operation = op;
        System.out.println(operation);
    }

}
