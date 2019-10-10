import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用Map封装数据库获取的信息
 * @author 张浩
 * @date 2019.10.10
 */
public class Demo02 {
    public static void main(String[] args) {
        List<Map<String,Object>> empList=new ArrayList<>();
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet set=null;
        connection=JDBCUtils.getConn();
        try {
            ps=connection.prepareStatement("select * from emp");
            set=ps.executeQuery();
            while (set.next()){
                Map<String,Object> map=new HashMap<>();

                map.put("id",set.getObject("id"));
                map.put("name",set.getObject("name"));
                map.put("salary",set.getObject("salary"));
                map.put("department",set.getObject("department"));
                empList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,ps,set);
        }
        for (Map<String,Object> row:empList) {
            for (String s:row.keySet()) {
                System.out.print(s+"--"+row.get(s)+"\t");
            }
            System.out.println();
        }
    }
}
