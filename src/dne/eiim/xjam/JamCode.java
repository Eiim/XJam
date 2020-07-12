package dne.eiim.xjam;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JamCode {
	public static void main(final String... args) throws FileNotFoundException {
		final XJam x = new XJam();
		final Block b = Block.parse(new FileReader(args[0]), false);
		final int n = Integer.parseInt(x.readNext());
		x.readLine();
		for (int i = 1; i <= n; ++i) {
			b.run(x);
			x.print("Case #" + i + ": ");
			x.dump();
			x.clear();
			x.println();
		}
	}
}
