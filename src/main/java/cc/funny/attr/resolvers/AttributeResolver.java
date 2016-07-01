package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;

public interface AttributeResolver {

	boolean canHandle(int attribute_name_index);
	
	AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException;

}
