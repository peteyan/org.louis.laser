package org.louis.laser.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStream extends InputStream {

	private java.io.FileInputStream in;

	public FileInputStream(String filename) throws FileNotFoundException {
		in = new java.io.FileInputStream(filename);
	}

	public FileInputStream(File file) throws FileNotFoundException {
		in = new java.io.FileInputStream(file);
	}

	@Override
	public byte readByte() {
		try {
			return (byte) in.read();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public byte[] readBytes(int length) {
		try {
			byte[] buffer = new byte[length];
			in.read(buffer);
			return buffer;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void readBytes(byte[] buffer, int index, int length) {
		try {
			in.read(buffer, index, length);
		} catch (IOException e) {
		}
	}

	@Override
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte getByte() {
		return -1;
	}

}
