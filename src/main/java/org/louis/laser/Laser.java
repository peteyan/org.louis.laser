package org.louis.laser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.louis.laser.codec.ArrayListCodec;
import org.louis.laser.codec.BooleanArrayCodec;
import org.louis.laser.codec.BooleanCodec;
import org.louis.laser.codec.ByteArrayCodec;
import org.louis.laser.codec.ByteCodec;
import org.louis.laser.codec.CharArrayCodec;
import org.louis.laser.codec.CharCodec;
import org.louis.laser.codec.Codec;
import org.louis.laser.codec.DateCodec;
import org.louis.laser.codec.DoubleArrayCodec;
import org.louis.laser.codec.DoubleCodec;
import org.louis.laser.codec.EnumCodec;
import org.louis.laser.codec.FloatArrayCodec;
import org.louis.laser.codec.FloatCodec;
import org.louis.laser.codec.HashMapCodec;
import org.louis.laser.codec.HashSetCodec;
import org.louis.laser.codec.IntArrayCodec;
import org.louis.laser.codec.IntCodec;
import org.louis.laser.codec.LinkedHashMapCodec;
import org.louis.laser.codec.LinkedHashSetCodec;
import org.louis.laser.codec.LinkedListCodec;
import org.louis.laser.codec.LongArrayCodec;
import org.louis.laser.codec.LongCodec;
import org.louis.laser.codec.ObjectArrayCodec;
import org.louis.laser.codec.ObjectCodec;
import org.louis.laser.codec.ShortArrayCodec;
import org.louis.laser.codec.ShortCodec;
import org.louis.laser.codec.StringArrayCodec;
import org.louis.laser.codec.StringCodec;
import org.louis.laser.codec.field.FieldCodec;
import org.louis.laser.codec.field.FieldFactory;
import org.louis.laser.codec.field.sun.SunFieldFactory;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public final class Laser {
	private static final Laser laser;
	private static final AtomicInteger HEADER = new AtomicInteger(-1);
	private Map<Class<?>, Codec<?>> codecs = new HashMap<Class<?>, Codec<?>>();
	private Map<Class<?>, Class<? extends Codec>> codecTypes = new HashMap<Class<?>, Class<? extends Codec>>();
	private Map<Class<?>, Integer> typeToHeaders = new HashMap<Class<?>, Integer>();
	private Map<Integer, Class<?>> headerToTypes = new HashMap<Integer, Class<?>>();
	private FieldFactory factory = new SunFieldFactory();
	private Objenesis objenesis = new ObjenesisStd(true);
	private boolean encodeFieldName = true;

	public static Laser laser() {
		return laser;
	}

	private Laser() {
		registerCodec(byte.class, new ByteCodec(false), true);
		registerCodec(Byte.class, new ByteCodec(true), true);
		registerCodec(byte[].class, new ByteArrayCodec(), true);
		registerCodec(char.class, new CharCodec(false), true);
		registerCodec(Character.class, new CharCodec(true), true);
		registerCodec(char[].class, new CharArrayCodec(), true);
		registerCodec(short.class, new ShortCodec(false), true);
		registerCodec(Short.class, new ShortCodec(true), true);
		registerCodec(short[].class, new ShortArrayCodec(), true);
		registerCodec(int.class, new IntCodec(false), true);
		registerCodec(Integer.class, new IntCodec(true), true);
		registerCodec(int[].class, new IntArrayCodec(), true);
		registerCodec(long.class, new LongCodec(false), true);
		registerCodec(Long.class, new LongCodec(true), true);
		registerCodec(long[].class, new LongArrayCodec(), true);
		registerCodec(float.class, new FloatCodec(false), true);
		registerCodec(Float.class, new FloatCodec(true), true);
		registerCodec(float[].class, new FloatArrayCodec(), true);
		registerCodec(double.class, new DoubleCodec(false), true);
		registerCodec(Double.class, new DoubleCodec(true), true);
		registerCodec(double[].class, new DoubleArrayCodec(), true);
		registerCodec(boolean.class, new BooleanCodec(false), true);
		registerCodec(Boolean.class, new BooleanCodec(true), true);
		registerCodec(boolean[].class, new BooleanArrayCodec(), true);
		registerCodec(String.class, new StringCodec(), true);
		registerCodec(String[].class, new StringArrayCodec(), true);
		registerCodec(Object.class, new ObjectCodec(), true);
		registerCodec(Object[].class, new ObjectArrayCodec(), true);
		registerCodec(Date.class, new DateCodec(), true);
		registerCodecType(List.class, ArrayListCodec.class, true);
		registerCodecType(ArrayList.class, ArrayListCodec.class, true);
		registerCodecType(LinkedList.class, LinkedListCodec.class, true);
		registerCodecType(HashSet.class, HashSetCodec.class, true);
		registerCodecType(LinkedHashSet.class, LinkedHashSetCodec.class, true);
		registerCodecType(Map.class, HashMapCodec.class, true);
		registerCodecType(HashMap.class, HashMapCodec.class, true);
		registerCodecType(LinkedHashMap.class, LinkedHashMapCodec.class, true);
		registerCodecType(Enum.class, EnumCodec.class, true);
	}

	public <T> T newInstance(Class<T> type) {
		return getObjectInstantiator(type).newInstance();
	}

	public <T> ObjectInstantiator<T> getObjectInstantiator(Class<T> type) {
		return objenesis.getInstantiatorOf(type);
	}

	public void registerCodecType(Class<?> type, Class<? extends Codec> codecType, boolean createHeader) {
		codecTypes.put(type, codecType);
		if (createHeader) {
			typeToHeaders.put(type, HEADER.incrementAndGet());
			headerToTypes.put(HEADER.get(), type);
		}
	}

	public void registerCodecType(Class<?> type, Class<? extends Codec> codecType) {
		registerCodecType(type, codecType, false);
	}

	protected void registerCodec(Class<?> type, Codec<?> codec, boolean createHeader) {
		codecs.put(type, (Codec<?>) codec);
		if (createHeader) {
			typeToHeaders.put(type, HEADER.incrementAndGet());
			headerToTypes.put(HEADER.get(), type);
		}
	}

	public void registerCodec(Class<?> type, Codec<?> codec) {
		registerCodec(type, codec, false);
	}

	public void removeCodec(Class<?> type) {
		codecs.remove(type);
		typeToHeaders.remove(type);
	}

	public boolean encodeFieldName() {
		return this.encodeFieldName;
	}

	public void encodeFieldName(boolean encodeFieldName) {
		this.encodeFieldName = encodeFieldName;
	}

	public void registerType(Class<?> type) {
		getCodec(type);
	}

	public <T> Codec<T> getCodec(Class<T> type) {
		return (Codec<T>) getCodec(type, null);
	}

	public Codec<?> getCodec(Class<?> type, Class<?>[] genericTypes) {
		Codec<?> codec = (Codec<?>) codecs.get(type);
		if (codec == null) {
			if (type.isEnum()) {
				codec = new EnumCodec((Class<? extends Enum<?>>) type);
				codecs.put(type, (Codec<Object>) codec);
			} else {
				Class<? extends Codec> codecType = codecTypes.get(type);
				if (codecType == null) {
					codec = new FieldCodec(this, type);
					codecs.put(type, (Codec<Object>) codec);
				} else {
					try {
						if (genericTypes != null) {
							int length = genericTypes.length + 1;
							Class<?>[] parameterTypes = new Class<?>[length];
							Object[] args = new Object[length];
							parameterTypes[0] = Laser.class;
							args[0] = this;
							for (int i = 1; i < length; i++) {
								parameterTypes[i] = Class.class;
							}
							System.arraycopy(genericTypes, 0, args, 1, genericTypes.length);
							codec = codecType.getConstructor(parameterTypes).newInstance(args);
						} else {
							codec = codecType.newInstance();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		return codec;
	}

	public void writeClass(Context context, OutputStream out, Class<?> type) throws Exception {
		Integer header = typeToHeaders.get(type);
		if (header != null) {
			out.writeInt(header);
			return;
		}
		header = context.getHeader(type);
		if (header != null) {
			out.writeInt(header);
			return;
		}
		out.writeInt(context.addHeader(type));
		out.writeString(type.getName());
	}

	public <T> Class<T> readClass(Context context, InputStream in) throws Exception {
		int header = in.readInt();
		Class<T> type = (Class<T>) headerToTypes.get(header);
		if (type == null) {
			type = (Class<T>) context.getType(header);
			if (type == null) {
				String clasName = in.readString();
				type = (Class<T>) Class.forName(clasName);
				context.addType(header, type);
			}
		}
		return type;
	}

	public void writeClassAndObject(Context context, OutputStream out, Object obj) throws Exception {
		writeClass(context, out, obj.getClass());
		writeObject(context, out, obj);
	}

	public void writeObject(Context context, OutputStream out, Object obj) throws Exception {
		Codec codec = getCodec(obj.getClass());
		codec.encode(this, context, out, obj);
	}

	public <T> T readClassAndObject(Context context, InputStream in) throws Exception {
		return (T) readObject(context, in, readClass(context, in));
	}

	public <T> T readObject(Context context, InputStream in, Class<T> type) throws Exception {
		Codec codec = getCodec(type);
		return (T) codec.decode(this, context, in, type);
	}

	public FieldFactory getFieldFactory() {
		return factory;
	}

	static {
		laser = new Laser();
	}

}
