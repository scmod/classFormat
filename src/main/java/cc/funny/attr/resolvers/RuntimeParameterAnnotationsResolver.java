package cc.funny.attr.resolvers;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.funny.structure.AttributeInfo;
import cc.funny.structure.annotations.Annotation;
import cc.funny.structure.annotations.ParameterAnnotation;
import cc.funny.structure.annotations.RuntimeParameterAnnotation;

public class RuntimeParameterAnnotationsResolver implements MethodInfoAttributeResolver {

	@Override
	public boolean canHandle(int attribute_name_index) {
		return canHandle(attribute_name_index,
				"RuntimeVisibleParameterAnnotations")
				|| canHandle(attribute_name_index,
						"RuntimeInvisibleParameterAnnotations");
	}

	@Override
	public AttributeInfo resolve(int attribute_name_index, DataInput di)
			throws IOException {
		/*
		 * currently no difference between RuntimeVisibleParameterAnnotations
		 * and RuntimeInvisibleParameterAnnotations, leave it instead of
		 * judge which type to new..
		 */
		RuntimeParameterAnnotation attr = new RuntimeParameterAnnotation();
		attr.setAttribute_name_index(attribute_name_index);
		/*
		 * The value of the attribute_length item indicates the length of the
		 * attribute, excluding the initial six bytes
		 */
		attr.setAttribute_length(di.readInt());

		int num_parameters = di.readUnsignedByte();
		attr.setNum_parameters(num_parameters);
		List<ParameterAnnotation> list = null;
		if (num_parameters > 0) {
			list = new ArrayList<>(num_parameters);
			for (int i = 0; i < num_parameters; i++) {
				ParameterAnnotation pa = new ParameterAnnotation();
				int num_annotations = di.readUnsignedShort();
				pa.setNum_annotations(num_annotations);
				List<Annotation> li = null;
				if(num_annotations > 0) {
					li = new ArrayList<>(num_annotations);
					for(int j=0; j<num_annotations; j++) {
						li.add(RuntimeAnnotationsResolver.resolveAnnotation(di));
					}
				}
				pa.setAnnotations(li);
				list.add(pa);
			}
		}
		attr.setParameter_annotations(list);
		return attr;
	}


}
