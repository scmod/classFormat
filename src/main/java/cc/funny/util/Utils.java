package cc.funny.util;

import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import cc.funny.attr.resolvers.FieldInfoAttributeResolver;
import cc.funny.inject.Inject;
import cc.funny.structure.FieldInfo;
import cc.funny.structure.InterfaceInfo;
import cc.funny.type_value.TypeValue;
import cc.funny.type_value.constantPool.CClass;
import cc.funny.type_value.constantPool.CDouble;
import cc.funny.type_value.constantPool.CFieldref;
import cc.funny.type_value.constantPool.CFloat;
import cc.funny.type_value.constantPool.CInteger;
import cc.funny.type_value.constantPool.CInterfaceMethodref;
import cc.funny.type_value.constantPool.CLong;
import cc.funny.type_value.constantPool.CMethodref;
import cc.funny.type_value.constantPool.CNameAndType;
import cc.funny.type_value.constantPool.CRef;
import cc.funny.type_value.constantPool.CString;
import cc.funny.type_value.constantPool.CUtf8;

public class Utils {

	private static final int CONSTANT_Class = 7;
	private static final int CONSTANT_Fieldref = 9;
	private static final int CONSTANT_Methodref = 10;
	private static final int CONSTANT_InterfaceMethodref = 11;
	private static final int CONSTANT_String = 8;
	private static final int CONSTANT_Integer = 3;
	private static final int CONSTANT_Float = 4;
	private static final int CONSTANT_Long = 5;
	private static final int CONSTANT_Double = 6;
	private static final int CONSTANT_NameAndType = 12;
	private static final int CONSTANT_Utf8 = 1;
	private static final int CONSTANT_MethodHandle = 15;
	private static final int CONSTANT_MethodType = 16;
	private static final int CONSTANT_InvokeDynamic = 18;

	private static final int Ref = 22;

	/**
	 * read and make a list of constant pool
	 * 
	 * @param di
	 * @param constant_pool_count
	 * @return
	 * @throws IOException
	 */
	public static List<TypeValue<?>> analyseConstantPool(DataInput di,
			int constant_pool_count) throws IOException {
		List<TypeValue<?>> context = new ArrayList<>(constant_pool_count + 1);
		// trick?
		context.add(null);
		// The constant_pool table is indexed from 1 to constant_pool_count - 1.
		for (int i = 1; i < constant_pool_count; i++) {
			int b = di.readUnsignedByte();// u1
			CRef tmp = null;
			switch (b) {
			case CONSTANT_Class:
				context.add(new CClass(di.readUnsignedShort()));
				break;

			case CONSTANT_Fieldref:
				tmp = new CFieldref();
			case CONSTANT_Methodref:
				tmp = new CMethodref();
			case CONSTANT_InterfaceMethodref:
				tmp = new CInterfaceMethodref();
			case Ref:
				tmp.setValue(di.readUnsignedShort(), di.readUnsignedShort());
				context.add(tmp);
				break;

			case CONSTANT_String:
				context.add(new CString(di.readUnsignedShort()));
				break;

			case CONSTANT_Integer:
				context.add(new CInteger(di.readInt()));
				break;
			case CONSTANT_Float:
				context.add(new CFloat(di.readFloat()));
				break;
			case CONSTANT_Long:
				context.add(new CLong(di.readLong()));
				break;
			case CONSTANT_Double:
				context.add(new CDouble(di.readDouble()));
				break;

			case CONSTANT_NameAndType:
				context.add(new CNameAndType(di.readUnsignedShort(), di
						.readUnsignedShort()));
				break;
			case CONSTANT_Utf8:
				int length = di.readUnsignedShort();
				byte[] utf = new byte[length];
				di.readFully(utf);
				context.add(new CUtf8(new String(utf, "utf-8")));
				break;

			// ignore...
			case CONSTANT_MethodHandle:
			case CONSTANT_MethodType:
			case CONSTANT_InvokeDynamic:
			default:
				System.out.println("i don't know...");
			}
		}
		return context;
	}

	public static List<InterfaceInfo> analyseInterfaces(DataInput di,
			int interfaces_count) throws IOException {
		List<InterfaceInfo> list = null;
		if (interfaces_count > 0) {
			list = new ArrayList<>();
			for (int i = 0; i < interfaces_count; i++) {
				list.add(new InterfaceInfo(di.readUnsignedShort()));
			}
		}
		return list;
	}

	public static List<FieldInfo> analyseFields(DataInput di, int fields_count)
			throws IOException {
		List<FieldInfo> list = null;
		if (fields_count > 0) {
			list = new ArrayList<>();
			for (int i = 0; i < fields_count; i++) {
				FieldInfo fi = new FieldInfo();
				fi.setAccess_flags(di.readUnsignedShort());
				fi.setName_index(di.readUnsignedShort());
				fi.setDescriptor_index(di.readUnsignedShort());
				int attributes_count = di.readUnsignedShort();
				fi.setAttributes(null);
				list.add(fi);
			}
		}
		return list;
	}

}
