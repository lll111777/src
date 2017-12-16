package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LoginPage {
    private JButton btnLogin;
    private JTextField accountField1;
    private JPasswordField passwordField1;
    private JLabel LabelAccount;
    private String Account;
    private String Password;

    static ResultSet rs=null;

    public LoginPage() {
        if(DBconnection.connect!=null){
            LabelInformation.setText("数据库在线！");
            LabelInformation.setText("连接数据库成功！");
        }else {
            LabelInformation.setText("数据库已挂！");
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
    }


    public void checkMessage(){
        accountField1.setText("");
        passwordField1.setText("");
        String message="SELECT [key] FROM [user] WHERE Account=\'"+Account+"\' AND "+"Password=\'"+Password+"\';";
        String message="SELECT key FROM user WHERE Account=\'"+Account+"\' AND "+"Password=\'"+Password+"\';";
        rs=DBconnection.selectQuery(message);
        int temp=0;
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
            } else {
                LabelInformation.setText("账号/密码错误！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private JLabel LabelPassword;
    public JPanel Panel1;
    private JLabel LabelInformation;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
