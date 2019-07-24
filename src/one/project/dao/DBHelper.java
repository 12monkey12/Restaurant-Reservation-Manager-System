package one.project.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	// 驱动程序只要加载一次，封装成静态代码块，加载类是执行
	static{
		try {
			Class.forName(ReadConfig.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// 将错误信息写入日志
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接的驱动
	 * @return
	 */
	private Connection getConnection() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(ReadConfig.getInstance().getProperty("url"), ReadConfig.getInstance());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	/**
	 * 给预编译执行语句块中的占位符赋值
	 * @param pstmt  要赋值的预编译执行语句块对象
	 * @param objects   要赋予的值
	 */
	private void setParams(PreparedStatement pstmt, Object...params){
		if (params == null || params.length <= 0) { // 说明预编译执行语句块中没有占位符
			return;
		}
		
		for (int i = 0, len = params.length; i < len; ++ i) {
			try {
				pstmt.setObject(i + 1, params[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 给预编译执行语句块中的占位符赋值
	 * @param pstmt
	 * @param list
	 */
	private void setParams(PreparedStatement pstmt, List<String> list){
		if (list == null || list.size() <= 0) { // 说明预编译执行语句块中没有占位符
			return;
		}
		
		for (int i = 0, len = list.size(); i < len; ++ i) {
			try {
				pstmt.setObject(i + 1, list.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭资源的方法
	 * @param rs  要关闭的结果集
	 * @param pstmt  要关闭的预编译语句块
	 * @param con  要关闭的连接
	 */
	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 更新数据的方法
	 * @param sql  要指定的更新语句，insert、delete和update
	 * @param params   要指定的sql语句中对应占位符的值
	 * @return
	 */
	public int update(String sql, Object...params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			con = this.getConnection();  // 获取连接
			pstmt = con.prepareStatement(sql);  // 创建预编译执行语句块
			this.setParams(pstmt, params);  // 给预编译执行语句块中的占位符赋值
			result = pstmt.executeUpdate();  // 执行获取结果
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(null, pstmt, con);
		}
		return result;
		
	}
	
	/**
	 * 获取结果集中的列的列名
	 * @param rs
	 * @return
	 */
	private String[] getColumnNames(ResultSet rs) {
		String[] columnNames = null;
		
		if (rs == null) {
			return null;
		}
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData(); // 获取结果集中的元数据
			int colCount = rsmd.getColumnCount();  // 获取结果集中列的数量
			columnNames = new String[colCount];
			
			// 循环获取列的名称
			for (int i = 0; i < colCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase(); // 根据列的编号获取列的名称
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return columnNames;
	}
	
	/**
	 * 查询全部数据的方法
	 * @param sql  要执行的查询语句
	 * @param params  查询语句中占位符的值
	 * @return  每一行数据以列名为键，对应列的值为值存到一个map中，将这些map即多行记录存到list中
	 */
	public List<Map<String, Object>> finds(String sql, Object...params) {
		List<Map<String, Object>> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Map<String, Object>>();
			// 处理结果集 -> 将结果集中的数据循环读取出来存到一个List<Map<String, String>>中
			Map<String, Object> map = null;
			
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) {  // 每循环一次就是一行数据，每一行数据就要存到一个map中
				map = new HashMap<String, Object>();
				Object obj = null;
				String typeName = null;
				Blob blob = null;
				byte[] bt = null;
				Date date = null;
				
				// 以列名为键，以值为值，存到map中
				for(String colName : colNames) {
					obj = rs.getObject(colName);
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();
//						System.out.println(typeName);
						if ("BLOB".equals(typeName)) {
							blob = rs.getBlob(colName);
							// 转成字节数组
							bt = blob.getBytes(1, (int)blob.length());
							map.put(colName, bt);
						} else if ("Date".equals(typeName)) {
							date = rs.getTimestamp(colName);
							map.put(colName, date);
						} else {
							map.put(colName, rs.getObject(colName));
						}
					}
					
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	/**
	 * 查询所有数据的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> finds(String sql, List<String> params) {
		List<Map<String, Object>> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Map<String, Object>>();
			// 处理结果集 -> 将结果集中的数据循环读取出来存到一个List<Map<String, String>>中
			Map<String, Object> map = null;
			
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) {  // 每循环一次就是一行数据，每一行数据就要存到一个map中
				map = new HashMap<String, Object>();
				Object obj = null;
				String typeName = null;
				Blob blob = null;
				byte[] bt = null;
				Date date = null;
				
				// 以列名为键，以值为值，存到map中
				for(String colName : colNames) {
					obj = rs.getObject(colName);
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();
						if ("BLOB".equals(typeName)) {
							blob = rs.getBlob(colName);
							// 转成字节数字
							bt = blob.getBytes(1, (int)blob.length());
							map.put(colName, bt);
						} else if ("Date".equals(typeName)) {
							date = rs.getTimestamp(colName);
							map.put(colName, date);
						} else {
							map.put(colName, rs.getObject(colName));
						}
					}
					
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	/**
	 * 查询单行数据的方法
	 * @param sql  要执行的查询语句
	 * @param params  查询语句中占位符的值
	 * @return  数据以列名为键，对应列的值为值存到一个map中
	 */
	public Map<String, Object> find(String sql, Object...params) {
		Map<String, Object> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			// 处理结果集 -> 将结果集中的数据循环读取出来存到一个List<Map<String, String>>中
			
			String[] colNames = this.getColumnNames(rs);
			if(rs.next()) {  // 每循环一次就是一行数据，每一行数据就要存到一个map中
				map = new HashMap<String, Object>();
				Object obj = null;
				Blob blob = null;
				String typeName = null;
				byte[] bt = null;
				
				// 以列名为键，以值为值，存到map中
				for(String colName : colNames) {
					obj = rs.getObject(colName);
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();
						if ("BLOB".equals(typeName)) {
							blob = rs.getBlob(colName);
							// 转成字节数组
							bt = blob.getBytes(1, (int)blob.length());
							map.put(colName, bt);
						} else {
							map.put(colName, rs.getObject(colName));
						}
					} 
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return map;
	}
	
	/**
	 * 查询一个值
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getTotal(String sql, Object...params) {
		int total = -1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return total;
	}
	
	/**
	 * 查询数据的方法
	 * @param sql 要执行的查询语句
	 * @param params 查询语句中占位符的值
	 * @return 每一行数据以列名为键，对应列的值存到一个map中，将这些map 即多行记录存到list中
	 */
	public List<Map<String, Object>> gets(String sql, Object...params) {
		List<Map<String, Object>> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Map<String, Object>>();
			//处理结果集 -> 将结果集中的数据循环读取出来存到一个List<Map<String, String>>中
			Map<String, Object> map = null;
			
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) { // 每循环一次就是一行数据，每一行数据就要存到一个map中
				map = new HashMap<String, Object>();
				Object obj = null;
				String typeName = null;
				Blob blob = null;
				//以列名为键，以值为值，存到map中
				byte[] bt = null;
				for (String colName : colNames) {
					obj = rs.getObject(colName);
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();
						if ("BLOB".equals(typeName)) {
							blob = rs.getBlob(colName);
							// 转成字节数组
							bt = blob.getBytes(1, (int)blob.length());
							map.put(colName, bt);
						} else {
							map.put(colName, obj);
						}
					}
					
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	/**
	 * 查询数据的方法
	 * @param sql 要执行的查询语句
	 * @param params 查询语句中占位符的值
	 * @return 每一行数据以列名为键，对应列的值存到一个map中，将这些map 即多行记录存到list中
	 */
	public List<Map<String, Object>> gets(String sql, List<Object> params) {
		List<Map<String, Object>> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Map<String, Object>>();
			//处理结果集 -> 将结果集中的数据循环读取出来存到一个List<Map<String, String>>中
			Map<String, Object> map = null;
			
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) { // 每循环一次就是一行数据，每一行数据就要存到一个map中
				map = new HashMap<String, Object>();
				Object obj = null;
				String typeName = null;
				Blob blob = null;
				//以列名为键，以值为值，存到map中
				byte[] bt = null;
				for (String colName : colNames) {
					obj = rs.getObject(colName);
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();
						if ("BLOB".equals(typeName)) {
							blob = rs.getBlob(colName);
							// 转成字节数组
							bt = blob.getBytes(1, (int)blob.length());
							map.put(colName, bt);
						} else {
							map.put(colName, obj);
						}
					}
					
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	/**
	 * 查询单行数据的方法
	 * @param sql 要执行的查询语句
	 * @param params 查询语句中占位符的值
	 * @return 每一行数据以列名为键，对应列的值存到一个map中，返回map
	 */
	public Map<String, Object> findl(String sql, Object... params) {
		Map<String, Object> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			//处理结果集 -> 将结果集中的数据循环读取出来存到一个List<Map<String, String>>中
			
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) { // 每循环一次就是一行数据，每一行数据就要存到一个map中
				map = new HashMap<String, Object>();
				Object obj = null;
				String typeName = null;
				Blob blob = null;
				//以列名为键，以值为值，存到map中
				byte[] bt = null;
				for (String colName : colNames) {
					obj = rs.getObject(colName);
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();
						if ("BLOB".equals(typeName)) {
							blob = rs.getBlob(colName);
							// 转成字节数组
							bt = blob.getBytes(1, (int)blob.length());
							map.put(colName, bt);
						} else {
							map.put(colName, obj);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return map;
	}
	
	/**
	 * 查询单行数据的方法
	 * @param sql 要执行的查询语句
	 * @param params 查询语句中占位符的值
	 * @return 每一行数据以列名为键，对应列的值存到一个map中，返回map
	 */
	public Map<String, Object> findl(String sql, List<Object> params) {
		Map<String, Object> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			
			//处理结果集 -> 将结果集中的数据循环读取出来存到一个List<Map<String, String>>中
			
			String[] colNames = this.getColumnNames(rs);
			while(rs.next()) { // 每循环一次就是一行数据，每一行数据就要存到一个map中
				map = new HashMap<String, Object>();
				Object obj = null;
				String typeName = null;
				Blob blob = null;
				//以列名为键，以值为值，存到map中
				byte[] bt = null;
				for (String colName : colNames) {
					obj = rs.getObject(colName);
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();
						if ("BLOB".equals(typeName)) {
							blob = rs.getBlob(colName);
							// 转成字节数组
							bt = blob.getBytes(1, (int)blob.length());
							map.put(colName, bt);
						} else {
							map.put(colName, obj);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return map;
	}
	
}
