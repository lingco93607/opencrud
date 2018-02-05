package com.z4.zhazha.forum.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class FileUtil {

	public static List<String> getFiles(File file) {
		if (!file.isDirectory()) {
			return null;
		} else {
			ArrayList<String> fileList = new ArrayList<String>();
			File[] files = file.listFiles();
			String fname;
			for (File f : files) {
				fname = f.getName();
				if (fname.length() >= 1)
					fileList.add(fname);
				// System.out.println("find pojo :" + f.getName());
			}
			return fileList;
		}
	}
	
	public static boolean dExist(String directoryName, boolean isLinux) {
		File file = new File(directoryName);
		if (file.exists() || file.isDirectory())
			return true;
		else {
			file.mkdir();
			if (isLinux) {
				try {
					Runtime.getRuntime().exec("chmod 777 " + directoryName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return true;
		}
	}
	
	public static boolean copyFile(String srcFileName, String destFileName, boolean overlay, boolean isLinux) {
		String MESSAGE = "";
		File srcFile = new File(srcFileName);

		// 判断源文件是否存在
		if (!srcFile.exists()) {
			MESSAGE = "源文件：" + srcFileName + "不存在！";
			JOptionPane.showMessageDialog(null, MESSAGE);
			return false;
		} else if (!srcFile.isFile()) {
			MESSAGE = "复制文件失败，源文件：" + srcFileName + "不是一个文件！";
			JOptionPane.showMessageDialog(null, MESSAGE);
			return false;
		}

		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if (overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				new File(destFileName).delete();
			}
		} else {
			// 如果目标文件所在目录不存在，则创建目录
			if (!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在 ,创建文件夹，并判断操作系统
				boolean mkpf = destFile.getParentFile().mkdirs();
				if (mkpf) {
					if (isLinux) {
						try {
							Runtime.getRuntime().exec("chmod 777 " + destFile.getParentFile());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					// 复制文件失败：创建目标文件所在目录失败
					return false;
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param filePath    需创建文件的父级目录
	 * @param fileName    需创建的文件名称
	 * @param isLinux     系统是否为linux系统
	 * @return				 创建成功则返回所创建的文件File
	 * @throws IOException
	 */
	public static File mkFile(String filePath, String fileName, boolean isLinux) throws IOException {
		File f = new File(filePath + File.separator + fileName);
		if (!f.getParentFile().exists()) {
			boolean mkpf = f.getParentFile().mkdirs();
			if (mkpf) {
				if (isLinux) {
					Runtime.getRuntime().exec("chmod 777 " + f.getParentFile());
				}
			}
		}
		f.createNewFile();
		return f;
	}
	
	/**
     * 文件重命名
     */
    public static String renameFileName(String fileName) {
        String formatDate = new SimpleDateFormat("yyMMddHHmmss").format(new Date()); // 当前时间字符串
        int random = new Random().nextInt(10000);
        String extension = fileName.substring(fileName.lastIndexOf(".")); // 文件后缀
 
        return formatDate + random + extension;
    }
}
