package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class LongCodec implements Codec<Long> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Long value) throws Exception {
		out.writeLong(value);
	}

	@Override
	public Long decode(Laser laser, Context context, InputStream in, Class<Long> type) throws Exception {
		return in.readLong();
	}
}