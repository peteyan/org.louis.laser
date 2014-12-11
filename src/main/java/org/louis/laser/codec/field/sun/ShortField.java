package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ShortField extends FieldDefinition {

	public ShortField(Field field) {
		super(field);
	}

	@Override
	protected void encode(Laser laser, Context context, OutputStream output, Object obj) throws Exception {
		output.writeShort(field.getShort(obj));
	}

	@Override
	protected void decode(Laser laser, Context context, InputStream in, Object obj) throws Exception {
		field.setShort(obj, in.readShort());
	}

}
