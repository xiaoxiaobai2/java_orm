import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    static Properties properties=null;
    static {
        properties=new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            //测试是否成功加载资源文件
            System.out.println(properties.getProperty("mysqlDriver"));
            System.out.println(properties.getProperty("mysqlURL"));
            System.out.println(properties.getProperty("mysqlUser"));
            System.out.println(properties.getProperty("mysqlPwd"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        Connection connection=null;
        try {
            //加载驱动类
            Class.forName(properties.getProperty("mysqlDriver"));
            //连接数据库
            connection = DriverManager.getConnection(properties.getProperty("mysqlURL"),
                    properties.getProperty("mysqlUser"),
                    properties.getProperty("mysqlPwd"));
        } catch (ClassNotFoundException e) {
            System.err.println("驱动类加载失败！");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("数据库连接失败！");
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection c, PreparedStatement ps, ResultSet set){
        if (set!=null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (c!=null){
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
