package cc.funny.structure;

public class ExceptionsAttribute extends AttributeInfo {

	private int number_of_exceptions;// u2
	private int[] exception_index_table;// u2

	public int getNumber_of_exceptions() {
		return number_of_exceptions;
	}

	public void setNumber_of_exceptions(int number_of_exceptions) {
		this.number_of_exceptions = number_of_exceptions;
	}

	public int[] getException_index_table() {
		return exception_index_table;
	}

	public void setException_index_table(int[] exception_index_table) {
		this.exception_index_table = exception_index_table;
	}

}
