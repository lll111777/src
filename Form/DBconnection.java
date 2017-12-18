package Form;
import java.sql.*;
import java.util.Properties;

public class DBconnection {
    public static Connection connect=null;
    public static PreparedStatement stmt=null;
    public static ResultSet rs=null;
    //设置属性
    public DBconnection(String db){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Properties p=new Properties();
            p.put("charSet","GBK");
            connect=DriverManager.getConnection("jdbc:odbc:"+db,p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //执行查询操作
    public static ResultSet selectQuery(String sql){
        try {
            stmt=connect.prepareStatement(sql);
            rs=stmt.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  rs;
    }
    public static void insertMessage(String sql){
        try {
            stmt=connect.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //关闭连接
    public  static void closeConnection(){
        try{
            //  rs.close();
            stmt.close();
            connect.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
