package pkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// Very much work in progress, currently throws FileNotFoundException upon Runtime
public class FileReader {
    six502 s5 = new six502();
    File file = new File("code.asm");
    public void readFile() throws FileNotFoundException {
       // return in.nextLine();
        Scanner in = new Scanner(file);
        int i = 0;
        while(in.hasNextLine()) {
          //  System.out.println("["+i+"]" + in.nextLine());
            s5.setUserInput(in.nextLine());
            i++;
        }
    }


}
