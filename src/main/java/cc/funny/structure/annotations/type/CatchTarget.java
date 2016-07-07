package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class CatchTarget implements TargetType {

	private int exception_table_index;//u2

	public int getException_table_index() {
		return exception_table_index;
	}

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		exception_table_index = di.readUnsignedShort();
		return this;
	}

}
