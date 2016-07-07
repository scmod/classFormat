package cc.funny.structure.annotations;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

import cc.funny.attr.resolvers.RuntimeAnnotationsResolver;
import cc.funny.structure.ElementValueAttribute;
import cc.funny.structure.ElementValuePair;

/**
 * dear...so complex...
 * 
 * @author John Smith
 */
public class Annotation implements ElementValueAttribute {

	private int type_index;// u2
	private int num_element_value_pairs;// u2
	private List<ElementValuePair> element_value_pairs;
	
	public int getType_index() {
		return type_index;
	}

	public void setType_index(int type_index) {
		this.type_index = type_index;
	}

	public int getNum_element_value_pairs() {
		return num_element_value_pairs;
	}

	public void setNum_element_value_pairs(int num_element_value_pairs) {
		this.num_element_value_pairs = num_element_value_pairs;
	}

	public List<ElementValuePair> getElement_value_pairs() {
		return element_value_pairs;
	}

	public void setElement_value_pairs(List<ElementValuePair> element_value_pairs) {
		this.element_value_pairs = element_value_pairs;
	}

	@Override
	public ElementValueAttribute setValue(DataInput di) throws IOException {
		return RuntimeAnnotationsResolver.resolveAnnotation(di);
	}

}
