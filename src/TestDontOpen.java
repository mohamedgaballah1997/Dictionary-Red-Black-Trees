import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestDontOpen {
	public static void main(String[] args) {
		//Node r = Node.createRoot("a");
		//r=r.insertNode("c");
		//r=r.insertNode("d");
//		r=r.insertNode("bac");
//		r=r.insertNode("bbq");
//		r=r.insertNode("doc");
//		r=r.insertNode("dz");
//		r=r.insertNode("car");
//		r=r.insertNode("comp");
		//r=r.insertNode("gar");
		//r=r.insertNode("apple");
		//r=r.insertNode("banana");
		//FileReader filereader = new FileReader("dictionary.txt");
		//BufferedReader bufferreader = new BufferedReader(filereader);
		/*r.printInorder();
		System.out.println();
		System.out.println();
		r=r.delete(r.value);
		r=r.delete("d");
		r.printInorder();
		System.out.println();
//		System.out.println();
//		r=r.delete("abc");
//		r.printInorder();
//		System.out.println();
//		System.out.println();
//		r=r.delete("bbq");
//		r.printInorder();
//		System.out.println();
//		System.out.println();
//		r=r.delete("aab");
//		r.printInorder();
//		System.out.println();
//		System.out.println();
//		r=r.delete("doc");
//		r.printInorder();
//		System.out.println();
//		System.out.println();
 * */
 
		File file = new File("dictionary.txt");
		Scanner sc;
		
		try {
			int counter=0;
			Node r=Node.nil;
			sc = new Scanner(file);
			while(sc.hasNextLine())
			{
				String str=sc.nextLine();
				//System.out.println(str);
			r= r.insertNode(str);	
			//r.printInorder(r);
			counter++;
			}
			r=r.insertNode("jtdhkjgtd");
			r=r.insertNode("jtdhjgtd");
			r=r.insertNode("jdhkjgtd");
			r=r.insertNode("jtdjgd");
			r=r.insertNode("jtkjgd");
			r=r.insertNode("tdhkjgd");
			r=r.insertNode("jtdhgd");
			r=r.insertNode("tdrrhkjg");
			r=r.insertNode("g");
			r=r.insertNode("tdhskjg");
			r=r.insertNode("tdhakjg");
			r=r.insertNode("trrehkjg");
			r=r.insertNode("tdhfkjg");
			r=r.insertNode("tdhgkjg");
			r=r.insertNode("tdghkjg");
			r=r.insertNode("tdehkjg");
			r=r.insertNode("tdhkgjg");
			r=r.insertNode("tdfhkjg");
			r=r.insertNode("tdvfhkjg");
			r=r.insertNode("tdfggfhkjg");
			r=r.insertNode("tdvfghkjg");
			r=r.insertNode("tdfgghkjg");
			r=r.insertNode("tdvfgghkjg");
			r=r.insertNode("tdfgghgkjg");
			r=r.insertNode("tdvgggfhkjg");
			r=r.insertNode("tdfhgghkjg");
			r=r.insertNode("tdvggggfhkjg");
			
			//r.printInorder(r);
			//System.out.println(counter);
			System.out.println(r.height());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	
	}

}
