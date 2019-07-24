package one.project.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import one.project.dao.ThingDao;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;

public class SeatUI extends Composite {
	private Composite composite = null;
	private List<Seat> seats = new ArrayList<Seat>();
	private Label label_tid = null;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SeatUI(Composite parent, int style, Label label_tid) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		this.label_tid = label_tid;
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		composite = new Composite(scrolledComposite, SWT.NONE);
		createSeat(parent);
		
//		System.out.println(parent.getSize().x+" "+parent.getSize().x);
		
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		// 当面板发生改变时
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				int x = 0,y = 10, index = 0;
				for (Seat seat : seats) {
					if (x + 280 > parent.getSize().x) { // 说明这一行已经放满了，则需要换行
						y += 180;
						index = 0;
					}

					x = index * 140 + 10;
					++index;
					seat.reSeat(x, y);
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void createSeat(Composite parent){
		// 循环生成座位
		ThingDao thingDao = new ThingDao();
		List<Map<String, Object>> list = thingDao.showTable();
		Seat seat = null;
		int x = 0, y = 10, index = 0;
		for(Map<String, Object> map : list){
			if ( x + 280 >  parent.getSize().x) {  // 说明这一行已经放满了，则需要换行
				y += 180;
				index = 0;
			}
			
			x = index * 140 + 10;
			++ index;
			seat = new Seat(composite, x, y, map);
			seats.add(seat);
		}
	}
	
	class Seat {
		private Composite composite = null;
		private Label label = null;
		private int x, y;
		private Map<String, Object> map = null;

		public Seat(Composite composite, int x, int y, Map<String, Object> map) {
			this.x = x;
			this.y = y;
			this.composite = composite;
			this.map = map;

			createContent();
		}

		public void createContent() {
			label = new Label(composite, SWT.BORDER);
			label.setBounds(x, y, 100, 140);
			
			String state = String.valueOf(map.get("t_state"));
			String table_state = null;
			if ("0".equals(state)) {  // 座位已经被预约了
				table_state = "预约";
				label.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			} else if ("1".equals(state)) {  // 座位正在被使用
				table_state = "占用";
				label.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
			} else if ("2".equals(state)) {  // 座位可以使用
				table_state = "空闲";
				label.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				label.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseUp(MouseEvent e) {
						label_tid.setText(String.valueOf(map.get("t_id")));
					}
				});
			}
			
			String text = "      " + String.valueOf(map.get("t_type")) + "\n\n\n" 
					+ "          " + String.valueOf(map.get("t_id")) + "\n\n\n"
					+ "        " + table_state;
			label.setText(text);
//			label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			
//			Label label_type = new Label(composite_1, SWT.CENTER);
//			label_type.setBounds(0, 0, 100, 20);
//			label_type.setText(String.valueOf(map.get("t_type")));
//
//			Label label_tid = new Label(composite_1, SWT.CENTER);
//			label_tid.setBounds(0, 60, 100, 20);
//			label_tid.setText(String.valueOf(map.get("t_id")));
//
//			Label label_state = new Label(composite_1, SWT.CENTER);
//			label_state.setBounds(0, 120, 100, 20);
//			label_state.setText(table_state);

		}
		
		/**
		 * 改变位置
		 * @param x
		 * @param y
		 */
		public void reSeat(int x, int y) {
			label.setBounds(x, y, 100, 140);
		}

	}
}
