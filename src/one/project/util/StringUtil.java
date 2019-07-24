package one.project.util;

public class StringUtil {

	/**
	 * 判断是否为空
	 * @param strs
	 * @return
	 */
	public static boolean isNull(String...strs) {
		if (strs == null || strs.length <= 0) {
			return true;
		}
		
		for (String str : strs) {
			if (str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否为空
	 * @param strs
	 * @return  为空的参数的编号
	 */
	public static int Null(String...strs) {
		int len = strs.length;
		if (strs == null || len <= 0) {
			return -1;
		}
		
		for (int i = 0;i < len; i++) {
			if (strs[i] == null || "".equals(strs[i])) { // 参数为空
				return i+1;
			}
		}

		// 没有为空的参数
		return 0;
	}
	
	/**
	 * 判断两次输入密码是否一样
	 * @param p
	 * @param p1
	 * @return  一样返回true,不一样返回false
	 */
	public static boolean equ(String p, String p1) {
		if (!p.equals(p1)) {
			return false;
		} else {
			return true;
		}
	}
}
