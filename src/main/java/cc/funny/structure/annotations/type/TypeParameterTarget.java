package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class TypeParameterTarget implements TargetType {

	private int type_parameter_index;//u1

	public int getType_parameter_index() {
		return type_parameter_index;
	}

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		type_parameter_index = di.readUnsignedByte();
		return this;
	}
	
}
