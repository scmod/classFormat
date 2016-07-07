package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.InnerClassesAttribute;

public class InnerClassesAttributeResolver implements
		ClassFileAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "InnerClasses");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		InnerClassesAttribute attr = new InnerClassesAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes
		 */
		attr.setAttribute_length(di.readInt());
		int number_of_classes = di.readUnsignedShort();
		attr.setNumber_of_classes(number_of_classes);
		if(number_of_classes > 0) {
			InnerClassesAttribute.ClassesInfo[] cis = new InnerClassesAttribute.ClassesInfo[number_of_classes];
			for(int i=0; i<number_of_classes; i++) {
				InnerClassesAttribute.ClassesInfo ci = new InnerClassesAttribute.ClassesInfo();
				ci.setInner_class_info_index(di.readUnsignedShort());
				//if outer_class_info_index == 0
				ci.setOuter_class_info_index(di.readUnsignedShort());
				ci.setInner_name_index(di.readUnsignedShort());
				ci.setInner_class_access_flags(di.readUnsignedShort());
				cis[i] = ci;
			}
		}
		return attr;
	}

}
