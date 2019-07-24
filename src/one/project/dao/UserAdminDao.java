package one.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import one.project.util.StringUtil;

public class UserAdminDao {

	// 获取登录结果集
	/**
	 * 用户或管理员登录
	 * @param flag
	 * @param account
	 * @param pwd
	 * @return
	 */
	public Map<String, Object> login(int flag, String account, String pwd) {
		DBHelper db = new DBHelper();
		String sql = null;
//		String user_sql = "select * from rusers where u_account = ? and u_password = ?";
//		String ad_sql = "select * from radmin where a_account = ? and a_password = ?";
		// 用户登录和管理员登录用不同的sql语句
		if (flag == 0) {
			sql = "select * from rusers where u_account = ? and u_password = ?";
		} else if (flag == 1) {
			sql = "select * from radmin where a_account = ? and a_password = ?";
		}
		return db.find(sql, account, pwd);
	}
	
	/**
	 * 添加用户
	 * @param account
	 * @param password
	 * @param name
	 * @param sex
	 * @param telephone
	 * @return
	 */
	public int register(String account, String password, String name, String sex, String telephone) {
		DBHelper db = new DBHelper();
		String sql = "insert into rusers values(sq_rusers_u_id.nextval, ?, ?, ?, ?, ?)";
		return db.update(sql, account, password, name, sex, telephone);
	}
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<Map<String, Object>> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select * from rusers order by u_id";
		return db.finds(sql);
	}
	
	/**
	 * 多条件组合查询用户信息，模糊查询
	 * @param account
	 * @param name
	 * @param sex
	 * @param telephone
	 * @return
	 */
	public List<Map<String, Object>> findByCondition(String account, String name, String sex, String telephone){
		DBHelper db = new DBHelper();
		List<String> params = new ArrayList<String>();
		String sql = "select * from rusers where 1 = 1";
		
		if (!StringUtil.isNull(account)) {
			sql += " and u_account like '%'||?||'%'";
			params.add(account);
		}
		if (!StringUtil.isNull(name)) {
			sql += " and u_name like '%'||?||'%'";
			params.add(name);
		}
		if (!StringUtil.isNull(sex)) {
			sql += " and u_sex = ?";
			params.add(sex);
		}
		if (!StringUtil.isNull(telephone)) {
			sql += " and u_telephone like '%'||?||'%'";
			params.add(telephone);
		}
		// 排序
		sql += " order by u_id";
		
		return db.finds(sql, params);
	}
	
	public Map<String, Object> findUserById(int id){
		DBHelper db = new DBHelper();
		String sql = "select * from rusers where u_id = ?";
		return db.find(sql, id);
	}
	
	// 修改密码
	public int modifyUserpwd(String id , String pwd){
		DBHelper db = new DBHelper();
		String sql = "update rusers set u_password = ? where u_id = ?";
		return db.update(sql, pwd, id);
	}
	
}
