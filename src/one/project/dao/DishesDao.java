package one.project.dao;

import java.util.List;
import java.util.Map;


public class DishesDao {
	// 根据菜系编号查询菜品
	public List<Map<String, Object>> finds(Integer c_id) {
		DBHelper db = new DBHelper();
		if (c_id == null) {
			String sql = "select d_id,d_name,c_name,d_prices,d_remark,d_photo from cuisine c,dishes d where c.c_id = d.d_fid";
			return db.gets(sql);
		} else { // 根据菜系编号查询
			String sql = "select d_id,d_name,c_name,d_prices,d_remark,d_photo from cuisine c,dishes d where c.c_id = d.d_fid and c_id = ?";
			return db.gets(sql, c_id);
		}
		
	}
	
	// 根据菜品编号将选中的菜添加到订单表中
	public Map<String, Object> addDishes(Integer d_id) {
		DBHelper db = new DBHelper();
		String sql = "select d_id,d_name,d_prices from dishes where d_id = ?";
		return db.findl(sql, d_id);
	}
	
	

}
