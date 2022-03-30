package com.lybank.common.bank;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * Created by acer on 2017/6/7.
 */
public class SHAUtils {

	public static final String SIGN_ALGORITHMS = "SHA-1";

	/**
	 * SHA1 安全加密算法
	 */
	public static String sign(String content, String inputCharset) {
		// 获取信息摘要 - 参数字典排序后字符串
		try {
			// 指定sha1算法
			MessageDigest digest = MessageDigest.getInstance(SIGN_ALGORITHMS);
			digest.update(content.getBytes(inputCharset));
			// 获取字节数组
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getParams(Map<String, Object> map, String appSecret) {
		ArrayList<String> keys = new ArrayList<String>();
		for (String m : map.keySet()) {
			keys.add(m);
		}
		Collections.sort(keys);
		StringBuffer sb = new StringBuffer();
		for (String k : keys) {
			sb.append(k).append("=").append(map.get(k)).append("&");
		}
		String pwd = sb.toString().substring(0, sb.length() - 1);
		System.out.println("签名前字符串：" + pwd);
		return SHAUtils.hamcsha1(pwd.getBytes(), appSecret).toUpperCase();
	}

	// 二行制转字符串
	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static byte[] hexStr2Bytes(String src) {
		/* 对输入值进行规范化整理 */
		src = src.trim().replace(" ", "").toUpperCase(Locale.US);
		// 处理值初始化
		int m = 0, n = 0;
		int iLen = src.length() / 2; // 计算长度
		byte[] ret = new byte[iLen]; // 分配存储空间

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	public static String hamcsha1(byte[] data, String key) {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(hexStr2Bytes(key), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			return byte2hex(mac.doFinal(data));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * sign256 SHA256加密
	 * 
	 * @param signData
	 * @return
	 */
	public static String sign256(String signData) {
		MessageDigest messageDigest;
		String encdeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash = messageDigest.digest(signData.getBytes("UTF-8"));
			encdeStr = Hex.encodeHexString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encdeStr;
	}

	public static void main(String[] args) {
		String ss = "1409303327346283432a238sk28dj3db283438234c23j8s3gjwdw";
		String rr = sign(ss, "UTF-8");
		System.out.println("rr-----" + rr);
	}
}
