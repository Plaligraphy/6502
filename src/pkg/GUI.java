package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class GUI {
    Code code = new Code();
    public String tf_Field;
    public void init() throws IOException, InterruptedException {
       // System.out.println("Not currently used: Need to think about what to use this for");
       // Thread.sleep(2000);
        createFrame(500,500,0,"6502 GUI");
       // code.exec();
    }private void createFrame(int x, int y, int opt, String title) {
        JFrame f = new JFrame(title);
        JPanel m = new JPanel(new BorderLayout()); f.add(m);
        JPanel s = new JPanel(); m.add(s, BorderLayout.SOUTH);
        JPanel n = new JPanel(); m.add(n, BorderLayout.NORTH);
        JPanel c = new JPanel(); m.add(c, BorderLayout.CENTER);
        switch(opt) {
            case 0:
                //Main screen
                JTextField tf = new JTextField(20);
                JButton submit_tf = new JButton("Submit");

                JTextArea ta = new JTextArea();

                JLabel sp = new JLabel();
                JLabel a = new JLabel();
                JLabel x_ = new JLabel();
                JLabel y_ = new JLabel();

                sp.setText("sp: " + code.sp);
                a.setText("a: " + code.a);
                x_.setText("x: " + code.x);
                y_.setText("y: " + code.y);
                Font reg_Font = new Font("Arial Black", Font.PLAIN, 20);
                sp.setFont(reg_Font);
                a.setFont(reg_Font);
                x_.setFont(reg_Font);
                y_.setFont(reg_Font);

                submit_tf.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        tf_Field = tf.getText();
                        try {
                            code.exec(tf_Field);
                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                n.add(sp); n.add(a); n.add(x_); n.add(y_);
                s.add(tf); s.add(submit_tf);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

        f.setSize(x,y);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
    }
}
