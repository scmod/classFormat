package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.ConstantValueAttribute;

public class ConstantValueAttributeResolver implements
		FieldInfoAttributeResolver {

	@Override
	public ConstantValueAttribute resolve(int attribute_name_index, DataInput di) throws IOException {
		ConstantValueAttribute attr = new ConstantValueAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item of a ConstantValue_attribute
		 * structure must be two
		 */
		di.skipBytes(4);
		attr.setAttribute_length(2);
		attr.setConstantvalue_index(di.readUnsignedShort());
		return attr;
	}

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "ConstantValue");
	}

}
