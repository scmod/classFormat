package cc.funny.structure.annotations;

import java.util.List;

import cc.funny.structure.AttributeInfo;

public class RuntimeParameterAnnotation extends AttributeInfo {

	private int num_parameters; //u1
	private List<ParameterAnnotation> parameter_annotations;

	public int getNum_parameters() {
		return num_parameters;
	}

	public void setNum_parameters(int num_parameters) {
		this.num_parameters = num_parameters;
	}

	public List<ParameterAnnotation> getParameter_annotations() {
		return parameter_annotations;
	}

	public void setParameter_annotations(
			List<ParameterAnnotation> parameter_annotations) {
		this.parameter_annotations = parameter_annotations;
	}

}
