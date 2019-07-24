package one.project.ui;

import one.project.dao.UserAdminDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class Register {

	protected Shell shell;
	private Text account;
	private Text pwd;
	private Text name;
	private Text telephone;
	private Text idcode;
	private Text pwd1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Register window = new Register();
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(631, 618);
		shell.setText("注册");
		
		// 如何让窗口居中显示？  显示器的大小？ （显示器的宽度-界面宽度）/2
		Rectangle rectangle = Display.getDefault().getClientArea();
		shell.setLocation((rectangle.width - shell.getSize().x)/2, (rectangle.height - shell.getSize().y)/2);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(97, 88, 76, 20);
		lblNewLabel.setText("账号:");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(97, 148, 76, 20);
		lblNewLabel_1.setText("密码:");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(97, 258, 76, 20);
		lblNewLabel_2.setText("昵称:");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(97, 314, 76, 20);
		lblNewLabel_3.setText("电话号码:");
		
		account = new Text(shell, SWT.BORDER);
		account.setBounds(214, 85, 263, 26);
		
		pwd = new Text(shell, SWT.BORDER);
		pwd.setBounds(214, 145, 263, 26);
		
		name = new Text(shell, SWT.BORDER);
		name.setBounds(214, 258, 263, 26);
		
		telephone = new Text(shell, SWT.BORDER);
		telephone.setBounds(214, 311, 263, 26);
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(97, 374, 76, 20);
		lblNewLabel_4.setText("性别:");
		
		Button btnRadioButton = new Button(shell, SWT.RADIO);
		btnRadioButton.setBounds(214, 374, 119, 20);
		btnRadioButton.setText("男");
		btnRadioButton.setSelection(true);
		
		Button btnRadioButton_1 = new Button(shell, SWT.RADIO);
		btnRadioButton_1.setBounds(358, 374, 119, 20);
		btnRadioButton_1.setText("女");
		
		Button ensure = new Button(shell, SWT.NONE);
		
		ensure.setBounds(210, 503, 170, 47);
		ensure.setText("立即注册");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(97, 433, 76, 20);
		label.setText("验证码:");
		
		idcode = new Text(shell, SWT.BORDER);
		idcode.setBounds(214, 430, 153, 26);
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(47, 21, 300, 20);
		lblNewLabel_5.setText("欢迎您注册饭店会员，如果您已拥有账号，请");
		
		Label separator1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator1.setBounds(45, 47, 522, 20);
		
		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		
		lblNewLabel_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_6.setBounds(350, 21, 66, 20);
		lblNewLabel_6.setText("点击登录");
		
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setBounds(97, 204, 76, 20);
		lblNewLabel_7.setText("确认密码：");
		
		pwd1 = new Text(shell, SWT.BORDER);
		pwd1.setBounds(214, 201, 263, 26);
		
		lblNewLabel_6.addMouseListener(new MouseAdapter() {  // 返回登录界面
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				new Login().open();
			}
		});
		
		ensure.addMouseListener(new MouseAdapter() {  // 点击注册按钮
			@Override
			public void mouseUp(MouseEvent e) {
				String a = account.getText();
				String p = pwd.getText();
				String p1 = pwd1.getText();
				String n = name.getText();
				String sex = null;
				String tele = telephone.getText();
				String[] tip = {"账号", "密码", "确认密码", "姓名", "电话"};
				int flag = StringUtil.Null(a, p, p1, n, tele);
				if ( flag  != 0 ) 
				{
					MessageDialog.openError(shell, "温馨提示", tip[flag-1] + "不能为空。。。");
					return;
				}
				if (!StringUtil.equ(p, p1)) {
					MessageDialog.openError(shell, "温馨提示", "两次密码输入不一致。。。");
					return;
				}
				
				// 获取性别
				if (btnRadioButton.getSelection()) {
					sex = "男";
				} else if (btnRadioButton_1.getSelection()) {
					sex = "女";
				}
				
				
				UserAdminDao userAdminDao = new UserAdminDao();
				int index = userAdminDao.register(a, p, n, sex, tele);
				// 注册成功，跳转到登录界面
				if (index > 0) {
					MessageDialog.openInformation(shell, "成功提示", "恭喜您注册成功，即将跳转到登录界面。。。");
					shell.dispose();
					new Login().open();
				}
				
			}
		});
		
	}
}
