package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.EnclosingMethodAttribute;

public class EnclosingMethodAttributeResolver implements
		ClassFileAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "EnclosingMethod");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		EnclosingMethodAttribute attr = new EnclosingMethodAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item must be four
		 */
		di.skipBytes(4);
		attr.setAttribute_length(4);
		attr.setClass_index(di.readUnsignedShort());
		attr.setMethod_index(di.readUnsignedShort());
		return attr;
	}

}
