package cc.funny.type_value.constantPool;


public class CString extends ConstantPoolTypeValue<Integer> {

	public CString(Integer value) {
		super("String", value);
	}

	@Override
	public String getStringValue() {
		return "#" + value.toString();
	}

}
