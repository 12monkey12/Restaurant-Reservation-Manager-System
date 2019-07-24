package one.project.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class Admin {

	protected Shell shell;
	private Label label_time;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Admin window = new Admin();
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
		shell.setSize(1292, 758);
		shell.setText("信息管理--" + StaticObject.name);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		// 声明图片变量
		Image image_admin = SWTResourceManager.getImage(Admin.class, "/images/admin.jpg");
		
		// 如何让窗口居中显示？  显示器的大小？ （显示器的宽度-界面宽度）/2
		Rectangle rectangle = Display.getDefault().getClientArea();
		shell.setLocation((rectangle.width - shell.getSize().x)/2, (rectangle.height - shell.getSize().y)/2);
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(image_admin);
		composite.setLayout(null);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(990, 23, 75, 20);
		lblNewLabel.setText("系统时间：");
		
		label_time = new Label(composite, SWT.NONE);
		label_time.setBounds(1071, 23, 170, 26);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(composite_3, SWT.BORDER);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setText("用户管理");
		
		TreeItem treeItem_6 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		treeItem_6.setText("查询用户");
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText("菜系管理");
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(treeItem, SWT.NONE);
		trtmNewTreeitem_2.setText("增删菜系");
		treeItem.setExpanded(true);
		
		TreeItem treeItem_1 = new TreeItem(tree, SWT.NONE);
		treeItem_1.setText("菜品管理");
		
		TreeItem treeItem_7 = new TreeItem(treeItem_1, SWT.NONE);
		treeItem_7.setText("查删菜品");
		
		TreeItem treeItem_8 = new TreeItem(treeItem_1, SWT.NONE);
		treeItem_8.setText("增加菜品");
		
		TreeItem trtmNewTreeitem_4 = new TreeItem(treeItem_1, SWT.NONE);
		trtmNewTreeitem_4.setText("菜品销售数量");
		treeItem_1.setExpanded(true);
		
		TreeItem treeItem_2 = new TreeItem(tree, SWT.NONE);
		treeItem_2.setText("订单管理");
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(treeItem_2, SWT.NONE);
		trtmNewTreeitem_1.setText("查询订单");
		treeItem_2.setExpanded(true);
		
		TreeItem treeItem_3 = new TreeItem(tree, SWT.NONE);
		treeItem_3.setText("预约管理");
		
		TreeItem trtmNewTreeitem_3 = new TreeItem(treeItem_3, SWT.NONE);
		trtmNewTreeitem_3.setText("查询预约");
		treeItem_3.setExpanded(true);
		
		TreeItem treeItem_4 = new TreeItem(tree, SWT.NONE);
		treeItem_4.setText("餐桌管理");
		
		TreeItem treeItem_9 = new TreeItem(treeItem_4, SWT.NONE);
		treeItem_9.setText("增删餐桌");
		treeItem_4.setExpanded(true);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(StaticObject.stackLayout);
		sashForm_1.setWeights(new int[] {206, 1065});
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] {73, 514, 40});
		
		Thread thread = new Thread(new MyThread());
		thread.start();
		
		tree.addSelectionListener(new SelectionAdapter() { // tree选择事件
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti = (TreeItem)e.item; // 获取用户点击的节点
				String nodeName = ti.getText();
				
				if ("查询用户".equals(nodeName)) {  // 点击  ”查询用户“
					if (StaticObject.researchUserUI == null) {
						StaticObject.researchUserUI = new ResearchUserUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.researchUserUI;
					
				} else if ("增删菜系".equals(nodeName)) {
					if (StaticObject.addDeleteCuisineUI == null) {
						StaticObject.addDeleteCuisineUI = new AddDeleteCuisineUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.addDeleteCuisineUI;
					
				} else if ("查删菜品".equals(nodeName)) {
					if (StaticObject.researchFoodUI == null) {
						StaticObject.researchFoodUI = new ResearchFoodUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.researchFoodUI;
					
				} else if ("增加菜品".equals(nodeName)) {
					if (StaticObject.addFoodUI == null) {
						StaticObject.addFoodUI = new AddFoodUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.addFoodUI;
					
				} else if ("查询订单".equals(nodeName)) {
					if (StaticObject.researchOrderUI == null) {
						StaticObject.researchOrderUI = new ResearchOrderUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.researchOrderUI;
					
				} else if ("查询预约".equals(nodeName)) {
					if (StaticObject.researchReservationUI == null) {
						StaticObject.researchReservationUI = new ResearchReservationUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.researchReservationUI;
					
				} else if ("增删餐桌".equals(nodeName)) {
					if (StaticObject.addDeleteTableUI == null) {
						StaticObject.addDeleteTableUI = new AddDeleteTableUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.addDeleteTableUI;
					
				} else if ("菜品销售数量".equals(nodeName)) {
					if (StaticObject.todaySpecialFoodUI == null) {
						StaticObject.todaySpecialFoodUI = new TodaySpecialFoodUI(composite_4, SWT.NONE);
					}
					StaticObject.stackLayout.topControl = StaticObject.todaySpecialFoodUI;
					
				}
				
				// 重新布局，刷新  
				composite_4.layout();
			}
		});
		
		// 关闭线程
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				thread.interrupt();
			}
		});

		
		// admin.jpg
		composite.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle bounds = image_admin.getBounds();
				int srcwidth = bounds.width;
				int srcHeight = bounds.height;
				Point size = composite.getSize();
				int destWidth = size.x;
				int destHeight = size.y;
				e.gc.drawImage(image_admin, 0, 0, srcwidth, srcHeight, 0, 0, destWidth, destHeight);
			}
		});
	}
	
	class MyThread implements Runnable{
		public void run(){
			while(true){
				shell.getDisplay().syncExec(new Runnable(){
					public void run(){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = sdf.format(new Date());
						label_time.setText(time);
					}
				});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
