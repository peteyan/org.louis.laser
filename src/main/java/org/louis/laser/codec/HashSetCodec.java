package org.louis.laser.codec;

import java.util.Collection;
import java.util.HashSet;

import org.louis.laser.Laser;

public class HashSetCodec<T> extends CollectionCodec<T> {

	public HashSetCodec() {
	}

	public HashSetCodec(Laser laser, Class<T> genericType) {
		super(laser, genericType);
	}

	@Override
	protected Collection<T> newCollection(int size) {
		return new HashSet<T>(size);
	}

}
