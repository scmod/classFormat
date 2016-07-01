package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;

public class SourceFileAttributeResolver implements ClassFileAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return false;
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		return null;
	}

}
