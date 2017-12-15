package Form;

import javax.swing.*;

public class Controller {
    public DBconnection database;
    public static int state;
    public static JFrame frame;

    public Controller(String db){
        database=new DBconnection(db);
        init();
    }
    public void init(){
        state=0;
        frame = new JFrame("LoginPage");

        LoginPage page1=new LoginPage();
        frame.setContentPane(page1.Panel1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,350);
        //frame.pack();
        frame.setVisible(true);
    }
    public static void switchState(int change){
        switch (change){
            case 1:
                state=1;
                frame.setVisible(false);
                frame.setTitle("管理员");
                frame.setContentPane(new MainForm().mainPanel);
                frame.setVisible(true);
                break;
            case 2:
                frame.setVisible(false);
                frame.setTitle("管理员");
                frame.setContentPane(new StuInformation().panel1);
                frame.setVisible(true);
                break;
            case 3:
                frame.setVisible(false);
                frame.setTitle("管理员");
                frame.setContentPane(new CoursesOfDepartment().panel1);
                frame.setVisible(true);
                break;
            case 4:
                frame.setVisible(false);
                frame.setTitle("管理员");
                frame.setContentPane(new CourseInformattion().panel1);
                frame.setVisible(true);
                break;
            default:
                break;
        }
    }
    public static void main(String[] args) {
        Controller controller=new Controller("Mydata");
    }
}
