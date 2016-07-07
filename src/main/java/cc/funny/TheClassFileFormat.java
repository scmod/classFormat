package cc.funny;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;
import org.apache.commons.lang3.text.translate.LookupTranslator;

import cc.funny.attr.Resolvers;
import cc.funny.inject.Cool;
import cc.funny.structure.ClassFile;
import cc.funny.type_value.TypeValue;
import cc.funny.util.DisplayUtils;
import cc.funny.util.Utils;

public class TheClassFileFormat implements Serializable, Cloneable {

	public static final ClassFile cf = new ClassFile();
	private static final String CRLF = "\n\r\'\"\b\f\\";

	public static void main(String[] args) throws IOException,
			URISyntaxException {
		System.out.println(Cool.getBean(Resolvers.class)
				.getFieldsAttrResolvers());

		// invoke lambda to bring in
		// CONSTANT_MethodHandle,CONSTANT_MethodType,CONSTANT_InvokeDynamic
		// ignore it temporary
		// new Thread(() -> {}).start();
		// RandomAccessFile di = new RandomAccessFile(TheClassFileFormat.class
		// .getResource("TheClassFileFormat.class").toURI().getPath(), "r");
		DataInput di = new DataInputStream(
				TheClassFileFormat.class
						.getResourceAsStream("TheClassFileFormat.class"));
		cf.setMagic(di.readInt());
		cf.setMinorVersion(di.readUnsignedShort());
		cf.setMajorVersion(di.readUnsignedShort());

		int constant_pool_count = di.readUnsignedShort();
		cf.setConstantPoolCount(constant_pool_count);
		List<TypeValue<?>> context = Utils.analyseConstantPool(di,
				constant_pool_count);
		cf.setConstantPool(context);

		// System.out.println(simpleFormatOutput(context));
		//
		// System.out.println("access_flags : "
		// + classModifiers(di.readUnsignedShort()));
		// System.out.println("this_class : "
		// + dig(context, di.readUnsignedShort()));
		// System.out.println("super_class : "
		// + dig(context, di.readUnsignedShort()));
		cf.setAccessFlags(di.readUnsignedShort());
		cf.setThisClass(di.readUnsignedShort());
		cf.setSuperClass(di.readUnsignedShort());

		int interfaces_count = di.readUnsignedShort();
		cf.setInterfacesCount(interfaces_count);
		cf.setInterfaces(Utils.analyseInterfaces(di, interfaces_count));

		int fields_count = di.readUnsignedShort();
		cf.setFieldsCount(fields_count);
		cf.setFields(Utils.analyseFields(di, fields_count));

		int methods_count = di.readUnsignedShort();
		cf.setMethodsCount(methods_count);
		cf.setMethods(Utils.analyseMethods(di, methods_count));

		int attributes_count = di.readUnsignedShort();
		cf.setAttributesCount(attributes_count);
		cf.setAttributes(Utils.analyseClassFileAttributes(di, attributes_count));

		System.out.println(cf);
		DisplayUtils.toString(cf);
	}

	private static final int ACC_PUBLIC = 0x0001;
	private static final int ACC_FINAL = 0x0010;
	private static final int ACC_SUPER = 0x0020;
	private static final int ACC_INTERFACE = 0x0200;
	private static final int ACC_ABSTRACT = 0x0400;
	private static final int ACC_SYNTHETIC = 0x1000;
	private static final int ACC_ANNOTATION = 0x2000;
	private static final int ACC_ENUM = 0x4000;

	// class's modifiers is different from method's&field's
	static String classModifiers(int mod) {
		StringBuilder sb = new StringBuilder();
		if ((mod & ACC_PUBLIC) != 0)
			sb.append("ACC_PUBLIC, ");
		if ((mod & ACC_FINAL) != 0)
			sb.append("ACC_FINAL, ");
		if ((mod & ACC_SUPER) != 0)
			sb.append("ACC_SUPER, ");
		if ((mod & ACC_INTERFACE) != 0)
			sb.append("ACC_INTERFACE, ");
		if ((mod & ACC_ABSTRACT) != 0)
			sb.append("ACC_ABSTRACT, ");
		if ((mod & ACC_SYNTHETIC) != 0)
			sb.append("ACC_SYNTHETIC, ");
		if ((mod & ACC_ANNOTATION) != 0)
			sb.append("ACC_ANNOTATION, ");
		if ((mod & ACC_ENUM) != 0)
			sb.append("ACC_ENUM, ");
		return sb.subSequence(0, sb.length() - 2).toString();
	}

}
