package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.StackMapTableAttribute;
import cc.funny.structure.stack.StackMapFrame;

public class StackMapTableAttributeResolver implements
		CodeAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "StackMapTable");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		StackMapTableAttribute attr = new StackMapTableAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes.
		 */
		attr.setAttribute_length(di.readInt());
		int number_of_entries = di.readUnsignedShort();
		attr.setNumber_of_entries(number_of_entries);
		List<StackMapFrame> list = null;
		if(number_of_entries > 0) {
			list = new ArrayList<>();
			for(int i=0; i<number_of_entries; i++) {
				list.add(StackMapFrame.getStackMapFrame(di));
			}
		}
		attr.setEntries(list);
		return attr;
	}
	
}
