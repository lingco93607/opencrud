package com.z4.zhazha.forum.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
	private static File file = null;

	/**
	 * 读取图像的二进制流
	 * 
	 * @param infile
	 * @return
	 */
	public static FileInputStream getByteImage(String infile) {
		FileInputStream inputImage = null;
		file = new File(infile);
		try {
			inputImage = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return inputImage;
	}

	/**
	 * 输出图片
	 * 
	 * @param inputStream
	 * @param path
	 */
	public static void readBlob(FileInputStream inputStream, String path) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buf)) != -1) {
				fileOutputStream.write(buf, 0, len);// 写
			}
			inputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readBlob(InputStream inputStream, String path) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buf)) != -1) {
				fileOutputStream.write(buf, 0, len);// 写
			}
			inputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readBlob(byte[] inputStream, String path) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int len = inputStream.length;
			fileOutputStream.write(inputStream);
			
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static InputStream byteToInputStream(byte[] b)
	{
		InputStream sbs = new ByteArrayInputStream(b); 
		return  sbs ; 
	}
}