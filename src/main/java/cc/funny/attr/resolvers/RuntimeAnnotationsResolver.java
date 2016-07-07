package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.ArrayValueAttribute;
import cc.funny.structure.AttributeInfo;
import cc.funny.structure.ClassInfoAttribute;
import cc.funny.structure.ConstantValueAttribute;
import cc.funny.structure.ElementValueAttribute;
import cc.funny.structure.ElementValuePair;
import cc.funny.structure.EnumConstValueAttribute;
import cc.funny.structure.annotations.Annotation;
import cc.funny.structure.annotations.RuntimeAnnotation;

public class RuntimeAnnotationsResolver implements ClassFileAttributeResolver,
		FieldInfoAttributeResolver, MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index, "RuntimeVisibleAnnotations")
				|| canHandle(attribute_name_index,
						"RuntimeInvisibleAnnotations");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		/*
		 * currently no difference between RuntimeVisibleAnnotations and
		 * RuntimeInvisibleAnnotations, so just leave it instead of judge which
		 * type to new..
		 */
		RuntimeAnnotation attr = new RuntimeAnnotation();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes
		 */
		attr.setAttribute_length(di.readInt());

		int num_annotations = di.readUnsignedShort();
		attr.setNum_annotations(num_annotations);
		List<Annotation> list = null;
		if (num_annotations > 0) {
			list = new ArrayList<>(num_annotations);
			for (int i = 0; i < num_annotations; i++) {
				list.add(resolveAnnotation(di));
			}
		}
		attr.setAnnotations(list);
		return attr;
	}

	public static Annotation resolveAnnotation(DataInput di) throws IOException {
		Annotation a = new Annotation();
		a.setType_index(di.readUnsignedShort());
		int num_element_value_pairs = di.readUnsignedShort();
		a.setNum_element_value_pairs(num_element_value_pairs);
		List<ElementValuePair> list = null;
		if (num_element_value_pairs > 0) {
			list = new ArrayList<>(num_element_value_pairs);
			for (int i = 0; i < num_element_value_pairs; i++) {
				list.add(resolveElementValuePair(di));
			}
		}
		a.setElement_value_pairs(list);
		return a;
	}

	public static ElementValuePair resolveElementValuePair(DataInput di)
			throws IOException {
		ElementValuePair evp = new ElementValuePair();
		evp.setElement_name_index(di.readUnsignedShort());
		evp.setValue(resolveElementValue(di));
		return evp;
	}

	public static ElementValuePair.ElementValue resolveElementValue(DataInput di)
			throws IOException {
		ElementValuePair.ElementValue ev = new ElementValuePair.ElementValue();
		int tag = di.readUnsignedByte();
		ev.setTag(tag);
		ElementValueAttribute eva = null;
		if (tag == 'B' || tag == 'C' || tag == 'D' || tag == 'F' || tag == 'I'
				|| tag == 'J' || tag == 'S' || tag == 'Z' || tag == 's') {
			eva = new ConstantValueAttribute();
		} else if (tag == 'e') {
			eva = new EnumConstValueAttribute();
		} else if (tag == 'c') {
			eva = new ClassInfoAttribute();
		} else if (tag == '@') {
			eva = new Annotation();
		} else if (tag == '[') {
			eva = new ArrayValueAttribute();
		} else {
			throw new RuntimeException("what's this?");
		}
		eva.setValue(di);
		ev.setValue(eva);
		return ev;
	}

}
