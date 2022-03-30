package com.lybank.common.bank;

import java.util.Random;

import org.springframework.util.StringUtils;

public class RandomUtil {
	public static String smallLetter = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
	public static String allNumber = "0,1,2,3,4,5,6,7,8,9";
	// private static long tick = (int)DateTime.Now.Ticks;
	private static long tick = getTick();

	public static Long getTick() {
		long milli = System.currentTimeMillis() + 8 * 3600 * 1000;
		long ticks = (milli * 10000) + 621355968000000000L;
		return ticks;
	}

	public static int random(int startValue, int endValue) {
		
		Random rand = new Random((int) ((tick & 0xffffffff) | (tick >> 32)));
		tick = rand.nextLong();
		return startValue+rand.nextInt(endValue);
	}

	/// <summary>
	/// 指定长度的随机字符串
	/// </summary>
	public static String randomString(int codeCount, String allChar) {
		if (StringUtils.isEmpty(codeCount))
			codeCount = 16;
		if (StringUtils.isEmpty(allChar))
			allChar = "1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,i,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
		String[] allCharArray = allChar.split(",");
		String RandomCode = "";
		int temp = -1;
		Random rand = new Random((int) ((tick & 0xffffffff) | (tick >> 32)));
		tick = rand.nextLong();
		for (int i = 0; i < codeCount; i++) {
			if (temp != -1) {
				rand = new Random((int) ((tick & 0xffffffff) | (tick >> 32)));
				tick = rand.nextLong();
			}
			int t = rand.nextInt(allCharArray.length - 1);

			while (temp == t) {
				t = rand.nextInt(allCharArray.length - 1);
				tick = rand.nextLong();
			}

			temp = t;
			RandomCode += allCharArray[t];
		}
		return RandomCode;
	}

	/// <summary>
	/// 获取随机MAC地址
	/// </summary>
	public static String randomMacAdder() {
		String[] adrArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		int[] indexArray = { 0, 2, 4, 6, 8, 10, 12, 14 };
		String retStr ="";

		for (int i = 0; i < 12; i++) {
			int index = 0;

			if (i != 1)
				index = random(0, 16);
			else
				// 第二位只能是偶数
				index = indexArray[random(0, 8)];

			if (i % 2 == 1 && i != 11)
				retStr = retStr + adrArray[index] + ":";
			else
				retStr = retStr + adrArray[index];

		}
		return retStr;
	}

	/// <summary>
	/// 指定长度的随机字符串
	/// </summary>
	public static String randomStrWithCount(int count) {
		String allStr = "0123456789abcdefghijklmnopqrstuvwsyz";

		// char[] allCharAry = allStr.ToCharArray();
		char[] allCharAry = allStr.toCharArray();
		char[] rltCharAry = new char[count];

		int temp = -1;
		int t = -1;

		Random rand = new Random((int) ((tick & 0xffffffff) | (tick >> 32)));
		tick = rand.nextLong();
		for (int i = 0; i < count; i++) {
			t = rand.nextInt(allCharAry.length);
			while (temp == t) {
				t = rand.nextInt(allCharAry.length);
			}
			temp = t;
			rltCharAry[i] = allCharAry[t];
		}
		return new String(rltCharAry);
	}

	/// <summary>
	/// 指定长度的随机字符串 16进制字符串
	/// </summary>
	public static String randomHexStrWithCount(int count) {
		String allStr = "0123456789ABCDEF";

		char[] allCharAry = allStr.toCharArray();
		char[] rltCharAry = new char[count];

		int temp = -1;
		int t = -1;

		Random rand = new Random((int) ((tick & 0xffffffff) | (tick >> 32)));

		tick = rand.nextLong();
		for (int i = 0; i < count; i++) {
			t = rand.nextInt(allCharAry.length);
			while (temp == t) {
				t = rand.nextInt(allCharAry.length);
			}
			temp = t;
			rltCharAry[i] = allCharAry[t];
		}
		return new String(rltCharAry);
	}
}