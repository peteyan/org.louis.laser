package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class IntCodec implements Codec<Integer> {

	private boolean wrapped;

	public IntCodec(boolean wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Integer value) throws Exception {
		if (wrapped && out.writeBoolean(value == null)) {
			return;
		}
		out.writeInt(value);
	}

	@Override
	public Integer decode(Laser laser, Context context, InputStream in, Class<Integer> type) throws Exception {
		if (wrapped && in.readBoolean()) {
			return null;
		}
		return in.readInt();
	}
}