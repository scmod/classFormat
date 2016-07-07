package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class FormalParameterTarget implements TargetType {

	private int formal_parameter_index;//u1

	public int getFormal_parameter_index() {
		return formal_parameter_index;
	}

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		formal_parameter_index = di.readUnsignedByte();
		return this;
	}

}
