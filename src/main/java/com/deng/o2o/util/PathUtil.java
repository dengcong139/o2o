package com.deng.o2o.util;

public class PathUtil {
	private static String seperator =System.getProperty("file.separator");
	//该方法是返回项目图片的根路径
	public  static String getImgBasePath() {
		String  os=System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")) {
			basePath="C:\\Users\\Lenovo\\Pictures\\imgs";
		}else {
			basePath ="/";
		}
		basePath=basePath.replace("/", seperator);
		return basePath;
	}
	
	//根据不同的业务需求返回项目图片的子路径
	public static String getShopImagePath(long shopId) {
		String imagePath ="/upload/item/shop/" + shopId +"/";
		return imagePath.replace("/",seperator);
	}
}
