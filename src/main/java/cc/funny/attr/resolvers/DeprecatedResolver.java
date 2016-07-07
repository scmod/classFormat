package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.DeprecatedAttribute;

public class DeprecatedResolver implements ClassFileAttributeResolver,
		FieldInfoAttributeResolver, MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "Deprecated");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		DeprecatedAttribute attr = new DeprecatedAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item must be zero.
		 */
		di.skipBytes(4);
		attr.setAttribute_length(0);
		return attr;
	}

}
