package cc.funny.structure;

import java.io.DataInput;
import java.io.IOException;

public class ClassInfoAttribute implements ElementValueAttribute {

	private int class_info_index;// u2

	public int getClass_info_index() {
		return class_info_index;
	}

	public void setClass_info_index(int class_info_index) {
		this.class_info_index = class_info_index;
	}

	@Override
	public ElementValueAttribute setValue(DataInput di) throws IOException {
		class_info_index = di.readUnsignedShort();
		return this;
	}
	
}
