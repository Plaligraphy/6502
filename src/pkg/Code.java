package pkg;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Code {
    File asm = new File("code.asm");
    Scanner in = new Scanner(System.in);
    public int sp, x, y, a;
    private int dataLoad;
    private String[] cmds = {"slc", "slx", "sly", "inc","inx", "iny",
    "dec", "dex", "dey", "ldx", "ldy", "ldc", "zlx", "exit"};


    public int mem0=0,mem1=0,mem2=0,mem3=0,mem4=0,mem5=0,mem6=0,mem7=0;

    public void init() throws IOException, InterruptedException {
        System.out.println("6502");
        if (!asm.exists()) asm.createNewFile();
        System.out.println("Code belongs in code.asm");
        sp = 0100;
        System.out.println("-- Execution started --");
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
    private void exec() throws InterruptedException, IOException {
        Thread.sleep(500);
        cls();
        System.out.println("type 'exit' to exit");
        System.out.println("Stack pointer set to " + sp);

        System.out.println("enter test command: ");
        String givenCommand = in.nextLine(); givenCommand.toLowerCase();
        boolean rst = Arrays.stream(cmds).anyMatch(givenCommand :: equals);
        if(!rst) {
            System.out.println("invalid!");
            exec();
        }
        if (givenCommand.equalsIgnoreCase("zlx")) {
            x=0;
            a=0;
            y=0;
            reload();
        }else if(givenCommand.contains("sl")) {
            if(givenCommand.contains("x")) {
                store("x");
            }else if(givenCommand.contains("y")) {
                store("y");
            }else if(givenCommand.contains("c")) {
                store("c");
            }
        }else if(givenCommand.contains("ld")) {
            if(givenCommand.contains("x")) { load("x");
            }else if(givenCommand.contains("y")) { load("y");}else if(givenCommand.contains("c")) { load("c"); }
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
        }else if(givenCommand.equalsIgnoreCase("exit")) {
            System.out.println("Exiting...");
            System.exit(0);
        }

      }private void store(String regStore) throws InterruptedException, IOException {
        System.out.println("enter store location: ");
        int storeLocale = in.nextInt();
        switch(storeLocale) {
            case 0:
                if(regStore.equalsIgnoreCase("x")) {
                    mem0+=x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem0+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem0+=a;
                    reload();
                }
                break;
            case 1:
                if(regStore.equalsIgnoreCase("x")) {
                    mem1+=x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem1+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem1+=a;
                    reload();
                }
                break;
            case 2:
                if(regStore.equalsIgnoreCase("x")) {
                    mem2+=x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem2+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem2+=a;
                    reload();
                }
                break;
            case 3:
                if(regStore.equalsIgnoreCase("x")) {
                    mem3+=x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem3+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem3+=a;
                    reload();
                }
                break;
            case 4:
                if(regStore.equalsIgnoreCase("x")) {
                    mem4+=x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem4+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem4+=a;
                    reload();
                }
                break;
            case 5:
                if(regStore.equalsIgnoreCase("x")) {
                    mem5+=x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem5+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem5+=a;
                    reload();
                }
                break;
            case 6:
                if(regStore.equalsIgnoreCase("x")) {
                    mem6+=x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem6+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem6+=a;
                    reload();
                }
                break;
            case 7:
                if(regStore.equalsIgnoreCase("x")) {
                    mem7 += x;
                    reload();
                }else if(regStore.equalsIgnoreCase("y")) {
                    mem7+=y;
                    reload();
                }else if(regStore.equalsIgnoreCase("c")) {
                    mem7+=a;
                    reload();
                }
                break;
        }
      }private void load(String reg) throws InterruptedException, IOException {//-----------------------------------------------
        System.out.println("memory location to transfer to register: ");
        dataLoad = in.nextInt();
        switch(dataLoad) {
            case 0:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem0;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem0;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem0;
                    reload();
                }
                break;
            case 1:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem1;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem1;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem1;
                    reload();
                }
                break;
            case 2:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem2;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem2;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem2;
                    reload();
                }
                break;
            case 3:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem3;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem3;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem3;
                    reload();
                }
                break;
            case 4:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem4;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem4;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem4;
                    reload();
                }
                break;
            case 5:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem5;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem5;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem5;
                    reload();
                }
                break;
            case 6:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem6;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem6;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem6;
                    reload();
                }
                break;
            case 7:
                if(reg.equalsIgnoreCase("x")) {
                    x+=mem7;
                    reload();
                }else if(reg.equalsIgnoreCase("y")) {
                    y+=mem7;
                    reload();
                }else if(reg.equalsIgnoreCase("c")) {
                    a+=mem7;
                    reload();
                }
                break;
           }
        }

      private void cls() {
        for (int i = 0; i < 50; ++i) System.out.println();
      }private void reload() throws InterruptedException, IOException {
        sp++;
        System.out.println("a: " + a);
        System.out.println("y: " + y);
        System.out.println("x: " + x);
        System.out.println("sp: " + sp);
        System.out.println("m0: " + mem0);
        System.out.println("m1: " + mem1);
        System.out.println("m2: " + mem2);
        System.out.println("m3: " + mem3);
        System.out.println("m4: " + mem4);
        System.out.println("m5: " + mem5);
        System.out.println("m6: " + mem6);
        System.out.println("m7: " + mem7);
        Thread.sleep(1000);
        exec();
    }
}
