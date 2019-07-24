package one.project.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;




import one.project.dao.OrderDao;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class UserOrderUI extends Composite {
	private Table table;
	private Text text;
	private int o_uid = StaticObject.number;
	private Text text_1;
	private Text text_2;
	

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public UserOrderUI(Composite parent, int style) {
		
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(208);
		tblclmnNewColumn.setText("菜名");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(224);
		tblclmnNewColumn_1.setText("单价");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(212);
		tblclmnNewColumn_2.setText("数量");

		Composite composite_1 = new Composite(sashForm, SWT.NONE);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		lblNewLabel.setBounds(240, 100, 42, 24);
		lblNewLabel.setText("总计");

		text = new Text(composite_1, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(288, 100, 94, 24);

		showOrderDishes(StaticObject.onumber);
		
		OrderDao orderDao = new OrderDao();
		Map<String, Object> map = orderDao.findOrder(StaticObject.number, StaticObject.tnumber);
		text.setText(String.valueOf(map.get("o_totalprice")));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		lblNewLabel_1.setBounds(240, 10, 42, 24);
		lblNewLabel_1.setText("人数");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(288, 10, 94, 24);
		
		text_1.setText(String.valueOf(map.get("o_number")));
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		lblNewLabel_2.setBounds(240, 55, 42, 24);
		lblNewLabel_2.setText("茶位");
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setEnabled(false);
		text_2.setText("1人/8￥");
		text_2.setBounds(288, 55, 94, 24);
		
		Button button = new Button(composite_1, SWT.NONE);
		
		button.setBounds(263, 147, 80, 27);
		button.setText("刷新");
		sashForm.setWeights(new int[] {401, 183});
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.clearAll();
				showOrderDishes(StaticObject.onumber);
				Map<String, Object> map = orderDao.findOrder(StaticObject.number, StaticObject.tnumber);
				text.setText(String.valueOf(map.get("o_totalprice")));
				text_1.setText(String.valueOf(map.get("o_number")));
				
				
			}
		});
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
