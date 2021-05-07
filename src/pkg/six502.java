package pkg;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class six502 {
    private boolean running = false;

    private final int memsize = 6;
    private String codeInput;
    private int a = 0;
    private int x = 0;
    private int y = 0;
    private int pc = 64;
    private int p = 1;
    int oper;

    private int[] memory = new int[memsize];
    public String userInput;
    public static void main(String[] args) {
        six502 s = new six502();
        System.out.println("-- 6502 Emu --");
        s.running = true;
        s.oper = 0;
        while(s.running) {
            System.out.println(s.oper);
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
        if(oper == 0) {
            userInput = getUserInput();
        }else if(oper == 1) {
            userInput = codeInput;
            System.out.println(oper);
        }
        switch(userInput) {
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
            case "debug":
                FileReader fr = new FileReader();
                try {
                    fr.readFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //unused
                break;
        }
        if(userInput.contains("st")) {
            String[] split = userInput.split(",");
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
        }else if(userInput.contains("ld")) {
            String[] split = userInput.split(",");
            split[0] = removeWhitespace(split[0]);
            split[1] = removeWhitespace(split[1]);
            switch(split[0]) {
                case "lda":
                    setA(memory[Integer.parseInt(split[1])]);
                    memory[Integer.parseInt(split[1])] = 0;
                    break;
                case "ldx":
                    setX(memory[Integer.parseInt(split[1])]);
                    memory[Integer.parseInt(split[1])] = 0;
                    break;
                case "ldy":
                    setY(memory[Integer.parseInt(split[1])]);
                    memory[Integer.parseInt(split[1])] = 0;
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
        if(memory[0] == 1 && memory[1] == 1) {
            //Addition interrupt when mem0 and mem1 are both equal to 1
            // Adds mem4 and mem5
            memory[3] = memory[4] + memory[5];
            System.out.println(memory[3]);
            memory[4] = 0;
            memory[5] = 0;
            memory[0] = 0;
            memory[1] = 0; //Could be sped up with for loop but eh
        }q
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
        setOper(1);
        codeInput = cmd;
    }
    public void setOper(int op) {
        oper = op;
        System.out.println(oper);
    }

}
