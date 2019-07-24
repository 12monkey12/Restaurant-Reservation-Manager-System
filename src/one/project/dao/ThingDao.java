package one.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import one.project.util.StringUtil;

public class ThingDao {

	 // 增加菜系
	public int addCuisine(String cuisineName) {
		DBHelper db = new DBHelper();
		String sql = "insert into cuisine values (seq_cuisine_c_id.nextval, ?)";
		return db.update(sql, cuisineName);
	}
	
	// 根据菜系编号查菜系名称
	public Map<String, Object> researchCuisineNameById(String id){
		DBHelper db = new DBHelper();
		String sql = "select * from cuisine where c_id = ?";
		return db.find(sql, id);
	}
	
	
	 // 查菜系所有数据
	public List<Map<String, Object>> showCuisine() {
		DBHelper db = new DBHelper();
		String sql = "select * from cuisine order by c_id";
		return db.finds(sql);
	}
	
	
	 // 查询菜品所有数据
	public List<Map<String, Object>> showFood() {
		DBHelper db = new DBHelper();
		String sql = "select d_id,d_name,c_name,d_prices from cuisine c,dishes d where c.c_id = d.d_fid order by d_id";
		return db.finds(sql);
	}
	
	// 根据菜品编号查菜品
	public Map<String, Object> researchDishesById(String id) {
		DBHelper db = new DBHelper();
		String sql = "select * from dishes where d_id = ?";
		return db.find(sql, id);
	}
	
	 // 查询所有桌子
	public List<Map<String, Object>> showTable() {
		DBHelper db = new DBHelper();
		String sql = "select * from rtables order by t_id";
		return db.finds(sql);
	}
	
	// 添加桌子
	public int addTable(String t_type){
		DBHelper db = new DBHelper();
		String sql = "insert into rtables values (seq_rtables_t_id.nextval, ?, default)";
		return db.update(sql, t_type);
	}
	
	// 删除桌子
	public int deleteTable(String id) {
		DBHelper db = new DBHelper();
		String sql = "delete from rtables where t_id = ?";
		return db.update(sql, id);
	}
	
	 // 条件查询菜品信息
	public List<Map<String, Object>> findFoodByCondition(String name, String fid, String price) {
		DBHelper db = new DBHelper();
		List<String> params = new ArrayList<String>();
		String sql = "select d_id,d_name,c_name,d_prices from cuisine c,dishes d where c.c_id = d.d_fid";
		
		if (!StringUtil.isNull(name)) {
			sql += " and d_name like '%'||?||'%'";
			params.add(name);
		}
		if (!StringUtil.isNull(fid) && !"0".equals(fid)) {
			sql += " and d_fid = ?";
			params.add(fid);
		}
		if (!StringUtil.isNull(price)) {
			sql += " and d_prices = ?";
			params.add(price);
		}
		sql += " order by d_id";
		return db.finds(sql, params);
	} 
	
