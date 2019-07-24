package one.project.ui;

import java.util.Map;

import one.project.dao.OrderDao;
import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class OrderTable {

	protected Shell shell;
	private Combo combo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OrderTable window = new OrderTable();
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
		shell.setImage(SWTResourceManager.getImage(OrderTable.class, "/images/logohead.png"));
		shell.setBackgroundImage(SWTResourceManager.getImage(OrderTable.class, "/images/ordertable_background.png"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setSize(600, 328);
		shell.setText("开位界面");
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(200, 83, 160, 30);
		
		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label.setBounds(151, 82, 43, 26);
		label.setText("桌号：");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
		btnNewButton.setBounds(230, 157, 100, 30);
		btnNewButton.setText("确认");

		center(shell);
		
		showTable();
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = combo.getText().trim();
				if (StringUtil.isNull(s)) {
					MessageDialog.openError(shell, "温馨提示", "桌子号不能为空。。。");
				} else {
					String t_id = s.substring(0, 2).trim();
					StaticObject.tnumber = Integer.parseInt(t_id);
					System.out.println(StaticObject.tnumber);
					
//					// 创建订单
//					OrderDao orderDao = new OrderDao();
//					orderDao.createOrder(StaticObject.number, StaticObject.tnumber,
//							0);
//
//					// 取订单号
//					Map<String, Object> map = orderDao.findOrder(
//							StaticObject.number, StaticObject.tnumber);
//					StaticObject.onumber = Integer.parseInt(map.get("o_id")
//							.toString());
//					System.out.println(StaticObject.onumber);
					
					UserOrder userOrder = new UserOrder();
					shell.dispose();
					userOrder.open();
				}
				
			}
		});
	}
	// 窗口居中
	private static void center(Shell shell) {
		Monitor monitor = shell.getMonitor();
		Rectangle bounds = monitor.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}
	
	// 显示桌号
	public void showTable(){
		OrderDao orderDao = new OrderDao();
		orderDao.addTableCombo(combo);
	}
}
