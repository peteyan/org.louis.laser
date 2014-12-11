package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class DoubleArrayCodec implements Codec<double[]> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, double[] values) throws Exception {
		out.writeDoubles(values);
	}

	@Override
	public double[] decode(Laser laser, Context context, InputStream in, Class<double[]> type) throws Exception {
		return in.readDoubles();
	}
}