package translate.tree;

import java.util.Stack;


public class MutiTree {
	public MutiNode root;
	public static Stack<MutiNode> stack=new Stack<MutiNode>(); 
	public void init()
	{
		MutiNode node=new MutiNode();
		node.data="#";
		stack.push(node);
		
	}
	public void CreateTree(String now)
	{
		root=new MutiNode();
		root.data=now;
		stack.push(root);
		//root.node=null;
	}
	public MutiNode insertNode(String []node)
	{
		int size=node.length;
		root.node=new MutiNode[size];//动态分配指针的大小
		for(int i=size-1;i>=0;i--)
		{
			MutiNode nodenew=new MutiNode();
			nodenew.data=node[i];
			root.node[i]=nodenew;
			stack.push(nodenew);
		}
		return root.node[0];
	}
	
	/*
	 * 后续遍历多叉树 所所有节点
	 * */
	public static void  travelTree (MutiNode tree,PrintTree<MutiNode> printTree)
	{
		
		//MutiNode nownode=tree.
		MutiNode sontree[]=tree.node;
		if(sontree!=null)
		{
			for(MutiNode son:sontree)
			{
				if(son!=null)
				{
					travelTree(son,printTree);
				}
				
			}
		}
//		if(tree.node==null)//判断是否是叶子节点
//		{
			//System.out.println(tree.data);
			printTree.printNode(tree);
		//}
	}
	/*
	 * 后续遍历多叉树叶子节点（终端节点）
	 * */
	public static void  travelTreeFinalNode(MutiNode tree,PrintTree<String> printTree)
	{
		
		//MutiNode nownode=tree.
		MutiNode sontree[]=tree.node;
		if(sontree!=null)
		{
			for(MutiNode son:sontree)
			{
				if(son!=null)
				{
					travelTreeFinalNode(son,printTree);
				}
				
			}
		}
		if(tree.node==null)//判断是否是叶子节点
		{
			//System.out.println(tree.data);
			printTree.printNode(tree.data);
		}
	}
	public static void  travelTreeNode(MutiNode tree,PrintTree<MutiNode> printTree)
	{
		
		//MutiNode nownode=tree.
		MutiNode sontree[]=tree.node;
		if(sontree!=null)
		{
			for(MutiNode son:sontree)
			{
				if(son!=null)
				{
					travelTreeNode(son,printTree);
				}
				
			}
		}
		if(tree.node==null)//判断是否是叶子节点
		{
			//System.out.println(tree.data);
			printTree.printNode(tree);
		}
	}
	/*
	 * 后续遍历多叉树 度为3的点
	 * */
	public static void  travelTreeGetSpecialDot(MutiNode tree)
	{
		
		//MutiNode nownode=tree.
		MutiNode sontree[]=tree.node;
		if(sontree!=null)
		{
			for(MutiNode son:sontree)
			{
				if(son!=null)
				{
					travelTreeGetSpecialDot(son);
				}
				
			}
		}
		if((sontree!=null)&&(sontree.length==2))//判断是否是叶子节点
		{
			System.out.println(tree.data);
		}
	}

}
