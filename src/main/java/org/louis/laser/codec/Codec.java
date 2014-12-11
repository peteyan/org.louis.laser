package org.louis.laser.codec;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public interface Codec<T> {

	void encode(Laser laser, OutputStream out, T value) throws Exception;

	T decode(Laser laser, InputStream in, Class<T> type) throws Exception;

}
