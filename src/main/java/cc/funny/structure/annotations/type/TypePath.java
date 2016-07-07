package cc.funny.structure.annotations.type;

import java.util.List;

public class TypePath {

	private int path_length;// u1
	private List<Path> path;

	public int getPath_length() {
		return path_length;
	}

	public void setPath_length(int path_length) {
		this.path_length = path_length;
	}

	public List<Path> getPath() {
		return path;
	}

	public void setPath(List<Path> path) {
		this.path = path;
	}

	public static class Path {
		private int type_path_kind;// u1
		private int type_argument_index;// u1

		public void setType_path_kind(int type_path_kind) {
			this.type_path_kind = type_path_kind;
		}

		public void setType_argument_index(int type_argument_index) {
			this.type_argument_index = type_argument_index;
		}

		public int getType_path_kind() {
			return type_path_kind;
		}

		public int getType_argument_index() {
			return type_argument_index;
		}
	}

}
