package cc.funny.structure.stack;

import java.io.DataInput;
import java.io.IOException;

public class ChopFrame extends StackMapFrame {

	@Override
	public StackMapFrame setValue(DataInput di) throws IOException {
		/*
		 * The offset_delta value for the frame is given explicitly
		 */
		setOffset_delta(di.readUnsignedShort());
		return this;
	}

}
