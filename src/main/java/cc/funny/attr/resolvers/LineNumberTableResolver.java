package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.LineNumberTableAttribute;
import cc.funny.structure.LocalVariableTableAttribute;

public class LineNumberTableResolver implements CodeAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "LineNumberTable");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		LineNumberTableAttribute attr = new LineNumberTableAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes
		 */
		attr.setAttribute_length(di.readInt());
		
		int line_number_table_length = di.readUnsignedShort();
		attr.setLine_number_table_length(line_number_table_length);
		
		List<LineNumberTableAttribute.LineNumber> list = null;
		if(line_number_table_length > 0) {
			list = new ArrayList<>(line_number_table_length);
			for(int i=0; i<line_number_table_length; i++) {
				LineNumberTableAttribute.LineNumber ln = new LineNumberTableAttribute.LineNumber();
				ln.setStart_pc(di.readUnsignedShort());
				ln.setLine_number(di.readUnsignedShort());
				list.add(ln);
			}
		}
		attr.setLine_number_table(list);
		return attr;
	}

}
