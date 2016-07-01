package cc.funny.structure;

import java.util.List;

/**
 * for MethodInfo
 * 
 * @author John Smith
 */
public class CodeAttribute extends AttributeInfo {

	private int max_stack;// u2
	private int max_locals;// u2
	private int code_length;// u4
	private byte[] code;// u1
	private int exception_table_length;// u2
	private List<ExceptionInfo> exception_table;
	private int attributes_count;// u2
	List<AttributeInfo> attributes;

	public int getMax_stack() {
		return max_stack;
	}

	public void setMax_stack(int max_stack) {
		this.max_stack = max_stack;
	}

	public int getMax_locals() {
		return max_locals;
	}

	public void setMax_locals(int max_locals) {
		this.max_locals = max_locals;
	}

	public int getCode_length() {
		return code_length;
	}

	public void setCode_length(int code_length) {
		this.code_length = code_length;
	}

	public byte[] getCode() {
		return code;
	}

	public void setCode(byte[] code) {
		this.code = code;
	}

	public int getException_table_length() {
		return exception_table_length;
	}

	public void setException_table_length(int exception_table_length) {
		this.exception_table_length = exception_table_length;
	}

	public List<ExceptionInfo> getException_table() {
		return exception_table;
	}

	public void setException_table(List<ExceptionInfo> exception_table) {
		this.exception_table = exception_table;
	}

	public int getAttributes_count() {
		return attributes_count;
	}

	public void setAttributes_count(int attributes_count) {
		this.attributes_count = attributes_count;
	}

	public List<AttributeInfo> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeInfo> attributes) {
		this.attributes = attributes;
	}

	public static class ExceptionInfo {
		private int start_pc;// u2
		private int end_pc;// u2
		private int handler_pc;// u2
		private int catch_type;// u2

		public int getStart_pc() {
			return start_pc;
		}

		public void setStart_pc(int start_pc) {
			this.start_pc = start_pc;
		}

		public int getEnd_pc() {
			return end_pc;
		}

		public void setEnd_pc(int end_pc) {
			this.end_pc = end_pc;
		}

		public int getHandler_pc() {
			return handler_pc;
		}

		public void setHandler_pc(int handler_pc) {
			this.handler_pc = handler_pc;
		}

		public int getCatch_type() {
			return catch_type;
		}

		public void setCatch_type(int catch_type) {
			this.catch_type = catch_type;
		}

	}

}
