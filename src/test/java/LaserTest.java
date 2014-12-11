import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.louis.laser.Laser;
import org.louis.laser.io.ByteArrayInputStream;
import org.louis.laser.io.ByteArrayOutputStream;

public class LaserTest {

	public static void main(String[] args) throws Exception {
		Map<String, A> as = new HashMap<String, A>();
		List<C> list = new ArrayList<C>();
		list.add(new C("1"));
		list.add(new C("2"));
		list.add(new C("3"));
		as.put("a", new A("1"));
		as.put("b", new B("2"));
		as.put("c", new C("3"));
		M m = new M(as, list);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Laser.laser().writeClassAndObject(out, m);
		System.out.println(out.toByteArray().length);
		Object readClassAndObject = Laser.laser().readClassAndObject(new ByteArrayInputStream(out.toByteArray()));
		System.out.println(readClassAndObject);

	}

	static class M {
		private int i = 0;
		private Integer j = 1;
		private List<C> list;
		private Map<String, A> as;

		public M(Map<String, A> as, List<C> list) {
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

		private String a;
	}

	static class B extends A {
		public B(String b) {
			super("A" + b);
			this.b = b;
		}

		private String b;
	}

	static class C extends A {

		public C(String c) {
			super("C" + c);
			this.c = c;
		}

		private String c;
	}

}
