package org.louis.laser.codec;

import java.lang.reflect.Modifier;
import java.util.Collection;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;
import org.louis.laser.util.GenericUtil;

public abstract class CollectionCodec<T> implements Codec<Collection<T>> {

	private final boolean isFinal;
	private Codec<T> genericCodec;
	private Class<T> genericType;

	public CollectionCodec() {
		this.isFinal = false;
	}

	public CollectionCodec(Laser laser, Class<T> genericType) {
		this.isFinal = Modifier.isFinal(genericType.getModifiers());
		if (this.isFinal) {
			this.genericType = genericType;
			this.genericCodec = laser.getCodec(genericType);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void encode(Laser laser, OutputStream out, Collection<T> values) throws Exception {
		if (values == null) {
			out.writeInt(-1);
			return;
		}
		int size = values.size();
		out.writeInt(size);
		if (size > 0) {
			boolean sameGeneric = false;
			if (!isFinal) {
				sameGeneric = GenericUtil.sameGeneric(values);
				out.writeBoolean(sameGeneric);
			}
			Codec<T> genericCodec = this.genericCodec;
			boolean writeClass = sameGeneric;
			for (T value : values) {
				if (!isFinal) {
					if (sameGeneric && writeClass) {
						writeClass = false;
						genericCodec = (Codec<T>) laser.getCodec(value.getClass());
						laser.writeClass(out, value.getClass());
					} else if (!sameGeneric) {
						laser.writeClass(out, value.getClass());
						genericCodec = (Codec<T>) laser.getCodec(value.getClass());
					}
				}
				genericCodec.encode(laser, out, value);
			}
		}
	}

	@Override
	public Collection<T> decode(Laser laser, InputStream in, Class<Collection<T>> type) throws Exception {
		int size = in.readInt();
		if (size == -1) {
			return null;
		}
		Collection<T> values = newCollection(size);
		if (size > 0) {
			boolean sameGeneric = false;
			if (!isFinal) {
				sameGeneric = in.readBoolean();
			}
			Class<T> genericType = this.genericType;
			Codec<T> genericCodec = this.genericCodec;
			boolean readClass = sameGeneric;
			for (int i = 0; i < size; i++) {
				if (!isFinal) {
					if (!sameGeneric) {
						genericType = laser.readClass(in);
						genericCodec = laser.getCodec(genericType);
					} else if (sameGeneric && readClass) {
						readClass = false;
						genericType = laser.readClass(in);
						genericCodec = laser.getCodec(genericType);
					}
				}
				values.add(genericCodec.decode(laser, in, genericType));
			}
		}
		return values;
	}

	protected abstract Collection<T> newCollection(int size);

}
