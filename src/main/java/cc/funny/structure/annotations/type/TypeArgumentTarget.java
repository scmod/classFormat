package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class TypeArgumentTarget implements TargetType {

	private int offset;// u2
	private int type_argument_index;// u1

	public int getOffset() {
		return offset;
	}

	public int getType_argument_index() {
		return type_argument_index;
	}

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		offset = di.readUnsignedShort();
		type_argument_index = di.readUnsignedByte();
		return this;
	}

}
