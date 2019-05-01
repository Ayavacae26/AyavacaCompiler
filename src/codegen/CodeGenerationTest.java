package codegen;

import static org.junit.Assert.*;
import org.junit.Test;
import parser.Parser;
import syntaxtree.*;

public class CodeGenerationTest {

	/**
	 * Test of writeCodeForRoot method, of class CodeGeneration.
	 */
	@Test
	public void testWriteCodeForRoot() {
		System.out.println("writeCodeForRoot");
		Parser p = new Parser("3 * (4 + 7)", false);
		ExpressionNode root = p.expression();
		CodeGeneration instance = new CodeGeneration();
		String expResult = ".data\n" + "answer:   .word   0\n" + "\n" + "\n" + ".text\n" + "main:\n"
				+ "addi   $t0,   $zero, 3\n" + "addi   $t2,   $zero, 4\n" + "addi   $t3,   $zero, 7\n"
				+ "add    $t1,   $t2,   $t3\n" + "mult   $t0,   $t1\n" + "mflo   $s0\n" + "sw     $s0,   answer\n"
				+ "addi   $v0,   10\n" + "syscall\n" + "";
		String result = instance.writeCodeEx(root, null);
		System.out.println("Code is \n" + result);
		assertEquals(expResult, result);
	}
}
