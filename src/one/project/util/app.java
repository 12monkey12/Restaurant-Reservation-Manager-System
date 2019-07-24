package one.project.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class app {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			app window = new app();
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
		shell.setSize(530, 420);
		shell.setText("SWT Application");

		//CalendarDialog cd = new CalendarDialog(shell, SWT.NONE);
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER | SWT.CALENDAR);
		dateTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(dateTime.getYear());
				System.out.println(dateTime.getMonth());
				System.out.println(dateTime.getDay());
				System.out.println(dateTime.getHours());
				System.out.println(dateTime.getMinutes());
				System.out.println(dateTime.getSeconds());
				System.out.println(dateTime.getData());
				System.out.println(dateTime);
			}
		});
		dateTime.addTouchListener(new TouchListener() {
			public void touch(TouchEvent arg0) {
				System.out.println(dateTime.getYear());
				System.out.println(dateTime.getMonth());
				System.out.println(dateTime.getDay());
				System.out.println(dateTime.getHours());
				System.out.println(dateTime.getMinutes());
				System.out.println(dateTime.getSeconds());
			}
		});
		dateTime.setBounds(45, 49, 316, 232);
		//cd.open(0, 0);
		
	}

}
