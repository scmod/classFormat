package cc.funny.structure.stack.verification;

import java.io.DataInput;
import java.io.IOException;

public class ObjectVariableInfo extends VerificationInfo {

	private int cpool_index;// u2

	public void setCpool_index(int cpool_index) {
		this.cpool_index = cpool_index;
	}

	public int getCpool_index() {
		return cpool_index;
	}

	@Override
	public VerificationInfo setValue(DataInput di) throws IOException {
		cpool_index = di.readUnsignedShort();
		return this;
	}

}
