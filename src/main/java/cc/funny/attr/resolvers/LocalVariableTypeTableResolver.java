package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.LineNumberTableAttribute;
import cc.funny.structure.LocalVariableTypeTableAttribute;

public class LocalVariableTypeTableResolver implements CodeAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "LocalVariableTypeTable");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		LocalVariableTypeTableAttribute attr = new LocalVariableTypeTableAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes
		 */
		attr.setAttribute_length(di.readInt());
		
		int local_variable_type_table_length = di.readUnsignedShort();
		attr.setLocal_variable_type_table_length(local_variable_type_table_length);
		
		List<LocalVariableTypeTableAttribute.LocalVariableType> list = null;
		if(local_variable_type_table_length > 0) {
			list = new ArrayList<>(local_variable_type_table_length);
			for(int i=0; i<local_variable_type_table_length; i++) {
				LocalVariableTypeTableAttribute.LocalVariableType lvt = new LocalVariableTypeTableAttribute.LocalVariableType();
				lvt.setStart_pc(di.readUnsignedShort());
				lvt.setLength(di.readUnsignedShort());
				lvt.setName_index(di.readUnsignedShort());
				lvt.setSignature_index(di.readUnsignedShort());
				lvt.setIndex(di.readUnsignedShort());
				list.add(lvt);
			}
		}
		attr.setLocal_variable_type_table(list);
		return attr;
	}

}
