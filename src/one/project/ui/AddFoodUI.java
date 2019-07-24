package one.project.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import one.project.dao.ThingDao;
import one.project.util.StringUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class AddFoodUI extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private String filePath = null; // 文件路径

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddFoodUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite, SWT.NONE);
		group.setText("增加菜品");
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBounds(30, 68, 76, 20);
		lblNewLabel.setText("菜品名称：");
		
		Label lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setBounds(349, 68, 76, 20);
		lblNewLabel_1.setText("菜系编号：");
		
		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBounds(668, 73, 45, 20);
		lblNewLabel_2.setText("价格：");
		
		Label lblNewLabel_3 = new Label(group, SWT.NONE);
		lblNewLabel_3.setBounds(30, 209, 45, 20);
		lblNewLabel_3.setText("描述：");
		
		Label lblNewLabel_4 = new Label(group, SWT.NONE);
		lblNewLabel_4.setBounds(668, 209, 45, 20);
		lblNewLabel_4.setText("图片：");
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(452, 589, 224, 56);
		btnNewButton.setText("确定添加");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(122, 65, 131, 26);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(772, 70, 125, 26);
		
		Combo combo = new Combo(group, SWT.READ_ONLY);
		combo.setBounds(442, 65, 131, 28);
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setBounds(83, 268, 314, 243);
		
		Canvas canvas = new Canvas(group, SWT.BORDER);
		canvas.setBounds(587, 268, 258, 254);
		
		// 设置combo的值
		ThingDao thingDao = new ThingDao();
		thingDao.addCombo2(combo);

		btnNewButton.addSelectionListener(new SelectionAdapter() { // 确定添加菜品按钮点击事件
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = text.getText().trim();
				String cid = combo.getText().split(" ")[0];
				String price = text_1.getText().trim();
				String mark = text_2.getText().trim();
				if (StringUtil.isNull(name)) {
					MessageDialog.openError(getShell(), "错误提示", "菜品名不能为空！");
					return;
				}
				if (StringUtil.isNull(cid)) {
					MessageDialog.openError(getShell(), "错误提示", "菜系编号不能为空！");
					return;
				}
				if (StringUtil.isNull(price)) {
					MessageDialog.openError(getShell(), "错误提示", "价格不能为空！");
					return;
				}
				
				byte[] photo = null;
				if (!StringUtil.isNull(filePath)) {
					File file = new File(filePath);
					if (file.exists() && file.isFile()) {
						FileInputStream fis = null;
						
						try {
							fis = new FileInputStream(filePath);
							photo = new byte[fis.available()];
							fis.read(photo);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							if (fis != null) {
								try {
									fis.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
				}
				
				// 将数据添加到数据库中
				int result = thingDao.addFood(name, cid, price, mark, photo);
				if (result > 0) {
					text.setText("");
					combo.deselectAll();
					text_1.setText("");
					text_2.setText("");
					canvas.setBackgroundImage(null);
					MessageDialog.openInformation(getShell(), "成功提示", "添加菜品成功！");
				} else {
					MessageDialog.openError(getShell(), "失败提示", "添加菜品失败！");
				}
				
			}
		});
		
		// 点击canvas是弹出选择文件窗口
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// 弹出文件选择框
				FileDialog fileDialog = new FileDialog(getShell());
				fileDialog.setText("选择图片");
				fileDialog.setFilterExtensions(new String[]{"*.gif", "*.png", "*.jpg"});
				filePath = fileDialog.open();
				
				if (filePath != null) { // 说明用户确实选择了图片
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(filePath);
						
						// 将图片显示在label中
						ImageData imageData = new ImageData(fis);
						imageData = imageData.scaledTo(canvas.getSize().x, canvas.getSize().y);
						Image image = new Image(Display.getDefault(), imageData);
						canvas.setBackgroundImage(image);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						if (fis != null) {
							try {
								fis.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
				}
				
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
