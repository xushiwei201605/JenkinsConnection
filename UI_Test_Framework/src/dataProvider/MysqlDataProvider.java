package dataProvider;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import utils.ObjectMap;

public class MysqlDataProvider extends NsDataProvider {
	ObjectMap objMap = new ObjectMap("ObjectMap/getMySqlValue.properties");
	
	public Object[][] getTestDataByMysql() throws Exception {
		// 从属性文件获取变量值
		String sql = objMap.getMySqlProperties("sqlstatement");
		// url指向要访问的数据库
		String url = objMap.getMySqlProperties("sqlurl");
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// mysql配置时的用户名
		String user = objMap.getMySqlProperties("username");
		// mysql配置时的密码
		String password = objMap.getMySqlProperties("pwd");
		List<Object[]> records = new ArrayList<Object[]>();

		try {
			// 加载驱动程序
			Class.forName(driver);
			// getConnection()方法，连接MySQL数据库
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("连接数据库成功");
			}
			// Statement里面带有很多方法，比如executeUpdata可以实现插入、更新和删除等
			// 创建statement类对象，用来执行SQL语句！！
			Statement statement = conn.createStatement();
			System.out.println("要执行的sql语句：" + sql);
//					ResultSet类，用来存放获取的结果集
			ResultSet rs = statement.executeQuery(sql);
			// 得到数据集的结构
			ResultSetMetaData rsMetaData = rs.getMetaData();
			// 获得该表的列数
			int cols = rsMetaData.getColumnCount();
			System.out.println(cols);
			while (rs.next()) {
				// String数组
				String fields[] = new String[cols];
				int col = 0;
				for (int i = 0; i < cols; i++) {
					// rs.getString(i+1)读取当前行指定的列，
//							String getString(int columnIndex),columnIndex从1开始，1代表第一列，
//							和数组的索引差1，因此要i+1
					fields[col] = rs.getString(i + 1);
					col++;
				}
				records.add(fields);
			}
//			System.out.println(records);
			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
		}
		return results;
	}
}
