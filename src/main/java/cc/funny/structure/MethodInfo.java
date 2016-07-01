package cc.funny.structure;

import java.util.List;

public class MethodInfo {
	
	private int access_flags;// u2
	private int name_index;// u2
	private int escriptor_index;// u2
	private int attributes_count;// u2
	private List<AttributeInfo> attributes;

	public int getAccess_flags() {
		return access_flags;
	}

	public void setAccess_flags(int access_flags) {
		this.access_flags = access_flags;
	}

	public int getName_index() {
		return name_index;
	}

	public void setName_index(int name_index) {
		this.name_index = name_index;
	}

	public int getEscriptor_index() {
		return escriptor_index;
	}

	public void setEscriptor_index(int escriptor_index) {
		this.escriptor_index = escriptor_index;
	}

	public int getAttributes_count() {
		return attributes_count;
	}

	public void setAttributes_count(int attributes_count) {
		this.attributes_count = attributes_count;
	}

	public List<AttributeInfo> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeInfo> attributes) {
		this.attributes = attributes;
	}

}