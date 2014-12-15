package org.louis.laser.codec.field.sun;

import java.util.HashMap;
import java.util.Map;

import org.louis.laser.Laser;
import org.louis.laser.codec.Codec;
import org.louis.laser.codec.field.FieldDefinition;
import org.louis.laser.codec.field.FieldFactory;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SunFieldFactory implements FieldFactory {

	private Map<Class<?>, FieldDefinition<?>> definitions = new HashMap<Class<?>, FieldDefinition<?>>();

	@Override
	public FieldDefinition<?> newFieldDefinition(Laser laser, Class<?> fieldType) {
		FieldDefinition<?> definition = definitions.get(fieldType);
		if (definition != null) {
			return definition;
		}
		if (fieldType == boolean.class) {
			definition = new BooleanField(false);
		} else if (fieldType == Boolean.class) {
			definition = new BooleanField(true);
		} else if (fieldType == byte.class) {
			definition = new ByteField(false);
		} else if (fieldType == Byte.class) {
			definition = new ByteField(true);
		} else if (fieldType == char.class) {
			definition = new CharField(false);
		} else if (fieldType == Character.class) {
			definition = new CharField(true);
		} else if (fieldType == double.class) {
			definition = new DoubleField(false);
		} else if (fieldType == Double.class) {
			definition = new DoubleField(true);
		} else if (fieldType == float.class) {
			definition = new FloatField(false);
		} else if (fieldType == Float.class) {
			definition = new FloatField(true);
		} else if (fieldType == int.class) {
			definition = new IntField(false);
		} else if (fieldType == Integer.class) {
			definition = new IntField(true);
		} else if (fieldType == long.class) {
			definition = new LongField(false);
		} else if (fieldType == Long.class) {
			definition = new LongField(true);
		} else if (fieldType == short.class) {
			definition = new ShortField(false);
		} else if (fieldType == Short.class) {
			definition = new ShortField(true);
		} else if (fieldType == String.class) {
			definition = new StringField();
		} else {
			Codec<?> codec = laser.getCodec(fieldType);
			if (codec == null) {
				throw new NullPointerException("type " + fieldType + ",codec is null");
			}
			definition = new ObjectField(fieldType, codec);
		}
		definitions.put(fieldType, definition);
		return definition;

	}

	@Override
	public FieldDefinition<?> newFieldDefinition(Laser laser, Class<?> fieldType, Class<?>[] genericTypes) {
		Codec<?> codec = laser.getCodec(fieldType, genericTypes);
		return new ObjectField(fieldType, codec);
	}
}
