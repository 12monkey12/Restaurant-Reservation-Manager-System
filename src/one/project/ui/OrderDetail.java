package one.project.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import one.project.dao.OrderDao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class OrderDetail {

	protected Shell shell;
	private Table table;
	

	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			OrderDetail window = new OrderDetail();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}s

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
		shell.setImage(SWTResourceManager.getImage(OrderDetail.class, "/images/logohead.png"));
		shell.setSize(416, 494);
		shell.setText("订单详情");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		sashForm.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(133);
		tblclmnNewColumn.setText("菜名");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(137);
		tblclmnNewColumn_1.setText("单价");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(130);
		tblclmnNewColumn_2.setText("数量");
		sashForm.setWeights(new int[] {285});

		showOrderDishes(PersonUI.onumberdetail);
		
	}
	private void showOrderDishes(Integer o_id) {
		OrderDao orderDao = new OrderDao();
		List<Map<String, Object>> list = orderDao.findOrderDishes(o_id);
		showData(list);
	}

	// table 点餐界面
	public void showData(List<Map<String, Object>> list) {
		table.removeAll(); // 先移除表格中以后的数据
		if (list != null && !list.isEmpty()) {
			TableItem ti = null;
			Object obj = null;
			Image image = null;
			ImageData imageData = null;
			InputStream is = null;
			byte[] bt = null;
			for (Map<String, Object> map : list) { // 每循环一次就是一行数据
				// 每一行数据就是一个TableItem对象
				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { String.valueOf(map.get("d_name")),
						String.valueOf(map.get("ot_price")),
						String.valueOf(map.get("ot_fcount")) });
				obj = map.get("d_photo");
				if (obj != null) {
					try {
						bt = (byte[]) obj;
						// 如何将字节数据变成字节流
						is = new ByteArrayInputStream(bt);
						imageData = new ImageData(is);
						imageData = imageData.scaledTo(50, 50);
						image = new Image(Display.getDefault(), imageData);
						ti.setImage(image);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (is != null) {
							try {
								is.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
