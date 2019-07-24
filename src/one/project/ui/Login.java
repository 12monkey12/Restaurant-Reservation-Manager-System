package one.project.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import one.project.dao.DBHelper;
import one.project.dao.UserAdminDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Login {

	protected Shell shell;
	private Text ad_account;
	private Text ad_pwd;
	private Text ad_idcode;
	private Text user_account;
	private Text user_pwd;
	private Text user_idcode;
	private String code1; //用户验证码
	private String code2; //管理员验证码

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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

	//生成验证码和验证码图片
	public String image(Label label) throws IOException {
		String testcode = "";// 验证码，保存验证码，取这个值于用户输入的值作比较
		// BufferedImage 的构造函数可以设置图片的大小
		BufferedImage image = new BufferedImage(label.getSize().x,
				label.getSize().y, BufferedImage.TYPE_INT_RGB);// 这里设置图片的大小
		// 这里需要使用到java.awt.Graphics来绘制图片
		java.awt.Graphics graphics = image.getGraphics();
		Color color = new Color(245, 245, 220);
		graphics.setColor(color);// 为图片添加的底色
		graphics.fillRect(0, 0, label.getSize().x, label.getSize().y);
		char[] content = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
				.toCharArray();
		Random random = new Random();
		int index;
		for (int i = 0; i < 4; i++) {// 验证码长度
			index = random.nextInt(content.length);
			testcode += String.valueOf(content[index]);// testcode是验证码
			// 图片中文字的颜色
			graphics.setColor(new Color(random.nextInt(255), random
					.nextInt(255), random.nextInt(255)));
			// 图片中文字的位置
			graphics.drawString(content[index] + "", 10 + 20 * i,
					10 + 3 * random.nextInt(4));
			// 1,验证码文字,2文字距离上边的距离3,距离下部分的距离,可以更改这后面的两个数据,来改变图片的,验证码显示位置
		}
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", stream);
		InputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
		label.setImage(new Image(null, new ImageData(inputStream).scaledTo(
				label.getSize().x, label.getSize().y)));
		return testcode;
	}
	
	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell(SWT.CLOSE);
		shell.setSize(1079, 611);
		shell.setText("登陆界面");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		// 如何让窗口居中显示？  显示器的大小？ （显示器的宽度-界面宽度）/2
		Rectangle rectangle = Display.getDefault().getClientArea();
		shell.setLocation((rectangle.width - shell.getSize().x)/2, (rectangle.height - shell.getSize().y)/2);
		
		// 声明图片对象
		Image img1 = SWTResourceManager.getImage(Login.class, "/images/head.png");
		Image img2 = SWTResourceManager.getImage(Login.class, "/images/lock.jpg");
				
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
					
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("用户");
		
		tabFolder.getSelectionIndex();
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/login_background2.png"));
		tbtmNewItem.setControl(composite_1);
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/login_background3.png"));
		composite_2.setBounds(656, 53, 355, 395);
		composite_2.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label label_3 = new Label(composite_2, SWT.CENTER);
		label_3.setLocation(0, 0);
		label_3.setSize(316, 40);
		label_3.setText("欢迎登录");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 18, SWT.NORMAL));
		
		Label label_4 = new Label(composite_2, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(Login.class, "/images/user.png"));
		label_4.setLocation(24, 84);
		label_4.setSize(32, 26);
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(Login.class, "/images/password.png"));
		lblNewLabel_1.setLocation(24, 154);
		lblNewLabel_1.setSize(32, 26);
		
		user_account = new Text(composite_2, SWT.BORDER);
		user_account.setLocation(74, 84);
		user_account.setSize(206, 26);
		
		Label lblNewLabel_5 = new Label(composite_2, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		lblNewLabel_5.setLocation(10, 224);
		lblNewLabel_5.setSize(58, 26);
		lblNewLabel_5.setText("验证码");
		
		user_pwd = new Text(composite_2, SWT.BORDER | SWT.PASSWORD);
		user_pwd.setLocation(74, 154);
		user_pwd.setSize(206, 26);
		
		user_idcode = new Text(composite_2, SWT.BORDER);
		user_idcode.setLocation(74, 224);
		user_idcode.setSize(119, 26);
		
		Label register = new Label(composite_2, SWT.CENTER);
		
		register.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		register.setLocation(0, 358);
		register.setSize(316, 20);
		register.setText("立即注册");
		
		Label fg_key = new Label(composite_2, SWT.NONE);
		fg_key.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		fg_key.setAlignment(SWT.RIGHT);
		fg_key.setLocation(240, 260);
		fg_key.setSize(107, 20);
		fg_key.setText("忘记密码？");
		
		Button user_button = new Button(composite_2, SWT.NONE);
		user_button.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.BOLD));

		user_button.setLocation(42, 292);
		user_button.setSize(232, 52);
		user_button.setText("登录");
		
		Label label_code = new Label(composite_2, SWT.NONE);
		
		label_code.setAlignment(SWT.CENTER);
		label_code.setBounds(199, 224, 94, 26);
		label_code.setText("获取验证码");
		
		
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("管理员");
		
		Composite register1 = new Composite(tabFolder, SWT.NONE);
		register1.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/login_background2.png"));
		tbtmNewItem_1.setControl(register1);
		
		Composite composite_3 = new Composite(register1, SWT.NONE);
		composite_3.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/login_background3.png"));
		composite_3.setBounds(656, 53, 355, 395);
		composite_3.setBackgroundMode(SWT.INHERIT_FORCE);
		
		
		Button ad_button = new Button(composite_3, SWT.NONE);
		ad_button.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.BOLD));
		ad_button.setLocation(42, 292);
		ad_button.setSize(232, 52);
		
		ad_button.setText("登录");
		
		Label label = new Label(composite_3, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(Login.class, "/images/user.png"));
		label.setLocation(24, 84);
		label.setSize(32, 26);
		
		ad_account = new Text(composite_3, SWT.BORDER);
		ad_account.setLocation(74, 84);
		ad_account.setSize(206, 26);
		
		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Login.class, "/images/password.png"));
		label_1.setLocation(24, 154);
		label_1.setSize(32, 26);
		
		ad_pwd = new Text(composite_3, SWT.BORDER | SWT.PASSWORD);
		ad_pwd.setLocation(74, 154);
		ad_pwd.setSize(206, 26);
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_2.setLocation(10, 224);
		label_2.setSize(58, 26);
		label_2.setText("验证码");
		
		ad_idcode = new Text(composite_3, SWT.BORDER);
		ad_idcode.setLocation(74, 224);
		ad_idcode.setSize(119, 26);
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.CENTER);
		lblNewLabel_3.setLocation(0, 0);
		lblNewLabel_3.setSize(316, 40);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 18, SWT.NORMAL));
		lblNewLabel_3.setText("欢迎登录");
		
		Label fg_key1 = new Label(composite_3, SWT.NONE);
		fg_key1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		fg_key1.setAlignment(SWT.RIGHT);
		fg_key1.setLocation(240, 260);
		fg_key1.setSize(107, 20);
		fg_key1.setText("忘记密码？");
		
		Label label_code2 = new Label(composite_3, SWT.NONE);
		
		label_code2.setBounds(200, 224, 94, 26);
		label_code2.setText("获取验证码");
		
		ad_button.addMouseListener(new MouseAdapter() {  // 管理员登录按钮点击事件
			@Override
			public void mouseUp(MouseEvent e) {
				
				String idcode = ad_idcode.getText().trim().toLowerCase();
				
				int flag = tabFolder.getSelectionIndex();
				String account = ad_account.getText().trim();
				String pwd = ad_pwd.getText().trim();
				
				showDialog(flag, account, pwd, idcode);
				
			}
		});
		
		label_code2.addMouseListener(new MouseAdapter() { //管理员验证码
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					code2 = image(label_code2).toLowerCase();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		user_button.addMouseListener(new MouseAdapter() { // 用户登录按钮点击事件
			@Override
			public void mouseUp(MouseEvent e) {
				String idcode = user_idcode.getText().trim().toLowerCase();
			
				int flag = tabFolder.getSelectionIndex();
				String account = user_account.getText().trim();
				String pwd = user_pwd.getText().trim();
				
				showDialog(flag, account, pwd, idcode);
				
			}
		});
		//用户点击获取验证码标签，显示验证码
		label_code.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					code1 = image(label_code).toLowerCase();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		tabFolder.addMouseListener(new MouseAdapter() {  // 选项卡点击事件
			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
		
		register.addMouseListener(new MouseAdapter() {  //立即注册按钮点击事件
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				new Register().open();
			}
		});
		
	}
	
	public void showDialog(int flag, String account, String pwd, String idcode) {
		if (StringUtil.isNull(account, pwd)) {
			MessageDialog.openError(shell, "温馨提示", "账号或密码不能为空。。。");
			return;
		}
//		if (StringUtil.isNull(idcode)) {
//			MessageDialog.openError(shell, "温馨提示", "账号或密码不能为空。。。");
//			return;
//		}
		
		// 判断获取用户验证码还是管理员验证码
		String name = null;
		String num = null;
		String code = null;
		if (flag == 0) { // 用户
			code = code1;
			name = "u_name";
			num = "u_id";
		} else if (flag == 1) { // 管理员
			code = code2;
			name = "a_name";
			num = "a_id";
		}
		System.out.println(code);
		System.out.println(idcode);
		System.out.println(flag);
		
		if (!idcode.equals(code) || "".equals(idcode)) {
			MessageDialog.openError(shell, "提示错误", "验证码错误，请重新输入");
			return;
		} 
		
		UserAdminDao userAdminDao = new UserAdminDao();
		Map<String, Object> map = userAdminDao.login(flag, account, pwd);
		if (map != null && !map.isEmpty()) {

			// 重置静态变量
			StaticObject.type = flag;
			StaticObject.name = String.valueOf(map.get(name));
			StaticObject.number = Integer.parseInt(map.get(num).toString());

			if (flag == 0) {
				Index index = new Index();
				shell.dispose();
				index.open();
			} else if (flag == 1) {
				Admin admin = new Admin();
				shell.dispose();
				admin.open();
			}
			// System.out.println(StaticObject.number);
		} else {
			MessageDialog.openError(shell, "错误提示", "账号或密码错误。。。");
		}
	}
}
