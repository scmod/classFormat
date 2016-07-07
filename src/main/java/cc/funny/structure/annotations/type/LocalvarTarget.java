package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalvarTarget implements TargetType {

	private int table_length;// u2
	private List<Table> table;

	@Override
	public TargetType setValue(DataInput di) throws IOException {
		table_length = di.readUnsignedShort();
		if (table_length > 0) {
			table = new ArrayList<>(table_length);
			for (int i = 0; i < table_length; i++) {
				Table t = new Table();
				t.start_pc = di.readUnsignedShort();
				t.length = di.readUnsignedShort();
				t.index = di.readUnsignedShort();
				table.add(t);
			}
		}
		return this;
	}

	public static class Table {
		private int start_pc;// u2
		private int length;// u2
		private int index;// u2

		public int getStart_pc() {
			return start_pc;
		}

		public int getLength() {
			return length;
		}

		public int getIndex() {
			return index;
		}
	}

}
