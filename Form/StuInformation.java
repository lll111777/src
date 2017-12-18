package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StuInformation {
    public JPanel panel1;
    private JTextField TextName;
    private JTextField TextSex;
    private JTextField TextAge;
    private JTextField TextYear;
    private JTextField TextGpa;
    private JTextField TextID;
    private JButton BtnCheck;
    private JButton BtnReturn;
    private JLabel LabelName;
    private JLabel LabelSex;
    private JLabel LabelYear;
    private JLabel LabelTime;
    private JLabel LabelGpa;
    private JLabel LabelID;
    static ResultSet rs=null;

    public StuInformation() {
        BtnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(1);
            }
        });
        BtnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInformation();
            }
        });
        BtnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(1);
            }
        });
    }
    public void getInformation(){
        String name,sex;
        int age,year;
        double gpa;
        int sid=Integer.parseInt(TextID.getText());
        String sql="SELECT * FROM Student WHERE sid="+sid+";";
        rs=DBconnection.selectQuery(sql);
        try {
            if (rs != null) {
                if (rs.next()){
                   name=rs.getString(2);
                   sex=rs.getString(3);
                   age=rs.getInt(4);
                   year=rs.getInt(5);
                   gpa=rs.getDouble(6);
                   TextName.setText(name);
                   TextSex.setText(sex);
                   TextAge.setText(String.valueOf(age));
                   TextYear.setText(String.valueOf(year));
                   TextGpa.setText(String.valueOf(gpa));
                }
                else {
                    JOptionPane.showMessageDialog(null, "该学生不存在", "提示", JOptionPane.ERROR_MESSAGE);

            }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
