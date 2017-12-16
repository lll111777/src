package Form;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CourseInformattion {
    public JPanel panel1;
    private JButton button1;
    private JComboBox comboBox2;
    private JComboBox comboBox1;
    private JList list1;
    private JComboBox comboBox2;
    private JTable table1;
    static ResultSet rs = null;
    private String[] example;
    private DefaultTableModel model;
    private Vector vName;
    private Vector vData;


    public CourseInformattion() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.switchState(1);
            }
        });
        init();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cname=(String)comboBox1.getSelectedItem();
                setComboBox2(cname);
                vData=new Vector();
                model=new DefaultTableModel(vData,vName);
                table1.setModel(model);
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox2.getSelectedItem()==null)
                    //System.out.println("Item is null")
                            ;
                else{
                    String cname = (String) comboBox1.getSelectedItem();
                    String sectno = (String) comboBox2.getSelectedItem();
                    //System.out.println("sectno is"+sectno);
                    //System.out.println("select index is"+comboBox2.getSelectedIndex());
                    int sectno_int=Integer.parseInt(sectno);
                    setTable1(cname, sectno_int);
                }
            }
        });
        vData=new Vector();
        vName=new Vector();
        vName.add("学号");
        vName.add("姓名");
        table1.setEnabled(false);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        // tcr.setHorizontalAlignment(JLabel.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        table1.setDefaultRenderer(Object.class, tcr);
        model=new DefaultTableModel(vData,vName);
        table1.setModel(model);

    }
    public void init() {
        String sql="select count(*) from course;";
        rs = DBconnection.selectQuery(sql);
        int count=0;
        try{
            if(rs.next())
                count=rs.getInt(1);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        sql = "SELECT cname FROM course;";
        rs = DBconnection.selectQuery(sql);
        //System.out.println(rs);
        example=new String[count];
        try {
            int i=0;
            if (rs != null) {

                while (rs.next()) {
                    //System.out.println("have");
                    example[i++]=rs.getString(1);
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
    public void setComboBox2(String cname) {
        String sql="SELECT count(*)\n" +
                "FROM [section]\n" +
                "WHERE (((section.cno)=(SELECT cno FROM course WHERE cname=\'"+cname+"\'))) and (((section.dname)=(SELECT dname FROM course WHERE cname=\'"+cname+"\')))\n" +
                ";\n";
        rs = DBconnection.selectQuery(sql);
        int count=0;
        try{
            if(rs.next())
                count=rs.getInt(1);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        sql="SELECT section.sectno\n" +
                "FROM [section]\n" +
                "WHERE (((section.cno)=(SELECT cno FROM course WHERE cname=\'"+cname+"\'))) and (((section.dname)=(SELECT dname FROM course WHERE cname=\'"+cname+"\')))\n" +
                ";\n";
        rs = DBconnection.selectQuery(sql);
        //System.out.println(rs);
        example=new String[count];
        try {
            int i=0;
            if (rs != null) {

                while (rs.next()) {
                    //System.out.println("have");
                    example[i++]=rs.getString(1);
                    //System.out.println(example[i-1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(example.length);
        comboBox2.removeAllItems();
        for(int i=0;i<example.length;i++)
            comboBox2.addItem(example[i]);
        comboBox2.setSelectedItem(null);

    }

    public void setTable1(String cname,Integer sectno)
    {
        String sql="SELECT count(*)\n" +
                "FROM enroll AS E\n" +
                "WHERE E.cno=(SELECT cno FROM course WHERE cname=\'"+cname+"\') AND\n" +
                "      E.sectno="+sectno+" and (((E.dname)=(SELECT dname FROM course WHERE cname=\'"+cname+"\')));";
        rs = DBconnection.selectQuery(sql);
        int count=0;
        try{
            if(rs.next())
                count=rs.getInt(1);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        sql = "SELECT E.sid, (SELECT sname FROM Student Where Student.sid=E.sid) AS name\n" +
                "FROM enroll AS E\n" +
                "WHERE E.cno=(SELECT cno FROM course WHERE cname=\'"+cname+"\') AND\n" +
                "      E.sectno="+sectno+" and (((E.dname)=(SELECT dname FROM course WHERE cname=\'"+cname+"\')));";
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
