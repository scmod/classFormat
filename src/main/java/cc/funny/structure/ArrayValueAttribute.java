package cc.funny.structure;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.attr.resolvers.RuntimeAnnotationsResolver;

public class ArrayValueAttribute implements ElementValueAttribute {

	private int num_values;// u2
	private List<ElementValuePair.ElementValue> values;

	public int getNum_values() {
		return num_values;
	}

	public void setNum_values(int num_values) {
		this.num_values = num_values;
	}

	public List<ElementValuePair.ElementValue> getValues() {
		return values;
	}

	public void setValues(List<ElementValuePair.ElementValue> values) {
		this.values = values;
	}

	@Override
	public ElementValueAttribute setValue(DataInput di) throws IOException {
		num_values = di.readUnsignedShort();
		if (num_values > 0) {
			values = new ArrayList<>(num_values);
			for (int i = 0; i < num_values; i++) {
				values.add(RuntimeAnnotationsResolver.resolveElementValue(di));
			}
		}
		return this;
	}

}
