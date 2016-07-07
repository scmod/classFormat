package cc.funny.structure;

import java.io.DataInput;
import java.io.IOException;

public class ConstantValueAttribute extends AttributeInfo implements ElementValueAttribute {

	private int constantvalue_index;// u2

	public int getConstantvalue_index() {
		return constantvalue_index;
	}

	public void setConstantvalue_index(int constantvalue_index) {
		this.constantvalue_index = constantvalue_index;
	}

	@Override
	public ElementValueAttribute setValue(DataInput di) throws IOException {
		constantvalue_index = di.readUnsignedShort();
		return this;
	}

}
