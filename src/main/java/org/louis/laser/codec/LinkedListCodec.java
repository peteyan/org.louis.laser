package org.louis.laser.codec;

import java.util.Collection;
import java.util.LinkedList;

import org.louis.laser.Laser;

public class LinkedListCodec<T> extends CollectionCodec<T> {

	public LinkedListCodec() {
	}

	public LinkedListCodec(Laser laser, Class<T> genericType) {
		super(laser, genericType);
	}

	@Override
	protected Collection<T> newCollection(int size) {
		return new LinkedList<T>();
	}

}
