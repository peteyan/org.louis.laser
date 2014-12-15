package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ShortCodec implements Codec<Short> {

	private boolean wrapped;

	public ShortCodec(boolean wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Short value) throws Exception {
		if (wrapped && out.writeBoolean(value == null)) {
			return;
		}
		out.writeShort(value);
	}

	@Override
	public Short decode(Laser laser, Context context, InputStream in, Class<Short> type) throws Exception {
		if (wrapped && in.readBoolean()) {
			return null;
		}
		return in.readShort();
	}
}