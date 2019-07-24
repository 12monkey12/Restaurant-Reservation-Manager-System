package one.project.ui;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ModifyPassWord {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ModifyPassWord window = new ModifyPassWord();
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
		shell.setSize(673, 427);
		shell.setText("修改密码");
		
		// 如何让窗口居中显示？  显示器的大小？ （显示器的宽度-界面宽度）/2
		Rectangle rectangle = Display.getDefault().getClientArea();
		shell.setLocation((rectangle.width - shell.getSize().x)/2, (rectangle.height - shell.getSize().y)/2);

	}

}
