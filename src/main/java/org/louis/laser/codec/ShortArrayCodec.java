package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ShortArrayCodec implements Codec<short[]> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, short[] values) throws Exception {
		out.writeShorts(values);
	}

	@Override
	public short[] decode(Laser laser, Context context, InputStream in, Class<short[]> type) throws Exception {
		return in.readShorts();
	}
}