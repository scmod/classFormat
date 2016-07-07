package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.MethodParametersAttribute;

public class MethodParametersResolver implements MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "MethodParameters");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		MethodParametersAttribute attr = new MethodParametersAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes.
		 */
		attr.setAttribute_length(di.readInt());
		
		int parameters_count = di.readUnsignedByte();
		attr.setParameters_count(parameters_count);
		List<MethodParametersAttribute.Parameter> list = null;
		if(parameters_count > 0) {
			list = new ArrayList<>(parameters_count);
			for(int i=0; i<parameters_count; i++) {
				MethodParametersAttribute.Parameter p = new MethodParametersAttribute.Parameter();
				p.setName_index(di.readUnsignedShort());
				p.setAccess_flags(di.readUnsignedShort());
				list.add(p);
			}
		}
		attr.setParameters(list);
		return attr;
	}

}
