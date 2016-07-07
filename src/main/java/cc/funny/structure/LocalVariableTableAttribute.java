package cc.funny.structure;

import java.util.List;

public class LocalVariableTableAttribute extends AttributeInfo {

	private int local_variable_table_length;// u2
	private List<LocalVariable> local_variable_table;

	public List<LocalVariable> getLocal_variable_table() {
		return local_variable_table;
	}

	public void setLocal_variable_table(List<LocalVariable> local_variable_table) {
		this.local_variable_table = local_variable_table;
	}

	public int getLocal_variable_table_length() {
		return local_variable_table_length;
	}

	public void setLocal_variable_table_length(int local_variable_table_length) {
		this.local_variable_table_length = local_variable_table_length;
	}

	public static class LocalVariable {
		private int start_pc;// u2
		private int length;// u2
		private int name_index;// u2
		private int descriptor_index;// u2
		private int index;// u2

		public int getStart_pc() {
			return start_pc;
		}

		public void setStart_pc(int start_pc) {
			this.start_pc = start_pc;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public int getName_index() {
			return name_index;
		}

		public void setName_index(int name_index) {
			this.name_index = name_index;
		}

		public int getDescriptor_index() {
			return descriptor_index;
		}

		public void setDescriptor_index(int descriptor_index) {
			this.descriptor_index = descriptor_index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}

}
