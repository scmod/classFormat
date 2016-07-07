package cc.funny.structure.stack;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.stack.verification.VerificationInfo;

public class SameLocals1StackItemExtendedFrame extends StackMapFrame {

	private List<VerificationInfo> stack = new ArrayList<>(1);

	public List<VerificationInfo> getStack() {
		return stack;
	}

	@Override
	public StackMapFrame setValue(DataInput di) throws IOException {
		/*
		 * The offset_delta value for the frame is given explicitly
		 */
		setOffset_delta(di.readUnsignedShort());
		stack.add(VerificationInfo.getVerificationInfo(di));
		return this;
	}

}
