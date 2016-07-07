package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class ThrowsTarget implements TargetType {

	private int throws_type_index;

	public int getThrows_type_index() {
		return throws_type_index;
	}

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		throws_type_index = di.readUnsignedShort();
		return this;
	}

}
