package org.louis.laser.codec.field;

import org.louis.laser.Laser;

public interface FieldFactory {

	FieldDefinition<?> newFieldDefinition(Laser laser, Class<?> fieldType);

	FieldDefinition<?> newFieldDefinition(Laser laser, Class<?> fieldType, Class<?>[] genericTypes);
}
