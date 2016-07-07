package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class OffsetTarget implements TargetType {

	private int offset;//u2

	public int getOffset() {
		return offset;
	}

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		offset = di.readUnsignedShort();
		return this;
	}

}
