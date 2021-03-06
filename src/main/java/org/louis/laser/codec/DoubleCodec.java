package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class DoubleCodec implements Codec<Double> {

	private boolean wrapped;

	public DoubleCodec(boolean wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Double value) throws Exception {
		if (wrapped && out.writeBoolean(value == null)) {
			return;
		}
		out.writeDouble(value);
	}

	@Override
	public Double decode(Laser laser, Context context, InputStream in, Class<Double> type) throws Exception {
		if (wrapped && in.readBoolean()) {
			return null;
		}
		return in.readDouble();
	}
}