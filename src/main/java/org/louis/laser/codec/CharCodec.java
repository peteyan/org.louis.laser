package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class CharCodec implements Codec<Character> {

	@Override
	public void encode(Laser laser, OutputStream out, Character value) throws Exception {
		out.writeChar(value);
	}

	@Override
	public Character decode(Laser laser, InputStream in, Class<Character> type) throws Exception {
		return in.readChar();
	}

}
