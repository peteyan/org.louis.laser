package org.louis.laser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class Context {

	private AtomicInteger classNameId = new AtomicInteger(64);
	private Map<Class<?>, Integer> typeToHeaders = new HashMap<Class<?>, Integer>();
	private Map<Integer, Class<?>> headerToTypes = new HashMap<Integer, Class<?>>();

	public int addHeader(Class<?> type) {
		int header = classNameId.getAndIncrement();
		typeToHeaders.put(type, header);
		return header;
	}

	public Integer getHeader(Class<?> type) {
		return typeToHeaders.get(type);
	}

	public Class<?> getType(int header) {
		return headerToTypes.get(header);
	}

	public void addType(int header, Class<?> type) {
		headerToTypes.put(header, type);
	}

}
