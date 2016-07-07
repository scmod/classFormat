package cc.funny.structure.annotations;

import java.util.List;

/**
 * similar to RuntimeAnnotation,but not a subclass of AttributeInfo
 * 
 * @author John Smith
 *
 */
public class ParameterAnnotation {

	private int num_annotations;// u2
	private List<Annotation> annotations;

	public int getNum_annotations() {
		return num_annotations;
	}

	public void setNum_annotations(int num_annotations) {
		this.num_annotations = num_annotations;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

}
