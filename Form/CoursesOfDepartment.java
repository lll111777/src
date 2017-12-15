package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursesOfDepartment {
    public JPanel panel1;
    private JComboBox comboBox1;
    private JButton button1;
    private JList list1;

    public CoursesOfDepartment() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(1);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CoursesOfDepartment");
        frame.setContentPane(new CoursesOfDepartment().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
    }
}
