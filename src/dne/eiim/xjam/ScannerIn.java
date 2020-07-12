package dne.eiim.xjam;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerIn implements In {
	private final Scanner sc;
	private boolean eof;
	
	public ScannerIn(final Scanner sc) {
		this.sc = sc;
	}

	@Override
	public String readNext() {
		if (eof) {
			throw new RuntimeException("EOF");
		}
		try {
			return sc.next();
		}
		catch (NoSuchElementException e) {
			eof = true;
			return "";
		}
	}
	
	@Override
	public String readLine() {
		if (eof) {
			throw new RuntimeException("EOF");
		}
		try {
			return sc.nextLine();
		}
		catch (NoSuchElementException e) {
			eof = true;
			return "\n";
		}
	}
	
	@Override
	public String readAll() {
		if (eof) {
			return "";
		}
		eof = true;
		try {
			return sc.useDelimiter("\\A").next();
		}
		catch (NoSuchElementException e) {
			return "";
		}
	}
}
