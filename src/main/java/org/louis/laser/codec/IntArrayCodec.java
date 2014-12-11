package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class IntArrayCodec implements Codec<int[]> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, int[] values) throws Exception {
		out.writeInts(values);
	}

	@Override
	public int[] decode(Laser laser, Context context, InputStream in, Class<int[]> type) throws Exception {
		return in.readInts();
	}
}