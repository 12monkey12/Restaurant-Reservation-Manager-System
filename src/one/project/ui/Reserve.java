package one.project.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;


import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Reserve {

	protected Shell shell;
	private Text text;
	private Label label_tid;
	private Label label_reservation;
	private Label label_used;
	private Label label_useful;
	private Label label_sum;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Reserve window = new Reserve();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1500, 800);
		shell.setText("预约座位");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		// 如何让窗口居中显示？  显示器的大小？ （显示器的宽度-界面宽度）/2
		Rectangle rectangle = Display.getDefault().getClientArea();
		shell.setLocation((rectangle.width - shell.getSize().x)/2, (rectangle.height - shell.getSize().y)/2);
				
		// 申明图片对象
		Image img_four = SWTResourceManager.getImage(Reserve.class, "/images/four.png");
		Image img_eight = SWTResourceManager.getImage(Reserve.class, "/images/eight.png");
		Image img_twelve = SWTResourceManager.getImage(Reserve.class, "/images/twelve.png");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(45, 33, 45, 20);
		lblNewLabel.setText("日期：");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setItems(new String[] {"今天", "明天", "后天", "大后天"});
		combo.setBounds(105, 33, 103, 28);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(45, 95, 45, 20);
		lblNewLabel_1.setText("时间：");
		
		Combo combo_1 = new Combo(composite, SWT.READ_ONLY);
		combo_1.setBounds(105, 92, 103, 28);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(83, 152, 98, 30);
		btnNewButton.setText("查询座位");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(30, 290, 178, 302);
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(70, 617, 98, 30);
		btnNewButton_1.setText("立即预约");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(31, 264, 45, 20);
		lblNewLabel_2.setText("备注：");
		
		Label lblNewLabel_11 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblNewLabel_11.setBounds(10, 194, 245, 20);
		
		Label lblNewLabel_13 = new Label(composite, SWT.NONE);
		lblNewLabel_13.setBounds(50, 220, 90, 20);
		lblNewLabel_13.setText("预约桌子号：");
		
		label_tid = new Label(composite, SWT.BORDER);
		label_tid.setBounds(146, 219, 35, 20);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.BORDER);
		
		Label lblNewLabel_3 = new Label(composite_2, SWT.NONE);
		lblNewLabel_3.setBounds(10, 10, 76, 20);
		lblNewLabel_3.setText("餐桌展示：");
		
		Canvas canvas_four = new Canvas(composite_2, SWT.NONE);
		canvas_four.setBounds(171, 10, 100, 100);
		
		Label lblNewLabel_4 = new Label(composite_2, SWT.NONE);
		lblNewLabel_4.setBounds(72, 63, 76, 20);
		lblNewLabel_4.setText("四人桌");
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setText("八人桌");
		label.setBounds(359, 63, 76, 20);
		
		Canvas canvas_eight = new Canvas(composite_2, SWT.NONE);
		canvas_eight.setBounds(458, 10, 100, 100);
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setText("十二人桌");
		label_1.setBounds(632, 63, 76, 20);
		
		Canvas canvas_twelve = new Canvas(composite_2, SWT.NONE);
		canvas_twelve.setBounds(731, 10, 100, 100);
		
		Label lblNewLabel_9 = new Label(composite_2, SWT.NONE);
		lblNewLabel_9.setBounds(1093, 10, 76, 20);
		lblNewLabel_9.setAlignment(SWT.CENTER);
		lblNewLabel_9.setText("主界面");
		
		// 主界面
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				Index index = new Index();
				index.open();
			}
		});
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_3, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);
		
		Composite composite_4 = new Composite(sashForm_2, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_4, SWT.NONE);
		group.setText("餐桌状态");
		
		Canvas canvas_3 = new Canvas(group, SWT.NONE);
		canvas_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		canvas_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		canvas_3.setBounds(99, 20, 30, 30);
		
		Label lblNewLabel_5 = new Label(group, SWT.NONE);
		lblNewLabel_5.setBounds(143, 30, 33, 20);
		lblNewLabel_5.setText("预约");
		
		Label lblNewLabel_6 = new Label(group, SWT.NONE);
		lblNewLabel_6.setBounds(295, 30, 33, 20);
		lblNewLabel_6.setText("占用");
		
		Canvas canvas_4 = new Canvas(group, SWT.NONE);
		canvas_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		canvas_4.setBounds(246, 20, 30, 30);
		
		Label lblNewLabel_7 = new Label(group, SWT.NONE);
		lblNewLabel_7.setBounds(623, 30, 33, 20);
		lblNewLabel_7.setText("总数：");
		
		Label lblNewLabel_8 = new Label(group, SWT.NONE);
		lblNewLabel_8.setBounds(463, 30, 33, 20);
		lblNewLabel_8.setText("空闲");
		
		Canvas canvas_5 = new Canvas(group, SWT.NONE);
		canvas_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		canvas_5.setBounds(405, 20, 30, 30);
		
		label_sum = new Label(group, SWT.NONE);
		label_sum.setAlignment(SWT.CENTER);
		label_sum.setBounds(660, 30, 44, 20);
		label_sum.setText("35");
		
		label_reservation = new Label(group, SWT.NONE);
		label_reservation.setAlignment(SWT.CENTER);
		label_reservation.setText("88");
		label_reservation.setBounds(182, 30, 33, 20);
		
		label_used = new Label(group, SWT.NONE);
		label_used.setAlignment(SWT.CENTER);
		label_used.setText("88");
		label_used.setBounds(334, 30, 33, 20);
		
		label_useful = new Label(group, SWT.NONE);
		label_useful.setAlignment(SWT.CENTER);
		label_useful.setBounds(502, 30, 33, 20);
		label_useful.setText("88");
		
		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		sashForm_2.setWeights(new int[] {60, 486});
		sashForm_1.setWeights(new int[] {124, 548});
		sashForm.setWeights(new int[] {265, 1214});
		
		setNumber();
		
		combo.addModifyListener(new ModifyListener() { // 根据日期下拉框值设置时间下拉框的值
			public void modifyText(ModifyEvent arg0) {
				String text = combo.getText();
				if ("今天".equals(text)) {
					Calendar cal = Calendar.getInstance();
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					combo_1.removeAll();  // 先删除所有项
					for (int i = hour + 1; i < 22; i++) {
						combo_1.add(String.valueOf(i));
					}
				} else {
					combo_1.removeAll();
					for (int i = 9; i < 22; i++) {
						combo_1.add(String.valueOf(i));
					}
				}
			}
		});
		
		// 缩放桌子图片
		canvas_four.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle bounds = img_four.getBounds();
				int srcWidth = bounds.width;
				int srcHeight = bounds.height;
				Point size = canvas_four.getSize();
				int destWidth = size.x;
				int destHeight = size.y;
				e.gc.drawImage(img_four, 0, 0, srcWidth, srcHeight, 0, 0, destWidth, destHeight);
			}
		});
		canvas_eight.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle bounds = img_eight.getBounds();
				int srcWidth = bounds.width;
				int srcHeight = bounds.height;
				Point size = canvas_eight.getSize();
				int destWidth = size.x;
				int destHeight = size.y;
				e.gc.drawImage(img_eight, 0, 0, srcWidth, srcHeight, 0, 0, destWidth, destHeight);
			}
		});
		canvas_twelve.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle bounds = img_twelve.getBounds();
				int srcWidth = bounds.width;
				int srcHeight = bounds.height;
				Point size = canvas_twelve.getSize();
				int destWidth = size.x;
				int destHeight = size.y;
				e.gc.drawImage(img_twelve, 0, 0, srcWidth, srcHeight, 0, 0, destWidth, destHeight);
			}
		});
		
		// 点击确定时间按钮，显示桌子的状态
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				label_tid.setText("");
				String date = combo.getText().trim();
				String time = combo_1.getText().trim();
				Date date1 = null;
				Date date2 = null;
				List<Map<String, Object>> list = null;
				if (StringUtil.isNull(date, time)) {
					MessageDialog.openError(shell, "错误提示", "日期和时间不能为空！");
					return;
				}
				
				// 写入桌子状态
				//①初始化桌子状态
				ThingDao thingDao = new ThingDao();
				thingDao.initTable();
				//if (result < 0) return;
			
				//②获取预约日期和日期时间
				date = getReservationDate(date, time);
				String datestr = date.split(" ")[0];
				System.out.println(datestr);
				System.out.println(date);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					date1 = sdf.parse(date);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				//③获取预约表数据进行比较
				list = thingDao.researchReservationBydt(datestr);
				if (list != null && !list.isEmpty()) {
					for (Map<String, Object> map : list) {
						date2 = (Date) map.get("r_date");
						if (isCross(date1, date2)) {
							thingDao.setRStateById(String.valueOf(map.get("r_tid")));
						}
					}
				}
				
				//④获取订单表数据进行比较
				list = thingDao.researchOrderBydt(datestr);
				if (list != null && !list.isEmpty()) {
					for (Map<String, Object> map : list) {
						date2 = (Date) map.get("o_starttime");
						if (isCross(date1, date2)) {
							thingDao.setOStateById(String.valueOf(map.get("o_tid")));
						}
					}
				}
				
				// 先将composite_5中的所有内容销毁掉
				Control[] controls = composite_5.getChildren();
				if (controls != null && controls.length > 0) {
					for (Control control : controls) {
						control.dispose();
					}
				}
				SeatUI seatUI = new SeatUI(composite_5, SWT.NONE, label_tid);
				composite_5.layout();
				
				setNumber();
			}
		});
		
		// 点击预约按钮，如果选了桌子号，则将预约数据存到数据库中
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tid = label_tid.getText().trim();
				String mark = text.getText().trim();
				String date = combo.getText().trim();
				String time = combo_1.getText().trim();
				if (StringUtil.isNull(tid, date, time)) {
					MessageDialog.openError(shell, "错误提示", "日期或时间或桌子号为空！");
					return;
				}

				// 先将composite_5中的所有内容销毁掉
				Control[] controls = composite_5.getChildren();
				if (controls != null && controls.length > 0) {
					for (Control control : controls) {
						control.dispose();
					}
				}
				
				// 将数据存到数据库中
				date = getReservationDate(date, time);
