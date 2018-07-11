/**
 * @Copyright: 2010 杭州海康威视系统技术有限公司
 * @address: http://www.hikvision.com
 * @ProjectName: CMS基线平台
 * @Description: 公司内部的基础平台
 */
package com.common.validator.SpecialLevel.constraints;


/**
 * 
 * <p>
 * 基本检测处理工具类
 * </p>
 * @author 
 * @version V1.0
 */
public class ValidatorUtils {
	
	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * @param String s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int getCharLength(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}
	
	/**
	 * 判断是否是中文
	 * 
	 * @param c
	 * @return
	 */
	private static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0?true:false;
	}
	
	public static boolean ipCheck(String text) {
	    if (text != null && !text.isEmpty()) {
	      // 定义正则表达式
	      String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
	           +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
	           +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
	           +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
	      // 判断ip地址是否与正则表达式匹配
	      if (text.matches(regex)) {
	        // 返回判断信息
	        return true;
	      } else {
	        // 返回判断信息
	        return false;
	      }
	    }
	    return false;
	}

	//端口号验证 1 ~ 65535
	public static boolean portCheck(Integer port){
		if(null != port && port.intValue() >= 1 && port.intValue() <= 65535){
			return true;
		}
		return false;
	}
}
