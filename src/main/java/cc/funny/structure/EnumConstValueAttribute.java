package cc.funny.structure;

import java.io.DataInput;
import java.io.IOException;

public class EnumConstValueAttribute implements ElementValueAttribute {

	private int type_name_index;// u2
	private int const_name_index;// u2

	public int getType_name_index() {
		return type_name_index;
	}

	public void setType_name_index(int type_name_index) {
		this.type_name_index = type_name_index;
	}

	public int getConst_name_index() {
		return const_name_index;
	}

	public void setConst_name_index(int const_name_index) {
		this.const_name_index = const_name_index;
	}

	@Override
	public ElementValueAttribute setValue(DataInput di) throws IOException {
		type_name_index = di.readUnsignedShort();
		const_name_index = di.readUnsignedShort();
		return this;
	}

}
