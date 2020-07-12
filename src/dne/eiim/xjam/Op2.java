package dne.eiim.xjam;

public abstract class Op2 extends Op {
	public Op2(final String name) {
		super(name);
	}

	@Override
	public void run(final XJam x) {
		Object b = x.pop();
		Object a = x.pop();
		final Object t = calc(x, a, b);
		if (t != null) {
			x.push(t);
		}
	}
	
	protected abstract Object calc(final XJam x, final Object a, final Object b);
}
