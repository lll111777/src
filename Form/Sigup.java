package Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Sigup {
    public JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton BtnS;
    private JButton BtnR;
    private JLabel LabelA;
    private JLabel LabelP1;
    private JLabel LabelP2;
    static ResultSet rs=null;

    public Sigup() {
        BtnS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sigup();
            }
        });
        BtnR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(6);
            }
        });
    }
    public void Sigup(){
        String account=textField1.getText();
        String password1=String.valueOf(passwordField1.getPassword());
        if(account.length()<1||password1.length()<1){
            init();
            JOptionPane.showMessageDialog(panel1, "账号密码不得为空！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String password2=String.valueOf(passwordField2.getPassword());
        String sql="SELECT * FROM User WHERE Account=\'"+account+"\';";
        rs=DBconnection.selectQuery(sql);
        try {
            if(rs.next()){
                JOptionPane.showMessageDialog(panel1, "该账户已经被注册", "错误", JOptionPane.ERROR_MESSAGE);
                init();
            }
            else
            { if(password1.equals(password2)){
                sql="INSERT INTO User VALUES(0,\'"+account+"\',\'"+password1+"\');";
                DBconnection.insertMessage(sql);
                if(rs!=null){
                    JOptionPane.showMessageDialog(panel1, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    Controller.switchState(6);
                }
               }else {
                 JOptionPane.showMessageDialog(panel1, "两次密码不相等", "提示", JOptionPane.ERROR_MESSAGE);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void init(){
        textField1.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }
}
