package pkg;

public class CommandHandler extends StorageHandler implements main {
    public void init() {
        setPc(64);
        setA(0);
        setX(0);
        setY(0);
        setP(100);
        setMem0(0);
        setMem1(0);
        setMem2(0);
        System.out.println(getP());
    }

}
/*
P -- Conversion Table
109 = Bad start clean run
402 = Error!
999 = Exiting...
100 = Clean Start
209 = Good Start bad run
 */