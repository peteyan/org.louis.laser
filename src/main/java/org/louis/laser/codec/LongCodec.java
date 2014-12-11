package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class LongCodec implements Codec<Long> {

	@Override
	public void encode(Laser laser, OutputStream out, Long value) throws Exception {
		out.writeLong(value);
	}

	@Override
	public Long decode(Laser laser, InputStream in, Class<Long> type) throws Exception {
		return in.readLong();
	}
}