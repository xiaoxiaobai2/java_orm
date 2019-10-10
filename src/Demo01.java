import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用Object[] 来封装数据库获取的信心
 * @author 张浩
 * @date 2019.10.10
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Object[]> empList=new ArrayList<>();
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet set=null;
        connection=JDBCUtils.getConn();
        try {
            ps=connection.prepareStatement("select * from emp");
            set=ps.executeQuery();
            while (set.next()){
                Object[] objects=new Object[4];
                objects[0]=set.getObject("id");
                objects[1]=set.getObject("name");
                objects[2]=set.getObject("salary");
                objects[3]=set.getObject("department");
                empList.add(objects);
//                System.out.println(set.getObject("id")+"--"+set.getObject("name")+"--"+set.getObject("salary")+"--"+set.getObject("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,ps,set);
        }
        for (Object[] o:empList) {
            for (int i = 0; i < o.length; i++) {
                System.out.print(o[i]+"--");
            }
            System.out.println();
        }
    }
}
