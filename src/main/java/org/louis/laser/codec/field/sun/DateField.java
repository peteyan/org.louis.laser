package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;
import java.util.Date;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class DateField extends FieldDefinition {

	public DateField(Field field) {
		super(field);
	}

	@Override
	protected void encode(Laser laser, Context context, OutputStream output, Object obj) throws Exception {
		Date date = (Date) field.get(obj);
		if (date == null) {
			output.writeLong(-1);
			return;
		}
		output.writeLong(date.getTime());
	}

	@Override
	protected void decode(Laser laser, Context context, InputStream in, Object obj) throws Exception {
		long time = in.readLong();
		if (time == -1) {
			return;
		}
		field.set(obj, new Date(time));
	}

}
