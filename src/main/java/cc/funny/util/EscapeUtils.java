/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.funny.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/**
 * almost copy from commons-lang3-3.4.jar and i retain the license at file
 * header, is it OK~
 * @modifier John Smith
 */
public class EscapeUtils {

	private static final String[][] JAVA_CTRL_CHARS_ESCAPE = { { "\b", "\\b" },
			{ "\n", "\\n" }, { "\t", "\\t" }, { "\f", "\\f" }, { "\r", "\\r" } };

	private static final CharSequenceTranslator ESCAPE_JAVA = new LookupTranslator(
			JAVA_CTRL_CHARS_ESCAPE)
			.with(JavaUnicodeEscaper.outsideOf(32, 0x7f));

	public static final String escapeJava(String input) {
		return ESCAPE_JAVA.translate(input);
	}

}

abstract class CharSequenceTranslator {

	static final char[] HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public abstract int translate(CharSequence input, int index, Writer out)
			throws IOException;

	public final String translate(final CharSequence input) {
		if (input == null) {
			return null;
		}
		try {
			final StringWriter writer = new StringWriter(input.length() * 2);
			translate(input, writer);
			return writer.toString();
		} catch (final IOException ioe) {

			throw new RuntimeException(ioe);
		}
	}

	public final void translate(final CharSequence input, final Writer out)
			throws IOException {
		if (out == null) {
			throw new IllegalArgumentException("The Writer must not be null");
		}
		if (input == null) {
			return;
		}
		int pos = 0;
		final int len = input.length();
		while (pos < len) {
			final int consumed = translate(input, pos, out);
			if (consumed == 0) {
				char c1 = input.charAt(pos);
				out.write(c1);
				pos++;
				if (Character.isHighSurrogate(c1) && pos < len) {
					char c2 = input.charAt(pos);
					if (Character.isLowSurrogate(c2)) {
						out.write(c2);
						pos++;
					}
				}
				continue;
			}

			for (int pt = 0; pt < consumed; pt++) {
				pos += Character.charCount(Character.codePointAt(input, pos));
			}
		}
	}

	public final CharSequenceTranslator with(
			final CharSequenceTranslator... translators) {
		final CharSequenceTranslator[] newArray = new CharSequenceTranslator[translators.length + 1];
		newArray[0] = this;
		System.arraycopy(translators, 0, newArray, 1, translators.length);
		return new AggregateTranslator(newArray);
	}

	public static String hex(final int codepoint) {
		return Integer.toHexString(codepoint).toUpperCase(Locale.ENGLISH);
	}

}

class AggregateTranslator extends CharSequenceTranslator {

	private final CharSequenceTranslator[] translators;

	public AggregateTranslator(final CharSequenceTranslator... translators) {
		this.translators = clone(translators);
	}

	public static <T> T[] clone(final T[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	@Override
	public int translate(final CharSequence input, final int index,
			final Writer out) throws IOException {
		for (final CharSequenceTranslator translator : translators) {
			final int consumed = translator.translate(input, index, out);
			if (consumed != 0) {
				return consumed;
			}
		}
		return 0;
	}

}

class LookupTranslator extends CharSequenceTranslator {

	private final HashMap<String, String> lookupMap;
	private final HashSet<Character> prefixSet;
	private final int shortest;
	private final int longest;

	public LookupTranslator(final CharSequence[]... lookup) {
		lookupMap = new HashMap<String, String>();
		prefixSet = new HashSet<Character>();
		int _shortest = Integer.MAX_VALUE;
		int _longest = 0;
		if (lookup != null) {
			for (final CharSequence[] seq : lookup) {
				this.lookupMap.put(seq[0].toString(), seq[1].toString());
				this.prefixSet.add(seq[0].charAt(0));
				final int sz = seq[0].length();
				if (sz < _shortest) {
					_shortest = sz;
				}
				if (sz > _longest) {
					_longest = sz;
				}
			}
		}
		shortest = _shortest;
		longest = _longest;
	}

	@Override
	public int translate(final CharSequence input, final int index,
			final Writer out) throws IOException {

		if (prefixSet.contains(input.charAt(index))) {
			int max = longest;
			if (index + longest > input.length()) {
				max = input.length() - index;
			}

			for (int i = max; i >= shortest; i--) {
				final CharSequence subSeq = input.subSequence(index, index + i);
				final String result = lookupMap.get(subSeq.toString());

				if (result != null) {
					out.write(result);
					return i;
				}
			}
		}
		return 0;
	}
}

abstract class CodePointTranslator extends CharSequenceTranslator {

	@Override
	public final int translate(final CharSequence input, final int index,
			final Writer out) throws IOException {
		final int codepoint = Character.codePointAt(input, index);
		final boolean consumed = translate(codepoint, out);
		return consumed ? 1 : 0;
	}

	public abstract boolean translate(int codepoint, Writer out)
			throws IOException;

}

class UnicodeEscaper extends CodePointTranslator {

	private final int below;
	private final int above;
	private final boolean between;

	public UnicodeEscaper() {
		this(0, Integer.MAX_VALUE, true);
	}

	protected UnicodeEscaper(final int below, final int above,
			final boolean between) {
		this.below = below;
		this.above = above;
		this.between = between;
	}

	@Override
	public boolean translate(final int codepoint, final Writer out)
			throws IOException {
		if (between) {
			if (codepoint < below || codepoint > above) {
				return false;
			}
		} else {
			if (codepoint >= below && codepoint <= above) {
				return false;
			}
		}

		if (codepoint > 0xffff) {
			out.write(toUtf16Escape(codepoint));
		} else {
			out.write("\\u");
			out.write(HEX_DIGITS[(codepoint >> 12) & 15]);
			out.write(HEX_DIGITS[(codepoint >> 8) & 15]);
			out.write(HEX_DIGITS[(codepoint >> 4) & 15]);
			out.write(HEX_DIGITS[(codepoint) & 15]);
		}
		return true;
	}

	protected String toUtf16Escape(final int codepoint) {
		return "\\u" + hex(codepoint);
	}
}

class JavaUnicodeEscaper extends UnicodeEscaper {

	public static JavaUnicodeEscaper outsideOf(final int codepointLow,
			final int codepointHigh) {
		return new JavaUnicodeEscaper(codepointLow, codepointHigh, false);
	}

	public JavaUnicodeEscaper(final int below, final int above,
			final boolean between) {
		super(below, above, between);
	}

	@Override
	protected String toUtf16Escape(final int codepoint) {
		final char[] surrogatePair = Character.toChars(codepoint);
		return "\\u" + hex(surrogatePair[0]) + "\\u" + hex(surrogatePair[1]);
	}

}
