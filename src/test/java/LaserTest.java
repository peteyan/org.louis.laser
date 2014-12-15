import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.louis.laser.Context;
import org.louis.laser.Laser;
import org.louis.laser.io.ByteArrayInputStream;
import org.louis.laser.io.ByteArrayOutputStream;

public class LaserTest {

	public static void main(String[] args) throws Exception {
		Map<String, A> as = new HashMap<String, A>();
		List<A> list = new ArrayList<A>();
		int length = 200;
		for (int i = 0; i < length; i++) {
			list.add(new A("a" + i));
			list.add(new B("b" + i));
			list.add(new C("c" + i));
			as.put("a" + i, new A("1"));
			as.put("b" + i, new B("2"));
			as.put("c" + i, new C("3"));
		}
		M m = new M(as, list);
		Laser.laser().registerType(M.class);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		long start = System.currentTimeMillis();
		Context context = new Context();
		Laser.laser().writeClassAndObject(context, out, m);
		System.out.println("writeclass=" + (System.currentTimeMillis() - start));
		System.out.println("length=" + out.toByteArray().length);

		context = new Context();
		start = System.currentTimeMillis();
		Laser.laser().readClassAndObject(context, new ByteArrayInputStream(out.toByteArray()));
		System.out.println("readclass=" + (System.currentTimeMillis() - start));
	}

	static class M {
		int i = 0;
		Integer j = 1;
		List<A> list;
		Map<String, A> as;

		public M(Map<String, A> as, List<A> list) {
			super();
			this.as = as;
			this.list = list;
		}
	}

	static class A {
		public A(String a) {
			super();
			this.a = a;
		}

		String a;
	}

	static class B extends A {
		public B(String b) {
			super("A" + b);
			this.b = b;
		}

		String b;
	}

	static class C extends A {

		public C(String c) {
			super("C" + c);
			this.c = c;
		}

		String c;
	}

}
