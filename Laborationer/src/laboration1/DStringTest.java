package laboration1;

public class DStringTest {

	private DString dstring;

	public void testAppendDString() {
		DString dstring1 = new DString(1);
		DString dstring2 = new DString();

		dstring1.append('A');
		dstring2.append('B');
		dstring2.append('C');

		dstring1.append(dstring2);

		System.out.println(dstring1.toString());
	}

	public void testDelete() {
		dstring = new DString("Hello");
		dstring.delete(2, 4);

		System.out.println(dstring.toString());
	}
	
	public void testSubstring() {
		dstring = new DString("Hello World");
		System.out.println(dstring.substring(6,dstring.length()));
		
		dstring = new DString("Hello Big World");
		System.out.println(dstring.substring(6));
		
	}
	
	public void testIndexOf() {
		dstring = new DString("Hello Big World");
		
		System.out.println(dstring.indexOf('B'));
	}
	
	public void testChaining(){
		DString str1 = new DString("Laboration");
		str1.append(' ').append('1').append(new DString("\nUppgift3")); 
		System.out.println(str1);
		str1.delete(2).delete(4,6).delete(8);
		System.out.println(str1);
	}

	public static void main(String[] args) {
		DStringTest dTest = new DStringTest();
		// dTest.testAppendDString();
//		dTest.testDelete();
//		dTest.testSubstring();
//		dTest.testIndexOf();
		dTest.testChaining();
	}
}
