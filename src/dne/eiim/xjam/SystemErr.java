package dne.eiim.xjam;

public class SystemErr extends PrintStreamOut {
	public SystemErr() {
		super(System.err);
	}
}
