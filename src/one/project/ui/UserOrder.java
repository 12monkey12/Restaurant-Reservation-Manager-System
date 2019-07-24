package one.project.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class UserOrder {
	private Composite composite_4;

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserOrder window = new UserOrder();
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
		shell.setImage(SWTResourceManager.getImage(UserOrder.class, "/images/logohead.png"));
		shell.setSize(1235, 858);
		shell.setText("点餐");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Image image = SWTResourceManager.getImage(Index.class,
				"/images/login_background3.png");
		Image imagelogo = SWTResourceManager.getImage(Index.class,
				"/images/logo.png");
		Image imageback = SWTResourceManager.getImage(Index.class,
				"/images/back_btn1.png");
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setBackgroundMode(SWT.INHERIT_FORCE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(imagelogo, 0, 0, imagelogo.getBounds().width,
						imagelogo.getBounds().height, 0, 0, arg0.width,
						arg0.height);
			}
		});
		lblNewLabel_2.setBounds(0, 0, 195, 50);
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(imageback, 0, 0, imageback.getBounds().width,
						imageback.getBounds().height, 0, 0, arg0.width,
						arg0.height);
			}
		});
		
		lblNewLabel_3.setBounds(1150, 0, 69, 50);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackgroundMode(SWT.INHERIT_FORCE);
		composite_3.setBackground(SWTResourceManager.getColor(245, 245, 245));
		composite_3.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(image, 0, 0, image.getBounds().width,
						image.getBounds().height, 0, 0, arg0.width,
						arg0.height);
			}
		});

		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setBackgroundImage(SWTResourceManager.getImage(UserOrder.class, "/images/login_background3.png"));
		label_1.setBackground(SWTResourceManager.getColor(245, 245, 245));

		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(20, 226, 160, 40);
		label_1.setText("点餐");

		Label lblNewLabel_1 = new Label(composite_3, SWT.NONE);
		lblNewLabel_1.setBackgroundImage(SWTResourceManager.getImage(UserOrder.class, "/images/login_background3.png"));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(245, 245, 245));

		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		lblNewLabel_1.setBounds(20, 317, 160, 40);
		lblNewLabel_1.setText("查看订单");

		Label label = new Label(composite_3, SWT.NONE);
		label.setBackgroundImage(SWTResourceManager.getImage(UserOrder.class, "/images/login_background3.png"));
		label.setBackground(SWTResourceManager.getColor(245, 245, 245));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label.setAlignment(SWT.CENTER);
		label.setBounds(20, 416, 160, 40);
		label.setText("用户中心");

		composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(StaticObject.stackLayout);
		sashForm_1.setWeights(new int[] {199, 1042});

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(
				UserOrder.class, "/images/login_background3.png"));

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(
				UserOrder.class, "/images/login_background3.png"));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(0, 5, 1244, 20);
		lblNewLabel.setText("雏翼有限公司©版权所有");
		sashForm.setWeights(new int[] { 57, 831, 27 });
		
		center(shell);

		// 点击“点餐”标签时
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (StaticObject.orderFoodUI == null) {
					StaticObject.orderFoodUI = new OrderFoodUI(composite_4, SWT.NONE);
				}
				StaticObject.stackLayout.topControl = StaticObject.orderFoodUI;
				composite_4.layout();
			}
		});

		// 点击“查看订单”标签时
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseDown(MouseEvent e) {
				if (StaticObject.userOrderUI == null) {
					StaticObject.userOrderUI = new UserOrderUI(composite_4,
							SWT.NONE);
				}
				StaticObject.stackLayout.topControl = StaticObject.userOrderUI;
				composite_4.layout();
			}
		});

		// 点击“用户中心”标签时
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (StaticObject.personUI == null) {
					StaticObject.personUI = new PersonUI(composite_4, SWT.NONE);
				}
				StaticObject.stackLayout.topControl = StaticObject.personUI;
				composite_4.layout();
			}
		});

		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Index index = new Index();
				shell.dispose();
				index.open();
			}
		});
	}
	private static void center(Shell shell) {
		Monitor monitor = shell.getMonitor();
		Rectangle bounds = monitor.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}
}
