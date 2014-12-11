package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ObjectCodec implements Codec<Object> {

	@Override
	public void encode(Laser laser, OutputStream out, Object value) throws Exception {
		laser.writeClassAndObject(out, value);
	}

	@Override
	public Object decode(Laser laser, InputStream in, Class<Object> type) throws Exception {
		return laser.readClassAndObject(in);
	}

}
