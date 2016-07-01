package cc.funny.type_value.constantPool;

import cc.funny.type_value.TypeValue;

public abstract class ConstantPoolTypeValue<T> extends TypeValue<T> {

	public ConstantPoolTypeValue(String type, T value) {
		super(type, value);
	}

}
