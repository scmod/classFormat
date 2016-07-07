package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class SupertypeTarget implements TargetType {

	private int supertype_target;//u2

	public int getSupertype_target() {
		return supertype_target;
	}

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		supertype_target = di.readUnsignedShort();
		return this;
	}

}
