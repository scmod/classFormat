package cc.funny.type_value.constantPool;


public class CUtf8 extends ConstantPoolTypeValue<String> {

	public CUtf8(String value) {
		super("Utf8", value);
	}

	@Override
	public String getStringValue() {
		return value;
	}

}
