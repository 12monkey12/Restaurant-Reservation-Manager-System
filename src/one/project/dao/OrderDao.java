package one.project.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Combo;

public class OrderDao {
	public int addOrder(String o_uid, String o_tid, String o_totalprices, Date date, String o_person ) {
		DBHelper db = new DBHelper();
		String sql = "insert into orders(o_id,o_uid,o_tid,o_totalprices,o_begintime,o_person) values (seq_orders_o_id, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi'), ?)";
		return db.update(sql, o_uid, o_tid, o_totalprices, date, o_person);
		
	}

	// 创建订单表
	public int createOrder(int o_uid, int o_tid, int o_totalPrice){
		DBHelper db = new DBHelper();
		String sql = "insert into orders(o_id,o_uid,o_tid,o_totalPrice) values(seq_orders_o_id.nextval, ?, ?, ?)";
		return db.update(sql, o_uid, o_tid, o_totalPrice);
	}
	// 创建订单表
	public int createOrder1(int o_uid, int o_tid, String o_totalPrice, String starttime, String people) {
		DBHelper db = new DBHelper();
		String sql = "insert into orders values(seq_orders_o_id.nextval, ?, ?, ?, to_date(?,'yyyy-mm-dd hh24:mi'), default, null, ?)";
		return db.update(sql, o_uid, o_tid, o_totalPrice, starttime, people);
	}
	
	// 查找订单
	public Map<String, Object> findOrder(Integer o_uid, Integer o_tid) {
		DBHelper db = new DBHelper();
		String sql = "select * from orders where o_uid = ? and o_tid = ?";
		return db.findl(sql, o_uid, o_tid);
	}
	
	// 增加订单详情表
	public int addOrderDetail(int ot_oid, int ot_did, int ot_price, int ot_fcount) {
		DBHelper db = new DBHelper();
		String sql = "insert into orderdetail values(seq_orderdetail_ot_id.nextval, ?, ?, ?, ?)";
		return db.update(sql, ot_oid, ot_did, ot_price, ot_fcount);
	}
	
	// 更新订单表
	public int updateOrder(int o_totalprices, String date, int o_number, int o_uid, int o_tid) {
		DBHelper db = new DBHelper();
		String sql = "update orders set o_totalprice = ?, o_starttime = to_date(?, 'yyyy-mm-dd hh24:mi'), o_number = ? where o_uid = ? and o_tid = ?";
		return db.update(sql, o_totalprices, date, o_number, o_uid, o_tid);
		
	}
	
	// 查找订单表和订单详情表
	public List<Map<String, Object>> findOrderTotal(int o_uid, int o_tid) {
		DBHelper db = new DBHelper();
		String sql = "select * from orderdetail ot, orders o, dishes d where ot.ot_oid = o.o_id and ot.ot_did = d.d_id and o_uid = ? and o_tid = ?";
		return db.gets(sql, o_uid, o_tid);
	}
	
	// 根据订单号查找菜品
	public List<Map<String, Object>> findOrderDishes(int o_id) {
		DBHelper db = new DBHelper();
		String sql = "select distinct d_name, ot_price,ot_fcount from orderdetail ot, orders o, dishes d where ot.ot_oid = o.o_id and ot.ot_did = d.d_id and o_id = ?";
		return db.gets(sql, o_id);
	}
	
	public void addTableCombo(Combo combo) {
		combo.removeAll(); //先移除所有数据
		DBHelper db = new DBHelper();
		String sql = "select t_id,t_type from rtables order by t_id";
		List<Map<String, Object>> list = db.finds(sql);
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				combo.add(String.valueOf(map.get("t_id")) + " " +String.valueOf(map.get("t_type")));
			}
		}
	}

}
