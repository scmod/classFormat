package cc.funny.structure.annotations;

import java.util.List;

import cc.funny.structure.AttributeInfo;

public class RuntimeTypeAnnotation extends AttributeInfo {

	private int num_annotations;// u2
	private List<TypeAnnotation> annotations;

	public List<TypeAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<TypeAnnotation> annotations) {
		this.annotations = annotations;
	}

	public int getNum_annotations() {
		return num_annotations;
	}

	public void setNum_annotations(int num_annotations) {
		this.num_annotations = num_annotations;
	}

}
