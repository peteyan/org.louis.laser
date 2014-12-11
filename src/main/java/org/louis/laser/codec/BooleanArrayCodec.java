package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class BooleanArrayCodec implements Codec<boolean[]> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, boolean[] values) throws Exception {
		out.writeBooleans(values);
	}

	@Override
	public boolean[] decode(Laser laser, Context context, InputStream in, Class<boolean[]> type) throws Exception {
		return in.readBooleans();
	}
}
