package cc.funny.structure.stack;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.stack.verification.VerificationInfo;

public class FullFrame extends StackMapFrame {

	private int  number_of_locals;//u2
	private List<VerificationInfo> locals;
	private int number_of_stack_items;//u2
	private List<VerificationInfo> stack;
	
	@Override
	public StackMapFrame setValue(DataInput di) throws IOException {
		/*
		 * The offset_delta value for the frame is given explicitly
		 */
		setOffset_delta(di.readUnsignedShort());
		
		number_of_locals = di.readUnsignedShort();
		if(number_of_locals > 0) {
			locals = new ArrayList<>(number_of_locals);
			for(int i=0; i<number_of_locals; i++) {
				locals.add(VerificationInfo.getVerificationInfo(di));
			}
		}
		
		number_of_stack_items = di.readUnsignedShort();
		if(number_of_stack_items > 0) {
			stack = new ArrayList<>(number_of_stack_items);
			for(int i=0; i<number_of_stack_items; i++) {
				stack.add(VerificationInfo.getVerificationInfo(di));
			}
		}
		return this;
	}

	public int getNumber_of_locals() {
		return number_of_locals;
	}

	public void setNumber_of_locals(int number_of_locals) {
		this.number_of_locals = number_of_locals;
	}

	public List<VerificationInfo> getLocals() {
		return locals;
	}

	public int getNumber_of_stack_items() {
		return number_of_stack_items;
	}

	public void setNumber_of_stack_items(int number_of_stack_items) {
		this.number_of_stack_items = number_of_stack_items;
	}

	public List<VerificationInfo> getStack() {
		return stack;
	}

}
