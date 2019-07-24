package one.project.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Composite;

import swing2swt.layout.BoxLayout;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.custom.CLabel;

public class Index {

	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Text text;
	private Text content2;
	private Map<String, Object> loginInfo;
	private Text text_1;
	private Text text_2;
	private Canvas canvas;
	private Canvas canvas_1;
	private Label label_fname1;
	private Label label_name1;
	private Label label_price1;
	private Label label_fname2;
	private Label label_name2;
	private Label label_price2;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Index window = new Index();
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
		this.loginInfo = loginInfo;
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(Index.class, "/images/logohead.png"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setSize(1272, 1100);
		//shell.setText("订餐系统" + "欢迎" + loginInfo.get("u_name") + "用户使用本系统");
		shell.setText("订餐系统" + "欢迎" + " " + StaticObject.name + " " + "用户使用本系统");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Image logophoto = SWTResourceManager.getImage(Index.class,
				"/images/logo.png");
		Image image = SWTResourceManager.getImage(Index.class,
				"/images/head.png");
		Image image_main_left = SWTResourceManager.getImage(Index.class,
				"/images/main_left_background2.png");
		Image image_main_right = SWTResourceManager.getImage(Index.class,
				"/images/main_right_photo.png");
		Image image_main_mid_top1 = SWTResourceManager.getImage(Index.class,
				"/images/label1.png");
		Image image_main_mid_top2 = SWTResourceManager.getImage(Index.class,
				"/images/label2.png");

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		sashForm.setBackgroundMode(SWT.INHERIT_FORCE);

		Composite head = new Composite(sashForm, SWT.NONE);
		head.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(head, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);

		Composite menu = new Composite(sashForm_1, SWT.NONE);
		menu.setBackgroundImage(SWTResourceManager.getImage(Index.class,
				"/images/head_menu_background.png"));
		menu.setBackgroundMode(SWT.INHERIT_FORCE);
		menu.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		menu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Label label_index = new Label(menu, SWT.NONE);

		label_index.setBackgroundImage(SWTResourceManager.getImage(Index.class,
				"/images/head_menu1_1.png"));
		label_index.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_index.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		label_index.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_index.setAlignment(SWT.CENTER);
		label_index.setBounds(329, 14, 100, 25);
		formToolkit.adapt(label_index, true, true);

		Label label_foodmenu = new Label(menu, SWT.NONE);

		label_foodmenu.setBackgroundImage(SWTResourceManager.getImage(
				Index.class, "/images/head_menu2_1.png"));
		label_foodmenu.setAlignment(SWT.CENTER);
		label_foodmenu.setBounds(444, 14, 100, 25);
		formToolkit.adapt(label_foodmenu, true, true);

		Label label_order = new Label(menu, SWT.NONE);

		label_order.setBackgroundImage(SWTResourceManager.getImage(Index.class,
				"/images/head_menu4_1.png"));
		label_order.setAlignment(SWT.CENTER);
		label_order.setBounds(675, 14, 100, 25);
		formToolkit.adapt(label_order, true, true);

		Label label_ufood = new Label(menu, SWT.NONE);

		label_ufood.setBackgroundImage(SWTResourceManager.getImage(Index.class,
				"/images/head_menu3_1.png"));
		label_ufood.setAlignment(SWT.CENTER);
		label_ufood.setBounds(559, 14, 100, 25);
		formToolkit.adapt(label_ufood, true, true);

		Label label_1 = new Label(menu, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(436, 14, 2, 25);
		formToolkit.adapt(label_1, true, true);

		Label label_2 = new Label(menu, SWT.SEPARATOR);
		label_2.setBounds(551, 14, 2, 25);
		formToolkit.adapt(label_2, true, true);

		Label label_3 = new Label(menu, SWT.SEPARATOR);
		label_3.setBounds(667, 14, 2, 25);
		formToolkit.adapt(label_3, true, true);

		Label label_4 = new Label(menu, SWT.SEPARATOR);
		label_4.setBounds(783, 14, 2, 25);
		formToolkit.adapt(label_4, true, true);

		Label label_aboutme = new Label(menu, SWT.NONE);
		
		label_aboutme.setBackgroundImage(SWTResourceManager.getImage(
				Index.class, "/images/head_menu6_1.png"));
		label_aboutme.setBounds(791, 14, 100, 25);
		formToolkit.adapt(label_aboutme, true, true);

		text = new Text(menu, SWT.BORDER);
		text.setBounds(922, 12, 143, 25);
		formToolkit.adapt(text, true, true);

		Button btn_search = new Button(menu, SWT.NONE);
		btn_search.setImage(SWTResourceManager.getImage(Index.class,
				"/images/button_search.png"));
		btn_search.setBounds(1065, 12, 50, 25);
		formToolkit.adapt(btn_search, true, true);

		Label logo = new Label(menu, SWT.NONE);
		logo.setLocation(0, 0);
		logo.setSize(182, 49);
		
		Button btn_login = new Button(menu, SWT.NONE);
		
		btn_login.setImage(SWTResourceManager.getImage(Index.class, "/images/btn_index1.png"));
		btn_login.setBackgroundImage(SWTResourceManager.getImage(Index.class, "/images/btn_index1.png"));
		btn_login.setBounds(1172, 7, 84, 34);
		formToolkit.adapt(btn_login, true, true);
		logo.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Rectangle bounds = logophoto.getBounds();
				int srcWidth = bounds.width;
				int srcHeight = bounds.height;
				Point size = logo.getSize();
				int destWidth = size.x;
				int destHeight = size.y;
				// Point p = head_label.getLocation();
				arg0.gc.drawImage(logophoto, 0, 0, srcWidth, srcHeight, 0, 0,
						destWidth, destHeight);
			}
		});

		Composite head_photo = new Composite(sashForm_1, SWT.NONE);
		head_photo.setLayout(new FillLayout(SWT.HORIZONTAL));

		Canvas canvas_menu = new Canvas(head_photo, SWT.DOUBLE_BUFFERED);
		canvas_menu.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				event.gc.drawImage(image, 0, 0, image.getBounds().width,
						image.getBounds().height, 0, 0, event.width,
						event.height);
			}
		});
		formToolkit.adapt(canvas_menu);
		formToolkit.paintBordersFor(canvas_menu);
		canvas_menu.setLayout(new FillLayout(SWT.HORIZONTAL));
		sashForm_1.setWeights(new int[] { 50, 501 });

		Composite body = new Composite(sashForm, SWT.NONE);
		body.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_2 = new SashForm(body, SWT.NONE);

		Composite main_left = new Composite(sashForm_2, SWT.NONE);
		main_left.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Text content = new Text(main_left, SWT.WRAP);  
		content.setBackgroundImage(SWTResourceManager.getImage(Index.class, "/images/main_left_background3.png"));
		
		content.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		content.setText("       xx西餐食品有限公司创建于1987年，是一家综合生产西餐生、熟肉制品的企业。公司所持有的“健士” 商标被评为北京市著名商标。2008年入选北京奥运会西式食品供应企业。并因服务表现突出，被北京市质量技术监督局评为“十家优秀供奥企业”之一。公司所持有的“健士”商标被评为北京市著名商标。");
		content.setLocation(0, 295);
		content.setSize(314, 175);
		
		Label label_main_left_1 = new Label(main_left, SWT.NONE);
		label_main_left_1.setBackgroundImage(SWTResourceManager.getImage(Index.class, "/images/label_background1.png"));
		label_main_left_1.setBackground(SWTResourceManager.getColor(248, 248, 255));
		label_main_left_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_main_left_1.setForeground(SWTResourceManager.getColor(255, 140, 0));
		label_main_left_1.setAlignment(SWT.CENTER);
		label_main_left_1.setBounds(0, 0, 314, 27);
		formToolkit.adapt(label_main_left_1, true, true);
		label_main_left_1.setText("餐厅简介");
		
		Label label = new Label(main_left, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 9, SWT.BOLD));
		label.setForeground(SWTResourceManager.getColor(255, 69, 0));
		label.setBounds(0, 28, 314, 2);
		formToolkit.adapt(label, true, true);
		
		Label lblRestaurant = new Label(main_left, SWT.HORIZONTAL | SWT.SHADOW_NONE);
		lblRestaurant.setBackgroundImage(SWTResourceManager.getImage(Index.class, "/images/label_background1.png"));
		lblRestaurant.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lblRestaurant.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		lblRestaurant.setAlignment(SWT.CENTER);
		lblRestaurant.setBounds(0, 31, 314, 25);
		formToolkit.adapt(lblRestaurant, true, true);
		lblRestaurant.setText("Restaurant  Profile");
		
		Composite main_left_photo = new Composite(main_left, SWT.NONE);
		main_left_photo.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(image_main_left, 0, 0, image_main_left.getBounds().width,
						image_main_left.getBounds().height, 0, 0, arg0.width,
						arg0.height);
			}
		});
		main_left_photo.setBounds(0, 58, 314, 238);
		formToolkit.adapt(main_left_photo);
		formToolkit.paintBordersFor(main_left_photo);

		Composite main = new Composite(sashForm_2, SWT.NONE);
		main.setBackgroundMode(SWT.INHERIT_FORCE);
		formToolkit.adapt(main);
		formToolkit.paintBordersFor(main);
		main.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_3 = new SashForm(main, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		formToolkit.adapt(sashForm_3);
		formToolkit.paintBordersFor(sashForm_3);

		Composite composite_1 = new Composite(sashForm_3, SWT.NONE);
		formToolkit.adapt(composite_1);
		formToolkit.paintBordersFor(composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label lblNewLabel_5 = new Label(composite_1, SWT.CENTER);
		lblNewLabel_5.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblNewLabel_5.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));
		lblNewLabel_5.setAlignment(SWT.CENTER);
		formToolkit.adapt(lblNewLabel_5, true, true);
		lblNewLabel_5.setText("今日特色菜");
		
		Composite main_mid = new Composite(sashForm_3, SWT.NONE);
		formToolkit.adapt(main_mid);
		formToolkit.paintBordersFor(main_mid);
		main_mid.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_4 = new SashForm(main_mid, SWT.NONE);
		formToolkit.adapt(sashForm_4);
		formToolkit.paintBordersFor(sashForm_4);
		
		Composite composite = new Composite(sashForm_4, SWT.NONE);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_5 = new SashForm(composite, SWT.NONE);
		sashForm_5.setOrientation(SWT.VERTICAL);
		formToolkit.adapt(sashForm_5);
		formToolkit.paintBordersFor(sashForm_5);
		
		Composite composite_3 = new Composite(sashForm_5, SWT.NONE);
		formToolkit.adapt(composite_3);
		formToolkit.paintBordersFor(composite_3);
		
		canvas = new Canvas(composite_3, SWT.NONE);
		canvas.setBounds(10, 10, 150, 185);
		formToolkit.adapt(canvas);
		formToolkit.paintBordersFor(canvas);
		
		label_price1 = new Label(composite_3, SWT.NONE);
		label_price1.setBounds(204, 134, 40, 20);
		formToolkit.adapt(label_price1, true, true);
		label_price1.setText("New Label");
		
		label_fname1 = new Label(composite_3, SWT.NONE);
		label_fname1.setBounds(204, 34, 76, 20);
		formToolkit.adapt(label_fname1, true, true);
		label_fname1.setText("New Label");
		
		label_name1 = new Label(composite_3, SWT.NONE);
		label_name1.setBounds(204, 84, 76, 20);
		formToolkit.adapt(label_name1, true, true);
		label_name1.setText("New Label");
		
		Label lblNewLabel_1 = new Label(composite_3, SWT.NONE);
		lblNewLabel_1.setBounds(258, 134, 34, 20);
		formToolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("＄");
		
		Composite composite_4 = new Composite(sashForm_5, SWT.NONE);
		formToolkit.adapt(composite_4);
		formToolkit.paintBordersFor(composite_4);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text_1 = new Text(composite_4, SWT.BORDER | SWT.READ_ONLY);
		formToolkit.adapt(text_1, true, true);
		sashForm_5.setWeights(new int[] {1, 1});
		
		Composite composite_2 = new Composite(sashForm_4, SWT.NONE);
		formToolkit.adapt(composite_2);
		formToolkit.paintBordersFor(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_6 = new SashForm(composite_2, SWT.NONE);
		sashForm_6.setOrientation(SWT.VERTICAL);
		formToolkit.adapt(sashForm_6);
		formToolkit.paintBordersFor(sashForm_6);
		
		Composite composite_5 = new Composite(sashForm_6, SWT.NONE);
		formToolkit.adapt(composite_5);
		formToolkit.paintBordersFor(composite_5);
		
		canvas_1 = new Canvas(composite_5, SWT.NONE);
		canvas_1.setBounds(10, 10, 150, 185);
		formToolkit.adapt(canvas_1);
		formToolkit.paintBordersFor(canvas_1);
		
		label_price2 = new Label(composite_5, SWT.NONE);
		label_price2.setText("New Label");
		label_price2.setBounds(204, 134, 40, 20);
		formToolkit.adapt(label_price2, true, true);
		
		label_fname2 = new Label(composite_5, SWT.NONE);
		label_fname2.setText("New Label");
		label_fname2.setBounds(204, 34, 76, 20);
		formToolkit.adapt(label_fname2, true, true);
		
		label_name2 = new Label(composite_5, SWT.NONE);
		label_name2.setText("New Label");
		label_name2.setBounds(204, 84, 76, 20);
		formToolkit.adapt(label_name2, true, true);
		
		Label label_5 = new Label(composite_5, SWT.NONE);
		label_5.setText("＄");
		label_5.setBounds(258, 134, 34, 20);
		formToolkit.adapt(label_5, true, true);
		
		Composite composite_6 = new Composite(sashForm_6, SWT.NONE);
		formToolkit.adapt(composite_6);
		formToolkit.paintBordersFor(composite_6);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text_2 = new Text(composite_6, SWT.BORDER | SWT.READ_ONLY);
		formToolkit.adapt(text_2, true, true);
		sashForm_4.setWeights(new int[] {1, 1});
		sashForm_3.setWeights(new int[] {49, 413});

		Composite main_right = new Composite(sashForm_2, SWT.NONE);
		formToolkit.adapt(main_right);
		formToolkit.paintBordersFor(main_right);
		
		Composite main_right_photo = new Composite(main_right, SWT.NONE);
		main_right_photo.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(image_main_right, 0, 0, image_main_right.getBounds().width,
						image_main_right.getBounds().height, 0, 0, arg0.width,
						arg0.height);
			}
		});
		main_right_photo.setBounds(0, 0, 274, 363);
		formToolkit.adapt(main_right_photo);
		formToolkit.paintBordersFor(main_right_photo);
		
		content2 = new Text(main_right, SWT.WRAP);
		content2.setText("                    联系电话\r\n\r\n           外送电话：18xxxxxxx25\r\n\r\n        预定电话：020-3xxxxxxx65\r\n");
		content2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		content2.setBackgroundImage(SWTResourceManager.getImage(Index.class, "/images/main_right_background3.png"));
		content2.setBounds(0, 363, 274, 106);
		formToolkit.adapt(content2, true, true);
		sashForm_2.setWeights(new int[] {313, 666, 271});

		Composite feat = new Composite(sashForm, SWT.NONE);
		feat.setBackgroundImage(SWTResourceManager.getImage(Index.class,
				"/images/login_background3.png"));
		feat.setBackgroundMode(SWT.INHERIT_FORCE);

		Label lblNewLabel = new Label(feat, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(Index.class,
				"/images/login_background3.png"));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(0, 7, 1184, 17);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("雏翼有限公司©版权所有");
		sashForm.setWeights(new int[] {552, 469, 34});

		center(shell);
		
		showSpecialFood();

		// 菜单栏“首页”标签事件
		label_index.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				label_index.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu1_2.png"));
			}

			@Override
			public void mouseExit(MouseEvent e) {
				label_index.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu1_1.png"));
			}
		});

		// 菜单栏“菜单”标签事件
		label_foodmenu.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				label_foodmenu.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu2_2.png"));
			}

			@Override
			public void mouseExit(MouseEvent e) {
				label_foodmenu.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu2_1.png"));
			}
		});

		// 菜单栏“用户点餐”标签事件
		label_ufood.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				label_ufood.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu3_2.png"));
			}

			@Override
			public void mouseExit(MouseEvent e) {
				label_ufood.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu3_1.png"));
			}
		});

		// 菜单栏“座位预定”标签事件
		label_order.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				label_order.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu4_2.png"));
			}

			@Override
			public void mouseExit(MouseEvent e) {
				label_order.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu4_1.png"));
			}
		});
		
		// 菜单栏“关于我们”标签事件
		label_aboutme.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				label_aboutme.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu6_2.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				label_aboutme.setBackgroundImage(SWTResourceManager.getImage(
						Index.class, "/images/head_menu6_1.png"));
			}
		});
		
		// 菜单栏“登录/注册”按钮事件
		btn_login.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				btn_login.setImage(SWTResourceManager.getImage(Index.class, "/images/btn_index2.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				btn_login.setImage(SWTResourceManager.getImage(Index.class, "/images/btn_index1.png"));
			}
		});
		
		// 用户点餐界面
		label_ufood.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				OrderTable orderTable = new OrderTable();
				orderTable.open();
			}
		});
		
		// 用户预约桌子
		label_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				Reserve reserve = new Reserve();
				reserve.open();
			}
		});
		
		// 用户登录注册界面
		btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				StaticObject.type = -1;
				StaticObject.number = -1;
				StaticObject.name = null;
				Login login = new Login();
				login.open();
			}
		});

	}

	/**
	 * 窗口居中显示
	 * 
	 * @param shell
	 *            要显示的窗口
	 */
	private static void center(Shell shell) {
		Monitor monitor = shell.getMonitor();
		Rectangle bounds = monitor.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}
	
	// 展示今日热销菜信息
		public void showSpecialFood(){
			ThingDao thingDao = new ThingDao();
			List<Map<String, Object>> list = thingDao.showFoodSaleCount();
			Map<String, Object> map1 = list.get(0);
			Map<String, Object> map2 = list.get(1);
			String fname1 = String.valueOf(map1.get("c_name"));
			String fname2 = String.valueOf(map1.get("c_name"));
			String id1 = String.valueOf(map1.get("d_id"));
			String id2 = String.valueOf(map2.get("d_id"));
			map1 = thingDao.researchDishesById(id1);
			map2 = thingDao.researchDishesById(id2);
			label_fname1.setText(fname1);;
			label_name1.setText(String.valueOf(map1.get("d_name")));;
			label_price1.setText(String.valueOf(map1.get("d_prices")));;
			text_1.setText(String.valueOf(map1.get("d_remark")));
			byte[] bt = (byte[]) map1.get("d_photo");
			InputStream is = new ByteArrayInputStream(bt);
			ImageData imageData = new ImageData(is);
			imageData = imageData.scaledTo(canvas.getSize().x, canvas.getSize().y);
			Image image = new Image(Display.getDefault(), imageData);
			canvas.setBackgroundImage(image);
			
			label_fname2.setText(fname2);;
			label_name2.setText(String.valueOf(map2.get("d_name")));;
			label_price2.setText(String.valueOf(map2.get("d_prices")));;
			text_2.setText(String.valueOf(map2.get("d_remark")));
			byte[] bt1 = (byte[]) map2.get("d_photo");
			InputStream is1 = new ByteArrayInputStream(bt1);
			ImageData imageData1 = new ImageData(is1);
			imageData1 = imageData1.scaledTo(canvas_1.getSize().x, canvas_1.getSize().y);
			Image image1 = new Image(Display.getDefault(), imageData1);
			canvas_1.setBackgroundImage(image1);
		}

	
}
