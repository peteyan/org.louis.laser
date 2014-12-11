package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class FloatField extends FieldDefinition {

	public FloatField(Field field) {
		super(field);
	}

	@Override
	protected void encode(Laser laser, Context context, OutputStream output, Object obj) throws Exception {
		output.writeFloat(field.getFloat(obj));
	}

	@Override
	protected void decode(Laser laser, Context context, InputStream in, Object obj) throws Exception {
		field.setFloat(obj, in.readFloat());
	}

}
