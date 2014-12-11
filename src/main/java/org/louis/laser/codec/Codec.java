package org.louis.laser.codec;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public interface Codec<T> {

	void encode(Laser laser, Context context, OutputStream out, T value) throws Exception;

	T decode(Laser laser, Context context, InputStream in, Class<T> type) throws Exception;

}
