package translate.tree;




public class testTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//B>TB'
		
		MutiNode stacktop=null;
		MutiTree tree=new MutiTree();
		tree.init();
		tree.CreateTree("B");
		String []node1={"T","B'"};
		stacktop=tree.root;
		tree.insertNode(node1);
		//T->HT'
		String []node2={"H","T'"};
		stacktop=tree.stack.peek();//当前的栈顶元素
		stacktop.insertNode(node2);
		

	}

}
