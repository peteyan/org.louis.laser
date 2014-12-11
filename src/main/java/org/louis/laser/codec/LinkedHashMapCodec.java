package org.louis.laser.codec;

import java.util.LinkedHashMap;
import java.util.Map;

import org.louis.laser.Laser;

public class LinkedHashMapCodec<K, V> extends MapCodec<K, V> {

	public LinkedHashMapCodec() {
	}

	public LinkedHashMapCodec(Laser laser,Class<K> genericKeyType, Class<V> genericValueType) {
		super(laser,genericKeyType, genericValueType);
	}

	@Override
	protected Map<K, V> newMap(int size) {
		return new LinkedHashMap<K, V>(size);
	}

}
