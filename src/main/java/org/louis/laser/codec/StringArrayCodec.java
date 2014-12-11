package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class StringArrayCodec implements Codec<String[]> {

	@Override
	public void encode(Laser laser, OutputStream out, String[] values) throws Exception {
		out.writeStrings(values);
	}

	@Override
	public String[] decode(Laser laser, InputStream in, Class<String[]> type) throws Exception {
		return in.readStrings();
	}
}
