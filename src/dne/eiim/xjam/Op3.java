package dne.eiim.xjam;

public abstract class Op3 extends Op {
	public Op3(final String name) {
		super(name);
	}

	@Override
	public void run(final XJam x) {
		Object c = x.pop();
		Object b = x.pop();
		Object a = x.pop();
		final Object t = calc(x, a, b, c);
		if (t != null) {
			x.push(t);
		}
	}
	
	protected abstract Object calc(final XJam x, final Object a, final Object b, final Object c);
}
