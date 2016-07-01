package cc.funny.type_value.constantPool;


public class CClass extends ConstantPoolTypeValue<Integer>{

	public CClass(Integer value) {
		super("Class", value);
	}

	public String getStringValue() {
		return "#" + value;
	}

}
