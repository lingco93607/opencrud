package com.z4.zhazha.forum.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.z4.zhazha.forum.annotation.Autocode;


/**
 * @author 爱育黎拨力八达
 * 
 *         用于自动从pojo包中读取类生成增删改查的所有文件 注意pojo类名不能写成java,或js的关键字，否则会出问题
 */
public class AutoCode {

	private final static String packagename = "com.z4.zhazha.forum";
	private final static String baseFolder = "src/test/java/com/z4/zhazha/forum";
	private final static String pojoFolder = "pojo";
	private final static String folderName = "auth";

	public static void changeIndexFile(String indexDesktopIconString,
			String indexDesktopListString) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							baseFolder + "/test/index.temp"))));

			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				tmp = tmp.replace("#indexDesktopIconString#",
						indexDesktopIconString);
				tmp = tmp.replace("#indexDesktopListString#",
						indexDesktopListString);
				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating index.html");
			PrintWriter printWriter = new PrintWriter(
					"src/main/webapp/index.html");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeDaoImplFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							"src/test/java/com/z4/zhazha/forum/test/JpaImpl.temp"))));

			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				tmp = tmp.replaceAll("#packagename#", packageName);
				tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#folderName#", "");
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating daoimpl:" + filename
					+ "DaoJpaImpl.java");
			PrintWriter printWriter = new PrintWriter(
					"src/main/java/com/z4/zhazha/forum/dao/impl/" + filename
							+ "DaoJpaImpl.java");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeRestImplFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							baseFolder + "/test/RestService.temp"))));

			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				tmp = tmp.replaceAll("#packagename#", packageName);
				tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#folderName#", "");
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating daoimpl:" + filename
					+ "RestService.java");
			PrintWriter printWriter = new PrintWriter(
					"src/main/java/com/z4/zhazha/forum/rest/" + filename
							+ "RestService.java");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeServiceImplFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							baseFolder + "/test/ServiceImpl.temp"))));

			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				tmp = tmp.replaceAll("#packagename#", packageName);
				tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#folderName#", "");
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating service:" + filename
					+ "ServiceImpl.java");
			PrintWriter printWriter = new PrintWriter(
					"src/main/java/com/z4/zhazha/forum/service/impl/" + filename
							+ "ServiceImpl.java");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeControllerFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							baseFolder + "/test/Controller.temp"))));

			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				tmp = tmp.replaceAll("#packagename#", packageName);
				tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#folderName#", "");
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating service:" + filename
					+ "ServiceImpl.java");
			PrintWriter printWriter = new PrintWriter(
					"src/main/java/com/z4/zhazha/forum/controller/" + filename
							+ "Controller.java");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeJSFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							baseFolder + "/test/js.temp"))));
			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				Map<String, String> fields = getAllFields(getPojoObject(
						packageName + ".pojo", filename));
				Iterator<Map.Entry<String, String>> itor = fields.entrySet()
						.iterator();
				String jsFieldString = "";
				String jsGridColumnString = "";
				String jsValidationString = "";

				int i = 0;
				while (itor.hasNext()) {
					Map.Entry<String, String> entry = itor.next();
					String n = entry.getKey();
					String c = entry.getValue().toLowerCase();
					if (0 == i) {
						jsFieldString = "{name : '" + n + "',type : '" + c
								+ "'}";
						jsGridColumnString = "{text : '"
								+ filename.toLowerCase() + " " + n
								+ "',datafield : '" + n + "',width : '20%'}";
						jsValidationString = "{input : '#"
								+ n
								+ "',message : '"
								+ n
								+ "不能为空!',action : 'keyup, blur',rule : 'required'}";
					} else {
						jsFieldString = jsFieldString + "," + "{name : '" + n
								+ "',type : '" + c + "'}";
						jsGridColumnString = jsGridColumnString + ","
								+ "{text : '" + filename.toLowerCase() + " "
								+ n + "',datafield : '" + n
								+ "',width : '20%'}";
						jsValidationString = jsValidationString
								+ ","
								+ "{input : '#"
								+ n
								+ "',message : '"
								+ n
								+ "不能为空!',action : 'keyup, blur',rule : 'required'}";
					}
					i++;
				}
				tmp = tmp.replace("#jsFieldString#", jsFieldString);
				tmp = tmp.replace("#jsGridColumnString#", jsGridColumnString);
				tmp = tmp.replace("#jsValidationString#", jsValidationString);
				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating js file:" + filename + ".js");

			createFolder("src/main/webapp/javascript/" + filename.toLowerCase());
			PrintWriter printWriter = new PrintWriter(
					"src/main/webapp/javascript/" + filename.toLowerCase()
							+ "/" + filename.toLowerCase() + ".js");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeInfoJSFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							"src/test/java/com/z4/zhazha/forum/test/info_js.temp"))));
			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				// tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				Map<String, String> fields = getAllFields(getPojoObject(
						packageName + ".pojo", filename));
				Iterator<Map.Entry<String, String>> itor = fields.entrySet()
						.iterator();
				String jsFieldString = "";
				String jsGridColumnString = "";
				String jsValidationString = "";

				int i = 0;
				while (itor.hasNext()) {
					Map.Entry<String, String> entry = itor.next();
					String n = entry.getKey();
					String c = entry.getValue().toLowerCase();
					if (0 == i) {
						jsFieldString = "{name : '" + n + "',type : '" + c
								+ "'}";
						jsGridColumnString = "{text : '"
								+ filename.toLowerCase() + " " + n
								+ "',datafield : '" + n + "',width : '20%'}";
						jsValidationString = "{input : '#"
								+ n
								+ "',message : '"
								+ n
								+ "不能为空!',action : 'keyup, blur',rule : 'required'}";
					} else {
						jsFieldString = jsFieldString + "," + "{name : '" + n
								+ "',type : '" + c + "'}";
						jsGridColumnString = jsGridColumnString + ","
								+ "{text : '" + filename.toLowerCase() + " "
								+ n + "',datafield : '" + n
								+ "',width : '20%'}";
						jsValidationString = jsValidationString
								+ ","
								+ "{input : '#"
								+ n
								+ "',message : '"
								+ n
								+ "不能为空!',action : 'keyup, blur',rule : 'required'}";
					}
					i++;
				}
				tmp = tmp.replace("#jsFieldString#", jsFieldString);
				tmp = tmp.replace("#jsGridColumnString#", jsGridColumnString);
				tmp = tmp.replace("#jsValidationString#", jsValidationString);
				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();
			System.out.println("creating js file:" + filename + "_info.js");
			PrintWriter printWriter = new PrintWriter(
					"src/main/webapp/javascript/" + filename.toLowerCase()
							+ "/" + filename.toLowerCase() + "_info.js");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeJSPFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							baseFolder + "/test/jsp.temp"))));
			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				Map<String, String> fields = getAllFields(getPojoObject(
						packageName + ".pojo", filename));
				Iterator<Map.Entry<String, String>> itor = fields.entrySet()
						.iterator();

				String jspRowName = "";

				while (itor.hasNext()) {
					Map.Entry<String, String> entry = itor.next();
					String n = entry.getKey();
					if (n.trim().equalsIgnoreCase("id")) {
						jspRowName = jspRowName
								+ "<tr>\n<td align='right'><b style='color:red;'></b>"
								+ n
								+ ":</td>\n<td align='right'><input id=\""
								+ n
								+ "\" name=\""
								+ n
								+ "\" class=\"rolestext\" type=\"text\" /></td></tr>";
					} else {
						jspRowName = jspRowName
								+ "<tr>\n<td align='right'><b style='color:red;'></b>"
								+ n
								+ ":</td>\n<td align='right'><input id=\""
								+ n
								+ "\" name=\""
								+ n
								+ "\" class=\"rolestext\" type=\"text\" /></td></tr>";
					}
				}
				tmp = tmp.replace("#jspRowName#", jspRowName);

				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating jsp file:" + filename + ".jsp");
			createFolder("src/main/webapp/jsp/" + filename.toLowerCase());
			PrintWriter printWriter = new PrintWriter("src/main/webapp/jsp/"
					+ filename.toLowerCase() + "/" + filename.toLowerCase()
					+ ".jsp");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeInfoJSPFile(String packageName, String filename) {
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							"src/test/java/com/z4/zhazha/forum/test/info_jsp.temp"))));
			StringBuffer strBuf = new StringBuffer();
			for (String tmp = null; (tmp = bufReader.readLine()) != null; tmp = null) {
				// tmp = tmp.replaceAll("#classname#", filename);
				tmp = tmp.replaceAll("#lowername#", filename.toLowerCase());
				Map<String, String> fields = getAllFields(getPojoObject(
						packageName + ".pojo", filename));
				Iterator<Map.Entry<String, String>> itor = fields.entrySet()
						.iterator();

				String jspRowName = "";

				while (itor.hasNext()) {
					Map.Entry<String, String> entry = itor.next();
					String n = entry.getKey();
					if (n.trim().equalsIgnoreCase("id")) {
						jspRowName = jspRowName
								+ "<tr>\n<td align='right'><b style='color:red;'></b>"
								+ n
								+ ":</td>\n<td align='right'><input id=\""
								+ n
								+ "\" name=\""
								+ n
								+ "\" value=\"${params."
								+ n
								+ "}"
								+ "\" class=\"rolestext\" type=\"text\" /></td></tr>";
					} else {
						jspRowName = jspRowName
								+ "<tr>\n<td align='right'><b style='color:red;'></b>"
								+ n
								+ ":</td>\n<td align='right'><input "
								+ "id=\""
								+ n
								+ "\" name=\""
								+ n
								+ "\" value=\"${params."
								+ n
								+ "}"
								+ "\" class=\"rolestext\" type=\"text\" /></td></tr>";
					}
				}
				tmp = tmp.replace("#jspRowName#", jspRowName);

				strBuf.append(tmp);
				strBuf.append(System.getProperty("line.separator"));
			}
			bufReader.close();

			System.out.println("creating jsp file:" + filename + "_info.jsp");
			PrintWriter printWriter = new PrintWriter("src/main/webapp/jsp/"
					+ filename.toLowerCase() + "/" + filename.toLowerCase()
					+ "_info.jsp");
			printWriter.write(strBuf.toString().toCharArray());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getFiles(File file) {
		if (!file.isDirectory()) {
			return null;
		} else {
			ArrayList<String> fileList = new ArrayList<String>();
			File[] files = file.listFiles();
			for (File f : files) {
				fileList.add(f.getName());
				System.out.println("find pojo :" + f.getName());
			}
			return fileList;
		}
	}

	public static void autoCodeFiles(String packageName) {
		System.out.println(System.getProperty("user.dir"));
		List<String> l = getFiles(new File("src/main/java/com/z4/zhazha/forum/pojo"));
		Iterator itr = l.iterator();
		String indexDesktopIconString = "";
		String indexDesktopListString = "";
		int i = 0;
		while (itr.hasNext()) {
			String s = (String) itr.next();

			if (s.contains(".java")) {
				s = s.replace(".java", "");

				Object f = getPojoObject(packageName + ".pojo", s);
				Annotation[] as = f.getClass().getAnnotations();
				if (f.getClass().isAnnotationPresent(Autocode.class)) {
					//changeJSFile(packageName, s);
					//changeJSPFile(packageName, s);
					//changeInfoJSFile(packageName, s);
					//changeJSPFile(packageName, s);
					changeDaoImplFile(packageName, s);
					changeServiceImplFile(packageName, s);
					changeControllerFile(packageName, s);
					changeRestImplFile(packageName, s);

					if (0 == i) {
						indexDesktopIconString = "'" + s.toLowerCase()
								+ "':{'title':'" + s.toLowerCase()
								+ " management','url':'./action/"
								+ s.toLowerCase()
								+ "/index','winWidth':950,'winHeight':500}";
					} else {
						indexDesktopIconString = indexDesktopIconString + ","
								+ "'" + s.toLowerCase() + "':{'title':'"
								+ s.toLowerCase()
								+ " management','url':'./action/"
								+ s.toLowerCase()
								+ "/index','winWidth':950,'winHeight':500}";
					}
					i++;
					indexDesktopListString = indexDesktopListString
							+ System.getProperty("line.separator")
							+ "<li class=\"desktop_icon\" id=\""
							+ s.toLowerCase()
							+ "\"><span class=\"icon\"><img src=\"icon/icon10.png\"/></span><div class=\"text\">"
							+ s.toLowerCase() + " management<s></s></div></li>";
				}

			}

		}
		changeIndexFile(indexDesktopIconString, indexDesktopListString);
	}

	/**
	 * get the object from the class name which will be used later
	 */
	public static Object getPojoObject(String packageName, String name) {
		Class clazz;
		try {
			clazz = Class.forName(packageName + "." + name);
			Object object = clazz.newInstance();
			return object;
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @param get
	 *            all the variables from the class which will be used to
	 *            generate the js and jsp code
	 * @return
	 */
	public static Map<String, String> getAllFields(Object f) {
		// 获取f对象对应类中的所有属性域
		Field[] fields = f.getClass().getDeclaredFields();
		Map<String, String> attrs = new HashMap<String, String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			// 对于每个属性，获取属性名
			String varName = fields[i].getName();
			Class t = fields[i].getType();
			t.getClass();

			System.out.println(fields[i].getType().getName());
			System.out.println(fields[i].getType().getSimpleName());
			if (t == int.class || t == String.class || t == float.class) {

				String[] temp = t.toString().split("\\.");
				attrs.put(varName, temp[temp.length - 1]);
				// System.out.println(varName + " is " + t.toString());
			} else if (t.getName().contains(packagename + "." + pojoFolder)) {
				System.out.println("in base " + t.getName());
			}
			// get the T type name if the field is set
			else if (t.isAssignableFrom(List.class)
					|| t.isAssignableFrom(Set.class)) {
				Type ft = fields[i].getGenericType();
				if (null == ft)
					continue;
				if (ft instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) ft;
					Class genericClazz = (Class) pt.getActualTypeArguments()[0];
					String clazzName = genericClazz.getName();
					if (clazzName.contains(packagename + ".pojo"))
						System.out.println("this object is in base "
								+ genericClazz.getName());
				}
			}

		}
		return attrs;
	}

	public static List<String> setAllComponentsName(Object f) {
		// 获取f对象对应类中的所有属性域
		Field[] fields = f.getClass().getDeclaredFields();
		List<String> attrs = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			// 对于每个属性，获取属性名
			String varName = fields[i].getName();
			Object t = fields[i].getType();
			t.getClass();
			if (t == int.class || t == String.class || t == float.class) {
				attrs.add(varName);
				// System.out.println(varName + " is " + t.toString());
			}
			// try {
			// // 获取原来的访问控制权限
			// boolean accessFlag = fields[i].isAccessible();
			// // 修改访问控制权限
			// fields[i].setAccessible(true);
			// // 获取在对象f中属性fields[i]对应的对象中的变量
			// Object o = fields[i].get(f);
			// System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
			// // 恢复访问控制权限
			// fields[i].setAccessible(accessFlag);
			// } catch (IllegalArgumentException ex) {
			// ex.printStackTrace();
			// } catch (IllegalAccessException ex) {
			// ex.printStackTrace();
			// }
		}
		return attrs;
	}

	public static void getAllClasses(String packageName) {
		System.out.println(System.getProperty("user.dir"));
		List<String> l = getFiles(new File("src/main/java/com/z4/zhazha/forum/pojo"));
		Iterator itr = l.iterator();
		while (itr.hasNext()) {
			String s = (String) itr.next();
			System.out.println("Now printing: " + s);
			System.out.println("------------------------");
			s = s.replace(".java", "");
			Class clazz;
			try {
				clazz = Class.forName(packageName + "." + s);
				Object object = clazz.newInstance();
				setAllComponentsName(object);
				System.out
						.println("***********************************************************");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void createFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("error creating folder");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		autoCodeFiles(packagename);
		// getAllClasses("com.z4.zhazha/forum.pojo");
		// setAllComponentsName(new Book());
	}
}
