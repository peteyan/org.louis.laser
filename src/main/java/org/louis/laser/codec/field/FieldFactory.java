package org.louis.laser.codec.field;

import java.lang.reflect.Field;

import org.louis.laser.Laser;

public interface FieldFactory {

	FieldDefinition newFieldDefinition(Laser laser, Field field, Class<?> fieldType);

	FieldDefinition newFieldDefinition(Laser laser, Field field, Class<?> fieldType, Class<?>[] genericTypes);
}
