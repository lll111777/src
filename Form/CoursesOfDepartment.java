package Form;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CoursesOfDepartment {
    public JPanel panel1;
    private JComboBox comboBox1;
    private JButton BtnReturn;
    private JLabel LabelDpt;
    private JTable table1;
    static ResultSet rs = null;
    private  String[] example;
    private DefaultTableModel model;
    private Vector vName;
    private Vector vData;


    public CoursesOfDepartment() {
        BtnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(1);
            }
        });
        init();

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dname=(String)comboBox1.getSelectedItem();
                setTable1(dname);
            }
        });
        vData=new Vector();
        vName=new Vector();
        vName.add("课程号");
        vName.add("课程名");
        table1.setEnabled(false);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        // tcr.setHorizontalAlignment(JLabel.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        table1.setDefaultRenderer(Object.class, tcr);
        model=new DefaultTableModel(vData,vName);
        table1.setModel(model);
    }

    public void init() {
        String sql = "SELECT dname FROM Dept;";
        rs = DBconnection.selectQuery(sql);
        //System.out.println(rs);
        example=new String[6];
        try {
            int i=0;
            if (rs != null) {

                while (rs.next()) {
                    //System.out.println("have");
                    example[i++]=rs.getString("dname");
                    //System.out.println(example[i-1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(example.length);

        for(int i=0;i<example.length;i++)
            comboBox1.addItem(example[i]);
        comboBox1.setSelectedItem(null);
    }


    public void setTable1(String dname)
    {
        String sql="SELECT count(*) FROM course WHERE dname=\'"+dname+"\';";
        rs = DBconnection.selectQuery(sql);
        int count=0;
        try{
            if(rs.next())
                count=rs.getInt(1);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        sql = "SELECT cno,cname FROM course WHERE dname=\'"+dname+"\';";
        rs = DBconnection.selectQuery(sql);
        //System.out.println(rs);
        vData=new Vector();
        try {
            int i=0;
            if (rs != null) {

                while (rs.next()) {
                    //System.out.println("have");
                    Vector vRow=new Vector();
                    vRow.add(rs.getInt(1));
                    vRow.add(rs.getString(2));
                    vData.add(vRow.clone());
                    //System.out.println(example[i-1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(example.length);

        model=new DefaultTableModel(vData,vName);
        table1.setModel(model);
    }
}
