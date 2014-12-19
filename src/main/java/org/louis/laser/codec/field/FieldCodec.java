package org.louis.laser.codec.field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.Codec;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class FieldCodec<T> implements Codec<T> {

	private Laser laser;
	private Class<T> type;
	private List<Field> fields = new ArrayList<Field>();
	private Map<Field, FieldDefinition<?>> wrappedFields = new HashMap<Field, FieldDefinition<?>>();

	public FieldCodec(Laser laser, Class<T> type) {
		this.laser = laser;
		this.type = type;
		this.init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		Class<T> type = this.type;
		if (type.isInterface()) {
			return;
		}
		while (!(type == Object.class)) {
			Field[] declaredFields = type.getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				Class<?> fieldType = field.getType();
				Type genericType = field.getGenericType();
				FieldDefinition<?> fieldDefinition = null;
				if (fieldType == genericType) {
					fieldDefinition = laser.getFieldFactory().newFieldDefinition(laser, fieldType);
				} else {
					Class<?>[] genericTypes = getGenericTypes(genericType);
					fieldDefinition = laser.getFieldFactory().newFieldDefinition(laser, fieldType, genericTypes);
				}
				wrappedFields.put(field, fieldDefinition);
				this.fields.add(field);
			}
			type = (Class<T>) type.getSuperclass();
		}
		Collections.sort(fields, new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	protected Class<?>[] getGenericTypes(Type genericType) {
		if (genericType instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) genericType).getActualTypeArguments();
			Class<?>[] generics = new Class<?>[types.length];
			for (int i = 0, n = types.length; i < n; i++) {
				Type actualType = types[i];
				if (actualType instanceof Class) {
					generics[i] = (Class<?>) actualType;
				}
			}
			return generics;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void encode(Laser laser, Context context, OutputStream out, T value) throws Exception {
		for (Field field : fields) {
			FieldDefinition<Object> fieldDefinition = (FieldDefinition<Object>) wrappedFields.get(field);
			fieldDefinition.encode(laser, context, field, out, field.get(value));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T decode(Laser laser, Context context, InputStream in, Class<T> type) throws Exception {
		T obj = laser.newInstance(type);
		for (Field field : fields) {
			FieldDefinition<Object> fieldDefinition = (FieldDefinition<Object>) wrappedFields.get(field);
			Object value = fieldDefinition.decode(laser, context, field, in);
			field.set(obj, value);
		}
		return obj;
	}
}
