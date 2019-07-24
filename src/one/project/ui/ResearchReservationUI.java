package one.project.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;

import com.ibm.icu.util.Calendar;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ResearchReservationUI extends Composite {
	private Table table;
	private Text text;
	private Text text_2;
	private Text text_3;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResearchReservationUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(this, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_1, SWT.NONE);
		group.setText("查询预约");
		
		Label lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setBounds(10, 50, 67, 20);
		lblNewLabel_1.setText("预约时间：");
		
		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBounds(251, 50, 67, 20);
		lblNewLabel_2.setText("用户姓名：");
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setBounds(324, 47, 112, 26);
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(898, 45, 98, 30);
		btnNewButton.setText("确定查询");
		
		Label lblNewLabel_3 = new Label(group, SWT.NONE);
		lblNewLabel_3.setBounds(461, 50, 53, 20);
		lblNewLabel_3.setText("桌子号：");
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setBounds(533, 47, 104, 26);
		
		DateTime dateTime = new DateTime(group, SWT.BORDER);
		dateTime.setBounds(95, 47, 119, 26);
		
		Label lblNewLabel_4 = new Label(group, SWT.NONE);
		lblNewLabel_4.setBounds(646, 50, 67, 20);
		lblNewLabel_4.setText("预约状态：");
		
		Combo combo = new Combo(group, SWT.NONE);
		combo.setItems(new String[] {"未赴约", "已赴约", "违约", "全部"});
		combo.setBounds(728, 50, 92, 28);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_2, SWT.NONE);
		
		Composite composite = new Composite(sashForm_2, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("预约编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("桌子号");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("客户号");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(235);
		tblclmnNewColumn_3.setText("预约日期");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(104);
		tblclmnNewColumn_4.setText("预约状态");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("已赴约");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("违约");
		
		Composite composite_3 = new Composite(sashForm_2, SWT.NONE);
		
		text = new Text(composite_3, SWT.BORDER | SWT.READ_ONLY);
		text.setLocation(46, 87);
		text.setSize(169, 329);
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setLocation(46, 41);
		lblNewLabel.setSize(76, 20);
		lblNewLabel.setText("备注：");
		sashForm_2.setWeights(new int[] {812, 322});
		sashForm_1.setWeights(new int[] {117, 608});
		
		showReservationInfo();
		
		// 当鼠标左击指定行时，将该行数据中的备注添加到text中
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button != 1)
					return;
				// 获取用户点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取要提取数据预约行的编号
				String id = tableItem.getText(0);

				ThingDao thingDao = new ThingDao();
				Map<String, Object> map = thingDao.showReservationById(id);
				if (map != null && map.size() > 0) {
					Object obj = map.get("r_note");
					String note = null;
					if (obj == null) {
						note = "暂无";
					} else {
						note = String.valueOf(obj);
					}
					text.setText(note);
				}

			}
		});
		
		// 根据条件查询预约数据
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String date = null;
				String uname = text_2.getText().trim();
				String tid = text_3.getText().trim();
				String type = combo.getText().trim();
				
				// 格式化date
				int year = dateTime.getYear();
				int month = dateTime.getMonth();
				int day = dateTime.getDay();
				Calendar cal = Calendar.getInstance();
				cal.set(year, month, day);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.format(cal.getTime());
//				date = year + "-" + month + "-" + day;
//				System.out.println(date);
				
				ThingDao thingDao = new ThingDao();
				String uid = null;
				if (!StringUtil.isNull(uname)) {
					int result = thingDao.researchIdByName(uname);
					uid = String.valueOf(result);
					if (result > 0) {
						List<Map<String, Object>> list = thingDao.researchReservationByCondition(date, uid, tid, type);
						showData(list);
					} else {
						MessageDialog.openError(getShell(), "失败提示", "用户姓名不存在！");
						return;
					}
				} else {
					List<Map<String, Object>> list = thingDao.researchReservationByCondition(date, uid, tid, type);
					showData(list);
				}
				
//				System.out.println(result);
				
			}
		});
		
		// 修改预约状态为已赴约
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取要修改预约数据的编号
				String rid = tableItem.getText(0);
				String tid = tableItem.getText(1);
				String name = tableItem.getText(2);
				String date = tableItem.getText(3);
				String type = "已赴约";
				
				// 弹出确认框
				MessageBox msgbox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				msgbox.setMessage("确认修改  用户姓名：" + name + "\n\t桌子号：" + tid + "\n\t预约时间：" + 
						date + " \n预约状态为已赴约吗？");
				int flag = msgbox.open();
				if (flag == SWT.NO) {
					return;
				} else if (flag == SWT.YES) {
					//System.out.println("yes");
					// 发送到数据库修改
					ThingDao thingDao = new ThingDao();
					int result = thingDao.modifyReservationState(rid, type);
					if (result > 0) {
						showReservationInfo();
					} else {
						MessageDialog.openError(getShell(), "失败提示", "修改预约失败！");
					}
				}
				
			}
		});
		
		// 修改预约状态为违约
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取预约行的编号
				String rid = tableItem.getText(0);
				String tid = tableItem.getText(1);
				String name = tableItem.getText(2);
				String date = tableItem.getText(3);
				String type = "违约";
				
				// 弹出确认框
				MessageBox msgbox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				msgbox.setMessage("确认修改  用户姓名：" + name + "\n\t桌子号：" + tid + "\n\t预约时间：" + 
						date + " \n预约状态为违约吗？");
				int flag = msgbox.open();
				if (flag == SWT.NO) {
					return;
				} else if (flag == SWT.YES) {
					//System.out.println("yes");
					// 发送到数据库修改
					ThingDao thingDao = new ThingDao();
					int result = thingDao.modifyReservationState(rid, type);
					if (result > 0) {
						showReservationInfo();
					} else {
						MessageDialog.openError(getShell(), "失败提示", "修改预约失败！");
					}
				}
			}
		});
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void showReservationInfo(){
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.showReservation();
		showData(list);
	}
	
	public void showData(List<Map<String, Object>> list){
		table.removeAll(); // 先删除以前的数据
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				// 插入一行数据
				ThingDao thingDao = new ThingDao();
				Map<String, Object> map1 = thingDao.researchNameById(String.valueOf(map.get("r_uid")));
				String name = String.valueOf(map1.get("u_name"));
				Date date = (Date) map.get("r_date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String str = sdf.format(date);
				String type = String.valueOf(map.get("r_type"));
				switch(type) {
				case "0":type = "未赴约";break;
				case "1":type = "已赴约";break;
				case "2":type = "违约";break;
				}
				tableItem.setText(new String[]{String.valueOf(map.get("r_id")), String.valueOf(map.get("r_tid")), 
						name, str, type});
			}
		}
	}
}
