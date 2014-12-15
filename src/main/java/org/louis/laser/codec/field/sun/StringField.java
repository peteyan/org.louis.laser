package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class StringField implements FieldDefinition<String> {

	@Override
	public void encode(Laser laser, Context context, Field field, OutputStream out, String value) throws Exception {
		out.writeString(value);
	}

	@Override
	public String decode(Laser laser, Context context, Field field, InputStream in) throws Exception {
		return in.readString();
	}
}
