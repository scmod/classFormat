package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

public class EmptyTarget implements TargetType {


	@Override
	public TargetType setValue(DataInput di) throws IOException {
		return this;
	}

}
