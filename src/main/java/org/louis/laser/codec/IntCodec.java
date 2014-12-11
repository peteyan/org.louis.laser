package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class IntCodec implements Codec<Integer> {

	@Override
	public void encode(Laser laser, OutputStream out, Integer value) throws Exception {
		out.writeInt(value);
	}

	@Override
	public Integer decode(Laser laser, InputStream in, Class<Integer> type) throws Exception {
		return in.readInt();
	}
}