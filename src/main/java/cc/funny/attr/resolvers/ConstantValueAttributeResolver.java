package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.TheClassFileFormat;
import cc.funny.structure.ConstantValueAttribute;

public class ConstantValueAttributeResolver implements
		FieldInfoAttributeResolver {

	@Override
	public ConstantValueAttribute resolve(int attribute_name_index, DataInput di) throws IOException {
		ConstantValueAttribute cva = new ConstantValueAttribute();
		cva.setConstantvalue_index(di.readUnsignedShort());
		/*
		 * The value of the attribute_length item of a ConstantValue_attribute
		 * structure must be two
		 */
		di.skipBytes(4);
		cva.setConstantvalue_index(2);
		cva.setConstantvalue_index(di.readUnsignedShort());
		return cva;
	}

	@Override
	public boolean canHandle(int attribute_name_index) {
		return TheClassFileFormat.cf.getConstantPool()
				.get(attribute_name_index).getStringValue()
				.equals("ConstantValue");
	}

}
