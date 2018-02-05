package com.z4.zhazha.forum.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author djz
 * @param
 * @date 
 * @version
 * @since
 * @throws
 * @return
 * @class
 * @extends
 * @type
 * @public
 * @private
 */

@Component
public class StringUtil {
	@Value("${app_zhazha_key}")
	static String app_zhazha_key = "2017_CETCOCEAN_ZHAZHA";
	
	public static String subString(String src, int num, String endffix)
	  {
	    if (src.length() >= num) {
	      return src.substring(0, num) + endffix;
	    }
	    return src;
	  }

	  public static String subString(String src, int num)
	  {
	    return subString(src, num, "...");
	  }

	  public static String subString(String src, int start, int end) {
	    if (src == null)
	      return "";
	    if (src.length() == 0)
	      return "";
	    if (start > end)
	      return "";
	    if (start > src.length())
	      return "";
	    if (end > src.length())
	      return "";
	    return src.substring(start, end);
	  }

	  public static boolean checkIsEmpty(String str)
	  {
	    if (str == null) {
	      return true;
	    }
	    return str.equals("");
	  }

	  public static String getSplitArr(String s, String splitchar, int num)
	  {
	    if (checkIsEmpty(s)) {
	      s = "";
	    }
	    String[] arr = s.split(splitchar);
	    if (num < arr.length) {
	      return arr[num];
	    }
	    return "-";
	  }

	  public static boolean isLetter(char c) {
	    int k = 128;
	    return c / k == 0;
	  }

	  public static int length(String s) {
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

	  public static String subStringE(String origin, int len, String c) {
	    if ((origin == null) || (origin.equals("")) || (len < 1))
	      return "";
	    byte[] strByte = new byte[len];
	    if (len > length(origin))
	      return origin + c;
	    try
	    {
	      System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
	      int count = 0;
	      for (int i = 0; i < len; i++) {
	        int value = strByte[i];
	        if (value < 0) {
	          count++;
	        }
	      }
	      if (count % 2 != 0) {
	        len++; len--; len = len == 1 ? len : len;
	      }
	      return new String(strByte, 0, len, "GBK") + c; } catch (UnsupportedEncodingException e) {
	    	  throw new RuntimeException(e);
	    }
	  }

	  public static String removeEndWith(String src, String endwith)
	  {
	    if (src.endsWith(endwith)) {
	      return src.substring(0, src.length() - endwith.length());
	    }
	    return "";
	  }

	  public static String getDefalutValue(String value, String defalutvalue) {
	    if (value == null) {
	      return defalutvalue;
	    }
	    if (value.equals("")) {
	      return defalutvalue;
	    }
	    return value;
	  }

	  public static Long getDefalutLongValue(Long value, Long defalutvalue) {
	    if (value == null) {
	      return defalutvalue;
	    }
	    return value;
	  }


	  public static boolean equals(String src, String[] des) {
	    for (int i = 0; i < des.length; i++) {
	      if (src.equals(des[i])) {
	        return true;
	      }
	    }
	    return false;
	  }

	  public static boolean isParseInt(String str) {
	    if (str == null) {
	      return false;
	    }
	    return str.trim().matches("\\d+");
	  }
	  
	  public static boolean isLegalPhone(String str) {
		String regExp = "^((13[0-9])|(15[^4])|(18[2,3,5-9])|(17[1-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	  }
	  
	  public static String randomCode(int length) {
		  String base = "0123456789";
		  Random random = new Random();
		  StringBuffer sb = new StringBuffer();
		  for (int i=0; i<length; i++) {
			  int num = random.nextInt(base.length());
			  sb.append(base.charAt(num));
		  }
		  return sb.toString();
	  }
	  
	  public static String createSign(SortedMap<String, String> packageParams) {
		  StringBuffer sb = new StringBuffer();
			Set es = packageParams.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String v = (String) entry.getValue();
				if (null != v && !"".equals(v) && !"sign".equals(k)
						&& !"key".equals(k)) {
					sb.append(k + "=" + v + "&");
				}
			}
			sb.append("key=" + app_zhazha_key);
			System.out.println(sb.toString());
			String sign = MD5Encode(sb.toString(), "UTF-8")
					.toUpperCase();
			return sign;
	  }
	  
	  public static String sign(String userName) {
		  //1，应从用户表中查找userName的用户user
		  //2，从token表中查找user.id的token
		  //3，若token为空，则new Token,存储到token表，返回tokenStr
			// 没有登陆
			String tokenStr = string2MD5("" + System.currentTimeMillis());
			return tokenStr;
			
			//4，若token不为空，则重新生成tokenStr，更新该token，返回tokenStr
		}
	  
	  public static String string2MD5(String inStr){  
	        MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            e.printStackTrace();  
	            return "";  
	        }  
	        char[] charArray = inStr.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  
	    }  
	  
	  public static String MD5Encode(String origin, String charsetname) {
			String resultString = null;
			try {
				resultString = new String(origin);
				MessageDigest md = MessageDigest.getInstance("MD5");
				if (charsetname == null || "".equals(charsetname))
					resultString = byteArrayToHexString(md.digest(resultString
							.getBytes()));
				else
					resultString = byteArrayToHexString(md.digest(resultString
							.getBytes(charsetname)));
			} catch (Exception exception) {
			}
			return resultString;
		}
	  
	  private static String byteArrayToHexString(byte b[]) {
			StringBuffer resultSb = new StringBuffer();
			for (int i = 0; i < b.length; i++)
				resultSb.append(byteToHexString(b[i]));

			return resultSb.toString();
		}

		private static String byteToHexString(byte b) {
			int n = b;
			if (n < 0)
				n += 256;
			int d1 = n / 16;
			int d2 = n % 16;
			return hexDigits[d1] + hexDigits[d2];
		}
		
		private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
