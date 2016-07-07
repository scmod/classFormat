package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.SignatureAttribute;

public class SignatureResolver implements ClassFileAttributeResolver,
		FieldInfoAttributeResolver, MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "Signature");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		SignatureAttribute attr = new SignatureAttribute();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item of a Signature_attribute
		 * structure must be two.
		 */
		di.skipBytes(4);
		attr.setAttribute_length(2);
		attr.setSignature_index(di.readUnsignedShort());
		return attr;
	}

}
