package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseInformattion {
    public JPanel panel1;
    private JButton button1;
    private JComboBox comboBox2;
    private JComboBox comboBox1;
    private JList list1;


    public CourseInformattion() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(1);
            }
        });
    }
}
