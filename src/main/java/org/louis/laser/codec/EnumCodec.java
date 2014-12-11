package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class EnumCodec implements Codec<Enum<?>> {

	private Enum<?>[] enumConstants;

	public EnumCodec(Class<? extends Enum<?>> enumType) {
		enumConstants = enumType.getEnumConstants();
	}

	@Override
	public void encode(Laser laser, OutputStream out, Enum<?> value) throws Exception {
		if (value == null) {
			out.writeInt(-1);
			return;
		}
		out.writeInt(value.ordinal());
	}

	@Override
	public Enum<?> decode(Laser laser, InputStream in, Class<Enum<?>> type) throws Exception {
		int ordinal = in.readInt();
		if (ordinal == -1) {
			return null;
		}
		return enumConstants[ordinal];
	}

}
