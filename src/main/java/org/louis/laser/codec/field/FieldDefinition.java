package org.louis.laser.codec.field;

import java.lang.reflect.Field;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public abstract class FieldDefinition {

	protected Field field;

	public FieldDefinition(Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return field.getName();
	}

	protected abstract void encode(Laser laser, Context context, OutputStream out, Object obj) throws Exception;

	protected abstract void decode(Laser laser, Context context, InputStream in, Object obj) throws Exception;

}
