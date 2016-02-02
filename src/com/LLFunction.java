package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

/**
 * LLListFunction List<String> inputlist=new ArrayList<String>()；从List中读取
 * @author Administrator
 *   S->A|C|F
 *   A->id=id
 *   C->if(E)S
 *   F->while(E)S
 *   E->id<id|id==id|id>id
 *   
 *   S语句
 *   A赋值
 *   C条件
 *   F循环
 *   E表达
 *   select(S->A|C|F)=first(A) id
 *              first(C) if
 *              first(F) while
 *	select(A->id=id) first(id=id) id
 *  select(c->if(E)s) first(if(E)S) if
 *  select(F->while(E)S) 	 first(while(E)S) while
 *  select(E->idU)      first(id) id
 *  select(U->==id) first(==id) ==
 *  select(U->>id)  first(>id)  >
 *  
 *  stack 是词法分析栈
 *  strque 是输入串存贮的栈
 */
public class LLFunction {
	Map<String,Integer> vn=new HashMap<String,Integer>();
	Map<String,Integer> vt=new HashMap<String,Integer>();
	Map<String,String> relation=new HashMap<String,String>();
	Stack<String>   stack=new Stack<String>();
	Queue<String> strque=new LinkedList<String>(); 
	Stack v[][]=new Stack[6][9];
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
		
		
		
		
		vt.put("id",0);
		vt.put("if",1);
		vt.put("while",2);
		vt.put("<",3);
		vt.put("==",4);
		vt.put(">",5);
		vt.put("=",6);
		vt.put("(",7);
		vt.put(")",8);
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
	public void initVector()
	{
		
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<9;j++)
			{
				v[i][j]=null;
			}
		}
		v[0][0]=new Stack();
		v[0][0].add("A");
		v[0][1]=new Stack();
		v[0][1].add("C");
		v[0][2]=new Stack();
		v[0][2].add("F");
		v[1][0]=new Stack();
		v[1][0].add("id");
		v[1][0].add("=");
		v[1][0].add("id");
		v[2][1]=new Stack();
		v[2][1].add("if");
		v[2][1].add("(");
		v[2][1].add("E");
		v[2][1].add(")");
		v[2][1].add("S");
		v[3][2]=new Stack();
		v[3][2].add("while");
		v[3][2].add("(");
		v[3][2].add("E");
		v[3][2].add(")");
		v[3][2].add("S");
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
		
		
		
		
		
		
	}
	/**
	 * 
	 * 将要分析的字符串以List的方式传人分析
	 * 
	 * ***/
	public boolean analyasis(List<String> in)
	{
		boolean result=false;
		stack.add("#");
		stack.add("S");
		setInStr(in);
		String nowc=getOneStack();
		String ch=pollChar();
		while(!(nowc.equals(ch)&&nowc.equals("#")))
		{
			
			if(vn.containsKey(nowc))//nowc 词法分析栈中当前串
			{
			
				if(findTable(nowc,ch))
				{
					
				
					Stack regular=v[i][j];
					if(regular!=null)
					{
						//String reverse=reverse(regular);
						stack.pop();
						putStackReverse(regular,nowc);
						
						
					}
				}
				
			}
			else if((vt.containsKey(nowc)))
			{
				if((nowc.equals(ch))&&(!ch.equals("#")))
				{
					stack.pop();
					this.strque.poll();
				}
				else if(!nowc.equals(ch))
				{
					System.out.println("please enter err");
				}
			}
			
			nowc=getOneStack();
			ch=pollChar();
		}
		System.out.println("success");
		return result;
	}
	public  void setInStr(List<String> in)
	{
		//int i=in.length();
		//char c[]=in.toCharArray();
		for(String ch:in)
		{
			strque.offer(ch);
		}
		
		
	}
	String getOneStack()
	{
		String c=stack.peek();
		return c;
	}
	String pollChar()
	{
		String ch=strque.peek();
		return ch;
	}
	public boolean findTable(String nowc,String ch)
	{
		boolean flag=false;
		//String joinc=joinString(nowc, ch);
		try{
			i=vn.get(nowc);
			j=vt.get(ch);
			if(v[i][j]!=null) flag=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public String joinString(String nowc,String ch)
	{
		String joinc=""+String.valueOf(nowc);
		joinc+=String.valueOf(ch);
		return joinc;
	}
	public String reverse(String str)
	{
		 StringBuffer sb = new StringBuffer(str);
		 sb.reverse();
		 String reversestr=sb.toString();
		 return reversestr;
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
			stack.push(str);
			regularstr.insert(0, str);
		}
		//regularstr.reverse();
		System.out.println(nowc+"->"+regularstr.toString());
	}
}
