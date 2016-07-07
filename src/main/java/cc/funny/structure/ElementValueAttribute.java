package cc.funny.structure;

import java.io.DataInput;
import java.io.IOException;

public interface ElementValueAttribute {
	
	/**
	 * is it all right to put the analyse logic here?
	 */
	ElementValueAttribute setValue(DataInput di) throws IOException;

}
