package one.project.ui;

import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;

public class TodaySpecialFoodUI extends Composite {
	private Table table;
	private Table table_1;
	private DateTime dateTime;
	private Combo combo_1;
	private Label label_sum;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TodaySpecialFoodUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		// 添加菜系编号到combo中
		ThingDao thingDao = new ThingDao();
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		
		SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_4, SWT.NONE);
		lblNewLabel.setBounds(40, 30, 76, 20);
		lblNewLabel.setText("菜系编号：");
		
		Combo combo = new Combo(composite_4, SWT.NONE);
		combo.setBounds(140, 27, 92, 28);
		
		Button btnNewButton = new Button(composite_4, SWT.NONE);
		btnNewButton.setBounds(266, 25, 98, 30);
		btnNewButton.setText("确定");
		
		Composite composite_5 = new Composite(sashForm_1, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_5, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(75);
		tblclmnNewColumn.setText("菜品编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(128);
		tblclmnNewColumn_1.setText("菜品名称");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(109);
		tblclmnNewColumn_2.setText("所属菜系名称");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(91);
		tblclmnNewColumn_3.setText("出售数量");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("菜品单价");
		
		thingDao.addCombo2(combo);
		sashForm_1.setWeights(new int[] {93, 596});
		
		Group group = new Group(sashForm, SWT.NONE);
		group.setText("营业额查询");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(group, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm_3, SWT.NONE);
		
		dateTime = new DateTime(composite, SWT.BORDER | SWT.SHORT);
		dateTime.setBounds(29, 52, 81, 32);
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(324, 34, 98, 30);
		btnNewButton_1.setText("确定");
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(29, 10, 76, 20);
		lblNewLabel_3.setText("年份：");
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBounds(145, 10, 76, 20);
		lblNewLabel_4.setText("月份：");
		
		combo_1 = new Combo(composite, SWT.READ_ONLY);
		combo_1.setItems(new String[] {"0", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		combo_1.setBounds(145, 52, 92, 28);
		
		Composite composite_1 = new Composite(sashForm_3, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_4.setWidth(182);
		tblclmnNewColumn_4.setText("菜系名称");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_5.setWidth(172);
		tblclmnNewColumn_5.setText("销售额");
		
		Composite composite_2 = new Composite(sashForm_3, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setBounds(254, 31, 76, 20);
		lblNewLabel_1.setText("总营业额：");
		
		label_sum = new Label(composite_2, SWT.NONE);
		label_sum.setBounds(355, 31, 115, 20);
		sashForm_3.setWeights(new int[] {111, 461, 91});
		sashForm.setWeights(new int[] {612, 562});
		
		showFoodSaleCountInfo();
		showTurnover();
		
		// 根据菜系编号查菜品
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String fid = combo.getText().trim().split(" ")[0];
				
				List<Map<String, Object>> list = thingDao.showFoodSaleCountByFid(fid);
				showData(list);
			}
		});
		
		// 查询营业额
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showTurnover();
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
	
	// 展示菜品购买数量排序
	public void showFoodSaleCountInfo(){
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.showFoodSaleCount();
		showData(list);
	}
	
	public void showData(List<Map<String, Object>> list){
		table.removeAll(); // 先移除以前的数据
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				// 插入一行数据
				String salecount = String.valueOf(map.get("salecount"));
				
				tableItem.setText(new String[]{
						String.valueOf(map.get("d_id")),
						String.valueOf(map.get("d_name")),
						String.valueOf(map.get("c_name")),
						salecount,
						String.valueOf(map.get("d_prices"))});
			}
		}
	}
	
	// 查询营业额
	public void showTurnover(){
		String year = String.valueOf(dateTime.getYear());
		String month = combo_1.getText();
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.researchTurnoverByYM(year, month);
	    showData1(list);
	    
	    int sum = 0;
		TableItem[] tableItems = table_1.getItems();
		for (TableItem tableItem : tableItems) {
			sum += Integer.parseInt(tableItem.getText(1));
		}
		label_sum.setText(String.valueOf(sum));
	}
	
	public void showData1(List<Map<String, Object>> list){
		table_1.removeAll(); // 先移除以前的数据
		if (list != null && !list.isEmpty()) {
			TableItem tableItem = null;
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table_1, SWT.NONE);
				// 插入一行数据
				tableItem.setText(new String[]{
						String.valueOf(map.get("c_name")),
						String.valueOf(map.get("salecount")),
						});
			}
		}
	}
}
