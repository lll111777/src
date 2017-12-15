package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CoursesOfDepartment {
    public JPanel panel1;
    private JComboBox comboBox1;
    private JButton BtnReturn;
    private JList list1;
    private JLabel LabelDpt;
    private JButton BtnCheck;
    static ResultSet rs = null;
    private static String[] example;


    public CoursesOfDepartment() {
        BtnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(1);
            }
        });
        init();

    }

    public void init() {
        String sql = "SELECT dname FROM Dept ORDER BY dname;";
        rs = DBconnection.selectQuery(sql);
        System.out.print(rs);
        example=new String[6];
        try {
            int i=0;
            if (rs != null) {
                while (rs.next()) {

                    example[i++]=rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(example);
        comboBox1=new JComboBox(example);
    }
}
