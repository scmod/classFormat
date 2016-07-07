package cc.funny.structure;


public class ElementValuePair {

	private int element_name_index;// u2
	private ElementValue value;

	public int getElement_name_index() {
		return element_name_index;
	}

	public void setElement_name_index(int element_name_index) {
		this.element_name_index = element_name_index;
	}

	public ElementValue getValue() {
		return value;
	}

	public void setValue(ElementValue value) {
		this.value = value;
	}

	public static class ElementValue {
		private int tag;//u1
		private ElementValueAttribute value;

		public int getTag() {
			return tag;
		}

		public void setTag(int tag) {
			this.tag = tag;
		}

		public ElementValueAttribute getValue() {
			return value;
		}

		public void setValue(ElementValueAttribute value) {
			this.value = value;
		}
	}

}
