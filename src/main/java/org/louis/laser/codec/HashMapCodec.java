package org.louis.laser.codec;

import java.util.HashMap;
import java.util.Map;

import org.louis.laser.Laser;

public class HashMapCodec<K, V> extends MapCodec<K, V> {

	public HashMapCodec() {
	}

	public HashMapCodec(Laser laser, Class<K> genericKeyType, Class<V> genericValueType) {
		super(laser, genericKeyType, genericValueType);
	}

	@Override
	protected Map<K, V> newMap(int size) {
		return new HashMap<K, V>(size);
	}
}
