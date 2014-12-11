package org.louis.laser.codec;

import java.util.ArrayList;
import java.util.Collection;

import org.louis.laser.Laser;

public class ArrayListCodec<T> extends CollectionCodec<T> {

	public ArrayListCodec() {
	}

	public ArrayListCodec(Laser laser, Class<T> genericType) {
		super(laser, genericType);
	}

	@Override
	protected Collection<T> newCollection(int size) {
		return new ArrayList<T>(size);
	}

}
