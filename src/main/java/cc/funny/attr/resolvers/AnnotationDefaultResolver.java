package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.annotations.AnnotationDefault;

public class AnnotationDefaultResolver implements MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "AnnotationDefault");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		AnnotationDefault attr = new AnnotationDefault();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes.
		 */
		attr.setAttribute_length(di.readInt());
		attr.setDefault_value(RuntimeAnnotationsResolver.resolveElementValue(di));
		return attr;
	}

}
