package cc.funny.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;
import org.apache.commons.lang3.text.translate.LookupTranslator;

import cc.funny.structure.ClassFile;
import cc.funny.type_value.TypeValue;
import static java.lang.Integer.parseInt;

public class DisplayUtils {

	public static String toString(ClassFile cf) {
		List<String> stringConstantPool = convertConstantPoolEscaped(cf);
		int size = stringConstantPool.size();
		for (int i = 1; i < size; i++) {
			System.out.println("#" + i + " : " + stringConstantPool.get(i));
		}
		return null;
	}

	/**
	 * convert all indexed values in constantPool into String
	 */
	public static List<String> convertConstantPool(ClassFile cf) {
		List<TypeValue<?>> list = cf.getConstantPool();
		int size = list.size();
		List<String> result = new ArrayList<>(size);
		result.add(null);
		// value at index zero is null;
		for (int i = 1; i < size; i++) {
			result.add(resolveIndex(list, i));
		}
		return result;
	}

	/**
	 * how to fast and convenient escape...
	 */
	public static List<String> convertConstantPoolEscaped(ClassFile cf) {
		List<TypeValue<?>> list = cf.getConstantPool();
		int size = list.size();
		List<String> result = new ArrayList<>(size);
		result.add(null);
		for (int i = 1; i < size; i++) {
			result.add(EscapeUtils.escapeJava(resolveIndex(list, i)));
		}
		return result;
	}

	private static String resolveIndex(List<TypeValue<?>> list, int index) {
		TypeValue<?> v = list.get(index);
		String tmp = v.getStringValue();
		if (tmp.charAt(0) == '#' && !v.getType().equals("Utf8")) {
			// resolve first index after '#'
			int secondPos = tmp.indexOf('#', 1);
			if (secondPos == -1) {
				return resolveIndex(list, parseInt(tmp.substring(1)));
			}
			char separator = tmp.charAt(secondPos - 1);
			return resolveIndex(list, parseInt(tmp.substring(1, secondPos - 1)))
					+ separator
					+ resolveIndex(list, parseInt(tmp.substring(secondPos + 1)));
		} else {
			return v.getStringValue();
		}
	}
	

}
