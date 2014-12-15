package org.louis.laser.codec.field;

import java.lang.reflect.Field;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public interface FieldDefinition<T> {

	public void encode(Laser laser, Context context, Field field, OutputStream out, T value) throws Exception;

	public T decode(Laser laser, Context context, Field field, InputStream in) throws Exception;

}
