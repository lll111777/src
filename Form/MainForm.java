package Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainForm {
    private JButton 查询学生信息Button;
    private JButton 查询系内课程Button;
    private JButton 查询课程信息Button;
    public JPanel mainPanel;
    private JLabel Label1;
    private JButton BtnC;


    public MainForm() {
        Label1.setFont(new Font("Dialog",1,52));
        Label1.setForeground(Color.blue);
        查询学生信息Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(2);
            }
        });
        查询系内课程Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(3);
            }
        });
        查询课程信息Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(4);
            }
        });
        BtnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(6);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }


}
