package one.project.ui;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.util.Calendar;

public class ResearchOrderUI extends Composite {
	private Table table;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResearchOrderUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(this, SWT.NONE);
		
		SashForm sashForm = new SashForm(sashForm_1, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite, SWT.NONE);
		group.setText("查询订单");
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBounds(25, 47, 67, 20);
		lblNewLabel.setText("订单时间：");
		
		Label lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setBounds(234, 47, 73, 20);
		lblNewLabel_1.setText("用户姓名：");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(313, 44, 73, 26);
		
		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBounds(419, 47, 54, 20);
		lblNewLabel_2.setText("桌子号：");
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setBounds(479, 44, 73, 26);
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(811, 45, 98, 30);
		btnNewButton.setText("确认查询");
		
		DateTime dateTime = new DateTime(group, SWT.BORDER);
		dateTime.setBounds(99, 47, 110, 28);
		
		Label lblNewLabel_3 = new Label(group, SWT.NONE);
		lblNewLabel_3.setBounds(576, 47, 67, 20);
		lblNewLabel_3.setText("订单状态:");
		
		Combo combo = new Combo(group, SWT.NONE);
		combo.setItems(new String[] {"未结账", "已结账", "全部"});
		combo.setBounds(649, 44, 92, 28);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("订单编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("用户姓名");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(75);
		tblclmnNewColumn_2.setText("餐桌号");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(93);
		tblclmnNewColumn_3.setText("总金额");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(218);
		tblclmnNewColumn_4.setText("下单时间");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(73);
		tblclmnNewColumn_5.setText("订单状态");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(237);
		tblclmnNewColumn_6.setText("结账时间");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_7.setWidth(74);
		tblclmnNewColumn_7.setText("就餐人数");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("已结账");
		sashForm.setWeights(new int[] {126, 483});
		sashForm_1.setWeights(new int[] {845});

		showOrderInfo();
		
		// 条件查询预约数据
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String date = null;
				String uname = text_1.getText().trim();
				String tid = text_2.getText().trim();
				String state = combo.getText().trim();
				
				// 格式化date
				int year = dateTime.getYear();
				int month = dateTime.getMonth();
				int day = dateTime.getDay();
				Calendar cal = Calendar.getInstance();
				cal.set(year, month, day);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.format(cal.getTime());
				
				ThingDao thingDao = new ThingDao();
				String uid = null;
				
				if (!StringUtil.isNull(uname)) {
					int result = thingDao.researchIdByName(uname);
					uid = String.valueOf(result);
					if (result > 0) {
						List<Map<String, Object>> list = thingDao.researchOrderByCondition(date, uid, tid, state);
						showData(list);
					} else {
						MessageDialog.openError(getShell(), "失败提示", "用户姓名不存在！");
						return;
					}
				} else {
					List<Map<String, Object>> list = thingDao.researchOrderByCondition(date, uid, tid, state);
					showData(list);
				}
			}
		});
		
		// 将指定订单状态修改为已结账状态
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取要修改订单的订单号
				String oid = tableItem.getText(0);
				String name = tableItem.getText(1);
				String tid = tableItem.getText(2);
				String starttime = tableItem.getText(4);
				
				// 弹出确认框
				MessageBox msgbox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				msgbox.setMessage("确认修改  用户姓名：" + name + "\n\t桌子号：" + tid + "\n\t下单时间：" + 
				starttime + " \n订单订单状态为已结账吗？");
				int flag = msgbox.open();
				if (flag == SWT.NO) {
					return;
				} else if (flag == SWT.YES) {
					//System.out.println("yes");
					// 发送到数据库修改
					ThingDao thingDao = new ThingDao();
					int result = thingDao.modifyOrderState(oid);
					if (result > 0) {
						showOrderInfo();
					} else {
						MessageDialog.openError(getShell(), "失败提示", "修改订单失败！");
					}
				}
			}
		});
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void showOrderInfo(){
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.showOrder();
		showData(list);
	}
	
	public void showData(List<Map<String, Object>> list){
		table.removeAll(); // 先删除表格以前的数据
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				String str_end = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				// 插入一行数据
				String state = String.valueOf(map.get("o_state"));
				switch(state){
				case "0":{
					state = "未结账";
				}break;
				case "1":{
					state = "已结账";
				}break;
				}
				ThingDao thingDao = new ThingDao();
				Map<String, Object> map1 = thingDao.researchNameById(String.valueOf(map.get("o_uid")));
				String name = String.valueOf(map1.get("u_name"));
				
				Object obj = map.get("o_endtime");
				if (obj == null) {
					str_end = "";
				} else {
					Date date_end = (Date) map.get("o_endtime");
					str_end = sdf.format(date_end);
				}
				Date date_start = (Date) map.get("o_starttime");
				String str_start = sdf.format(date_start);
				
				tableItem.setText(new String[] {
						String.valueOf(map.get("o_id")), 
						name,
						String.valueOf(map.get("o_tid")),
						String.valueOf(map.get("o_totalprice")), 
						str_start,
						state, 
						str_end, 
						String.valueOf(map.get("o_number")) });
			}
		}
	}
}
