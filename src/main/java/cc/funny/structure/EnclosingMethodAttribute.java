package cc.funny.structure;

public class EnclosingMethodAttribute extends AttributeInfo {

	private int class_index;//u2
	private int method_index;// u2

	public int getClass_index() {
		return class_index;
	}

	public void setClass_index(int class_index) {
		this.class_index = class_index;
	}

	public int getMethod_index() {
		return method_index;
	}

	public void setMethod_index(int method_index) {
		this.method_index = method_index;
	}

}
