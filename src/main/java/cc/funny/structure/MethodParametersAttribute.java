package cc.funny.structure;

import java.util.List;

public class MethodParametersAttribute extends AttributeInfo {

	private int parameters_count;// u1
	private List<Parameter> parameters;

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public int getParameters_count() {
		return parameters_count;
	}

	public void setParameters_count(int parameters_count) {
		this.parameters_count = parameters_count;
	}

	public static class Parameter {
		private int name_index;// u2
		private int access_flags;// u2

		public int getName_index() {
			return name_index;
		}

		public void setName_index(int name_index) {
			this.name_index = name_index;
		}

		public int getAccess_flags() {
			return access_flags;
		}

		public void setAccess_flags(int access_flags) {
			this.access_flags = access_flags;
		}
	}
}
