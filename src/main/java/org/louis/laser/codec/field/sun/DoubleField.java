package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Laser;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class DoubleField extends FieldDefinition {

	public DoubleField(Field field) {
		super(field);
	}

	@Override
	protected void encode(Laser laser, OutputStream output, Object obj) throws Exception {
		output.writeDouble(field.getDouble(obj));
	}

	@Override
	protected void decode(Laser laser, InputStream in, Object obj) throws Exception {
		field.setDouble(obj, in.readDouble());
	}

}
