package org.louis.laser.io;

import java.io.UnsupportedEncodingException;

public abstract class OutputStream extends java.io.OutputStream {

	public abstract void writeByte(byte b);

	public abstract void writeBytes(byte[] buffer, int index, int length);

	public abstract void close();

	public abstract void flush();

	@Override
	@Deprecated
	public final void write(int b) {
		writeByte((byte) b);
	}

	public void write(byte[] buffer, int index, int length) {
		writeBytes(buffer, index, length);
	}

	public void write(byte[] value) {
		writeBytes(value);
	}

	public void writeBytes(byte[] values) {
		if (values == null) {
			writeInt(-1);
			return;
		}
		writeInt(values.length);
		if (values.length > 0) {
			writeBytes(values, 0, values.length);
		}
	}

	public boolean writeBoolean(boolean value) {
		if (value) {
			writeByte((byte) 0);
		} else {
			writeByte((byte) -1);
		}
		return value;
	}

	public void writeShort(short value) {
		writeByte((byte) (value >> 8));
		writeByte((byte) value);
	}

	public void writeInt(int value) {
//		value = (value << 1) ^ (value >> 31);
		if (value >> 7 == 0) {
			writeByte((byte) value);
			return;
		}
		if (value >> 14 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7));
			return;
		}
		if (value >> 21 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14));
			return;
		}
		if (value >> 28 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14 | 0x80));
			writeByte((byte) (value >> 21));
			return;
		}
		writeByte((byte) ((value & 0x7F) | 0x80));
		writeByte((byte) (value >> 7 | 0x80));
		writeByte((byte) (value >> 14 | 0x80));
		writeByte((byte) (value >> 21 | 0x80));
		writeByte((byte) (value >> 28));
	}

	public void writeLong(long value) {
		if (value >> 7 == 0) {
			writeByte((byte) value);
			return;
		}
		if (value >> 14 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7));
			return;
		}
		if (value >> 21 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14));
			return;
		}
		if (value >> 28 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14 | 0x80));
			writeByte((byte) (value >> 21));
			return;
		}
		if (value >> 35 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14 | 0x80));
			writeByte((byte) (value >> 21 | 0x80));
			writeByte((byte) (value >> 28));
			return;
		}
		if (value >> 42 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14 | 0x80));
			writeByte((byte) (value >> 21 | 0x80));
			writeByte((byte) (value >> 28 | 0x80));
			writeByte((byte) (value >> 35));
			return;
		}
		if (value >> 49 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14 | 0x80));
			writeByte((byte) (value >> 21 | 0x80));
			writeByte((byte) (value >> 28 | 0x80));
			writeByte((byte) (value >> 35 | 0x80));
			writeByte((byte) (value >> 42));
			return;
		}
		if (value >> 56 == 0) {
			writeByte((byte) ((value & 0x7F) | 0x80));
			writeByte((byte) (value >> 7 | 0x80));
			writeByte((byte) (value >> 14 | 0x80));
			writeByte((byte) (value >> 21 | 0x80));
			writeByte((byte) (value >> 28 | 0x80));
			writeByte((byte) (value >> 35 | 0x80));
			writeByte((byte) (value >> 42 | 0x80));
			writeByte((byte) (value >> 49));
			return;
		}
		writeByte((byte) ((value & 0x7F) | 0x80));
		writeByte((byte) (value >> 7 | 0x80));
		writeByte((byte) (value >> 14 | 0x80));
		writeByte((byte) (value >> 21 | 0x80));
		writeByte((byte) (value >> 28 | 0x80));
		writeByte((byte) (value >> 35 | 0x80));
		writeByte((byte) (value >> 42 | 0x80));
		writeByte((byte) (value >> 49 | 0x80));
		writeByte((byte) (value >> 56));
	}

	public void writeFloat(float value) {
		writeInt(Float.floatToIntBits(value));
	}

	public void writeDouble(double value) {
		writeLong(Double.doubleToLongBits(value));
	}

	public void writeChar(char value) {
		writeByte((byte) (value >>> 8));
		writeByte((byte) value);
	}

	public void writeChars(char[] values) {
		if (values == null) {
			writeInt(-1);
			return;
		}
		writeInt(values.length);
		if (values.length > 0) {
			for (char value : values) {
				writeChar(value);
			}
		}
	}

	public void writeString(String value) throws Exception {
		if (value == null) {
			writeInt(-1);
			return;
		}
		if (value.length() == 0) {
			writeInt(0);
			return;
		}
		try {
			writeBytes(value.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
	}

	public void writeShorts(short[] values) {
		if (values == null) {
			writeInt(-1);
			return;
		}
		writeInt(values.length);
		if (values.length > 0) {
			for (short s : values) {
				writeShort(s);
			}
		}
	}

	public void writeInts(int[] values) {
		if (values == null) {
			writeInt(-1);
		} else {
			writeInt(values.length);
			if (values.length > 0) {
				for (int value : values) {
					writeInt(value);
				}
			}
		}
	}

	public void writeLongs(long[] values) {
		if (values == null) {
			writeInt(-1);
			return;
		}
		writeInt(values.length);
		if (values.length > 0) {
			for (long l : values) {
				writeLong(l);
			}
		}
	}

	public void writeFloats(float[] values) {
		if (values == null) {
			writeInt(-1);
			return;
		}
		writeInt(values.length);
		if (values.length > 0) {
			for (float f : values) {
				writeFloat(f);
			}
		}
	}

	public void writeDoubles(double[] doubles) {
		if (doubles == null || doubles.length == 0) {
			writeInt(0);
			return;
		}
		writeInt(doubles.length);
		for (double d : doubles) {
			writeDouble(d);
		}
	}

	public void writeBooleans(boolean[] booleans) {
		if (booleans == null || booleans.length == 0) {
			writeInt(0);
			return;
		}
		writeInt(booleans.length);
		for (boolean b : booleans) {
			writeBoolean(b);
		}
	}

	public void writeStrings(String[] values) throws Exception {
		if (values == null) {
			writeInt(-1);
			return;
		}
		writeInt(values.length);
		if (values.length > 0) {
			for (String value : values) {
				writeString(value);
			}
		}
	}

}
