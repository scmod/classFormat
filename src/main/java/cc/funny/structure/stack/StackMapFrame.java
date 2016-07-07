package cc.funny.structure.stack;

import java.io.DataInput;
import java.io.IOException;

import javax.management.RuntimeErrorException;

public abstract class StackMapFrame {

	private int frame_type;
	private int offset_delta;// u1?

	public int getOffset_delta() {
		return offset_delta;
	}

	public void setOffset_delta(int offset_delta) {
		this.offset_delta = offset_delta;
	}

	public int getFrame_type() {
		return frame_type;
	}

	public void setFrame_type(int frame_type) {
		this.frame_type = frame_type;
	}

	public abstract StackMapFrame setValue(DataInput di) throws IOException;

	public static StackMapFrame getStackMapFrame(DataInput di)
			throws IOException {
		int frame_type = di.readUnsignedByte();
		StackMapFrame f = null;
		if (frame_type >= 0 && frame_type <= 63) {
			f = new SameFrame();
		} else if (frame_type >= 64 && frame_type <= 127) {
			f = new SameLocals1StackItemFrame();
		} else if (frame_type == 247) {
			f = new SameLocals1StackItemExtendedFrame();
		} else if (frame_type >= 248 && frame_type <= 250) {
			f = new ChopFrame();
		} else if (frame_type == 251) {
			f = new SameFrameExtended();
		} else if (frame_type >= 252 && frame_type <= 254) {
			f = new AppendFrame();
		} else if (frame_type == 255) {
			f = new FullFrame();
		} else {
			throw new RuntimeException("unknown frame_type");
		}
		f.setFrame_type(frame_type);
		f.setValue(di);
		return f;
	}

}
