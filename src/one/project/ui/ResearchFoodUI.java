package one.project.ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.wb.swt.SWTResourceManager;

public class ResearchFoodUI extends Composite {
	private Text text;
	private Text text_1;
	private Text text_7;
	private Table table;
	private Combo combo;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResearchFoodUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBounds(20, 32, 76, 20);
		lblNewLabel.setText("菜品名称：");
		
		text = new Text(composite_2, SWT.BORDER);
		text.setBounds(103, 29, 201, 26);
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setBounds(356, 35, 45, 20);
		lblNewLabel_1.setText("价格：");
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setBounds(407, 32, 73, 26);
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setBounds(20, 97, 76, 20);
		lblNewLabel_2.setText("菜系编号：");
		
		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.setBounds(542, 74, 111, 43);
		btnNewButton.setText("确认查询");
		
		combo = new Combo(composite_2, SWT.READ_ONLY);
		combo.setBounds(103, 94, 92, 28);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(99);
		tblclmnNewColumn.setText("菜品编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(250);
		tblclmnNewColumn_1.setText("菜品名称");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(170);
		tblclmnNewColumn_2.setText("所属菜系名称");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("价格");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("删除");
		sashForm_1.setWeights(new int[] {173, 528});
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		text_7 = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_7.setBounds(100, 50, 200, 200);
		
		Canvas canvas = new Canvas(composite, SWT.BORDER);
		canvas.setBounds(100, 350, 200, 200);
		
		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setBounds(10, 55, 76, 20);
		lblNewLabel_5.setText("描述：");
		
		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setBounds(10, 352, 76, 20);
		lblNewLabel_6.setText("图片：");
		sashForm.setWeights(new int[] {728, 341});
		
		showFoodInfo();

		btnNewButton.addSelectionListener(new SelectionAdapter() { // 确定查询按钮，查询菜品信息
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = text.getText().trim();
				String price = text_1.getText().trim();
				String fid = combo.getText().trim().split(" ")[0];
				
				ThingDao thingDao = new ThingDao();
				List<Map<String, Object>> list = thingDao.findFoodByCondition(name, fid, price);
				showData(list);
			}
		});
		
		// 右击删除时
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取用户点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取要删除菜品行的编号
				String id = tableItem.getText(0);
				String name = tableItem.getText(1);
				
				// 弹出确认框
				MessageBox msgbox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				msgbox.setMessage("确认删除 " + name + " 菜品吗？");
				int flag = msgbox.open();
				if (flag == SWT.NO) {
					return;
				} else if (flag == SWT.YES) {
					//System.out.println("yes");
					// 发送到数据库删除
					ThingDao thingDao = new ThingDao();
					int result = thingDao.deleteFood(id);
					if (result > 0) {
						showFoodInfo();
					} else {
						MessageDialog.openError(getShell(), "失败提示", "删除菜系失败！");
					}
				}
			}
		});
		
		// 右击鼠标显示当前行菜品的描述和图片
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button != 1) return;
				// 获取用户点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取要提取数据菜品行的编号
				String id = tableItem.getText(0);
				
				ThingDao thingDao = new ThingDao();
				Map<String, Object> map = thingDao.showFoodById(id);
				if (map != null && map.size() > 0) {
					text_7.setText(String.valueOf(map.get("d_remark")));
					Object obj = map.get("d_photo");
//					System.out.println("1" + obj.toString());
					if (obj != null) {
//						System.out.println(obj.toString());
						byte[] bt = (byte[]) obj;
						// 将字节数据变成字节流
						InputStream is = new ByteArrayInputStream(bt);
						ImageData imageData = new ImageData(is);
						imageData = imageData.scaledTo(canvas.getSize().x, canvas.getSize().y);
						Image image = new Image(Display.getDefault(), imageData);
						canvas.setBackgroundImage(image);
					} else {
						canvas.setBackgroundImage(null);
					}
					
				}
				
			}
		});
		
		// 修改图片
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// 获取点击的行
				TableItem tableItem = tableCursor.getRow();
				if (tableItem == null)return;
				// 获取点击菜品行的编号
				String id = tableItem.getText(0);
				
				// 弹出文件选择框
				FileDialog fd = new FileDialog(getShell());
				fd.setText("图片选择");
				fd.setFilterExtensions(new String[]{"*.gif;*.png;*.jpg", "*.gif", "*.png", "*.jpg"});
				String path = fd.open();
//				System.out.println(path);
				
				File file = null;
				FileInputStream fis = null;
				byte[] bt = null;
				if (!StringUtil.isNull(path)) { // 如果用户确实选择了图片
					file = new File(path);
					if (file.exists() && file.isFile()) {
						try {
							fis = new FileInputStream(file);
							bt = new byte[fis.available()];
							fis.read(bt);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							if (fis != null) {
								try {
									fis.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						
						// 更新数据库
						ThingDao thingDao = new ThingDao();
						int result = thingDao.modifyFoodImage(bt, id);
						if (result > 0) {
							MessageDialog.openInformation(getShell(), "成功提示", "修改菜品图片成功！");
							return;
						} else {
							MessageDialog.openError(getShell(), "失败提示", "修改菜品图片失败！");
							return;
						}
					}
					MessageDialog.openError(getShell(), "错误提示", "请选择正确的图片路径！");
				}
			}
		});
	}
	
	public void showFoodInfo() {
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.showFood();
		showData(list);
		
		// 将菜系编号填入combo中
		thingDao.addCombo(combo);
	}
	
	public void showData(List<Map<String, Object>> list) {
		table.removeAll(); // 先移除以前的数据
		// 判断list是否为空
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				// 插入一行数据
				tableItem.setText(new String[] {
						String.valueOf(map.get("d_id")),
						String.valueOf(map.get("d_name")),
						String.valueOf(map.get("c_name")),
						String.valueOf(map.get("d_prices")) });
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
