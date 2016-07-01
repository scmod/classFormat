package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.ConstantValueAttribute;
import cc.funny.structure.SourceFileAttribute;

public class SourceFileAttributeResolver implements ClassFileAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "SourceFile");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		SourceFileAttribute attr = new SourceFileAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item of a SourceFile_attribute
		 * structure must be two.
		 */
		di.skipBytes(4);
		attr.setAttribute_length(2);
		attr.setSourcefile_index(di.readUnsignedShort());
		return attr;
	}

}
