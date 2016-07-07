package cc.funny.structure.stack.verification;

import java.io.DataInput;
import java.io.IOException;

public class UninitializedVariableInfo extends VerificationInfo {

	private int offset;// u2

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public VerificationInfo setValue(DataInput di) throws IOException {
		offset = di.readUnsignedShort();
		return this;
	}

}
