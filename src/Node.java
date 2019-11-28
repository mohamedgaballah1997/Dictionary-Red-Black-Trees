
public class Node {
    public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLUE = "\u001B[31m";
String value;
boolean color;
Node left;
Node right;
Node parent;
final static Node nil=new Node("AAAAAA",null);
static int counter=0;

public Node(String value,Node parent) {
	super();
	this.value = value;
	if(parent==null) {
		this.color=true;
		this.parent=nil;

		
	}
	else {
		this.color=false;
		this.parent=parent;
	}
	this.left=this.right=nil;
}

public static Node createRoot(String value) {
	Node newNode=new Node(value, null);
	nil.right=nil.left=nil.parent=nil;
	newNode.color=true;
	return newNode;
}

public Node insertNode(String value) {
	if(this==nil) {
		counter++;
		return createRoot(value);
		
	}
	if(value.compareTo(this.value)>0) {
		if(this.right==nil) {
			this.right=new Node(value,this);
			counter++;
			this.right.balance();
		}
		else {
				this.right.insertNode(value);
		}
	}
		else if(value.compareTo(this.value)<0)  {
			if(this.left==nil) {
				this.left=new Node(value,this);
				counter++;
				this.left.balance();
			}
			else {
					this.left.insertNode(value);
			}
		}
		else {
			System.out.println(this.value + " already exists");
		}
	
 return this.getRoot();	
 }
public Node search(String srch )
{
	Node r=this;
    Node found = null;
    while ((r != nil) || found!=null)
    {
        String rval = r.value;
        if (rval.compareToIgnoreCase(srch)>0)
            r = r.left;
        else if (rval.compareToIgnoreCase(srch)<0)
            r = r.right;
        else
        {
            found = r;
            break;
        }
        found = r.search(srch);
    }
    return found;

}
public Node getGrandParent() {
	return this.parent.parent;
}
public Node getUncle() {
	Node parent=this.parent;
	Node grandParent=parent.parent;
	if(parent.equals(grandParent.right))
		return grandParent.left;
	else 
		return grandParent.right;
}
public void swapColor() {
	if(this.parent.equals(nil)) 
		this.color=true;
	else
		this.color=!this.color;
}
public boolean isleaf() {
	if(this.right==nil && this.left==nil) return true;
	
			return false;
}
public Node getPre() {
	Node temp=this;
	if(temp.left == nil) return temp.parent;
	temp=temp.left;
	while(temp.right!=nil) temp=temp.right;
	return temp;
}
/*
public Node delete() {
	if(this.color == false || this.parent.color==false)
	{
		Node pre=this.getPre();
		if(pre.direction()=='r')
			pre.parent.right=nil;
		else
			pre.parent.left=nil;
		String tempV=this.value;
		this.value=pre.value;
		pre.value=tempV;
		boolean tempC=this.color;
		this.color=pre.color;
		pre.color=tempC;
		return this.getRoot();
	}
	
	return this.getRoot();
}*/
public void balance() {
	Node parent=this.parent;
	Node grandparent=this.getGrandParent();
	if(this.parent.color==true) return;
	Node uncle=this.getUncle();

	if(uncle.color==false) {
				uncle.color=true;
		this.parent.color=true;
		this.getGrandParent().color=false;
		getGrandParent().balance();
	}
	
	else {
		if(this.direction()=='r' && parent.direction()=='l') {

			this.rotateLeft();
			this.rotateRight();
			parent=this;
			
		}
		else if(this.direction()=='l' && parent.direction()=='r') {
			this.rotateRight();
			this.rotateLeft();
			parent=this.parent;
		}
		else if(this.direction()=='r' && parent.direction()=='r') {
			parent.rotateLeft();
			
			
		}
		else if(this.direction()=='l' && parent.direction()=='l') {
			parent.rotateRight();
		}
    //    System.out.println("after rec:");
		parent.swapColor();
		grandparent.swapColor();
	}
}

public Node getRoot() {
	Node temp=this;
	while(!temp.parent.equals(nil))
		temp=temp.parent;
	temp.color=true;
	//nil.left=nil.right=nil.parent=nil;
	return temp;
}
public void rotateLeft() {
	// "this" is the parent node
	Node grandParent=this.parent;
	// x is parent of grand  parent(subtree)
	Node x=this.getGrandParent();
	
	this.parent=x;
	if(!x.equals(nil)) {
		if(grandParent.direction()=='r')
			x.right=this;
		else
			x.left=this;
	}
	//m is left child of parent (subtree)
	Node m=this.left;
	grandParent.right=m;
	if(!m.equals(nil)) {
		m.parent=grandParent;
	}
	this.left=grandParent;
	grandParent.parent=this;
}

void transplant(Node target, Node with){ 
	
	Node tmp;
    if(target.parent == nil){
        tmp=target.getRoot();
        tmp= with;
    }
    else if(target == target.parent.left){
        target.parent.left = with;
    }else
        target.parent.right = with;
    with.parent = target.parent;
}

Node delete(String value){
	Node root=this.getRoot();
	Node z;
  if((z = search(value))==null) {
	  System.out.println("word does not exist");
	  return this.getRoot();
  }
  Node x;
  Node y = z; // temporary reference y
  boolean y_original_color = y.color;
  if(z.left == nil){
      x = z.right;  
     transplant(z, z.right); 
 	if(z==root) {
		root=x;
	}
  }
  else if(z.right == nil){
      x = z.left;
      transplant(z, z.left);
  	if(z==root) {
		root=x;
	}
  }
  else{
      y = z.getPre();
      y_original_color = y.color;
      x = y.left;
      z.value=y.value;
         transplant(y, x);
         root=root.getRoot();
      }
  if(y_original_color==true)
      deleteFixup(x);  
	nil.left=nil.right=nil.parent=nil;
	System.out.println("Deleted Succesfully");

	counter--;
  return root.getRoot();
}

void deleteFixup(Node x){
  while(x!=x.getRoot() && x.color == true){ 
      if(x == x.parent.left){
          Node w = x.parent.right;
          if(w.color == false){
        	  
              w.color = true;
              x.parent.color = false;
              w.rotateLeft();
              w = x.parent.right;
          }
          if(w.left.color == true && w.right.color == true){
              w.color = false;
              x = x.parent;
              continue;
          }
          else if(w.right.color == true){
              w.left.color = true;
              w.color = false;
              w.left.rotateRight();
              w = x.parent.right;
          }
          if(w.right.color == false){
              w.color = x.parent.color;
              x.parent.color = true;
              w.right.color = true;
              w.rotateLeft();
              x = x.getRoot();
          }
      }else{
          Node w = x.parent.left;
          if(w.color == false){
              w.color = true;
              x.parent.color = false;
              w.rotateRight();
              w = x.parent.left;
          }
          if(w.right.color == true && w.left.color == true){
              w.color = false;
              x = x.parent;
              continue;
          }
          else if(w.left.color == true){
              w.right.color = true;
              w.color = false;
              w.right.rotateLeft();
              w = x.parent.left;
          }
          if(w.left.color == false){
              w.color = x.parent.color;
              x.parent.color = true;
              w.left.color = true;
              w.rotateRight();
              x = x.getRoot();
          }
      }
  }
  x.color = true; 
}

public void rotateRight() {
	// this is the parent
	Node grandParent=this.parent;
	// x is parent of grand  parent(subtree)
	Node x=this.getGrandParent();
	
	this.parent=x;
	if(!x.equals(nil)) {
		if(grandParent.direction()=='r')
			x.right=this;
		else
			x.left=this;
	}
	//m is left child of parent (subtree)
	Node m=this.right;
	grandParent.left=m;
	if(!m.equals(nil)) {
		m.parent=grandParent;
	}
	this.right=grandParent;
	grandParent.parent=this;
}
public char direction() {
	Node parent=this.parent;
	if(this.equals(parent.right)) return 'r';
	else return 'l';
}
public void printInorder() 
{ 
    if (this != nil) {

    /* first recur on left child */
   this.left.printInorder(); 

    /* then print the data of node */
    System.out.print(this.value + " ");
    if(this.color==true)
    System.out.println("B");
    else
    System.out.println("R");
    //System.out.print(ANSI_BLUE+ node.value + " "+ ANSI_RESET);
    

    /* now recur on right child */
    this.right.printInorder(); 
    }

} 
public int height() {
	if(this==nil) return -1;
	return 1+ Math.max(this.right.height(), this.left.height());
}


}