	/**
	 * 将菜系编号添加到combo控件中
	 * @param combo
	 */
	public void addCombo(Combo combo) {
		combo.removeAll(); //先移除所有数据
		combo.add("0 全部");
		DBHelper db = new DBHelper();
		String sql = "select c_id,c_name from cuisine order by c_id";
		List<Map<String, Object>> list = db.finds(sql);
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				combo.add(String.valueOf(map.get("c_id")) + " " +String.valueOf(map.get("c_name")));
			}
		}
	}
	public void addCombo2(Combo combo) {
		combo.removeAll(); //先移除所有数据
		DBHelper db = new DBHelper();
		String sql = "select c_id,c_name from cuisine order by c_id";
		List<Map<String, Object>> list = db.finds(sql);
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				combo.add(String.valueOf(map.get("c_id")) + " " +String.valueOf(map.get("c_name")));
			}
		}
	}
	
	// 删除菜系
	public int deleteCuisine(String id) {
		DBHelper db = new DBHelper();
		String sql = "delete from cuisine where c_id = ?";
		return db.update(sql, id);
	}
	
	//删除菜品
	public int deleteFood(String id){
		DBHelper db = new DBHelper();
		String sql = "delete from dishes where d_id = ?";
		return db.update(sql, id);
	}
	
	//根据菜品编号显示菜品描述和图片
	public Map<String, Object> showFoodById(String id) {
		DBHelper db = new DBHelper();
		String sql = "select d_remark,d_photo from dishes where d_id = ?";
		return db.find(sql, id);
	}
	
	// 添加菜品
	public int addFood(String name, String cid, String price, String mark, byte[] photo){
		DBHelper db = new DBHelper();
		String sql = "insert into dishes values (seq_dishes_d_id.nextval, ?, ?, ?, ?, ?)";
		return db.update(sql, name, cid, price, mark, photo);
	}
	
	// 查询菜品出售数量
	public List<Map<String, Object>> showFoodSaleCount(){
		DBHelper db = new DBHelper();
		String sql = "select d.d_id,d.d_name,c.c_name,nvl((select sum(ot_fcount) from orderdetail od,orders o "
				+ "where od.ot_did = d.d_id and o.o_state = 1 and od.ot_oid = o.o_id group by ot_did),0) salecount,"
				+ "d.d_prices from dishes d,cuisine c where d.d_fid = c.c_id order by salecount desc";
		return db.finds(sql);
	}
	
	// 根据菜系编号查菜品出售数量
	public List<Map<String, Object>> showFoodSaleCountByFid(String fid) {
		DBHelper db = new DBHelper();
		List<String> params = new ArrayList<String>();
		String sql = "select d.d_id,d.d_name,c.c_name,nvl((select sum(ot_fcount) from orderdetail od,orders o "
				+ "where od.ot_did = d.d_id and o.o_state = 1 and od.ot_oid = o.o_id group by ot_did),0) salecount,"
				+ "d.d_prices from dishes d,cuisine c where d.d_fid = c.c_id";

		if (!StringUtil.isNull(fid)) {
			sql += " and c.c_id = ?";
			params.add(fid);
		}
		sql += " order by salecount desc";
		return db.finds(sql, params);
	}

	
	// 修改菜品图片
	public int modifyFoodImage(byte[] bt, String id){
		DBHelper db = new DBHelper();
		String sql = "update dishes set d_photo = ? where d_id = ?";
		return db.update(sql, bt, id);
	}
	
	// 查询所有预约
	public List<Map<String, Object>> showReservation(){
		DBHelper db = new DBHelper();
		String sql = "select * from reservation order by r_id";
		return db.finds(sql);
	}
	
	// 添加预约
	public int addReservation(String tid, String uid, String date, String note){
		DBHelper db = new DBHelper();
		String sql = "insert into reservation values (seq_reservation_r_id.nextval, ?, 1, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), default, ?)";
		return db.update(sql, tid, date, note);
	}
	
	// 根据编号查预约表
	public Map<String, Object> showReservationById(String id){
		DBHelper db = new DBHelper();
		String sql = "select * from reservation where r_id = ?";
		return db.find(sql, id);
	}
	
	// 根据用户编号查预约表
	public List<Map<String, Object>> researchReservationById(int id){
		DBHelper db = new DBHelper();
		String sql = "select * from reservation where r_uid = ?";
		return db.finds(sql, id);
	}
	
	// 根据条件查询预约
	public List<Map<String, Object>> researchReservationByCondition(String date, String uid, String tid, String type){
		DBHelper db = new DBHelper();
		List<String> params = new ArrayList<String>();
		String sql = "select * from reservation where 1 = 1";
		if (StringUtil.isNull(date)) {
			sql += " and to_char(r_date, 'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd')";
		} else {
			sql += " and to_char(r_date, 'yyyy-mm-dd') = ?";
			params.add(date);
		}
		if (!StringUtil.isNull(uid)) {
			sql += " and r_uid = ?";
			params.add(uid);
		}
		if (!StringUtil.isNull(tid)) {
			sql += " and r_tid = ?";
			params.add(tid);
		}
		if (!StringUtil.isNull(type) && !"全部".equals(type)) {
			if ("未赴约".equals(type)) {
				sql += " and r_type = 0";
			} else if ("已赴约".equals(type)) {
				sql += " and r_type = 1";
			} else if ("违约".equals(type)) {
				sql += " and r_type = 2";
			}
		}
		sql += " order by r_id";
		return db.finds(sql, params);
	}
	
	// 修改预约状态
	public int modifyReservationState(String id , String flag){
		DBHelper db = new DBHelper();
		String sql = null;
		if ("已赴约".equals(flag)){
			sql = "update reservation set r_type = 1 where r_id = ?";
		} else if ("违约".equals(flag)) {
			sql = "update reservation set r_type = 2 where r_id = ?";
		}
		return db.update(sql, id);
	}
	
	// 查询桌子数
	public String[] researchTabelNumber(){
		DBHelper db = new DBHelper();
		String[] str = null;
		String sql_sum = "select count(*) from rtables";
		String sql_used = "select count(*) from rtables where t_state = 1";
		String sql_useful = "select count(*) from rtables where t_state = 2";
		String sql_reservation = "select count(*) from rtables where t_state = 0";
		String sum = String.valueOf(db.getTotal(sql_sum));
		String used = String.valueOf(db.getTotal(sql_used));
		String useful = String.valueOf(db.getTotal(sql_useful));
		String reservation = String.valueOf(db.getTotal(sql_reservation));
		return str = new String[]{reservation, used, useful, sum};
	}
	
	// 查询所有订单
	public List<Map<String, Object>> showOrder(){
		DBHelper db = new DBHelper();
		String sql = "select o_id,o_uid,o_tid,o_totalPrice,o_starttime,o_state,o_endtime,o_number from orders order by o_id";
		return db.finds(sql);
	}
	
	// 修改订单状态
	public int modifyOrderState(String id){
		DBHelper db = new DBHelper();
		String sql = "update orders set o_state = 1,o_endtime = sysdate where o_id = ?";
		return db.update(sql, id);
	}
	
	// 通过用户姓名查编号
	public int researchIdByName(String uname){ 
		DBHelper db = new DBHelper();
		String sql = "select u_id from rusers where u_name = ?";
		return db.getTotal(sql, uname);
	}
	
	// 通过编号查用户姓名
		public Map<String, Object> researchNameById(String id){ 
			DBHelper db = new DBHelper();
			String sql = "select u_name from rusers where u_id = ?";
			return db.find(sql, id);
		}
	
	// 条件查询订单
	public List<Map<String, Object>> researchOrderByCondition(String date, String uname, String tid, String state){
		DBHelper db = new DBHelper();
		List<String> params = new ArrayList<String>();
		String sql = "select * from orders where 1 = 1";
		if (StringUtil.isNull(date)) {
			sql += " and to_char(o_starttime, 'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd')";
		} else {
			sql += " and to_char(o_starttime, 'yyyy-mm-dd') = ?";
			params.add(date);
		}
		if (!StringUtil.isNull(uname)) {
			String sql_uid = "select u_id from rusers where u_name = uname";
			sql += " and o_uid = ?";
			params.add(uname);
		}
		if (!StringUtil.isNull(tid)) {
			sql += " and o_tid = ?";
			params.add(tid);
		}
		if (!StringUtil.isNull(state) && !"全部".equals(state)) {
			if ("未结账".equals(state)) {
				sql += " and o_state = 0";
			} else if ("已结账".equals(state)) {
				sql += " and o_state = 1";
			}
		}
		sql += " order by o_id";
		return db.finds(sql, params);
	}
	
	// 根据用户编号查订单表
	public List<Map<String, Object>> researchOrderById(int id){
		DBHelper db = new DBHelper();
		String sql = "select * from orders where o_uid = ?";
		return db.finds(sql, id);
	}
	
	// 初始化桌子状态
	public int initTable(){
		DBHelper db = new DBHelper();
		String sql = "update rtables set t_state = 2";
		return db.update(sql);
	}
	
	// 获取用户选择日期的预约表预约状态为已预约的数据
	public List<Map<String, Object>> researchReservationBydt(String date){
		DBHelper db = new DBHelper();
		String sql = "select r_tid,r_date from reservation where r_type = 0 and to_char(r_date, 'yyyy-mm-dd') = ?";
		return db.finds(sql, date);
	}
	
	// 根据桌子id设置桌子状态为已预约
	public int setRStateById(String id){
		DBHelper db = new DBHelper();
		String sql = "update rtables set t_state = 0 where t_id = ?";
		return db.update(sql, id);
	}
	
	// 根据用户选择日期和订单状态搜索数据
	public List<Map<String, Object>> researchOrderBydt(String date) {
		DBHelper db = new DBHelper();
		String sql = "select o_tid,o_starttime from orders where o_state = 0 and to_char(o_starttime, 'yyyy-mm-dd') = ?";
		return db.finds(sql, date);
	}
	
	// 根据桌子id设置桌子状态为已预约
	public int setOStateById(String id) {
		DBHelper db = new DBHelper();
		String sql = "update rtables set t_state = 1 where t_id = ?";
		return db.update(sql, id);
	}
	
	// 根据年月查销售额
	public List<Map<String, Object>> researchTurnoverByYM(String year, String month){
		DBHelper db = new DBHelper();
		List<String> params = new ArrayList<String>();
		params.add(year);
		String sql = "select c1.c_name,nvl((select sum(d.d_prices*od.ot_fcount) "
				+ "from orders o,orderdetail od,dishes d,cuisine c "
				+ "where o.o_id = od.ot_oid and o.o_state = 1 and d.d_id = od.ot_did and c.c_id = d.d_fid and "
				+ "c.c_id = c1.c_id and to_char(o.o_starttime,'yyyy') = ?";
		
		if (!StringUtil.isNull(month) && !"0".equals(month)) {
			sql += " and to_char(o.o_starttime,'mm') = ?";
			params.add(month);
		}
		sql += " group by c.c_id),0) salecount from cuisine c1 order by salecount desc";
		return db.finds(sql, params);
	}
	
}
