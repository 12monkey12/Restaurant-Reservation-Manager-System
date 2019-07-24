package one.project.ui;

import java.util.Map;

import one.project.dao.ThingDao;
import one.project.dao.UserAdminDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

public class ModifyPassWord1 extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ModifyPassWord1(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(476, 416);
		shell.setText("修改密码");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLocation(82, 51);
		lblNewLabel.setSize(76, 20);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("旧密码");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLocation(81, 104);
		lblNewLabel_1.setSize(76, 20);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setText("新密码");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setLocation(83, 162);
		lblNewLabel_2.setSize(76, 20);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setText("确认密码");
		
		text = new Text(composite, SWT.BORDER);
		text.setLocation(196, 57);
		text.setSize(199, 26);
		
		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_1.setLocation(193, 110);
		text_1.setSize(199, 26);
		
		text_2 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_2.setLocation(198, 161);
		text_2.setSize(199, 26);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLocation(153, 293);
		btnNewButton.setSize(123, 41);
		btnNewButton.setText("确定");

		// 确认修改密码
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String oldpwd = text.getText().trim();
				String newpwd = text_1.getText().trim();
				String renewpwd = text_2.getText().trim();
				
				if (StringUtil.isNull(oldpwd, newpwd)){
					MessageDialog.openError(shell, "错误提示", "旧密码或新密码为空！");
					return;
				}
				if (!newpwd.equals(renewpwd)) {
					MessageDialog.openError(shell, "错误提示", "两次输入新密码不一致！");
					return;
				}
				
				// 更新数据库
				UserAdminDao userAdminDao = new UserAdminDao();
				Map<String, Object> map = userAdminDao.findUserById(3);
				String pwd = String.valueOf(map.get("u_password"));
				if (!pwd.equals(oldpwd)) {
					MessageDialog.openError(shell, "错误提示", "原始密码输入不正确！");
					return;
				}
				int result = userAdminDao.modifyUserpwd("1", newpwd);
				if (result > 0) {
					MessageDialog.openInformation(shell, "成功提示", "恭喜您修改密码成功！");
					shell.dispose();
				} else {
					MessageDialog.openError(shell, "失败提示", "修改密码失败,请重新修改！");
				}
				
			}
		});
	}
}
