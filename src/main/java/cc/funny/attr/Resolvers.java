package cc.funny.attr;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

import cc.funny.attr.resolvers.AttributeResolver;
import cc.funny.attr.resolvers.ClassFileAttributeResolver;
import cc.funny.attr.resolvers.CodeAttributeResolver;
import cc.funny.attr.resolvers.FieldInfoAttributeResolver;
import cc.funny.attr.resolvers.MethodInfoAttributeResolver;
import cc.funny.inject.Inject;
import cc.funny.structure.AttributeInfo;

public class Resolvers {

	@Inject
	List<FieldInfoAttributeResolver> fieldsAttrResolvers;
	@Inject
	List<MethodInfoAttributeResolver> methodsAttrResolvers;
	@Inject
	List<ClassFileAttributeResolver> classFileAttrResolvers;
	@Inject
	List<CodeAttributeResolver> codeAttrResolvers;

	public List<FieldInfoAttributeResolver> getFieldsAttrResolvers() {
		return fieldsAttrResolvers;
	}

	// lambda exp manages the checked exception, eh.. really ugly
	public AttributeInfo resolve(InfoType it, int attribute_name_index, DataInput di) throws IOException {
		if (it.equals(InfoType.ClassFile)) {
			return resolve(fieldsAttrResolvers, attribute_name_index, di);
		} else if (it.equals(InfoType.field_info)) {
			return resolve(fieldsAttrResolvers, attribute_name_index, di);
		} else if (it.equals(InfoType.method_info)) {
			return resolve(fieldsAttrResolvers, attribute_name_index, di);
		} else if (it.equals(InfoType.Code)) {
			return resolve(fieldsAttrResolvers, attribute_name_index, di);
		} else {
			// impossible....
			return null;
		}
	}

	private AttributeInfo resolve(List<? extends AttributeResolver> resolvers,
			int attribute_name_index, DataInput di) throws IOException {
		int size = resolvers.size();
		for (int i = 0; i < size; i++) {
			if (resolvers.get(i).canHandle(attribute_name_index)) {
				return resolvers.get(i).resolve(attribute_name_index, di);
			}
		}
		return null;
	}
}
