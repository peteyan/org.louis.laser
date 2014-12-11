package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ShortArrayCodec implements Codec<short[]> {

	@Override
	public void encode(Laser laser, OutputStream out, short[] values) throws Exception {
		out.writeShorts(values);
	}

	@Override
	public short[] decode(Laser laser, InputStream in, Class<short[]> type) throws Exception {
		return in.readShorts();
	}
}