package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ByteCodec implements Codec<Byte> {

	private boolean wrapped;

	public ByteCodec(boolean wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Byte value) throws Exception {
		if (wrapped && out.writeBoolean(value == null)) {
			return;
		}
		out.writeByte(value);
	}

	@Override
	public Byte decode(Laser laser, Context context, InputStream in, Class<Byte> type) throws Exception {
		if (wrapped && in.readBoolean()) {
			return null;
		}
		return in.readByte();
	}
}