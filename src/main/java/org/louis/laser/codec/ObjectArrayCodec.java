package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class ObjectArrayCodec implements Codec<Object[]> {

	@Override
	public void encode(Laser laser, Context context, OutputStream out, Object[] values) throws Exception {
		if (values == null) {
			out.writeInt(-1);
			return;
		}
		int length = values.length;
		out.writeInt(length);
		if (length > 0) {
			for (Object obj : values) {
				laser.writeClassAndObject(context, out, obj);
			}
		}
	}

	@Override
	public Object[] decode(Laser laser, Context context, InputStream in, Class<Object[]> type) throws Exception {
		int length = in.readInt();
		if (length == -1) {
			return null;
		}
		Object[] objs = new Object[length];
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				objs[i] = laser.readClassAndObject(context, in);
			}
		}
		return objs;
	}

}
