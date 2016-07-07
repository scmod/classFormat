package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.attr.InfoType;
import cc.funny.attr.Resolvers;
import cc.funny.inject.Cool;
import cc.funny.structure.AttributeInfo;
import cc.funny.structure.CodeAttribute;

/**
 * for MethodInfo, and CodeAttributeResolver is just a interface for subclasses
 * 
 * @author John Smith
 */
public class MCodeAttributeResolver implements MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "Code");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		CodeAttribute attr = new CodeAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes.
		 */
		attr.setAttribute_length(di.readInt());
		attr.setMax_stack(di.readUnsignedShort());
		attr.setMax_locals(di.readUnsignedShort());
		int code_length = di.readInt();
		byte[] b = new byte[code_length];
		di.readFully(b);
		attr.setCode(b);

		int exception_table_length = di.readUnsignedShort();
		List<CodeAttribute.ExceptionInfo> eis = null;
		if (exception_table_length > 0) {
			eis = new ArrayList<>();
			for (int i = 0; i < exception_table_length; i++) {
				CodeAttribute.ExceptionInfo ei = new CodeAttribute.ExceptionInfo();
				ei.setStart_pc(di.readUnsignedShort());
				ei.setEnd_pc(di.readUnsignedShort());
				ei.setHandler_pc(di.readUnsignedShort());
				ei.setCatch_type(di.readUnsignedShort());
				eis.add(ei);
			}
		}

		int attributes_count = di.readUnsignedShort();
		attr.setAttributes_count(attributes_count);
		List<AttributeInfo> list = null;
		if (attributes_count > 0) {
			list = new ArrayList<>();
			for (int i = 0; i < attributes_count; i++) {
				int inner_attribute_name_index = di.readUnsignedShort();
				list.add(Cool.getBean(Resolvers.class).resolve(InfoType.Code, inner_attribute_name_index, di));
			}
		}
		attr.setAttributes(list);
		return attr;
	}

}
