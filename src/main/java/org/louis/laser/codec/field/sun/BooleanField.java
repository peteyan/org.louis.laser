package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class BooleanField implements FieldDefinition<Boolean> {

	private boolean wrapped;

	public BooleanField(boolean wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void encode(Laser laser, Context context, Field field, OutputStream out, Boolean value) throws Exception {
		if (wrapped && out.writeBoolean(value == null)) {
			return;
		}
		out.writeBoolean(value);
	}

	@Override
	public Boolean decode(Laser laser, Context context, Field field, InputStream in) throws Exception {
		if (wrapped && in.readBoolean()) {
			return null;
		}
		return in.readBoolean();
	}

}
