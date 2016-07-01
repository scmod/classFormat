package cc.funny.type_value.constantPool;


public class CNumber<T extends Number> extends ConstantPoolTypeValue<Number> {

	public CNumber(String type, T value) {
		super(type, value);
	}

	@Override
	public String getStringValue() {
		return value.toString();
	}

}