//				System.out.println(date);
				ThingDao thingDao = new ThingDao();
				int result = thingDao.addReservation(tid, String.valueOf(StaticObject.number), date, mark);
				if (result > 0) {
					combo.deselectAll();
					combo_1.deselectAll();
					label_tid.setText("");
					text.setText("");
					MessageDialog.openInformation(shell, "成功提示", "恭喜您预约 " + date + " 时间的 " + tid + "号桌子" 
					+ " 成功！\n\n请在预约点之后半个小时内到饭店就餐");
				} else {
					MessageDialog.openInformation(shell, "失败提示", "预约失败，请重新预约！");
				}
			}
			
		});
		
	}
	
	// 设置桌子总数，可用桌子数，预约桌子数，占用桌子数
	public void setNumber(){
		ThingDao thingDao = new ThingDao();
//		String tsum = null;
//		String tuseful = null;
//		String treservation = null;
//		String tused = null;
		String[] strs = thingDao.researchTabelNumber();
		label_reservation.setText(strs[0]);
		label_used.setText(strs[1]);
		label_useful.setText(strs[2]);
		label_sum.setText(strs[3]);
	}
	
	// 获取预约日期
	public String getReservationDate(String date, String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		Calendar cal = Calendar.getInstance();
		if ("今天".equals(date)) {
		} else if ("明天".equals(date)) {
			cal.setTime(date1);
			cal.add(Calendar.DATE, 1);
			date1 = cal.getTime();
		} else if ("后天".equals(date)) {
			cal.setTime(date1);
			cal.add(Calendar.DATE, 2);
			date1 = cal.getTime();
		} else if ("大后天".equals(date)) {
			cal.setTime(date1);
			cal.add(Calendar.DATE, 3);
			date1 = cal.getTime();
		}
		date = sdf.format(date1) + " " + time + ":00:00";
		return date;
	}
	
	// 判断两个日期是否相交
	public boolean isCross(Date date1, Date date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.HOUR_OF_DAY, 3);
		Date date1_end = cal.getTime();
		cal.setTime(date2);
		cal.add(Calendar.HOUR_OF_DAY, 3);
		Date date2_end = cal.getTime();
		
		// 不相交
		if (date1_end.before(date2) || date2_end.before(date1)) {
			return false;
		}
		return true;
	}
}
