package one.project.util;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import one.project.dao.ReadConfig;

import com.ibm.icu.util.Calendar;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test test = new test();
		//test.test2();
		test.isCross();
	}
	
	public void test1(){
		String str = "2018-06-03";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			sdf.parse(str);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("不合法的日期");
		}
		System.out.println("程序结束！");
	}
	
	public void test2(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select r_date from reservation";
		List<Map<String, Object>> list = null;
		try {
			Class.forName(ReadConfig.getInstance().getProperty("driverClass"));
			conn = DriverManager.getConnection(ReadConfig.getInstance().getProperty("url"), ReadConfig.getInstance());
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				//Date date = rs.getDate(1);
				//Date date = rs.getTime(1);
				//Date date = rs.getTimestamp(1);
				Date date = rs.getTimestamp(1);
				Object obj = date;
				System.out.println(String.valueOf(obj));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void isCross(){
		String da1 = "2019-07-13 23:11:30";
		String da2 = "2019-07-13 17:11:30";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(da1);
			date2 = sdf.parse(da2);
			System.out.println(date1);
			System.out.println(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.HOUR_OF_DAY, 3);
		Date date1_end = cal.getTime();
		cal.setTime(date2);
		cal.add(Calendar.HOUR_OF_DAY, 3);
		Date date2_end = cal.getTime();
		System.out.println(date1_end);
		System.out.println(date2_end);
		if (date1_end.before(date2) || date2_end.before(date1)) {
			System.out.println("不相交");
		} else System.out.println("相交");
		
		
		
	}

}
