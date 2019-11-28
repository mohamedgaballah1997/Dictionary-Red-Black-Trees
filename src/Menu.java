import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		String wordScanned; 
		boolean work=true;
		Node r=Node.nil;
				  Scanner scan = new Scanner(System.in);
				  Scanner scan2 = new Scanner(System.in);
			        while(work) {
			            System.out.println("\n1.- Insert word\n"
			                    + "2.- Delete word\n"
			                    + "3.- Search word\n"
			                    + "4.- Print Height\n"
			                    + "5.- End\n");
			            int choice = scan.nextInt();
			            switch (choice) {
			            case 1:
			            	System.out.println("Enter Word:");
			            	wordScanned = scan2.nextLine();
			            	r=r.insertNode(wordScanned);
                            System.out.println("Dictionary size= "+r.counter);
                            System.out.println("Height= "+r.height());
                            r.printInorder();
			            	break;
			            case 2:
			            	System.out.println("Enter Word You Want To Delete:");
			            	wordScanned = scan2.nextLine();
			            	r=r.delete(wordScanned);
			            	 System.out.println("Dictionary size= "+r.counter);
	                         System.out.println("Height="+r.height());	
	                         r.printInorder();
			            	break;
			            case 3:
			            	System.out.println("Enter Word You Want To Search:");
			            	wordScanned= scan2.nextLine();
			            	if(r.search(wordScanned)==null)
			            		System.out.println("This word doesn't exist");
			            	else
			            		System.out.println("This word exist");
			            	break;
			            case 4:
			            	int height= r.height();
			            	System.out.println(""+height);
			            	break;
			            	
			            case 5:
			            	work=false;
			            	break;
			            }

	}

}
}