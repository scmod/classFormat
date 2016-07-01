package cc.funny.type_value;

/**
 * generic here seems useless.... 
 * if i want to getValue from List<TypeValue<?>>... 
 * to cast TypeValue<?> to TypeValue<T> is unavoidable and
 * this is the same thing if "value" is set to be an Object
 * 
 * @author John Smith
 * @param <T>
 */
public abstract class TypeValue<T> {

	protected String type;
	protected T value;

	// public TypeValue() {
	// }

	public TypeValue(String type, T value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public T getValue() {
		return value;
	}

	public abstract String getStringValue();
}
