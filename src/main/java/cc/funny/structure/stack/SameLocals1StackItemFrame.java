package cc.funny.structure.stack;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.stack.verification.VerificationInfo;

public class SameLocals1StackItemFrame extends StackMapFrame {

	private List<VerificationInfo> stack = new ArrayList<>(1);

	public List<VerificationInfo> getStack() {
		return stack;
	}

	@Override
	public StackMapFrame setValue(DataInput di) throws IOException {
		/** 64-127 */
		/*
		 * The offset_delta value for the frame is given by the formula
		 * frame_type - 64
		 */
		setOffset_delta(getFrame_type() - 64);
		stack.add(VerificationInfo.getVerificationInfo(di));
		return this;
	}

}
