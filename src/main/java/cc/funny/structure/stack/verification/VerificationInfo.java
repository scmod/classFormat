package cc.funny.structure.stack.verification;

import java.io.DataInput;
import java.io.IOException;

public abstract class VerificationInfo {

	public static final int ITEM_Top = 0;
	public static final int ITEM_Integer = 1;
	public static final int ITEM_Float = 2;
	public static final int ITEM_Double = 3;
	public static final int ITEM_Long = 4;
	public static final int ITEM_Null = 5;
	public static final int ITEM_UninitializedThis = 6;
	public static final int ITEM_Object = 7;
	public static final int ITEM_Uninitialized = 8;

	private int tag;// u1

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public abstract VerificationInfo setValue(DataInput di) throws IOException;

	public static VerificationInfo getVerificationInfo(DataInput di)
			throws IOException {
		VerificationInfo v = null;
		int tag = di.readUnsignedByte();
		switch (tag) {
		case ITEM_Top:
			v = new TopVariableInfo();
			break;
		case ITEM_Integer:
			v = new IntegerVariableInfo();
			break;
		case ITEM_Float:
			v = new FloatVariableInfo();
			break;
		case ITEM_Double:
			v = new DoubleVariableInfo();
			break;
		case ITEM_Long:
			v = new LongVariableInfo();
			break;
		case ITEM_Null:
			v = new NullVariableInfo();
			break;
		case ITEM_UninitializedThis:
			v = new UninitializedThisVariableInfo();
			break;
		case ITEM_Object:
			v = new ObjectVariableInfo();
			break;
		case ITEM_Uninitialized:
			v = new UninitializedVariableInfo();
			break;
		default:
			throw new RuntimeException("unknown verificationInfo");
		}
		v.setTag(tag);
		v.setValue(di);
		return v;
	}
}
