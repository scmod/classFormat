package cc.funny.structure.stack;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.stack.verification.VerificationInfo;

public class AppendFrame extends StackMapFrame {

	private List<VerificationInfo> locals;
	
	@Override
	public StackMapFrame setValue(DataInput di) throws IOException {
		/*
		 * The offset_delta value for the frame is given explicitly
		 */
		setOffset_delta(di.readUnsignedShort());
		
		int locals_length = getFrame_type() - 251;
		locals = new ArrayList<>(locals_length);
		for(int i=0; i<locals_length; i++) {
			locals.add(VerificationInfo.getVerificationInfo(di));
		}
		return this;
	}

}
