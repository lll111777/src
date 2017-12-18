package Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LoginPage extends Container {
    private JButton btnLogin;
    private JTextField accountField1;
    private JPasswordField passwordField1;
    private JLabel LabelAccount;
    private String Account;
    private String Password;

    static ResultSet rs=null;

    public LoginPage() {
        if(DBconnection.connect!=null){
            LabelInformation.setText("连接数据库成功！");
        }else {
            LabelInformation.setText("连接数据库失败！");
        }
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account=accountField1.getText();
                Password=String.valueOf(passwordField1.getPassword());
                checkMessage();
            }
        });
        BtnS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(5);
            }
        });
    }


    public void checkMessage(){
        accountField1.setText("");
        passwordField1.setText("");
        String message="SELECT key FROM user WHERE Account=\'"+Account+"\' AND "+"Password=\'"+Password+"\';";
        rs=DBconnection.selectQuery(message);
        int temp=-1;
        try {
            if (rs != null) {
                if (rs.next()){
                temp= rs.getInt(1);}
                else {
                    LabelInformation.setText("账号/密码错误！");
                    return;
                }
               if(temp==0){
                   LabelInformation.setText("管理员");
                   Controller.switchState(1);
               }
               else LabelInformation.setText("学生");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private JLabel LabelPassword;
    public JPanel Panel1;
    private JLabel LabelInformation;
    private JButton BtnS;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
