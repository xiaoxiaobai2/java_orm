import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用javabean封装数据库获取的信息
 * @author 张浩
 * @date 2019.10.10
 */
public class Demo03 {
    public static void main(String[] args) {
        List<Emp> empList=new ArrayList<>();
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet set=null;
        connection=JDBCUtils.getConn();
        try {
            ps=connection.prepareStatement("select * from emp");
            set=ps.executeQuery();
            while (set.next()){
                Emp emp=new Emp();
                emp.setId( set.getInt("id"));
                emp.setName(set.getString("name"));
                emp.setSalary(set.getDouble("salary"));
                emp.setDepartment(set.getString("department"));
                empList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,ps,set);
        }
        for (Emp row:empList) {
            System.out.print("Name"+"--"+row.getName()+"\t");
            System.out.print("ID"+"--"+row.getId()+"\t");
            System.out.print("Salary"+"--"+row.getSalary()+"\t");
            System.out.print("Department"+"--"+row.getDepartment()+"\t");
            System.out.println();
        }
    }
}
