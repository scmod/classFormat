package cc.funny.structure.annotations;

import cc.funny.structure.annotations.type.TargetType;
import cc.funny.structure.annotations.type.TypePath;

public class TypeAnnotation extends Annotation {

	private int target_type;// u1
	private TargetType target_info;
	private TypePath target_path;

	public int getTarget_type() {
		return target_type;
	}

	public void setTarget_type(int target_type) {
		this.target_type = target_type;
	}

	public TargetType getTarget_info() {
		return target_info;
	}

	public void setTarget_info(TargetType target_info) {
		this.target_info = target_info;
	}

	public TypePath getTarget_path() {
		return target_path;
	}

	public void setTarget_path(TypePath target_path) {
		this.target_path = target_path;
	}

}
