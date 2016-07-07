package cc.funny.structure;

import java.util.List;

import cc.funny.structure.stack.StackMapFrame;


public class StackMapTableAttribute extends AttributeInfo {

	private int number_of_entries;// u1
	private List<StackMapFrame> entries;

	public int getNumber_of_entries() {
		return number_of_entries;
	}

	public void setNumber_of_entries(int number_of_entries) {
		this.number_of_entries = number_of_entries;
	}

	public List<StackMapFrame> getEntries() {
		return entries;
	}

	public void setEntries(List<StackMapFrame> entries) {
		this.entries = entries;
	}

}
