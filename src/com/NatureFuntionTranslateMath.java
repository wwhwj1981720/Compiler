package com;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import unicom.CFile;
import unicom.WordUnit;
/**
 * @author Administrator
 *    本项目研究  java 简单语法算术表达的解析，同时在 改用Deque<WordUnit> strque=new ArrayDeque<WordUnit>();
 *  stack 是词法分析栈
 *  strque 是输入串存贮的栈
 *  
 *  
 *  
 *  
 *  B->B+T|B-T|T
 *   T->T*H|T/H|H
 *   H->num|id|(B)
 *   因为要进行语义分析，所以可以先拿算术表达式进行试验

B->TB'
B'->+TB'|-TB'|ε
T->HT'
T'->*HT'|/HT'|ε
H->num|id|(B)
	num	id	(	)	*	/	+	-	#
B	TB'	TB'	TB'						
B'				ε			+TB'	-TB'	ε
T	HT'	HT'	HT'						
T'				ε	*HT'	/HT'	ε	ε	ε
H	num	id	(E)						
									

 *   
 *   
 *   
 */
public class NatureFuntionTranslateMath  {
	//Queue<WordUnit> strque=new LinkList<WordUnit>(); 
	Deque<WordUnit> strque=new ArrayDeque<WordUnit>();
	CFile cf=new CFile("d:\\mid.txt");//结果输出文件
	Map<String,Integer> vn=new HashMap<String,Integer>();
	Map<String,Integer> vt=new HashMap<String,Integer>();
	Map<String,String> relation=new HashMap<String,String>();
	Stack<String>   stack=new Stack<String>();
	//Queue<String> strque=new LinkedList<String>(); 
	static int M=5;
	static int N=9;
	Stack v[][]=new Stack[M][N];//ll(1)分析标表的 规则存储
	int i=0;
	int j=0;
	//String  in;
	public void init()
	{
		vn.put("B",0);
		vn.put("B'",1);
		vn.put("T",2);
		vn.put("T'",3);
		vn.put("H",4);
		
		
		
		
		
		vt.put("num",0);
		vt.put("id",1);
		vt.put("(",2);
		vt.put(")",3);
		vt.put("*",4);
		vt.put("/",5);
		vt.put("+",6);
		vt.put("-",7);
		vt.put("#",8);
		
//		relation.put("Sid", "A");
//		relation.put("Sif", "C");
//		relation.put("Swhile", "F");
//		relation.put("Aid", "id=id");
//		relation.put("Cif", "if(E)S");
//		
//		relation.put("Fwhile", "while(E)S");
//		relation.put("Eid", "idU");
//		relation.put("U<", "<id");
//		relation.put("U<", "<id");
//		relation.put("U==", "==id");
//		relation.put("U>", ">id");
//		
		
	}
	public void VT()
	{
		
	}
	//初始化LL分析表
	/*
	 * 		0	1	2	3	4	5		6	7	8	9
		id	if	while	<	==	>	=	(	)	public	type
0	S										S->publicclasstype{DP}	
1	A	A->Id=id										
2	C		C->if(E)s									
3	F			F->while(E)S								
4	E	E->idU										
5	U				U-><id	U->==id	U->>id					
6	D											D->typeM
7	M	M->idN										
8	N										N->ε	N->D

	 * */
	public void initVector()
	{
		
		for(int i=0;i<M;i++)
		{
			for(int j=0;j<N;j++)
			{
				v[i][j]=null;//开始对矩阵要初始化 
			}
		}
		v[0][0]=new Stack();
		v[0][0].add("T");
		v[0][0].add("B'");
		v[0][1]=new Stack();
		v[0][1].add("T");
		v[0][1].add("B'");
		
		v[0][2]=new Stack();
		v[0][2].add("T");
		v[0][2].add("B'");
		
		v[1][3]=new Stack();
		v[1][3].add("ε");
		
		
		v[1][6]=new Stack();
		v[1][6].add("+");
		v[1][6].add("T");
		v[1][6].add("B'");
		
		v[1][7]=new Stack();
		v[1][7].add("-");
		v[1][7].add("T");
		v[1][7].add("B'");
		
		
		
		v[1][8]=new Stack();
		v[1][8].add("ε");
		
		v[2][0]=new Stack();
		v[2][0].add("H");
		v[2][0].add("T'");
		v[2][1]=new Stack();
		v[2][1].add("H");
		v[2][1].add("T'");
		
		v[2][2]=new Stack();
		v[2][2].add("H");
		v[2][2].add("T'");
		
		v[3][3]=new Stack();
		v[3][3].add("ε");
		
		//v[1][0].add("S");
		v[3][4]=new Stack();
		v[3][4].add("*");
		v[3][4].add("H");
		v[3][4].add("T'");
		
		
		
		v[3][5]=new Stack();
		v[3][5].add("/");
		v[3][5].add("H");
		v[3][5].add("T'");
		v[3][6]=new Stack();
		v[3][6].add("ε");
		
		v[3][7]=new Stack();
		v[3][7].add("ε");
		
		
		v[3][8]=new Stack();
		v[3][8].add("ε");
	
		v[4][0]=new Stack();
		v[4][0].add("num");
		
		v[4][1]=new Stack();
		v[4][1].add("id");
		
		
		v[4][2]=new Stack();
		v[4][2].add("(");	
		v[4][2].add("B");
		v[4][2].add(")");
		
		
	}
	/*
	 * 开始 是  B 不是 S
	 * 
	 * **/
	public boolean analyasis(List<WordUnit> in)
	{

		boolean result=false;
		stack.clear();
		stack.add("#");
		stack.add("B");
		setInStr(in);
		String nowc=getOneStack();//词法分析栈中的词
		WordUnit ch=pollWordUnit();//待匹配串中的词
		while(!(nowc.equals(ch.getNature())&&nowc.equals("#")))
		{
			
			if(vn.containsKey(nowc))//nowc 词法分析栈中当前串 
			{
			
				if(findTable(nowc,ch))
				{
					Stack regular=v[i][j];
					if(regular!=null)
					{
						stack.pop();
						putStackReverse(regular,nowc);
												
					}
				}
				else
				{
					System.out.println("ll 分析表中没有 匹配的"+nowc+" "+ch);
					break;
				}
				
			}
			else if((vt.containsKey(nowc)))
			{
				if((nowc.equals(ch.getNature()))&&(!ch.getNature().equals("#")))
				{
					stack.pop();
					//从栈中弹出  终端字符
					//this.strque.poll();//将匹配字符串中的结果也弹出
					try {
						cf.writeOneRow(this.strque.poll().getValue());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(!nowc.equals(ch.getNature()))
				{
					System.out.println("分析栈中的 字符与当前串中的字符不匹配"+nowc+" "+ch);
					break;
				}
			}
			
			nowc=getOneStack();
			ch=pollWordUnit();
		}
		System.out.println("success");
		return result;
	
	}
	/**
	 *    带匹配的串初始化
	 * 
	 * **/
	public  void setInStr(List<WordUnit> in)
	{
		strque.clear();
		for(WordUnit ch:in)
		{
			strque.offer(ch);
		}
		
		
	}
	String getOneStack()
	{
		String c=stack.peek();
		return c;
	}
	//从带匹配的队列中取当前词
	public WordUnit pollWordUnit()
	{
			WordUnit ch=strque.peek();
			return ch;
	}
	public boolean findTable(String nowc,WordUnit ch)
	{
		boolean flag=false;
		//String joinc=joinString(nowc, ch);
		try{
			i=vn.get(nowc);
			j=vt.get(ch.getNature());
			if(v[i][j]!=null) flag=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public void putStackReverse(Stack<String> regular,String nowc)
	{
		int len=regular.size();//规则的长度
		StringBuffer regularstr = new StringBuffer();
		String str=null;
		int pos=0;
		for(int i=0;i<len;i++)
		{
			pos=len-1-i;
			str=regular.get(pos);
			if(!str.equals("ε"))  //注意 ε不用往stack中添加
			stack.push(str);
			regularstr.insert(0, str);
		}
		//regularstr.reverse();
		System.out.println(nowc+"->"+regularstr.toString());
		grammarTranslate();
	}
	public void grammarTranslate()
	{
		
	}
}
