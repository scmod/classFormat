package cc.funny.structure;

public class AttributeInfo {

	private int attribute_name_index;// u2
	private int attribute_length;// u4

	public int getAttribute_name_index() {
		return attribute_name_index;
	}

	public void setAttribute_name_index(int attribute_name_index) {
		this.attribute_name_index = attribute_name_index;
	}

	public int getAttribute_length() {
		return attribute_length;
	}

	public void setAttribute_length(int attribute_length) {
		this.attribute_length = attribute_length;
	}

}
