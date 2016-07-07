package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.annotations.Annotation;
import cc.funny.structure.annotations.RuntimeTypeAnnotation;
import cc.funny.structure.annotations.TypeAnnotation;
import cc.funny.structure.annotations.type.CatchTarget;
import cc.funny.structure.annotations.type.EmptyTarget;
import cc.funny.structure.annotations.type.FormalParameterTarget;
import cc.funny.structure.annotations.type.LocalvarTarget;
import cc.funny.structure.annotations.type.OffsetTarget;
import cc.funny.structure.annotations.type.SupertypeTarget;
import cc.funny.structure.annotations.type.TargetType;
import cc.funny.structure.annotations.type.ThrowsTarget;
import cc.funny.structure.annotations.type.TypeArgumentTarget;
import cc.funny.structure.annotations.type.TypeParameterBoundTarget;
import cc.funny.structure.annotations.type.TypeParameterTarget;
import cc.funny.structure.annotations.type.TypePath;
import cc.funny.structure.annotations.type.TypePath.Path;
import static cc.funny.structure.annotations.type.TargetType.*;

public class RuntimeTypeAnnotationsResolver implements
		ClassFileAttributeResolver, FieldInfoAttributeResolver,
		MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "RuntimeVisibleTypeAnnotations")
				|| canHandle(attribute_name_index,
						"RuntimeInvisibleTypeAnnotations");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		RuntimeTypeAnnotation attr = new RuntimeTypeAnnotation();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes.
		 */
		attr.setAttribute_length(di.readInt());

		int num_annotations = di.readUnsignedShort();
		attr.setNum_annotations(num_annotations);
		List<TypeAnnotation> list = null;
		if (num_annotations > 0) {
			list = new ArrayList<>(num_annotations);
			for (int i = 0; i < num_annotations; i++) {
				TypeAnnotation ta = new TypeAnnotation();
			}
		}
		return attr;
	}

	private TypeAnnotation resolveTypeAnnotation(DataInput di)
			throws IOException {
		TypeAnnotation ta = new TypeAnnotation();
		int target_type = di.readUnsignedByte();
		ta.setTarget_type(target_type);
		//TargetType
		TargetType tt = null;
		switch (target_type) {
		case type_parameter_target_ClassFile:
		case type_parameter_target_method_info:
			tt = new TypeParameterTarget();
			break;
		case supertype_target_ClassFile:
			tt = new SupertypeTarget();
			break;
		case type_parameter_bound_target_ClassFile:
		case type_parameter_bound_target_method_info:
			tt = new TypeParameterBoundTarget();
			break;
		case empty_target_field_info:
		case empty_target__method_info_1:
		case empty_target_method_info_2:
			tt = new EmptyTarget();
			break;
		case formal_parameter_target_method_info:
			tt = new FormalParameterTarget();
			break;
		case throws_target_method_info:
			tt = new ThrowsTarget();
			break;
		case localvar_target_Code_1:
		case localvar_target_Code_2:
			tt = new LocalvarTarget();
			break;
		case catch_target_Code_1:
			tt = new CatchTarget();
			break;
		case offset_target_Code_1:
		case offset_target_Code_2:
		case offset_target_Code_3:
		case offset_target_Code_4:
			tt = new OffsetTarget();
			break;
		case type_argument_target_Code_1:
		case type_argument_target_Code_2:
		case type_argument_target_Code_3:
		case type_argument_target_Code_4:
		case type_argument_target_Code_5:
			tt = new TypeArgumentTarget();
			break;
		default:
		}
		if(tt != null) {
			tt.setValue(di);
		}
		ta.setTarget_info(tt);
		//TypePath
		TypePath tp = new TypePath();
		int path_length = di.readUnsignedByte();
		tp.setPath_length(path_length);
		List<TypePath.Path> list = null;
		if(path_length > 0) {
			list = new ArrayList<>(path_length);
			for(int i=0; i<path_length; i++) {
				TypePath.Path p = new TypePath.Path();
				p.setType_path_kind(di.readUnsignedByte());
				p.setType_argument_index(di.readUnsignedByte());
				list.add(p);
			}
		}
		tp.setPath(list);
		ta.setTarget_path(tp);
		
		Annotation a = RuntimeAnnotationsResolver.resolveAnnotation(di);
		ta.setType_index(a.getType_index());
		ta.setNum_element_value_pairs(a.getNum_element_value_pairs());
		ta.setElement_value_pairs(a.getElement_value_pairs());
		return ta;
	}

}
