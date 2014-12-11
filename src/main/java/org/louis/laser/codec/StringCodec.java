package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class StringCodec implements Codec<String> {

	@Override
	public void encode(Laser laser, OutputStream out, String value) throws Exception {
		out.writeString(value);
	}

	@Override
	public String decode(Laser laser, InputStream in, Class<String> type) throws Exception {
		return in.readString();
	}
}