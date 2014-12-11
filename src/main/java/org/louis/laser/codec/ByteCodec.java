package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ByteCodec implements Codec<Byte> {

	@Override
	public void encode(Laser laser, OutputStream out, Byte value) throws Exception {
		out.writeByte(value);
	}

	@Override
	public Byte decode(Laser laser, InputStream in, Class<Byte> type) throws Exception {
		return in.readByte();
	}
}