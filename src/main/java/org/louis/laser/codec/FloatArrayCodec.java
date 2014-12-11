package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class FloatArrayCodec implements Codec<float[]> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, float[] values) throws Exception {
		out.writeFloats(values);
	}

	@Override
	public float[] decode(Laser laser, Context context, InputStream in, Class<float[]> type) throws Exception {
		return in.readFloats();
	}

}