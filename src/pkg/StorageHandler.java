package pkg;

import java.util.Scanner;

public class StorageHandler implements main {
    private int a,x,y,pc,p; //Accumulator, index regs, P = Status, Program Counter
    private int mem0,mem1,mem2;  //Memory integers, might switch to hex
    public String cmd;
    public Scanner in = new Scanner(System.in);
    public void setA(int a) {
        this.a = a;
    }

    public void setP(int p) {
        this.p = p;
        System.out.println("setting p");
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getA() {
        return a;
    }
    public int getPc() {
        return pc;
    }

    public int getP() {
        System.out.println("getting p");
        return p;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setMem0(int mem0) {
        this.mem0 = mem0;
    }
    public void setMem1(int mem1) {
        this.mem1 = mem1;
    }
    public void setMem2(int mem2) {
        this.mem2 = mem2;
    }
    public int getMem0() {
        return mem0;
    }
    public int getMem1() {
        return mem1;
    }
    public int getMem2() {
        return mem2;
    }
    public void runtime() {
        //Ran every revolution
        System.out.println("\n");
        System.out.println("A: " + getA());
        System.out.println("X: " + getX());
        System.out.println("Y: " + getY());
        System.out.println("CPU Status: " + getP());
        System.out.println("Program Counter: " + getPc());
        System.out.println("Mem0 = " + getMem0());
        System.out.println("Mem1 = " + getMem1());
        System.out.println("Mem2 = " + getMem2());
        System.out.println();
        System.out.println("Cmd: ");
        setCmd(in.nextLine());
    }
    public String getCmd() {
        return cmd;
    }
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

}
