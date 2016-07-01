package cc.funny.type_value.constantPool;


public abstract class CRef extends ConstantPoolTypeValue<Integer[]> {

	public CRef(String type) {
		super(type, null);
	}
	
	@Override
	public String getStringValue() {
		return "#" + value[0] + "." + "#" + value[1];
	}
	
	public void setValue(Integer... value) {
		this.value = value;
	}

}
