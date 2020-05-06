package com.junghuan.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字串 工具類別
 * 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	protected static final String[] ASCII_TABLE = { "!", "\"", "#", "$", "%", "&", "\'", "(", ")", "*", "+", ",", "-", ".", "/", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?", "@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", "\\", "]", "^", "_", "`", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "{", "|", "}", "~", " " };
	protected static final String[] BIG5_TABLE = { "！", "”", "＃", "＄", "％", "＆", "’", "（", "）", "＊", "＋", "，", "－", "．", "／", "０", "１", "２", "３", "４", "５", "６", "７", "８", "９", "：", "；", "＜", "＝", "＞", "？", "＠", "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ", "Ｚ", "〔", "＼", "〕", "︿", "＿", "｀", "ａ", "ｂ", "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ", "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ", "｛", "｜", "｝", "～", "　" };

	private static final Pattern numPattern = Pattern.compile("[0-9]*");

	public final static String DASH = "-";
	/** "%" */
	public final static String PERCENT_SIGN = "%";

	/**
	 * Replaces all occurences of a substring within a string with another string.
	 * 
	 * @param inString
	 *            String to examine
	 * @param oldPattern
	 *            String to replace
	 * @param newPattern
	 *            String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		// Pick up error conditions
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer(); // Output StringBuffer we'll build up
		int pos = 0; // Our position in the old string
		int index = inString.indexOf(oldPattern); // The index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos)); // Remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * 
	 * @param s
	 *            the String to tokenize
	 * @param delimiters
	 *            the delimiter characters, assembled as String
	 * @param trimTokens
	 *            trim the tokens via String.trim
	 * @param ignoreEmptyTokens
	 *            omit empty tokens from the result array
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim
	 */
	public static String[] tokenizeToStringArray(String s, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
		StringTokenizer st = new StringTokenizer(s, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!(ignoreEmptyTokens && token.length() == 0)) {
				tokens.add(token);
			}
		}
		return (String[]) tokens.toArray(new String[tokens.size()]);
	}

	/**
	 * Take a String which is a delimited list and convert it to a String array
	 * 
	 * @param s
	 *            String
	 * @param delimiter
	 *            delimiter. This will not be returned
	 * @return an array of the tokens in the list
	 */
	public static String[] delimitedListToStringArray(String s, String delimiter) {
		if (s == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { s };
		}

		List l = new LinkedList();
		int pos = 0;
		int delpos = 0;
		while ((delpos = s.indexOf(delimiter, pos)) != -1) {
			l.add(s.substring(pos, delpos));
			pos = delpos + delimiter.length();
		}
		if (pos <= s.length()) {
			// add rest of String
			l.add(s.substring(pos));
		}

		return (String[]) l.toArray(new String[l.size()]);
	}

	public static int subStringCount(String str, String substr) {
		if (str == null || substr == null) {
			return 0;
		}

		int i = 0;
		int pos = 0;
		int len = str.length();
		while (pos < len && (pos = str.indexOf(substr, pos)) > 0) {
			i++;
			pos++;
		}

		return i;
	}

	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/***
	 * 是否為整數數字
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		Matcher isNum = numPattern.matcher(str.trim());
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/***
	 * 根據傳入的charSet進行getByte轉換成new String
	 * 
	 * @param str
	 * @param charSetOld
	 * @param charSetNew
	 * @return
	 */
	public static String getNewString(String str, String charSetOld, String charSetNew) {
		try {
			if (isEmpty(charSetOld)) {
				return str;
			}
			else if (isEmpty(charSetNew)) {
				return new String(str.getBytes(charSetOld));
			}
			else {
				return new String(str.getBytes(charSetOld), charSetNew);
			}
		}
		catch (UnsupportedEncodingException e) {
			// throw e;
			return str;
		}
	}

	/**
	 * 將分號與等號分隔的字串轉為HashMap key與value以等號分隔，等號左邊為key，右邊為value 每組key+value以分號為結束 ex: Cust_Name=AAA;Current_Time=96/01/02 12:12;
	 * 
	 * @param String
	 * @return HashMap
	 */
	public static HashMap transStringToHashMap(String pstr) {
		StringTokenizer tokens = new StringTokenizer(pstr, ";");

		int arraySize = tokens.countTokens();
		HashMap hm = new HashMap();
		int i = 0;

		// logger.debug("arraySize = " + arraySize);

		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			int idx = token.indexOf("=");
			if (idx >= 0) {
				String key = token.substring(0, idx);
				String val = token.substring(idx + 1);
				hm.put(key, val);

				// logger.debug("token = " + token);
				// logger.debug("key = " + key + ", val = " + val);
			}
		}

		return hm;
	}

	/**
	 * 填充string到固定的長度
	 * <p>
	 * 
	 * <pre>
	 * StringUtil.pad(' ',true,null,5)     = "     "
	 * StringUtil.pad(' ',true,"test",5)   = " test"
	 * StringUtil.pad(' ',false,"test",5)  = "test "
	 * </pre>
	 * </p>
	 *
	 * @param c
	 *            填充的字符
	 * @param isHeader
	 *            if true 填充頭部 else 從尾部填充
	 * @param str
	 *            原始字串
	 * @param length
	 *            要返回的字串的長度
	 * @return 填充后的字串
	 * @author kayang
	 * @since 2009-8-20
	 */
	public static String pad(char c, boolean isHeader, String str, int length) {
		StringBuilder sbu = new StringBuilder();
		String curStr = str;
		if (curStr == null) {
			curStr = "";
		}
		for (int i = 0; i < length - curStr.length(); i++) {
			sbu.append(c);
		}
		if (isHeader) {
			return sbu.append(curStr).toString();
		}
		else {
			return new StringBuilder(curStr).append(sbu).toString();
		}
	}

	/**
	 * 將字串轉換為金額的表示方式
	 *
	 * <pre>
	 * StringUtils.getMoneyStr(null, 1) = ""
	 * StringUtils.getMoneyStr(" ", 2) = ""
	 * StringUtils.getMoneyStr("1234567.89", 2) = "1,234,567.89"
	 * StringUtils.getMoneyStr("1234567.00", 1) = "1,234,567.0";
	 * StringUtils.getMoneyStr("1234567.0100", 2) = "1,234,567.01";
	 * </pre>
	 *
	 * @param sMoney
	 *            待轉換之字串
	 * @param iScale
	 *            小數有效位數
	 * @return 金額表示型態的字串，永不為null
	 */
	public static String getMoneyStr(String sMoney, int iScale) {

		if (isBlank(sMoney)) {
			return "";
		}

		String tmpMoneyStr = sMoney.trim();

		StringBuilder sb = new StringBuilder();

		if (tmpMoneyStr.startsWith("+") || tmpMoneyStr.startsWith("-")) {
			String sSign = substring(tmpMoneyStr, 0, 1);
			// '-' 放回去, '+' 濾除
			sb.append("-".equals(sSign) ? sSign : "");
			tmpMoneyStr = substring(tmpMoneyStr, 1);
		}

		// 整數位的字串
		String sInt = "";

		// 小數位的字串
		String sDecimal = "";

		// 小數點
		String sDot = ".";

		int index = tmpMoneyStr.indexOf('.');
		// 傳入的值有小數位
		if (index >= 0) {
			sInt = tmpMoneyStr.substring(0, index);
			sDecimal = substring(tmpMoneyStr, index + 1);
		}
		// 傳入的值沒有小數位
		else {
			sInt = tmpMoneyStr;
		}

		// 整數位的字串，帶千分位
		sInt = getIntMoneyStr(sInt);
		// 記錄「整數位」的值
		sb.append(isBlank(sInt) ? "0" : sInt);

		// 傳的的 scale 大於 0，表示顯示的字串要有小數位
		if (iScale > 0) {
			sb.append(sDot);
			// sDecimal字串長度 > iScale
			if (length(sDecimal) > iScale) {
				sb.append(substring(sDecimal, 0, iScale));
			}
			// sDecimal字串長度 < iScale，右補0
			else {
				sb.append(rightPad(sDecimal, iScale, "0"));
			}
		}
		// 傳入的 scale 等於 0，若小數位有值，顯示該小數位
		else if (iScale == 0) {
			// 若傳入的值，具備「小數位」
			if (StringUtils.isNotBlank(sDecimal)) {
				// 移除右側的0
				sDecimal = StringUtils.trimRightZero(sDecimal);
				if (StringUtils.isNotBlank(sDecimal)) {
					sb.append(sDot).append(sDecimal);
				}
			}
		}
		// 不要有小數位
		else {
			// nothing.
		}

		return sb.toString();
	}

	/**
	 * 將字串轉換為金額表示型態的字串
	 *
	 * <pre>
	 * StringUtils.getIntMoneyStr(&quot;001234567&quot;) = &quot;1,234,567&quot;
	 * </pre>
	 *
	 * @param sInt
	 * @return
	 *
	 */
	private static String getIntMoneyStr(String sInt) {

		if (isBlank(sInt)) {
			return "";
		}

		String tmpIntStr = sInt.trim();

		StringBuilder sb = new StringBuilder();

		tmpIntStr = trimLeftZero(tmpIntStr);
		int iLen = tmpIntStr.length();

		for (int i = 0; i < iLen; i++) {

			char ch = tmpIntStr.charAt(i);

			sb.append(ch);
			// 剩餘長度
			int iRemainLen = iLen - i - 1;

			if ((iRemainLen > 0) && (iRemainLen % 3 == 0)) {
				sb.append(",");
			}

		}
		return sb.toString();

	}

	/**
	 * 移除字串左邊的<code>0</code>字元
	 *
	 * <pre>
	 * StringUtils.trimLeftZero(null) = ""
	 * StringUtils.trimLeftZero("0012345600") = "12345600"
	 * </pre>
	 *
	 * @param sSource
	 *            待處理之字串
	 * @return 移除左側<code>0</code>字元後的字串，永不為null
	 */
	public static String trimLeftZero(String sSource) {

		if (sSource == null) {
			return "";
		}

		int iLen = sSource.length();
		int index = -1;
		for (int i = 0; i < iLen; i++) {
			char ch = sSource.charAt(i);

			if (ch != '0') {
				index = i;
				break;
			}
		}
		String s = "";
		// 發現非0之數字
		if (index >= 0) {
			s = sSource.substring(index, iLen);
		}

		return s;

	}

	/**
	 * StringUtils.trimRightZero("01234500") = "012345"
	 *
	 * @param sSource
	 * @return
	 */
	public static String trimRightZero(String sSource) {

		if (sSource == null) {
			return "";
		}

		int iLen = sSource.length();
		int index = -1;
		// 由後至前
		for (int i = (iLen - 1); i >= 0; i--) {
			char ch = sSource.charAt(i);
			if (ch != '0') {
				index = i;
				break;
			}
		}

		String s = "";
		if (index >= 0) {
			s = sSource.substring(0, index + 1);
		}

		return s;
	}

	/**
	 * 替特殊字符「$」加上跳脫符號 千萬不要添加「\\」判斷，會導致通知內容中出現「\」
	 * 
	 * @param s
	 * @return
	 */
	public static String quoteReplacement(String s) {
		if (s.indexOf('$') == -1) {
			return s;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '$') {
				sb.append('\\');
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 移除字串左邊空白(全形/半形)
	 * 
	 * @param sValue
	 * @return
	 * 
	 */
	public static String trimLeftSpace(String sValue) {

		if (StringUtils.isBlank(sValue)) {
			return sValue;
		}

		int iLen = sValue.length();
		int index = -1;
		for (int i = 0; i < iLen; i++) {
			char ch = sValue.charAt(i);

			if (ch != ' ' && ch != '　') {
				index = i;
				break;
			}
		}
		String sResult = "";
		// 發現非0之數字
		if (index >= 0) {
			sResult = sValue.substring(index, iLen);
		}

		return sResult;
	}

	/**
	 * 移除字串左右邊之全形空白
	 *
	 * @param sValue
	 * @return
	 */
	public static String trimAllBigSpace(String sValue) {

        String sResult = trimToEmpty(sValue);
	    
		if (isBlank(sResult)) {
			return sResult;
		}

		while (sResult.endsWith("　") || sResult.endsWith(" ")) {
			sResult = sResult.substring(0, sResult.length() - 1);
		}

		while (sResult.startsWith("　") || sResult.startsWith(" ")) {
			sResult = sResult.substring(1);
		}

		return sResult;
	}

	/**
	 * 取字串後幾碼
	 *
	 * @param str
	 * @param len
	 * @return
	 */
	public static String right(final String str, final int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	/**
	 * 根據字串長度切割字串，一個中文字長度是1
	 *
	 * <pre>
	 * StringUtils.getTokens(null, *)         	= []
	 * StringUtils.getTokens("", *)           	= []
	 * StringUtils.getTokens("abc def", -1) 	= []
	 * StringUtils.getTokens("abc def", 0)  	= []
	 * StringUtils.getTokens("abc  def", 2) 	= ["ab", "c ", "de", "f"]
	 * </pre>
	 *
	 * @param sData
	 *            原始字串
	 * @param iLength
	 *            切割的長度
	 * @return 字串陣列，永不為null
	 */
	public static String[] getTokens(String sData, int iLength) {

		if (null == sData || iLength < 1) {
			return new String[0];
		}

		List<String> tokens = new ArrayList<>();

		int iLeft = 0;

		int iDataLen = sData.length();

		while (iLeft < iDataLen) {

			int iRight = (iLeft + iLength) > iDataLen ? iDataLen : iLeft + iLength;

			String sToken = sData.substring(iLeft, iRight);

			iLeft += iLength;

			tokens.add(sToken);
		}

		return tokens.toArray(new String[tokens.size()]);
	}

	/**
	 * 根據分隔子切割字串
	 *
	 * <pre>
	 * StringUtils.getTokens(null, *)         	= []
	 * StringUtils.getTokens("", *)           	= []
	 * StringUtils.getTokens("abc def", null) 	= ["abc", "def"]
	 * StringUtils.getTokens("abc def", " ")  	= ["abc", "def"]
	 * StringUtils.getTokens("abc  def", " ") 	= ["abc", "def"]
	 * StringUtils.getTokens("ab:cd:ef", ":") 	= ["ab", "cd", "ef"]
	 * StringUtils.getTokens("ab:cd:ef:", ":") = ["ab", "cd", "ef", ""]
	 * </pre>
	 *
	 * @param sData
	 *            原始字串
	 * @param iLength
	 *            切割的長度(byte)
	 * @return 切割後的字串陣列，永不為NULL
	 */
	public static String[] getTokens(String sData, String sDelim) {

		if (null == sData) {
			return new String[0];
		}

		List<String> tokens = new ArrayList<>();

		int iDataLen = sData.length();
		int iDelimLen = sDelim.length();

		int iLeft = 0;
		int iRight = sData.indexOf(sDelim);

		while (iRight >= 0) {

			String sToken = sData.substring(iLeft, iRight).trim();
			tokens.add(sToken);
			iLeft = iRight + iDelimLen;
			iRight = sData.indexOf(sDelim, iLeft);
		}

		if (iLeft < iDataLen) {
			String sToken = sData.substring(iLeft, iDataLen);
			tokens.add(sToken);
		}

		// 取最後一個token，如果為delim則加入一個空白("")token
		if (iDataLen >= iDelimLen) {
			String sLastToken = sData.substring(iDataLen - iDelimLen, iDataLen);

			if (sLastToken.equals(sDelim)) {
				tokens.add("");
			}
		}
		return tokens.toArray(new String[tokens.size()]);

	}

	/**
	 * 轉全形
	 *
	 * @param s
	 * @return
	 */
	public static String toFullChar(String s) {
		if (s == null || "".equalsIgnoreCase(s)) {
			return "";
		}

		char[] ca = s.toCharArray();
		StringBuilder outStr = new StringBuilder();

		for (int a = 0; a < ca.length; a++) {
			String caStr = String.valueOf(ca[a]);
			for (int b = 0; b < ASCII_TABLE.length; b++) {
				if (caStr.equals(ASCII_TABLE[b])) {
					caStr = BIG5_TABLE[b];
					break;
				}
			}

			outStr.append(caStr);
		}

		return outStr.toString();
	}

	/**
	 * 轉半形
	 *
	 * @param s
	 * @return
	 */
	public static String toHalfChar(String s) {
		if (s == null || "".equalsIgnoreCase(s)) {
			return "";
		}

		char[] ca = s.toCharArray();
		StringBuilder outStr = new StringBuilder();

		for (int a = 0; a < ca.length; a++) {
			String caStr = String.valueOf(ca[a]);
			for (int b = 0; b < BIG5_TABLE.length; b++) {
				if (caStr.equals(BIG5_TABLE[b])) {
					caStr = ASCII_TABLE[b];
					break;
				}
			}

			outStr.append(caStr);
		}

		return outStr.toString();
	}
	
    /**
     * 改呼叫 splitPreserveAllTokens()，取得全部元素
     * 
     * @param str
     * @param separatorChar
     * @return
     */
    public static String[] split(final String str, final String separatorChar) {
        return splitPreserveAllTokens(str, separatorChar);
    }
    
	/**
	 * 過濾特殊字符
	 * @param str
	 * @return
	 */
	public static String StringFilter(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	} 
	
}

