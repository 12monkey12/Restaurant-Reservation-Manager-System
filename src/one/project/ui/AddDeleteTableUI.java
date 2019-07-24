package one.project.ui;

import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddDeleteTableUI extends Composite {
	private Table table;
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddDeleteTableUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(176);
		tblclmnNewColumn.setText("桌子号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(314);
		tblclmnNewColumn_1.setText("桌子类型");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("删除");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_1, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		group.setText("添加桌子");
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBounds(69, 185, 76, 20);
		lblNewLabel.setText("桌子类型：");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(254, 182, 161, 26);
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(154, 529, 118, 45);
		btnNewButton.setText("确认添加");
		
		// 点击添加桌子按钮，向数据库添加桌子
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String t_type = text.getText();
				ThingDao thingDao = new ThingDao();
				int result = thingDao.addTable(t_type);
				if (result > 0) {
					text.setText("");
					showTableInfo();
					MessageDialog.openInformation(getShell(), "成功提示", "添加桌子成功！");
				} else {
					MessageDialog.openError(getShell(), "失败提示", "添加桌子失败！");
				}
			}
		});
		sashForm.setWeights(new int[] {635, 457});
		
		showTableInfo();

		// 右击点击删除
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取用户点击的行
				TableItem tableItem = tableCursor.getRow();
				// 获取要删除的桌子编号
				String id = tableItem.getText(0);
				String name = tableItem.getText(1);

				// 弹出确认框
				MessageBox msgbox = new MessageBox(getShell(),
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				msgbox.setMessage("确认删除 " + name + " 吗？");
				int flag = msgbox.open();
				if (flag == SWT.NO) {
					return;
				} else if (flag == SWT.YES) {
					// System.out.println("yes");
					// 发送到数据库删除
					ThingDao thingDao = new ThingDao();
					int result = thingDao.deleteTable(id);
					if (result > 0) {
						showTableInfo();
					} else {
						MessageDialog.openError(getShell(), "失败提示", "删除桌子失败！");
					}
				}
			}
		});
	}

	public void showTableInfo(){
		table.removeAll(); // 先移除表格以前的数据
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.showTable();
		// 判断list是否为空
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				// 插入一行数据
				tableItem.setText(new String[]{String.valueOf(map.get("t_id")), 
						String.valueOf(map.get("t_type"))});
			}
		}
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
