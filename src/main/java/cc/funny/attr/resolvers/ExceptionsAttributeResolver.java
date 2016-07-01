package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.ExceptionsAttribute;

public class ExceptionsAttributeResolver implements MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "Exceptions");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		ExceptionsAttribute attr = new ExceptionsAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes.
		 */
		attr.setAttribute_length(di.readInt());
		
		int number_of_exceptions = di.readUnsignedShort();
		attr.setNumber_of_exceptions(number_of_exceptions);
		if(number_of_exceptions > 0) {
			int[] es = new int[number_of_exceptions];
			for(int i=0; i<number_of_exceptions; i++) {
				es[i] = di.readUnsignedShort();
			}
		}
		return attr;
	}

}
