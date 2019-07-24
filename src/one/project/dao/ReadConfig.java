package one.project.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig extends Properties{

	private static ReadConfig instance = new ReadConfig();
	
	/**
	 * 读取文件
	 */
	private ReadConfig() {
		InputStream is = null;
		try {
			is = ReadConfig.class.getClassLoader().getResourceAsStream("db.properties");
			this.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ReadConfig getInstance() {
		return instance;
	}
}
