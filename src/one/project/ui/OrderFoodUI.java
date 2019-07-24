package one.project.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import one.project.dao.DishesDao;
import one.project.dao.OrderDao;
import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class OrderFoodUI extends Composite {
	private Table table;
	private Table table_1;
	private Text text;
	private Text text_totalprices;
	private Text text_fcount;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public OrderFoodUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.NONE);

		Composite composite = new Composite(sashForm, SWT.NONE);

		Tree tree = new Tree(composite, SWT.BORDER);

		tree.setLocation(0, 0);
		tree.setSize(172, 850);
		tree.setBackgroundImage(SWTResourceManager.getImage(OrderFoodUI.class,
				"/images/register_background3.png"));

		TreeItem ti1 = new TreeItem(tree, SWT.NONE);
		ti1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		ti1.setText("湘菜");

		TreeItem ti2 = new TreeItem(tree, SWT.NONE);
		ti2.setText("粤菜");

		TreeItem ti3 = new TreeItem(tree, SWT.NONE);
		ti3.setText("川菜");

		TreeItem ti4 = new TreeItem(tree, SWT.NONE);
		ti4.setText("鲁菜");

		TreeItem ti5 = new TreeItem(tree, SWT.NONE);
		ti5.setText("苏菜");

		TreeItem ti6 = new TreeItem(tree, SWT.NONE);
		ti6.setText("闽菜");

		TreeItem ti7 = new TreeItem(tree, SWT.NONE);
		ti7.setText("浙菜");

		TreeItem ti8 = new TreeItem(tree, SWT.NONE);
		ti8.setText("徽菜");

		TreeItem ti9 = new TreeItem(tree, SWT.NONE);
		ti9.setText("酒水");

		TreeItem ti10 = new TreeItem(tree, SWT.NONE);
		ti10.setText("所有菜系");

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);

		Composite comp_2 = new Composite(sashForm_1, SWT.NONE);
		comp_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_2 = new SashForm(comp_2, SWT.NONE);

		Composite composite_3 = new Composite(sashForm_2, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_3, SWT.NONE);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(124);
		tableColumn.setText("图片");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(127);
		tableColumn_1.setText("菜品编号");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(124);
		tableColumn_2.setText("菜名");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(130);
		tableColumn_3.setText("所属菜系");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(126);
		tableColumn_4.setText("单价");

		TableCursor tableCursor = new TableCursor(table, SWT.NONE);

		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("添加");

		Composite composite_4 = new Composite(sashForm_2, SWT.NONE);

		Canvas canvas = new Canvas(composite_4, SWT.NONE);
		canvas.setBounds(32, 40, 200, 200);

		text = new Text(composite_4, SWT.BORDER);
		text.setBounds(32, 284, 200, 200);
		sashForm_2.setWeights(new int[] { 636, 266 });

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_FORCE);

		table_1 = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(0, 0, 503, 236);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("菜品编号");

		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("菜名");

		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("单价");

		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("数量");

		TableColumn tblclmnNewColumn = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("总计");

		TableCursor tableCursor_1 = new TableCursor(table_1, SWT.NONE);

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBounds(509, 23, 36, 17);
		lblNewLabel.setText("总价：");

		text_totalprices = new Text(composite_2, SWT.BORDER);
		text_totalprices.setEnabled(false);
		text_totalprices.setBounds(547, 20, 114, 23);

		Button btn_add = new Button(composite_2, SWT.NONE);

		btn_add.setBounds(509, 117, 80, 27);
		btn_add.setText("增加");

		Button btn_orderupdate = new Button(composite_2, SWT.NONE);

		btn_orderupdate.setBounds(509, 173, 80, 27);
		btn_orderupdate.setText("下单");

		Button btn_reduce = new Button(composite_2, SWT.NONE);

		btn_reduce.setBounds(607, 117, 80, 27);
		btn_reduce.setText("减少");

		Button btn_delete = new Button(composite_2, SWT.NONE);

		btn_delete.setBounds(607, 173, 80, 27);
		btn_delete.setText("删除");

		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setBounds(509, 72, 33, 17);
		lblNewLabel_2.setText("人数");

		text_fcount = new Text(composite_2, SWT.BORDER);
		text_fcount.setBounds(547, 69, 114, 23);
		sashForm_1.setWeights(new int[] { 617, 238 });
		sashForm.setWeights(new int[] { 152, 905 });

		// 树形菜单事件
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti = (TreeItem) e.item;
				String nodeName = ti.getText();
				if ("所有菜系".equals(nodeName)) {
					showDishes(null);
				} else if ("湘菜".equals(nodeName)) {
					showDishes(1);
				} else if ("粤菜".equals(nodeName)) {
					showDishes(2);
				} else if ("川菜".equals(nodeName)) {
					showDishes(3);
				} else if ("鲁菜".equals(nodeName)) {
					showDishes(4);
				} else if ("苏菜".equals(nodeName)) {
					showDishes(5);
				} else if ("闽菜".equals(nodeName)) {
					showDishes(6);
				} else if ("浙菜".equals(nodeName)) {
					showDishes(7);
				} else if ("徽菜".equals(nodeName)) {
					showDishes(8);
				} else if ("酒水".equals(nodeName)) {
					showDishes(9);
				}
			}
		});

		// 双击添加菜品进菜单
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TableItem ti;
				boolean bol = false;

				// 获取用户点击的这个行
				TableItem ti1 = tableCursor.getRow();

				if (bol == false) {
					for (int i = 0; i < table_1.getItemCount(); i++) {
						for (int k = table_1.getItemCount() - 1; k >= i; k--) {
							ti = table_1.getItem(k);
							if (ti1.getText(1) == ti.getText(0)) {

								bol = true;
							}
						}

					}
				}

				if (bol) {
					MessageDialog.openError(getShell(), "温馨提示",
							"订单中已存在改菜品，如想要再次购买该菜品，请于订单表中增加");

				} else {
					TableItem ti2 = new TableItem(table_1, SWT.NONE);
					Add(table_1, ti1, ti2);
					// 计算总价
					int b1 = 0;
					for (int k = 0; k < table_1.getItemCount(); k++) {
						ti = table_1.getItem(k);
						Integer a1 = Integer.parseInt(ti.getText(4));
						b1 += a1;
						String c = String.valueOf(b1);
						text_totalprices.setText(c);
					}
					bol = false;
				}

			}

			// 单击菜品显示菜品描述和图片
			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button != 1)
					return;
				// 获取用户点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取要提取数据菜品行的编号
				String id = tableItem.getText(1);

				ThingDao thingDao = new ThingDao();
				Map<String, Object> map = thingDao.showFoodById(id);
				if (map != null && map.size() > 0) {
					text.setText(String.valueOf(map.get("d_remark")));
					Object obj = map.get("d_photo");
					// System.out.println("1" + obj.toString());
					if (obj != null) {
						// System.out.println(obj.toString());
						byte[] bt = (byte[]) obj;
						// 将字节数据变成字节流
						InputStream is = new ByteArrayInputStream(bt);
						ImageData imageData = new ImageData(is);
						imageData = imageData.scaledTo(canvas.getSize().x,
								canvas.getSize().y);
						Image image = new Image(Display.getDefault(), imageData);
						canvas.setBackgroundImage(image);
					} else {
						canvas.setBackgroundImage(null);
					}
				}
			}
		});

		// 添加菜品数量
		btn_add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				TableItem ti = tableCursor_1.getRow();
				if (ti == null) {
					MessageDialog.openError(getShell(), "错误提示", "请先点击要增加的菜品行！");
					return;
				}
				Integer a = Integer.parseInt(ti.getText(2));
				Integer b = Integer.parseInt(ti.getText(3));
				b++;
				ti.setText(new String[] { String.valueOf(ti.getText(0)),
						String.valueOf(ti.getText(1)),
						String.valueOf(ti.getText(2)), String.valueOf(b),
						String.valueOf(a * b) });

				int b1 = 0;
				for (int k = 0; k < table_1.getItemCount(); k++) {
					ti = table_1.getItem(k);
					Integer a1 = Integer.parseInt(ti.getText(4));

					b1 += a1;
					String c = String.valueOf(b1);
					text_totalprices.setText(c);
				}

			}
		});

		// 减少菜品数量
		btn_reduce.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti = tableCursor_1.getRow();

				Integer a = Integer.parseInt(ti.getText(2));
				Integer b = Integer.parseInt(ti.getText(3));
				if (b == 1) {
					MessageDialog.openError(getShell(), "温馨提示", "菜品数量已为1，无法减少。。。");
				} else {
					b--;
					ti.setText(new String[] { String.valueOf(ti.getText(0)),
							String.valueOf(ti.getText(1)),
							String.valueOf(ti.getText(2)), String.valueOf(b),
							String.valueOf(a * b) });
				}
				

				int b1 = 0;
				for (int k = 0; k < table_1.getItemCount(); k++) {
					ti = table_1.getItem(k);
					Integer a1 = Integer.parseInt(ti.getText(4));
					b1 += a1;
					String c = String.valueOf(b1);
					text_totalprices.setText(c);
				}
			}
		});

		// 删除菜品
		btn_delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti;
				int index = table_1.getSelectionIndex();
				table_1.remove(index);
				int b1 = 0;
				for (int k = 0; k < table_1.getItemCount(); k++) {
					ti = table_1.getItem(k);
					Integer a1 = Integer.parseInt(ti.getText(4));

					b1 += a1;
					String c = String.valueOf(b1);
					text_totalprices.setText(c);
				}
			}
		});

		// 点击下单按钮，更新订单表，并创建订单详情表
		btn_orderupdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = new Date();
				String str = sdf.format(date);

				String text1 = text_totalprices.getText().trim();
				String text2 = text_fcount.getText().trim();

