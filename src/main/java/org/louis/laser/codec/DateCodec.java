package org.louis.laser.codec;

import java.util.Date;

import org.louis.laser.Laser;
import org.louis.laser.io.InputStream;
import org.louis.laser.io.OutputStream;

public class DateCodec implements Codec<Date> {

	@Override
	public void encode(Laser laser, OutputStream out, Date value) throws Exception {
		if (value == null) {
			out.writeLong(-1);
			return;
		}
		out.writeLong(value.getTime());
	}

	@Override
	public Date decode(Laser laser, InputStream in, Class<Date> type) throws Exception {
		long time = in.readLong();
		if (time == -1) {
			return null;
		}
		return new Date(time);
	}

}
