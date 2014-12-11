package org.louis.laser.io;


public class ByteArrayOutputStream extends OutputStream {

	private java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();

	public byte[] toByteArray() {
		return out.toByteArray();
	}

	@Override
	public void writeByte(byte b) {
		out.write(b);
	}

	@Override
	public void writeBytes(byte[] buffer, int index, int length) {
		out.write(buffer, index, length);
	}

	@Override
	public void close() {
	}

	@Override
	public void flush() {
	}

	public void reset() {
		out.reset();
	}

}
