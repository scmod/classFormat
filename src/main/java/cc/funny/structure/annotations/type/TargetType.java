package cc.funny.structure.annotations.type;

import java.io.DataInput;
import java.io.IOException;

/**
 * Table 4.7.20-A,Table 4.7.20-B
 */
public interface TargetType {

	int type_parameter_target_ClassFile = 0x00;
	int type_parameter_target_method_info = 0x01;
	
	int supertype_target_ClassFile = 0x10;
	int type_parameter_bound_target_ClassFile = 0x11;
	int type_parameter_bound_target_method_info = 0x12;
	int empty_target_field_info = 0x13;
	int empty_target__method_info_1 = 0x14;
	int empty_target_method_info_2 = 0x15;
	int formal_parameter_target_method_info = 0x16;
	int throws_target_method_info = 0x17;
	
	int localvar_target_Code_1 = 0x40;
	int localvar_target_Code_2 = 0x41;
	int catch_target_Code_1 = 0x42;
	int offset_target_Code_1 = 0x43;
	int offset_target_Code_2 = 0x44;
	int offset_target_Code_3 = 0x45;
	int offset_target_Code_4 = 0x46;
	int type_argument_target_Code_1 = 0x47;
	int type_argument_target_Code_2 = 0x48;
	int type_argument_target_Code_3 = 0x49;
	int type_argument_target_Code_4 = 0x4A;
	int type_argument_target_Code_5 = 0x4B;
	
	TargetType setValue(DataInput di) throws IOException;
	
}
