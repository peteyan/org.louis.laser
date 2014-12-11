package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Laser;
import org.louis.laser.codec.Codec;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

@SuppressWarnings("unchecked")
public class ObjectField<T> extends FieldDefinition {

	private Codec<T> codec = null;
	private Class<T> fieldType;

	public ObjectField(Field field, Class<T> fieldType, Codec<T> codec) {
		super(field);
		this.fieldType = fieldType;
		this.codec = codec;
	}

	protected T get(Object obj) throws IllegalArgumentException, IllegalAccessException {
		return (T) field.get(obj);
	}

	protected void set(Object obj, T value) throws IllegalArgumentException, IllegalAccessException {
		field.set(obj, value);
	}

	@Override
	protected void encode(Laser laser, OutputStream output, Object obj) throws Exception {
		T value = get(obj);
		output.writeBoolean(value != null);
		if (value == null) {
			return;
		}
		codec.encode(laser, output, value);
	}

	@Override
	protected void decode(Laser laser, InputStream in, Object obj) throws Exception {
		boolean b = in.readBoolean();
		if (b) {
			T value = codec.decode(laser, in, fieldType);
			set(obj, value);
		} else {
			set(obj, null);
		}
	}

}
