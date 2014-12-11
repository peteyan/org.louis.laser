package org.louis.laser.io;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class InputStream extends java.io.InputStream {

	public abstract byte readByte();

	public abstract byte[] readBytes(int length);

	public abstract void readBytes(byte[] buffer, int index, int length);

	public abstract void close();

	public abstract byte getByte();

	@Override
	@Deprecated
	public int read() throws IOException {
		return readByte();
	}

	public int read(byte[] buffer, int index, int length) {
		readBytes(buffer, index, length);
		return length;
	}

	public void readBytes(byte[] buffer) {
		readBytes(buffer, 0, buffer.length);
	}

	public boolean readBoolean() {
		byte b = readByte();
		return b == ((byte) 0) ? true : false;
	}

	public byte[] readBytes() {
		int length = readInt();
		if (length > 0) {
			return readBytes(length);
		} else if (length == 0) {
			return new byte[0];
		}
		return null;
	}

	public short readShort() {
		return (short) (((readByte() & 0xFF) << 8) | (readByte() & 0xFF));
	}

	public int readInt() {
		byte b = readByte();
		int i = b & 0x7F;
		if ((b & 0x80) != 0) {
			b = readByte();
			i |= (b & 0x7F) << 7;
			if ((b & 0x80) != 0) {
				b = readByte();
				i |= (b & 0x7F) << 14;
				if ((b & 0x80) != 0) {
					b = readByte();
					i |= (b & 0x7F) << 21;
					if ((b & 0x80) != 0) {
						b = readByte();
						i |= (b & 0x7F) << 28;
					}
				}
			}
		}
		// i = ((i >>> 1) ^ -(i & 1));
		return i;
	}

	public long readLong() {
		byte b = readByte();
		long result = b & 0x7F;
		if ((b & 0x80) != 0) {
			b = readByte();
			result |= (b & 0x7F) << 7;
			if ((b & 0x80) != 0) {
				b = readByte();
				result |= (b & 0x7F) << 14;
				if ((b & 0x80) != 0) {
					b = readByte();
					result |= (b & 0x7F) << 21;
					if ((b & 0x80) != 0) {
						b = readByte();
						result |= (long) (b & 0x7F) << 28;
						if ((b & 0x80) != 0) {
							b = readByte();
							result |= (long) (b & 0x7F) << 35;
							if ((b & 0x80) != 0) {
								b = readByte();
								result |= (long) (b & 0x7F) << 42;
								if ((b & 0x80) != 0) {
									b = readByte();
									result |= (long) (b & 0x7F) << 49;
									if ((b & 0x80) != 0) {
										b = readByte();
										result |= (long) b << 56;
									}
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	public float readFloat() {
		return Float.intBitsToFloat(readInt());
	}

	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}

	public char readChar() {
		return (char) (((readByte() & 0xFF) << 8) | (readByte() & 0xFF));
	}

	public char[] readChars() {
		int length = readInt();
		if (length == -1) {
			return null;
		}
		char[] values = new char[length];
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				values[i] = readChar();
			}
		}
		return values;
	}

	public String readString() throws Exception {
		int length = readInt();
		if (length == -1) {
			return null;
		} else if (length == 0) {
			return "";
		}
		try {
			return new String(readBytes(length), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
	}

	public String[] readStrings() throws Exception {
		int length = readInt();
		if (length != -1) {
			String[] values = new String[length];
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					values[i] = readString();
				}
			}
			return values;
		}
		return null;
	}

	public short[] readShorts() {
		int length = readInt();
		if (length == -1) {
			return null;
		}
		short[] shorts = new short[length];
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				shorts[i] = readShort();
			}
		}
		return shorts;
	}

	public int[] readInts() {
		int length = readInt();
		if (length != -1) {
			int[] values = new int[length];
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					values[i] = readInt();
				}
			}
			return values;
		}
		return null;
	}

	public long[] readLongs() {
		int length = readInt();
		if (length == -1) {
			return null;
		}
		long[] longs = new long[length];
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				longs[i] = readLong();
			}
		}
		return longs;
	}

	public float[] readFloats() {
		int length = readInt();
		if (length == -1) {
			return null;
		}
		float[] floats = new float[length];
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				floats[i] = readFloat();
			}
		}
		return floats;
	}

	public double[] readDoubles() {
		int length = readInt();
		double[] doubles = null;
		if (length > 0) {
			doubles = new double[length];
			for (int i = 0; i < length; i++) {
				doubles[i] = readDouble();
			}
		}
		return doubles;
	}

	public boolean[] readBooleans() {
		int length = readInt();
		boolean[] booleans = null;
		if (length > 0) {
			booleans = new boolean[length];
			for (int i = 0; i < length; i++) {
				booleans[i] = readBoolean();
			}
		}
		return booleans;
	}
}
