package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class DoubleCodec implements Codec<Double> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Double value) throws Exception {
		out.writeDouble(value);
	}

	@Override
	public Double decode(Laser laser, Context context, InputStream in, Class<Double> type) throws Exception {
		return in.readDouble();
	}
}