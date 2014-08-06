package com.learnr.util;

import java.util.List;
import java.util.ArrayList;

/**
 * Odd ball utilities that are just convenient to have.
 */
public class StringUtils {
	/**
	 * Join all the Objects together with a separator.
	 * 
	 * @param separator
	 *            The joining string.
	 * @param iterable
	 *            An Iterable of Objects to join.
	 * @return The joined String
	 */
	public static String Join(String separator, Iterable<?> iterable) {
		List<Object> items = new ArrayList<Object>();
		for (Object item : iterable) {
			items.add(item);
		}
		return Join(separator, items);
	}

	/**
	 * Join a list of Objects with the given separator.
	 * 
	 * @param separator
	 *            The joining string.
	 * @param items
	 *            The items to be joined.
	 * @return The joined String.
	 */
	public static String Join(String separator, List<?> items) {
		int count = items.size();
		if (count == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < count - 1; ++i) {
			builder.append(items.get(i));
			builder.append(separator);
		}
		builder.append(items.get(count - 1));
		return builder.toString();
	}

	/**
	 * Join any number of Objects.
	 * 
	 * @param separator
	 *            The joining string.
	 * @param items
	 *            The array of Objects to join.
	 * @return The joined String.
	 */
	public static String Join(String separator, Object... items) {
		if (items.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < items.length - 1; ++i) {
			builder.append(items[i]);
			builder.append(separator);
		}
		builder.append(items[items.length - 1]);
		return builder.toString();
	}

	/**
	 * Construct a string of repeats.
	 * 
	 * @param value
	 *            The repeating unit.
	 * @param times
	 *            The number of repeats.
	 * @return The repeated string.
	 */
	public static String Repeat(String value, int times) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < times; ++i) {
			builder.append(value);
		}
		return builder.toString();
	}

	/**
	 * Returns the indicated number of prefix characters.
	 * 
	 * @param string
	 *            The string.
	 * @param count
	 *            How many characters to grab.
	 * @return The prefix string.
	 */
	public static String Head(String string, int count) {
		if (string.length() < count) {
			return string;
		}
		return string.substring(0, count);
	}

	/**
	 * Return the tail of the given string of given size.
	 * 
	 * @param string
	 *            The string.
	 * @param count
	 *            The length of the tail to return.
	 * @return The tail string.
	 */
	public static String Tail(String string, int count) {
		int length = string.length();
		if (length < count) {
			return string;
		}
		return string.substring(length - count, length);
	}

	/**
	 * Get the prefix of the given string up to but not including the given character sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The prefix. If the sequence is not found the original string is returned.
	 */
	public static String BeforeFirst(String string, String sequence) {
		int index = string.indexOf(sequence);
		if (index == -1) {
			return string;
		}
		return string.substring(0, index);
	}

	/**
	 * Get the prefix of the given string up to but not including the last instance of the given character sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The prefix. If the sequence is not found the original string is returned.
	 */
	public static String BeforeLast(String string, String sequence) {
		int index = string.lastIndexOf(sequence);
		if (index == -1) {
			return "";
		}
		return string.substring(0, index);
	}

	/**
	 * Get the prefix of the given string up to and including the given character sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The prefix. If the sequence is not found a 0 length string is returned.
	 */
	public static String ThroughFirst(String string, String sequence) {
		int index = string.indexOf(sequence);
		if (index == -1) {
			return "";
		}
		return string.substring(0, index + sequence.length());
	}

	/**
	 * Get the prefix of the given string up to and including the last instance of the given character sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The prefix. If the sequence is not found a 0 length string is returned.
	 */
	public static String ThroughLast(String string, String sequence) {
		int index = string.lastIndexOf(sequence);
		if (index == -1) {
			return "";
		}
		return string.substring(0, index + sequence.length());
	}

	/**
	 * Get the suffix of the string after the last instance of the given sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The suffix. If the sequence is not found then the original string is returned.
	 */
	public static String AfterLast(String string, String sequence) {
		int index = string.lastIndexOf(sequence);
		if (index == -1) {
			return string;
		}
		return string.substring(index + sequence.length(), string.length());
	}

	/**
	 * Get the suffix of the string after the first instance of the given sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The suffix. If the sequence is not found then the original string is returned.
	 */
	public static String AfterFirst(String string, String sequence) {
		int index = string.indexOf(sequence);
		if (index == -1) {
			return string;
		}
		return string.substring(index + sequence.length(), string.length());
	}

	/**
	 * Get the suffix of the string starting at the last instance of the given sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The suffix. If the sequence is not found then a 0 length string is returned.
	 */
	public static String FromLast(String string, String sequence) {
		int index = string.lastIndexOf(sequence);
		if (index == -1) {
			return "";
		}
		return string.substring(index, string.length());
	}

	/**
	 * Get the suffix of the string starting at the first instance of the given sequence.
	 * 
	 * @param string
	 *            The string.
	 * @param sequence
	 *            The sequence to match.
	 * @return The suffix. If the sequence is not found then a 0 length string is returned.
	 */
	public static String FromFirst(String string, String sequence) {
		int index = string.indexOf(sequence);
		if (index == -1) {
			return "";
		}
		return string.substring(index, string.length());
	}

	private static char[] HEXDIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * A utility to get a hex encoded string of arbitrary byte data.
	 * 
	 * @param bytes
	 *            The data.
	 * @return A String.
	 */
	public static String BytesToHex(byte[] bytes) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			out.append(HEXDIGITS[(bytes[i] >> 4) & 0xf]);
			out.append(HEXDIGITS[bytes[i] & 0xf]);
		}
		return out.toString();
	}

	/**
	 * Returns the string to the first {@code count} characters, adding the prefix and suffix if the string is trimmed.
	 * 
	 * Use {@link #Head} where no prefix or suffix should be added if the result is trimmed.
	 * 
	 * @param string
	 *            The string.
	 * @param count
	 *            How many characters to grab.
	 * @param prefix
	 *            Prefix to add if result is trimmed.
	 * @param suffix
	 *            Suffix to add if result is trimmed.
	 * @return The possibly trimmed string.
	 */
	public static String trim(String string, int count, String prefix, String suffix) {
		if (string.length() < count) {
			return string;
		}
		return prefix + string.substring(0, count) + suffix;
	}

	/**
	 * Returns the string to the first {@code count} characters, adding the prefix and suffix if the string is trimmed.
	 * 
	 */
	public static String trim(String string, int count, String suffix) {
		return trim(string, count, "", suffix);
	}
}
