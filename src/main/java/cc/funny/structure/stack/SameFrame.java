package cc.funny.structure.stack;

import java.io.DataInput;
import java.io.IOException;

public class SameFrame extends StackMapFrame {

	@Override
	public StackMapFrame setValue(DataInput di) throws IOException {
		/*
		 * The offset_delta value for the frame is the value of the tag item,
		 * frame_type.
		 */
		setOffset_delta(getFrame_type());
		return this;
	}

}
