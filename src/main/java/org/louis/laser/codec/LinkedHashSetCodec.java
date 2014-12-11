package org.louis.laser.codec;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.louis.laser.Laser;

public class LinkedHashSetCodec<T> extends CollectionCodec<T> {

	public LinkedHashSetCodec() {
	}

	public LinkedHashSetCodec(Laser laser, Class<T> genericType) {
		super(laser, genericType);
	}

	@Override
	protected Collection<T> newCollection(int size) {
		return new LinkedHashSet<T>(size);
	}

}
