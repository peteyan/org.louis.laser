package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ByteArrayCodec implements Codec<byte[]> {

	@Override
	public void encode(Laser laser,Context context, OutputStream out, byte[] values) throws Exception {
		out.writeBytes(values);
	}

	@Override
	public byte[] decode(Laser laser,Context context, InputStream in, Class<byte[]> type) throws Exception {
		return in.readBytes();
	}
}