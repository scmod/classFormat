package cc.funny.structure;

import java.util.List;

import cc.funny.type_value.TypeValue;

public class ClassFile {

	int magic;
	int minorVersion;
	int majorVersion;
	int constantPoolCount;
	List<TypeValue<?>> constantPool;
	int accessFlags;
	int thisClass;
	int superClass;
	int interfacesCount;
	List<InterfaceInfo> interfaces;
	int fieldsCount;
	List<FieldInfo> fields;
	int methodsCount;
	List<MethodInfo> methods;
	int attributesCount;
	List<AttributeInfo> attributes;

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	public int getConstantPoolCount() {
		return constantPoolCount;
	}

	public void setConstantPoolCount(int constantPoolCount) {
		this.constantPoolCount = constantPoolCount;
	}

	public List<TypeValue<?>> getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(List<TypeValue<?>> constantPool) {
		this.constantPool = constantPool;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}

	public int getThisClass() {
		return thisClass;
	}

	public void setThisClass(int thisClass) {
		this.thisClass = thisClass;
	}

	public int getSuperClass() {
		return superClass;
	}

	public void setSuperClass(int superClass) {
		this.superClass = superClass;
	}

	public int getInterfacesCount() {
		return interfacesCount;
	}

	public void setInterfacesCount(int interfacesCount) {
		this.interfacesCount = interfacesCount;
	}

	public List<InterfaceInfo> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<InterfaceInfo> interfaces) {
		this.interfaces = interfaces;
	}

	public int getFieldsCount() {
		return fieldsCount;
	}

	public void setFieldsCount(int fieldsCount) {
		this.fieldsCount = fieldsCount;
	}

	public List<FieldInfo> getFields() {
		return fields;
	}

	public void setFields(List<FieldInfo> fields) {
		this.fields = fields;
	}

	public int getMethodsCount() {
		return methodsCount;
	}

	public void setMethodsCount(int methodsCount) {
		this.methodsCount = methodsCount;
	}

	public List<MethodInfo> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodInfo> methods) {
		this.methods = methods;
	}

	public int getAttributesCount() {
		return attributesCount;
	}

	public void setAttributesCount(int attributesCount) {
		this.attributesCount = attributesCount;
	}

	public List<AttributeInfo> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeInfo> attributes) {
		this.attributes = attributes;
	}

}
