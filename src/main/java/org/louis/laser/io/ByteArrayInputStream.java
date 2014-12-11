package org.louis.laser.io;

import java.io.IOException;

public class ByteArrayInputStream extends InputStream {

	private java.io.ByteArrayInputStream in = null;

	public ByteArrayInputStream(byte[] buffer) {
		in = new java.io.ByteArrayInputStream(buffer);
	}

	@Override
	public byte readByte() {
		return (byte) in.read();
	}

	@Override
	public byte[] readBytes(int length) {
		byte[] buffer = new byte[length];
		try {
			in.read(buffer);
			return buffer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void readBytes(byte[] buffer, int index, int length) {
		in.read(buffer, index, length);
	}

	@Override
	public void close() {
	}

	@Override
	public byte getByte() {
		throw new UnsupportedOperationException();
	}

}
