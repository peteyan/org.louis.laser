package org.louis.laser.codec.field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.codec.Codec;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class FieldCodec<T> implements Codec<T> {

	private Laser laser;
	private Class<T> type;
	private List<FieldDefinition> definitions = new ArrayList<FieldDefinition>();

	public FieldCodec(Laser laser, Class<T> type) {
		this.laser = laser;
		this.type = type;
		this.init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		if (type.isInterface()) {
			return;
		}
		while (!(type == Object.class)) {
			Field[] fields = type.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				Class<?> fieldType = field.getType();
				Type genericType = field.getGenericType();
				FieldDefinition fieldDefinition = null;
				if (fieldType == genericType) {
					fieldDefinition = laser.getFieldFactory().newFieldDefinition(laser, field, fieldType);
				} else {
					Class<?>[] genericTypes = getGenericTypes(genericType);
					fieldDefinition = laser.getFieldFactory().newFieldDefinition(laser, field, fieldType, genericTypes);
				}
				definitions.add(fieldDefinition);
			}
			type = (Class<T>) type.getSuperclass();
		}
		Collections.sort(definitions, new Comparator<FieldDefinition>() {
			@Override
			public int compare(FieldDefinition o1, FieldDefinition o2) {
				return o1.field.getName().compareTo(o2.field.getName());
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

	@Override
	public void encode(Laser laser, Context context, OutputStream out, T value) throws Exception {
		for (FieldDefinition fieldDefinition : definitions) {
			fieldDefinition.encode(laser, context, out, value);
		}
	}

	@Override
	public T decode(Laser laser, Context context, InputStream in, Class<T> type) throws Exception {
		T obj = laser.newInstance(type);
		for (FieldDefinition fieldDefinition : definitions) {
			fieldDefinition.decode(laser, context, in, obj);
		}
		return obj;
	}
}
