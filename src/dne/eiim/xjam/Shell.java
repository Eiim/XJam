package dne.eiim.xjam;

import java.io.StringReader;

public class Shell {
	public static void main(final String... args) {
		final XJam x = new XJam();
		x.setArgs(args);
		while (true) {
			System.out.print("> ");
			final String s = x.readLine();
			final Block b = Block.parse(new StringReader(s), false);
			x.runCode(b);
			x.clear();
			System.out.println();
		}
	}
}
