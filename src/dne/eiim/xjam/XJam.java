package dne.eiim.xjam;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class XJam {
	private static Set<Class<?>> TYPES = new HashSet<Class<?>>(Arrays.<Class<?>>asList(ArrayList.class, Long.class, Double.class,
			BigInteger.class, Character.class, Block.class));
	
	private List<Object> stack;
	private Object[] var = new Object[26];
	private final In in;
	private final Out out;
	private final Out err;
	private final List<Integer> marks = new ArrayList<Integer>();
	private final Random r = new Random();
	private Memo memo;
	private String[] args;
	
	public XJam() {
		this(new SystemIn(), new SystemOut(), new SystemErr());
	}
	
	public XJam(final In in, final Out out) {
		this(in, out, out);
	}

	public XJam(final In in, final Out out, final Out err) {
		this.in = in;
		this.out = out;
		this.err = err;
		stack = new ArrayList<Object>();
		setVars();
	}
	
	private void setVars() {
		Arrays.fill(var, new ArrayList<Object>());
		for (int i = 0; i < 11; ++i) { // A to K = 10 to 20
			var[i] = i + 10l;
		}
		setVar('N', Conv.strToList("\n"));
		setVar('P', Math.PI);
		setVar('S', Conv.strToList(" "));
		setVar('T', 0l);
		setVar('U', 0l);
		setVar('V', 0l);
		setVar('W', -1l);
		setVar('X', 1l);
		setVar('Y', 2l);
		setVar('Z', 3l);
		
		// XJam vars
		for (int i = 0; i < 11; ++i) { // Bold Serif Capital X to Bold Fraktur Capital X = 10 to 20
			var[i] = i;
		}
		var[13] = Conv.strToList("\n");
		var[15] = Math.PI;
		var[18] = Conv.strToList(" ");
		var[19] = 0l;
		var[20] = 0l;
		var[21] = 0l;
		var[22] = -1l;
		var[24] = 2l;
		var[25] = 3l;
		var[25] = Math.E;
	}
	
	public void checkType(final Object x) {
		if (!TYPES.contains(x.getClass())) {
			throw new IllegalArgumentException("Tried to push " + x.getClass());
		}
	}
	
	public void push(final Object x) {
//		checkType(x);
		stack.add(x);
	}
	
	public Object pop() {
		final int n = stack.size();
		if (n == 0) {
			throw new RuntimeException("The stack is empty");
		}
		for (int i = marks.size() - 1; i >= 0; --i) {
			if (marks.get(i) == n) {
				marks.set(i, n - 1);
			}
		}
		return stack.remove(n - 1);
	}
	
	public Object peek() {
		return stack.get(stack.size() - 1);
	}
	
	public void mark() {
		marks.add(stack.size());
	}
	
	public void popMark() {
		final int start = marks.isEmpty() ? 0 : marks.remove(marks.size() - 1);
		final List<Object> l = stack.subList(start, stack.size());
		final List<Object> r = new ArrayList<Object>(l);
		l.clear();
		push(r);
	}
	
	public Object get(final int x) {
		final int y = x < 0 ? -1 - x : stack.size() - 1 - x;
		if (y < 0 || y >= stack.size()) {
			throw new IllegalArgumentException("Stack size " + stack.size() + ", x=" + x);
		}
		return stack.get(y);
	}
	
	public Object getVar(final char c) {
		return var[c];
	}
	
	public void setVar(final char c, final Object x) {
//		checkType(x);
//		if (c < 'A' || c > 'Z') {
//			throw new IllegalArgumentException("Invalid variable name: " + c);
//		}
		var[c] = x;
	}
	
	public String readNext() {
		return in.readNext();
	}
	
	public String readLine() {
		return in.readLine();
	}
	
	public String readAll() {
		return in.readAll();
	}
	
	public void print(final Object o) {
		out.print(o);
	}
	
	public void println() {
		out.println();
	}
	
	public void println(final Object o) {
		out.println(o);
	}
	
	public void err(final Object o) {
		err.print(o);
	}
	
	public void errln() {
		err.println();
	}
	
	public void errln(final Object o) {
		err.println(o);
	}
	
	public Random getRandom() {
		return r;
	}
	
	public Memo getMemo() {
		return memo;
	}

	public void setMemo(final Memo memo) {
		this.memo = memo;
	}
	
	public String[] getArgs() {
		return args;
	}

	public void setArgs(final String[] args) {
		this.args = args;
	}

	private void dump(final Object o) {
		if (o instanceof List) {
			for (Object x : (List<?>) o) {
				dump(x);
			}
		}
		else {
			print(o);
		}
	}
	
	public void dump() {
		dump(stack);
	}
	
	public void show() {
		println();
		println("Stack: " + Conv.repr(stack));
	}

	public void clear() {
		stack.clear();
		marks.clear();
		setVars();
		memo = null;
	}
	
	public void runCode(final Block b) {
		try {
			b.run(this);
		}
		catch (Exception e) {
			final String s = e.getClass().getSimpleName();
	        final String msg = e.getMessage();
	        errln(msg == null ? s : (s + ": " + msg));
			System.err.println("Java exception:");
			e.printStackTrace();
		}
		dump();
	}
	
	public static String run(final String code, final String input) {
		return run(code, input, new String[0]);
	}
	
	public static String run(final String code, final String input, final String[] args) {
		final In in = new CStreamIn(new StringReader(input));
		final Out out = new StringOut();
		final XJam x = new XJam(in, out);
		x.setArgs(args);
		final Block b = Block.parse(new StringReader(code), false);
		x.runCode(b);
		return out.toString();
	}
	
	public static void main(final String... args) throws FileNotFoundException {
		final XJam x = new XJam();
		final Block b = Block.parse(new FileReader(args[0]), false);
		x.setArgs(Arrays.copyOfRange(args, 1, args.length));
		x.runCode(b);
	}
}
