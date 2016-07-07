package cc.funny.structure.annotations;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.ElementValuePair;

public class AnnotationDefault extends AttributeInfo {

	private ElementValuePair.ElementValue default_value;

	public ElementValuePair.ElementValue getDefault_value() {
		return default_value;
	}

	public void setDefault_value(ElementValuePair.ElementValue default_value) {
		this.default_value = default_value;
	}

}
