
public class BinaryTree {

	/**
	 * @param args
	 */
	Node root;
	public void addNode(int key, String name){
		Node newNode = new Node(key, name);
		if(root == null){
			root = newNode;
		}
		else{
			Node focusNode = root;
			Node parent;
			while(true){
				parent = focusNode;
				if(key<focusNode.Key){
					focusNode = focusNode.leftchild;
					if(focusNode == null){
						parent.leftchild = newNode;
						return;
					}
				}
				else{
					focusNode = focusNode.rightchild;
					if(focusNode == null){
						parent.rightchild = newNode;
						return;
					}
				}				
			}
		}
		
	}
	
	public void inordertraversetree(Node focusNode){
		if(focusNode != null){
			inordertraversetree(focusNode.leftchild);
			System.out.println(focusNode);
			inordertraversetree(focusNode.rightchild); 
						
		}
		
	}
	public void preordertraversetree(Node focusNode){
		if(focusNode != null){
			System.out.println(focusNode);
			preordertraversetree(focusNode.leftchild);
			preordertraversetree(focusNode.rightchild);
		}
	}
	public void postordertraversetree(Node focusNode){
		if(focusNode != null){
			postordertraversetree(focusNode.leftchild);
			postordertraversetree(focusNode.rightchild);
			System.out.println(focusNode);
		}
	}
	
	public Node findNode(int key){
		Node focusNode = root;
		while(focusNode.Key != key){
			if(key < focusNode.Key){
				focusNode = focusNode.leftchild;
			}
			else{
				focusNode = focusNode.rightchild;
			}
			
			if(focusNode == null){
				return null;
			}
		}
		return focusNode;
	}
	
	int maxDepth(Node node) {
	    if (node == null) {
	        return (0);
	    } else {
	        // compute the depth of each subtree
	        int lDepth = maxDepth(node.leftchild);
	        int rDepth = maxDepth(node.rightchild);
	        // use the larger one
	        if (lDepth > rDepth)
	            return (lDepth + 1);
	        else
	            return (rDepth + 1);
	    }
	}
	
	
	public boolean remove(int key){
		Node focusNode = root;
		Node parentNode = root;
		boolean isItaleftchild = true;  //use this to determine in the left or right
		
		while(focusNode.Key != key){
			parentNode = focusNode;
			if(key < focusNode.Key){
				isItaleftchild = true;
				focusNode = focusNode.leftchild;
			}
			else{
				isItaleftchild = false;
				focusNode = focusNode.rightchild;
			}
			
			if(focusNode == null){
				return false;
			}
		}
		
		// when the delete node is root or leaf node
		if(focusNode.leftchild == null && focusNode.rightchild == null){    
			if(focusNode == root){
				root = null;
			}
			else if(isItaleftchild){
					parentNode.leftchild =null;
				}
				else{
					parentNode.rightchild = null;
				}
		}
		
		//when the delete node is root or have no rightchild 
		if(focusNode.rightchild == null){
			if(focusNode == root){
				root = focusNode.leftchild;
			}
			else if(isItaleftchild){
				parentNode.leftchild = focusNode.leftchild;
			}
			else{
				parentNode.rightchild = focusNode.leftchild;
			}
			
		}
		
		if(focusNode.leftchild == null){
			if(focusNode == root){
				root = focusNode.rightchild;
			}
			else if(isItaleftchild){
				parentNode.leftchild = focusNode.rightchild;
			}
			else{
				parentNode.rightchild = focusNode.rightchild;
			}
		}
		
		if(focusNode.leftchild != null && focusNode.rightchild != null){
			Node replacement = getReplacementNode(focusNode);
			if(focusNode == root){
				root = replacement;
			}else if(isItaleftchild){
				parentNode.leftchild = replacement;
			}
			else{
				parentNode.rightchild = replacement;
			}
			
			replacement.leftchild = focusNode.leftchild; //check this in order to not break the tree
		}
		
		return true;
	}
	public Node getReplacementNode(Node replaceNode){
		Node replacementParent = replaceNode;
		Node replacement = replaceNode;
		
		Node focusNode = replaceNode.rightchild;
		
		while(focusNode != null){
			replacementParent = replacement; 
			replacement = focusNode;
			focusNode = focusNode.leftchild;  //add the replaceNode.leftchild to the leftmost leaf of the replaceNode.rightchild
		}
		
		/*if(replacement != replaceNode.rightchild){
			replacementParent.leftchild = replacement.rightchild;
			replacement.rightchild = replaceNode.rightchild;
		}*/
		return replacement;
		 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree bt = new BinaryTree();
		
		bt.addNode(15, "A");
		bt.addNode(16, "C");
		bt.addNode(8, "B");
		bt.addNode(10, "E");
		bt.addNode(9, "G");
		bt.addNode(17, "F");
		bt.addNode(1, "D");
		bt.inordertraversetree(bt.root);
		System.out.println(bt.maxDepth(bt.root));
		
		/*bt.addNode(15, "A");
		bt.addNode(10, "B");
		bt.addNode(16, "C");
		bt.addNode(8, "D");
		bt.addNode(12, "E");
		bt.addNode(18, "F");
		bt.addNode(20, "G");
		bt.addNode(6, "H");
		bt.addNode(4, "I");
		bt.addNode(7, "J");
		bt.addNode(3, "K");
		bt.addNode(5, "L");
		bt.inordertraversetree(bt.root);
		bt.remove(8);
		System.out.println("===========================");
		bt.inordertraversetree(bt.root);*/
		
		/*Node getnode;
		bt.addNode(5, "A");
		bt.addNode(4, "B");
		bt.addNode(6, "C");
		bt.addNode(2, "D");
		bt.addNode(1, "E");
		bt.addNode(3, "F");
		bt.addNode(8, "G");
		bt.addNode(7, "H");
		bt.addNode(9, "I");
		System.out.println("INORDER");
		bt.inordertraversetree(bt.root);
		System.out.println("remove 4");
		bt.remove(4);
		bt.inordertraversetree(bt.root);*/
		
		/*System.out.println("PREORDER");
		bt.preordertraversetree(bt.root);
		System.out.println("POSTORDER");
		bt.postordertraversetree(bt.root);	
		System.out.println("Search Node 2");
		getnode = bt.findNode(2);
		System.out.println(getnode);
		System.out.println("Search Node 7");
		System.out.println(bt.findNode(7));
		System.out.println("Search Node 10");
		System.out.println(bt.findNode(10));*/
		
	}

}

class Node{
	int Key;
	String Name;
	Node leftchild;
	Node rightchild;
	
	Node(int key, String name){
		this.Key = key;
		this.Name = name;
				
	}
	
	public String toString(){
		return Name + "has a key" + Key;
		//this can be use to return a key and print it out
	}
	
	
}


