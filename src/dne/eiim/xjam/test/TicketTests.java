package dne.eiim.xjam.test;

import dne.eiim.xjam.XJam;

import static org.junit.Assert.*;
import org.junit.Test;

public class TicketTests {
	protected static void check(final String code, final String result) {
		assertEquals(result, XJam.run(code, ""));
	}
	
	@Test
	public void ticket1() {
		check("9223372036854775807)", "9223372036854775808");
		check("-9223372036854775808(", "-9223372036854775809");
	}
	
	@Test
	public void ticket2() {
		check("5,:mp", "00110");
		check("2,:p", "0\n1\n");
		check("2,\"AB\"fm<`", "[\"AB\" \"BA\"]");
		check("[[[1 2][3 4]]]:z`", "[[[1 3] [2 4]]]");
		check("[[1 2][3 4]]5ff+`", "[[6 7] [8 9]]");
		check("[[1 2 3 4][5 6 7 8][9 1]]::+`", "[10 26 10]");
	}
	
	@Test
	public void ticket4() {
		check("5 7 9]{6>}=", "7");
		check("5 7 9]{6>}#", "1");
	}
	
	@Test
	public void ticket6() {
		check("5{_}{(_}w", "432100");
	}
	
	@Test
	public void ticket7() {
		check("{\"\\\"\"}", "{\"\\\"\"}");
		check("{\"\\\"\"}`", "{\"\\\"\"}");
	}
	
	@Test
	public void ticket8() {
		check("6mf2a|`", "[2 3]");
		check("6mF0=[1 2]|`", "[2 1]");
		check("6mf0=mp", "1");
	}
	
	@Test
	public void ticket9() {
		check("'A66&", "@");
		check("'A66|", "C");
	}
	
	@Test
	public void ticket12() {
		check("3{)}/]`", "[1 2 3]");
		check("3{)}%`", "[1 2 3]");
		check("5{mp},`", "[2 3]");
	}
	
	@Test
	public void ticket13() {
		check("3,1&", "1");
		check("3,5|", "0125");
		check("3,1^", "02");
		check("1 3,-", "");
	}
	
	@Test
	public void ticket20() {
		check("[1 2 3] [7 6 2] .+`", "[8 8 5]");
	}
	
	@Test
	public void ticket23() {
		check("'a'\\]`", "\"a\\\\\"");
		check("'a'\"]`", "\"a\\\"\"");
		check("'\\'a]`", "\"\\a\"");
		check("'\"'a]`", "\"\\\"a\"");
		check("'a'\\'b]`", "\"a\\b\"");
		check("'a'\"'b]`", "\"a\\\"b\"");
		check("'a'\\'\\'b]`", "\"a\\\\\\b\"");
		check("'a'\\'\"'b]`", "\"a\\\\\\\"b\"");
		check("'a'\"'\\'b]`", "\"a\\\"\\b\"");
	}
	
	@Test
	public void ticket25() {
		check("1]{}#]0-", "");
	}
	
	@Test
	public void ticket30() {
		check("[1 2 3 4 5 6]3ew`", "[[1 2 3] [2 3 4] [3 4 5] [4 5 6]]");
	}
	
	@Test
	public void ticket35() {
		check("[1 2 3] 5 10 e[`", "[10 10 1 2 3]");
		check("[1 2 3] 5 10 e]`", "[1 2 3 10 10]");
		check("[1 2 3] 2 10 e]`", "[1 2 3]");
		check("[1 2 3] 2 10 e[`", "[1 2 3]");
	}
	
	@Test
	public void ticket41() {
		check("[1 2 3]e!`", "[[1 2 3] [1 3 2] [2 1 3] [2 3 1] [3 1 2] [3 2 1]]");
		check("[3 1 3]e!`", "[[1 3 3] [3 1 3] [3 3 1]]");
		check("[3 1 3]m!`", "[[3 1 3] [3 3 1] [1 3 3] [1 3 3] [3 3 1] [3 1 3]]");
	}
	
	@Test
	public void ticket43() {
		check("[[1 2][]3[[4] 5]]e_`", "[1 2 3 4 5]");
	}
	
	@Test
	public void ticket44() {
		check("3 1b`", "[1 1 1]");
	}
	
	@Test
	public void ticket45() {
		check("[1 2]3m*`", "[[1 1 1] [1 1 2] [1 2 1] [1 2 2] [2 1 1] [2 1 2] [2 2 1] [2 2 2]]");
		check("3 2m*`", "[[0 0] [0 1] [0 2] [1 0] [1 1] [1 2] [2 0] [2 1] [2 2]]");
		check("[1 2][3 4]m*`", "[[1 3] [1 4] [2 3] [2 4]]");
	}
	
	@Test()
	public void ticket47() {
		assertTrue(XJam.run("[1 2 3]0/", "").contains("RuntimeException: Invalid size for splitting"));
	}
	
	@Test
	public void ticket52() {
		check("{\"\\a\"}`", "{\"\\a\"}");
		check("{\"\\a\"}", "{\"\\a\"}");
		check("\"\\a\"`", "\"\\a\"");
		check("\"\\a\"", "\\a");
	}
}
