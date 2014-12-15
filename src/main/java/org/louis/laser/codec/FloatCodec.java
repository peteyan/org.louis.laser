package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class FloatCodec implements Codec<Float> {

	private boolean wrapped;

	public FloatCodec(boolean wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Float value) throws Exception {
		if (wrapped && out.writeBoolean(value == null)) {
			return;
		}
		out.writeFloat(value);
	}

	@Override
	public Float decode(Laser laser, Context context, InputStream in, Class<Float> type) throws Exception {
		if (wrapped && in.readBoolean()) {
			return null;
		}
		return in.readFloat();
	}
}
