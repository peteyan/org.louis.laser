package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class BooleanCodec implements Codec<Boolean> {

	private boolean wrapped;

	public BooleanCodec(boolean wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Boolean value) throws Exception {
		if (wrapped && out.writeBoolean(value == null)) {
			return;
		}
		out.writeBoolean(value);
	}

	@Override
	public Boolean decode(Laser laser, Context context, InputStream in, Class<Boolean> type) throws Exception {
		if (wrapped && in.readBoolean()) {
			return null;
		}
		return in.readBoolean();
	}
}