package one.project.ui;

import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;
import one.project.dao.UserAdminDao;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PersonUI extends Composite {

	private Label label_name;
	private Label label_sex;
	private Label label_tele;
	private Table table;
	private Table table_1;
	public static int onumberdetail = -1; // 订单编号
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PersonUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		// 声明图片变量
		Image image_user = SWTResourceManager.getImage(PersonUI.class, "/images/user.jpg");
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		
		Canvas canvas = new Canvas(composite, SWT.NONE);
		canvas.setBackgroundImage(image_user);
		canvas.setBounds(10, 10, 78, 88);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(107, 10, 45, 20);
		lblNewLabel.setText("姓名：");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblNewLabel_1.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				lblNewLabel_1.setBackground(null);
			}
		});
		lblNewLabel_1.setBounds(867, 10, 78, 20);
		lblNewLabel_1.setText("修 改 密 码");
		
		label_name = new Label(composite, SWT.NONE);
		label_name.setBounds(188, 10, 76, 20);
		label_name.setText("New Label");
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(107, 49, 45, 20);
		lblNewLabel_3.setText("性别：");
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBounds(105, 78, 68, 20);
		lblNewLabel_4.setText("电话号码：");
		
		label_sex = new Label(composite, SWT.NONE);
		label_sex.setBounds(188, 49, 76, 20);
		label_sex.setText("New Label");
		
		label_tele = new Label(composite, SWT.NONE);
		label_tele.setBounds(188, 78, 178, 20);
		label_tele.setText("New Label");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_2, SWT.NONE);
		group.setText("我的订单");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("订单编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(91);
		tblclmnNewColumn_1.setText("餐桌号");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(81);
		tblclmnNewColumn_2.setText("总金额");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(208);
		tblclmnNewColumn_3.setText("下单时间");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(117);
		tblclmnNewColumn_4.setText("订单状态");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(113);
		tblclmnNewColumn_6.setText("就餐人数");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		
		mntmNewItem.setText("查看详情");
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group_1 = new Group(composite_3, SWT.NONE);
		group_1.setText("我的预约");
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("预约编号");
		
		TableColumn tblclmnNewColumn_8 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("桌子号");
		
		TableColumn tblclmnNewColumn_9 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_9.setWidth(257);
		tblclmnNewColumn_9.setText("预约日期");
		
		TableColumn tblclmnNewColumn_10 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_10.setWidth(100);
		tblclmnNewColumn_10.setText("预约状态");
		sashForm_1.setWeights(new int[] {1, 1});
		sashForm.setWeights(new int[] {108, 513});

		showUserInfo();
		showOrderReservation();
		
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle bounds = image_user.getBounds();
				int srcWidth = bounds.width;
				int srcHeight = bounds.height;
				Point size = canvas.getSize();
				int destWidth = size.x;
				int destHeight = size.y;
				e.gc.drawImage(image_user, 0, 0, srcWidth, srcHeight, 0, 0, destWidth, destHeight);
			}
		});
		
		// 修改密码
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				ModifyPassWord1 mpwd = new ModifyPassWord1(getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				mpwd.open();
			}
		});
		
		// 查看订单详情
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti = tableCursor.getRow();
				String s = ti.getText(0);
				int o_id = Integer.parseInt(s);
				onumberdetail = o_id;
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.open();
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void showUserInfo(){
		UserAdminDao userAdminDao = new UserAdminDao();
		Map<String, Object> map = userAdminDao.findUserById(StaticObject.number);
		if (map != null && !map.isEmpty()) {
			label_name.setText(String.valueOf(map.get("u_name")));
			label_sex.setText(String.valueOf(map.get("u_sex")));
			label_tele.setText(String.valueOf(map.get("u_tel")));
		}
	}

	public void showOrderReservation(){
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> rlist = thingDao.researchReservationById(StaticObject.number);
		List<Map<String, Object>> olist = thingDao.researchOrderById(StaticObject.number);
		
		table.removeAll(); // 先删除表格以前的数据
		if (olist != null && !olist.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : olist) {
				tableItem = new TableItem(table, SWT.NONE);
				// 插入一行数据
				String state = String.valueOf(map.get("o_state"));
				switch(state){
				case "0":state = "未结账";break;
				case "1":state = "已结账";break;
				}
				
				tableItem.setText(new String[]{String.valueOf(map.get("o_id")), 
						String.valueOf(map.get("o_tid")), String.valueOf(map.get("o_totalprice")), 
						String.valueOf(map.get("o_starttime")), state, 
						String.valueOf(map.get("o_number"))});
			}
		}
		
		table_1.removeAll(); // 先删除以前的数据
		if (rlist != null && !rlist.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : rlist) {
				tableItem = new TableItem(table_1, SWT.NONE);
				// 插入一行数据
				String type = String.valueOf(map.get("r_type"));
				switch(type) {
				case "0":type = "未赴约";break;
				case "1":type = "已赴约";break;
				case "2":type = "违约";break;
				}
				tableItem.setText(new String[]{String.valueOf(map.get("r_id")), String.valueOf(map.get("r_tid")), 
						String.valueOf(map.get("r_date")), type});
			}
		}
	}
}
