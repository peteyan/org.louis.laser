package org.louis.laser.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.louis.laser.io.OutputStream;

public class FileOutputStream extends OutputStream {

	private java.io.FileOutputStream out;

	public FileOutputStream(String filename) throws FileNotFoundException {
		out = new java.io.FileOutputStream(filename);
	}

	public FileOutputStream(String filename, boolean append) throws FileNotFoundException {
		out = new java.io.FileOutputStream(filename, append);
	}

	public FileOutputStream(File file) throws FileNotFoundException {
		out = new java.io.FileOutputStream(file);
	}

	public FileOutputStream(File file, boolean append) throws FileNotFoundException {
		out = new java.io.FileOutputStream(file, append);
	}

	@Override
	public void writeByte(byte b) {
		try {
			out.write(b);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeBytes(byte[] buffer, int index, int length) {
		try {
			out.write(buffer, index, length);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		try {
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void flush() {
		try {
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
