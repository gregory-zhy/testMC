package cn.no7player.utils.signature;

import java.security.MessageDigest;

/**
 * SHA1 和 MD5 加密算法
 *
 * @author Michael
 *
 */
public class EncoderHandler {

	private static final String ALGORITHM = "MD5";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * encode string
	 *
	 * @param algorithm	加密算法
	 * @param str	加密对象
	 * @return String	16进制
	 */
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			//指定加密算法类型
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] bytes = messageDigest.digest();
			//转换为16进制
			return getFormattedText(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 把密文转换成十六进制的字符串形式.
	 *
	 * @param bytes	密文.
	 * @return 转换后的16进制密文.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		System.out.println("111111 MD5  :"
				+ EncoderHandler.encode("MD5", "111111"));
		System.out.println("111111 SHA1 :"
				+ EncoderHandler.encode("SHA1", "111111"));
	}
    
	
}
