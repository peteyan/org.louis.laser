package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;
import java.util.Date;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class DateField implements FieldDefinition<Date> {

	public DateField() {
	}

	@Override
	public void encode(Laser laser, Context context, Field field, OutputStream output, Date value) throws Exception {
		if (value == null) {
			output.writeLong(-1);
			return;
		}
		output.writeLong(value.getTime());
	}

	@Override
	public Date decode(Laser laser, Context context, Field field, InputStream in) throws Exception {
		long time = in.readLong();
		if (time == -1) {
			return null;
		}
		return new Date(time);
	}

}
