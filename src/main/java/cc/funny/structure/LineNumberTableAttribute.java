package cc.funny.structure;

import java.util.List;

public class LineNumberTableAttribute extends AttributeInfo {

	private int line_number_table_length;// u2
	private List<LineNumber> line_number_table;

	public int getLine_number_table_length() {
		return line_number_table_length;
	}

	public void setLine_number_table_length(int line_number_table_length) {
		this.line_number_table_length = line_number_table_length;
	}

	public List<LineNumber> getLine_number_table() {
		return line_number_table;
	}

	public void setLine_number_table(List<LineNumber> line_number_table) {
		this.line_number_table = line_number_table;
	}

	public static class LineNumber {
		private int start_pc;// u2
		private int line_number;// u2

		public int getStart_pc() {
			return start_pc;
		}

		public void setStart_pc(int start_pc) {
			this.start_pc = start_pc;
		}

		public int getLine_number() {
			return line_number;
		}

		public void setLine_number(int line_number) {
			this.line_number = line_number;
		}
	}

}
