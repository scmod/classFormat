package cc.funny.type_value.constantPool;


public class CNameAndType extends ConstantPoolTypeValue<Integer[]> {

	public CNameAndType(Integer... value) {
		super("NameAndType", value);
	}

	@Override
	public String getStringValue() {
		return "#" + value[0] + ":" + "#" + value[1];
	}

}
