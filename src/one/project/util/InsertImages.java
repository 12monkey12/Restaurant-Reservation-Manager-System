package one.project.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import one.project.dao.DBHelper;

public class InsertImages {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InsertImages insertImages = new InsertImages();
		insertImages.update();
	}
	public void update(){
		String path = "C:/Users/22901/Desktop/新建文件夹 (2)/湘菜/腊肉炒猪血丸子38.png";
		byte[] photo = null;
		String name = "腊肉炒猪血丸子";
		
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(path);
			photo = new byte[fis.available()];
			fis.read(photo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		String sql = "update dishes set d_photo = ? where d_name = ?";
		DBHelper db = new DBHelper();
		int result = db.update(sql, photo, name);
		
		if (result > 0) {
			System.out.println("插入成功！");
		} else System.out.println("插入失败！");
	}

}
