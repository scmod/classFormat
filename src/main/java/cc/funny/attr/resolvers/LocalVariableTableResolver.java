package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.LocalVariableTableAttribute;

public class LocalVariableTableResolver implements CodeAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "LocalVariableTable");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		LocalVariableTableAttribute attr = new LocalVariableTableAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes
		 */
		attr.setAttribute_length(di.readInt());
		
		int local_variable_table_length = di.readUnsignedShort();
		attr.setLocal_variable_table_length(local_variable_table_length);
		
		List<LocalVariableTableAttribute.LocalVariable> list = null;
		if(local_variable_table_length > 0) {
			list = new ArrayList<>(local_variable_table_length);
			for(int i=0; i<local_variable_table_length; i++) {
				LocalVariableTableAttribute.LocalVariable lv = new LocalVariableTableAttribute.LocalVariable();
				lv.setStart_pc(di.readUnsignedShort());
				lv.setLength(di.readUnsignedShort());
				lv.setName_index(di.readUnsignedShort());
				lv.setDescriptor_index(di.readUnsignedShort());
				lv.setIndex(di.readUnsignedShort());
				list.add(lv);
			}
		}
		attr.setLocal_variable_table(list);
		return attr;
	}
	

}
