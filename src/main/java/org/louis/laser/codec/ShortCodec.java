package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ShortCodec implements Codec<Short> {

	@Override
	public void encode(Laser laser, OutputStream out, Short value) throws Exception {
		out.writeShort(value);
	}

	@Override
	public Short decode(Laser laser, InputStream in, Class<Short> type) throws Exception {
		return in.readShort();
	}
}