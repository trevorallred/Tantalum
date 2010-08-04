package tantalum.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.Joiner;

public class Strings {
	
	public static String joinForDB(Iterable<Integer> parts) {
		return Joiner.on(",").join(parts);
	}
	
	public static boolean isEmpty(String value) {
		if (value == null)
			return true;
		value = value.trim();
		return value.length() == 0;
	}

	/**
	 * Replaces single quotes in a string with two single quotes. This formats
	 * it properly for use in a SQL statement.
	 */
	public static String escapeQuotes(String value) {
		if (value == null)
			return "";
		StringBuffer strval = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			// Found a single quote; replace it with 2 for the SQL statement
			if ((ch == 146) || (ch == '\'') || (ch == '%'))
				strval.append("''");
			// if find double quote, change to single quote since html input
			// can't handle double quotes BJ 3-7-05
			else if (ch == 92)
				strval.append("\\\\");
			else if (ch == '"')
				strval.append("''");
			else
				// Just append the char to the strval for the complete string
				strval.append(ch);
		}
		return strval.toString();
	}

	public static String formatDateTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
	
}
