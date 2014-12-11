package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ObjectCodec implements Codec<Object> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Object value) throws Exception {
		laser.writeClassAndObject(context, out, value);
	}

	@Override
	public Object decode(Laser laser, Context context, InputStream in, Class<Object> type) throws Exception {
		return laser.readClassAndObject(context, in);
	}

}
