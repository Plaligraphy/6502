package pkg;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Code {
    File asm = new File("code.asm");

    private int sp, x, y, a;
    private String[] cmds = {"lda", "ldx", "ldy", "sta", "stx", "sty", "inc","inx", "iny",
    "dec", "dex", "dey"};

    public void init() throws IOException, InterruptedException {
        System.out.println("6502");
        if (!asm.exists()) asm.createNewFile();
        System.out.println("Code belongs in code.asm");
        sp = 0100;
        exec();
    }
    private void read() throws IOException {
        FileReader fr = new FileReader(asm);
        char [] a = new char[50];
        fr.read(a);
        for(char c : a)
            System.out.print(c);
        fr.close();
        }
    private void exec() throws InterruptedException {
        Thread.sleep(2000);
        cls();
        Scanner in = new Scanner(System.in);
        System.out.println("-- Execution started --");
        System.out.println("Stack pointer set to " + sp);

        System.out.println("enter test command: ");
        String givenCommand = in.nextLine(); givenCommand.toLowerCase();
        boolean rst = Arrays.stream(cmds).anyMatch(givenCommand :: equals);
        if(!rst) {
            System.out.println("invalid!");
            exec();
        }
        if(givenCommand.contains("ld")) {
            System.out.println("load");
        }else if(givenCommand.contains("st")) {
            System.out.println("store");
        }else if(givenCommand.contains("in")) {
            if(givenCommand.contains("x")) {
                System.out.println("inx issued");
                x++;
                reload();
            }else if(givenCommand.contains("y")) {
                System.out.println("iny issued");
                y++;
                reload();
            }else if(givenCommand.contains("c")) {
                System.out.println("inc issued");
                a++;
                reload();
            }
        }else if(givenCommand.contains("de")) {
            if(givenCommand.contains("x")) {
                System.out.println("dex issued");
                x--;
                reload();
            }else if(givenCommand.contains("y")) {
                System.out.println("dey issued");
                y--;
                reload();
            }else if(givenCommand.contains("c")) {
                System.out.println("dec issued");
                a--;
                reload();
            }
        }

      }private void cls() {
        for (int i = 0; i < 50; ++i) System.out.println();
      }private void reload() throws InterruptedException {
        sp++;
        System.out.println("a: " + a);
        System.out.println("y: " + y);
        System.out.println("x: " + x);
        System.out.println("sp: " + sp);
        Thread.sleep(3000);
        exec();
    }
}
