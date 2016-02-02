package com;

import java.io.IOException;
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
 *    本项目研究  java 简单语法的解析,本次研究对  S->publicclasstype{DP}的描述是有问题的应该改成     S->publicclasstype{Z}
 *  stack 是词法分析栈
 *  strque 是输入串存贮的栈
 *  
 *  S->publicclasstype{DP}
 *  D->typeid
 *  D->typeidD
 *  P->publictype(type){L}  
 *   L->A|C|F
 *   A->id=id
 *   C->if(E)L
 *   F->while(E)L
 *   E->id<id|id==id|id>id

 *   
 *   S开始  java
 *   D 定义 变量
 *   P 函数
 *   L 语句
 *   A赋值
 *   C条件
 *   F循环ε
 *   E表达
 *   select(S->publicclasstype{DP})=first(publicclasstype{DP}) public
 *   D->typeM
 *   M->varN
 *   N->ε|D
 *  
 *   select(D->typeM)=first(typeM) type
 *   select(M->varN)=first(varN) var
 *   select(N->ε|D)=first(D) type
 *                  follow(N->ε) public,}
 *                  
 *                  
 *   select(P->publictypeid(type){L})=firest(publictypeid(type){L}) public   
 *   select(L->A|C|F)=first(A) id
 *              first(C) if
 *              first(F) while
 *	select(A->id=id) first(id=id) id
 *  select(c->if(E)s) first(if(E)S) if
 *  select(F->while(E)S) 	 first(while(E)S) while
 *  select(E->idU)      first(id) id
 *  select(U->==id) first(==id) ==
 *  select(U->>id)  first(>id)  >
 *   
 */
public class LLNatureFuntionTranslateJava  {
	Queue<WordUnit> strque=new LinkedList<WordUnit>(); 
	CFile cf=new CFile("d:\\mid.txt");//结果输出文件
	Map<String,Integer> vn=new HashMap<String,Integer>();
	Map<String,Integer> vt=new HashMap<String,Integer>();
	Map<String,String> relation=new HashMap<String,String>();
	Stack<String>   stack=new Stack<String>();
	//Queue<String> strque=new LinkedList<String>(); 
	static int M=11;
	static int N=15;
	Stack v[][]=new Stack[M][N];//ll(1)分析标表的 规则存储
	int i=0;
	int j=0;
	//String  in;
	public void init()
	{
		vn.put("S",0);
		vn.put("A",1);
		vn.put("C",2);
		vn.put("F",3);
		vn.put("E",4);
		vn.put("U",5);
		vn.put("D",6);
		vn.put("M",7);
		vn.put("N",8);
		vn.put("P",9);
		vn.put("L",10);
		
		
		
		
		vt.put("id",0);
		vt.put("if",1);
		vt.put("while",2);
		vt.put("<",3);
		vt.put("==",4);
		vt.put(">",5);
		vt.put("=",6);
		vt.put("(",7);
		vt.put(")",8);
		vt.put("public",9);
		vt.put("type",10);
		vt.put("class",11);
		vt.put("{",12);
		vt.put("}",13);
		vt.put("#",14);
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
		v[0][9]=new Stack();
//		v[0][0].add("A");
//		v[0][1]=new Stack();
//		v[0][1].add("C");
//		v[0][2]=new Stack();
//		v[0][2].add("F");
		v[0][9].add("public");
		v[0][9].add("class");
		v[0][9].add("type");
		v[0][9].add("{");
		v[0][9].add("D");
		v[0][9].add("P");
		v[0][9].add("}");
		
		v[1][0]=new Stack();
		v[1][0].add("id");
		v[1][0].add("=");
		v[1][0].add("id");
		//v[1][0].add("S");
		v[2][1]=new Stack();
		v[2][1].add("if");
		v[2][1].add("(");
		v[2][1].add("E");
		v[2][1].add(")");
		v[2][1].add("L");
		
		v[3][2]=new Stack();
		v[3][2].add("while");
		v[3][2].add("(");
		v[3][2].add("E");
		v[3][2].add(")");
		v[3][2].add("L");
		
		v[4][0]=new Stack();
		v[4][0].add("id");
		v[4][0].add("U");
		
		v[5][3]=new Stack();
		v[5][3].add("<");
		v[5][3].add("id");
		v[5][4]=new Stack();
		v[5][4].add("==");
		v[5][4].add("id");
		v[5][5]=new Stack();
		v[5][5].add(">");
		v[5][5].add("id");
		
		v[6][10]=new Stack();
		v[6][10].add("type");
		v[6][10].add("M");
		
		v[7][0]=new Stack();
		v[7][0].add("id");
		v[7][0].add("N");
		
		v[8][9]=new Stack();
		v[8][9].add("ε");
	
		
		v[8][10]=new Stack();
		v[8][10].add("D");
		
		
		v[9][9]=new Stack();
		v[9][9].add("public");
		v[9][9].add("type");
		v[9][9].add("id");
		v[9][9].add("(");
		v[9][9].add("type");
		v[9][9].add(")");
		v[9][9].add("{");
		v[9][9].add("L");
		v[9][9].add("}");
		//publictypeid(type){L}
		
		v[10][0]=new Stack();
		v[10][0].add("A");
		v[10][1]=new Stack();
		v[10][1].add("C");
		v[10][2]=new Stack();
		v[10][2].add("F");
		
		
	}
	public boolean analyasis(List<WordUnit> in)
	{

		boolean result=false;
		stack.clear();
		stack.add("#");
		stack.add("S");
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
		int len=regular.size();
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