//				// 更新订单
//				OrderDao orderDao = new OrderDao();
//				if (StringUtil.isNull(text2)) {
//					MessageDialog.openError(getShell(), "温馨提示", "人数不能为空。。。");
//				} else {
//					int o_totalPrice = Integer.parseInt(text1);
//					int o_person = Integer.parseInt(text2);
//					o_totalPrice += o_person * 8;
//					System.out.println(o_totalPrice);
//					orderDao.updateOrder(o_totalPrice, str, o_person,
//							StaticObject.number, StaticObject.tnumber);
//					MessageDialog.openInformation(getShell(), "成功提示",
//							"下单成功，请稍候片刻，马上为您上菜");
//				}
				
				// 创建订单
				if (StringUtil.isNull(text2)) {
					MessageDialog.openError(getShell(), "错误提示", "人数不能为空。。。");
					return;
				}
				if (table_1.getItemCount() == 0) {
					MessageDialog.openError(getShell(), "错误提示", "订单不能为空。。。");
					return;
				}
				OrderDao orderDao = new OrderDao();
				int result = orderDao.createOrder1(StaticObject.number, StaticObject.tnumber, text1, str, text2);
				if (result > 0) {
					MessageDialog.openInformation(getShell(), "成功提示", "下单成功，请稍候片刻，马上为您上菜");
				} else {
					MessageDialog.openError(getShell(), "错误提示", "下单失败，请重新下单！");
					return;
				}
				Map<String, Object> map = orderDao.findOrder(StaticObject.number, StaticObject.tnumber);
				StaticObject.onumber = Integer.parseInt(map.get("o_id").toString());
				
				// 创建订单详情表
				TableItem ti = null;
				for (int i = 0; i < table_1.getItemCount(); i++) {
					ti = table_1.getItem(i);
					int ot_did = Integer.parseInt(ti.getText(0));
					int ot_price = Integer.parseInt(ti.getText(2));
					int ot_fcount = Integer.parseInt(ti.getText(3));
					orderDao.addOrderDetail(StaticObject.onumber, ot_did,
							ot_price, ot_fcount);
				}

			}
		});

	}

	public int tableItemCount(Table table) {
		return table.getItemCount();

	}

	private void deleteRepeat(Table table, int lenth, TableItem ti) {
		int[] a = new int[lenth];
		for (int i = 0; i < lenth; i++) {
			ti = table.getItem(i);
			Integer b = Integer.parseInt(ti.getText(0));
			a[i] = b;
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			Integer key, value;
			for (int k = 0; k < a.length; k++) {
				if (map.get(a[k]) != null) {
					map.put(a[k], map.get(a[k]) + 1);
				} else {
					map.put(a[k], 1);
				}

			}
			// 得到map中所有的键
			Set<Integer> keyset = map.keySet();
			// 创建set集合的迭代器
			Iterator<Integer> it = keyset.iterator();

			while (it.hasNext()) {
				// int[] arrKey, arrValue;
				key = it.next();
				value = map.get(key);
				int count = value;
				// if (count >= 2) {
				// Integer b1 = Integer.parseInt(ti.getText(2));
				// ti.setText(new String[] { String.valueOf(ti.getText(0)),
				// String.valueOf(ti.getText(1)),
				// String.valueOf(ti.getText(2)),
				// String.valueOf(value), String.valueOf(value * b1) });
				// }

				System.out.println(key + "共有" + value + "次    ");
				// if (value == 2) {
				// table.remove(lenth-2);
				// }
			}
		}
	}

	private void Add(Table table, TableItem ti1, TableItem ti2) {
		int k = 1;
		Integer a1 = Integer.parseInt(ti1.getText(4));
		ti2.setText(new String[] { String.valueOf(ti1.getText(1)),
				String.valueOf(ti1.getText(2)), String.valueOf(ti1.getText(4)),
				String.valueOf(k), String.valueOf(k * a1) });

	}

	private void showDishes(Integer c_id) {
		DishesDao dishesDao = new DishesDao();
		List<Map<String, Object>> list = dishesDao.finds(c_id);
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
				ti.setText(new String[] { "", String.valueOf(map.get("d_id")),
						String.valueOf(map.get("d_name")),
						String.valueOf(map.get("c_name")),
						String.valueOf(map.get("d_prices")) });
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

	// private void showDishes1(Integer d_id, int i) {
	// DishesDao dishesDao = new DishesDao();
	// Map<String, Object> list = dishesDao.addDishes(d_id);
	// showData1(list, i);
	//
	// }
	//
	// // table_1 顶单界面
	// public void showData1(Map<String, Object> map, int i) {
	//
	// if (map != null && !map.isEmpty()) {
	// TableItem ti = null;
	//
	// // 每循环一次就是一行数据
	// // 每一行数据就是一个TableItem对象
	// ti = new TableItem(table_1, SWT.NONE);
	//
	// Object ob = map.get("d_prices");
	// Integer a = Integer.parseInt(ob.toString());
	//
	// ti.setText(new String[] { String.valueOf(map.get("d_id")),
	// String.valueOf(map.get("d_name")),
	// String.valueOf(map.get("d_prices")), String.valueOf(i),
	// String.valueOf(i * a) });
	//
	// }
	// }

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
