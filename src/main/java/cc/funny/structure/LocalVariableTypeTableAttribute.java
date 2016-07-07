package cc.funny.structure;

import java.util.List;

public class LocalVariableTypeTableAttribute extends AttributeInfo {

	private int local_variable_type_table_length;// u2
	private List<LocalVariableType> local_variable_type_table;

	public int getLocal_variable_type_table_length() {
		return local_variable_type_table_length;
	}

	public void setLocal_variable_type_table_length(
			int local_variable_type_table_length) {
		this.local_variable_type_table_length = local_variable_type_table_length;
	}

	public List<LocalVariableType> getLocal_variable_type_table() {
		return local_variable_type_table;
	}

	public void setLocal_variable_type_table(
			List<LocalVariableType> local_variable_type_table) {
		this.local_variable_type_table = local_variable_type_table;
	}

	public static class LocalVariableType {
		private int start_pc;// u2
		private int length;// u2
		private int name_index;// u2
		private int signature_index;// u2
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

		public int getSignature_index() {
			return signature_index;
		}

		public void setSignature_index(int signature_index) {
			this.signature_index = signature_index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		

	}

}
