package one.project.ui;

import java.util.List;
import java.util.Map;

import one.project.dao.UserAdminDao;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.wb.swt.SWTResourceManager;

public class ResearchUserUI extends Composite {
	private Text text_name;
	private Text text_telephone;
	private Table table;
	private Text text_account;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResearchUserUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(284, 13, 45, 20);
		label.setText("姓名：");
		
		text_name = new Text(composite, SWT.BORDER);
		text_name.setBounds(335, 10, 115, 26);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(25, 62, 45, 20);
		label_1.setText("性别：");
		
		text_telephone = new Text(composite, SWT.BORDER);
		text_telephone.setBounds(373, 59, 224, 26);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(695, 35, 115, 40);
		btnNewButton.setText("查询");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(25, 10, 45, 20);
		lblNewLabel.setText("账号：");
		
		text_account = new Text(composite, SWT.BORDER);
		text_account.setBounds(87, 7, 174, 26);
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setItems(new String[] {"男", "女"});
		combo.setBounds(87, 62, 92, 28);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(306, 62, 45, 20);
		lblNewLabel_1.setText("电话：");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(78);
		tblclmnNewColumn.setText("编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setText("账户");
		tblclmnNewColumn_1.setWidth(211);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(187);
		tblclmnNewColumn_2.setText("密码");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("姓名");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(75);
		tblclmnNewColumn_4.setText("性别");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(157);
		tblclmnNewColumn_5.setText("电话");
		sashForm.setWeights(new int[] {111, 496});
		
		showUserInfo();
		
		btnNewButton.addSelectionListener(new SelectionAdapter() { // 查询按钮点击事件
			@Override
			public void widgetSelected(SelectionEvent e) {
				String account = text_account.getText().trim();
				String name = text_name.getText().trim();
				String telephone = text_telephone.getText().trim();
				String sex = combo.getText();
				
				UserAdminDao userAdminDao = new UserAdminDao();
				List<Map<String, Object>> list = userAdminDao.findByCondition(account, name, sex, telephone);
				showData(list);
			}
		});

	}
	
	public void showUserInfo() {
		UserAdminDao userAdminDao = new UserAdminDao();
		List<Map<String, Object>> list = userAdminDao.findAll();
		showData(list);
	}
	
	public void showData(List<Map<String, Object>> list) {
		table.removeAll();  // 先移除表格以前的数据
		// 判断list是否为空
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				// 插入一行数据
				tableItem.setText(new String[] {
						String.valueOf(map.get("u_id")),
						String.valueOf(map.get("u_account")),
						String.valueOf(map.get("u_password")),
						String.valueOf(map.get("u_name")),
						String.valueOf(map.get("u_sex")),
						String.valueOf(map.get("u_tel")) });
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
