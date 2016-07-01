package cc.funny.structure;

public class InnerClassesAttribute extends AttributeInfo {

	private int number_of_classes;// u2
	private ClassesInfo[] classesInfo;
	
	public int getNumber_of_classes() {
		return number_of_classes;
	}

	public void setNumber_of_classes(int number_of_classes) {
		this.number_of_classes = number_of_classes;
	}

	public ClassesInfo[] getClassesInfo() {
		return classesInfo;
	}

	public void setClassesInfo(ClassesInfo[] classesInfo) {
		this.classesInfo = classesInfo;
	}

	public static class ClassesInfo {
		private int inner_class_info_index;// u2
		private int outer_class_info_index;// u2
		private int inner_name_index;// u2
		private int inner_class_access_flags;// u2

		public int getInner_class_info_index() {
			return inner_class_info_index;
		}

		public void setInner_class_info_index(int inner_class_info_index) {
			this.inner_class_info_index = inner_class_info_index;
		}

		public int getOuter_class_info_index() {
			return outer_class_info_index;
		}

		public void setOuter_class_info_index(int outer_class_info_index) {
			this.outer_class_info_index = outer_class_info_index;
		}

		public int getInner_name_index() {
			return inner_name_index;
		}

		public void setInner_name_index(int inner_name_index) {
			this.inner_name_index = inner_name_index;
		}

		public int getInner_class_access_flags() {
			return inner_class_access_flags;
		}

		public void setInner_class_access_flags(int inner_class_access_flags) {
			this.inner_class_access_flags = inner_class_access_flags;
		}
	}

}
