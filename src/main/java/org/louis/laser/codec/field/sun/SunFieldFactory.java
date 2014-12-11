package org.louis.laser.codec.field.sun;

import java.lang.reflect.Field;

import org.louis.laser.Laser;
import org.louis.laser.codec.Codec;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.codec.field.FieldFactory;

public class SunFieldFactory implements FieldFactory {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public FieldDefinition newFieldDefinition(Laser laser, Field field, Class<?> fieldType) {
		FieldDefinition definition = null;
		if (fieldType == boolean.class) {
			definition = new BooleanField(field);
		} else if (fieldType == byte.class) {
			definition = new ByteField(field);
		} else if (fieldType == char.class) {
			definition = new CharField(field);
		} else if (fieldType == double.class) {
			definition = new DoubleField(field);
		} else if (fieldType == float.class) {
			definition = new FloatField(field);
		} else if (fieldType == int.class) {
			definition = new IntField(field);
		} else if (fieldType == long.class) {
			definition = new LongField(field);
		} else if (fieldType == short.class) {
			definition = new ShortField(field);
		} else if (fieldType == String.class) {
			definition = new StringField(field);
		} else {
			Codec<?> codec = laser.getCodec(fieldType);
			if (codec == null) {
				throw new NullPointerException("type " + fieldType + ",codec is null");
			}
			definition = new ObjectField(field, fieldType, codec);
		}
		return definition;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public FieldDefinition newFieldDefinition(Laser laser, Field field, Class<?> fieldType, Class<?>[] genericTypes) {
		Codec<?> codec = laser.getCodec(fieldType, genericTypes);
		return new ObjectField(field, fieldType, codec);
	}
}
