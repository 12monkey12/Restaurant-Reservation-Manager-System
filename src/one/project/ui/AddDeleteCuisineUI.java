package one.project.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddDeleteCuisineUI extends Composite {
	private Text text;
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddDeleteCuisineUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		group.setText("添加菜系");
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(480, 74, 98, 30);
		btnNewButton.setText("确认添加");
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBounds(54, 79, 76, 20);
		lblNewLabel.setText("菜系名称：");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(136, 76, 261, 26);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(216);
		tblclmnNewColumn.setText("菜系编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(245);
		tblclmnNewColumn_1.setText("菜系名称");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("删除");
		sashForm_1.setWeights(new int[] {618});
		sashForm.setWeights(new int[] {155, 554});
		
//		MyThread myThread = new MyThread();
//		Thread thread = new Thread(myThread);
//		thread.start();
		
		showCuisineInfo();
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  // 添加按钮点击事件，添加菜系
				String cuisineName = text.getText().trim();
				if (StringUtil.isNull(cuisineName)) {
					return;
				}
				
				ThingDao thingDao = new ThingDao();
				int index = thingDao.addCuisine(cuisineName);
				if (index > 0) {
					text.setText("");
					showCuisineInfo();
					MessageDialog.openInformation(getShell(), "成功提示", "添加菜系成功。。。");
				} else {
					MessageDialog.openError(getShell(), "失败提示", "添加菜系失败。。。");
				}
			}
		});
		
		// 右击删除时
		mntmNewItem.addSelectionListener(new SelectionAdapter() { 
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取用户点击的行
				TableItem tableItem = tableCursor.getRow();
				// 从这一行数据中取出要删除的菜系编号
				String id = tableItem.getText(0);
				String name = tableItem.getText(1);
				
				// 弹出确认框
				MessageBox msgbox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				msgbox.setMessage("确认删除 " + name + " 菜系吗？");
				int flag = msgbox.open();
				if (flag == SWT.NO) {
					return;
				} else if (flag == SWT.YES) {
					//System.out.println("yes");
					// 发送到数据库删除
					ThingDao thingDao = new ThingDao();
					int result = thingDao.deleteCuisine(id);
					if (result > 0) {
						showCuisineInfo();
					} else {
						MessageDialog.openError(getShell(), "失败提示", "删除菜系失败！");
					}
				}
			}
		});
	}
	
	public void showCuisineInfo() {
		table.removeAll(); //先移除表格之前的数据
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.showCuisine();
		// 判断list是否为空
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				// 插入一行数据
				tableItem.setText(new String[]{String.valueOf(map.get("c_id")), String.valueOf(map.get("c_name"))});
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
//	// 创建一个线程实时更新label中的时间
//	class MyThread implements Runnable{
//		public void run() {
//			while(true) {
//				
//				label_time.getDisplay().syncExec(new Runnable(){
//					public void run() {
//						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						String time = sdf.format(new Date());
//						label_time.setText(time);
//					}
//				});
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		}
//	}
}
