package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.Codec;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ObjectField<T> implements FieldDefinition<T> {

	private Codec<T> codec = null;
	private Class<T> fieldType;

	public ObjectField(Class<T> fieldType, Codec<T> codec) {
		this.fieldType = fieldType;
		this.codec = codec;
	}

	@Override
	public void encode(Laser laser, Context context, Field field, OutputStream out, T value) throws Exception {
		if (out.writeBoolean(value == null)) {
			return;
		}
		codec.encode(laser, context, out, value);
	}

	@Override
	public T decode(Laser laser, Context context, Field field, InputStream in) throws Exception {
		if (in.readBoolean()) {
			return null;
		}
		return codec.decode(laser, context, in, fieldType);
	}

}
