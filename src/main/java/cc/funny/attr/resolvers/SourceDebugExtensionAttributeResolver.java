package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.SourceDebugExtensionAttribute;

public class SourceDebugExtensionAttributeResolver implements
		ClassFileAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "SourceDebugExtension");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		SourceDebugExtensionAttribute attr = new SourceDebugExtensionAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes.
		 */
		int length = di.readUnsignedShort();
		attr.setAttribute_length(length);
		byte[] b = new byte[length];
		di.readFully(b);
		attr.setDebug_extension(b);
		return attr;
	}

}
