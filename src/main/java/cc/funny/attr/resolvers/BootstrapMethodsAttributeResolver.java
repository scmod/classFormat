package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.BootstrapMethodsAttribute;

public class BootstrapMethodsAttributeResolver implements
		ClassFileAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		//TODO :skip it now
		return false;
//		return canHandle(attribute_name_index, "BootstrapMethods");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		BootstrapMethodsAttribute attr = new BootstrapMethodsAttribute();
		return attr;
	}

}
