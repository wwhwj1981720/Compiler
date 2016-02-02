package translate.tree;

import java.util.Vector;

import unicom.WordUnit;

public class MutiNode {
	public String data;
	MutiNode node[];//开始为空指针
	public WordUnit word=new WordUnit();
	public int blockrow; 
	public WordUnit getWord() {
		return word;
	}
	/*拷贝构造函数*/
	public void setWord(WordUnit word) {
		this.word.setID(word.getID());
		this.word.setNature(word.getNature());
		this.word.setValue(word.getValue());
		this.word.setRow(word.getRow());
		this.data=word.getNature();
	}
	public void insertNode()
	{
		
	}
	public MutiNode getRoot(String word)
	{
		MutiNode root=new MutiNode();
		root.data=word;
		//root.node
		return root;
	}
	public MutiNode insertNode(String []node)
	{
		int size=node.length;
		this.node=new MutiNode[size];//动态分配指针的大小
		for(int i=size-1;i>=0;i--)
		{
			MutiNode nodenew=new MutiNode();
			nodenew.data=node[i];
			this.node[i]=nodenew;
			MutiTree.stack.push(nodenew);
		}
		return MutiTree.stack.peek();
	}
	public MutiNode insertNode(StringBuffer []node)
	{
		int size=node.length;
		this.node=new MutiNode[size];//动态分配指针的大小
		for(int i=size-1;i>=0;i--)
		{
			MutiNode nodenew=new MutiNode();
			nodenew.data=node[i].toString();
			this.node[i]=nodenew;
			if(!nodenew.data.equals("ε"))
			{
				MutiTree.stack.push(nodenew);
			}
		}
		return MutiTree.stack.peek();
	}
	/*
	 * ε与 @+ 的字符串不能入栈
	 * 
	 * **/
	public MutiNode insertNode(Vector<String> vtranslate)
	{
		int size=vtranslate.size();
		this.node=new MutiNode[size];//动态分配指针的大小
		for(int i=size-1;i>=0;i--)
		{
			MutiNode nodenew=new MutiNode();
			nodenew.data=vtranslate.get(i);
			this.node[i]=nodenew;
			if(!(nodenew.data.equals("ε")||ContainSubStr(nodenew.data,"@")))
			{
				MutiTree.stack.push(nodenew);
			}
		}
		return MutiTree.stack.peek();
	}
	public boolean ContainSubStr(String str,String sub)
	{
		boolean flag=false;
		//String str="@+";  //待判断的字符串
		String reg=".*"+sub+".*";  //判断字符串中是否含有特定字符串ll
		if(str.matches(reg)) flag=true;
		return flag;
	}

}
