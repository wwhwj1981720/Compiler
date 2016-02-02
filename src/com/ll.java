package com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
//
/**
 * @author wangwh96
 *    实现自顶向下的语法分析
 *    文法为
 *    G[A]:
 *    A->aBc
 *    B->bB|d|eB    编译原理书的 78页例子
 *    
 *    a            b       c     d        e
 * A  A->aBc
 * B               B->bB         B->d     B->eB
 *
 */
public class ll {
	Set<Character> vn=new HashSet<Character>();
	Set<Character> vt=new HashSet<Character>();
	Map<String,String> relation=new HashMap<String,String>();
	Stack<Character>   stack=new Stack<Character>();
	Queue<Character> strque=new LinkedList<Character>(); 
	//String  in;
	/**
	 * 
	 * 初始化 ll(1) 分析表 
	 * */
	public void init()
	{
		vn.add('A');
		vn.add('B');
		vt.add('a');
		vt.add('b');
		vt.add('c');
		vt.add('d');
		vt.add('e');
		relation.put("Aa", "aBc");
		relation.put("Bb", "bB");
		relation.put("Bd", "d");
		relation.put("Be", "eB");
	}
	public boolean analyasis(String in)
	{
		boolean result=false;
		stack.add('#');
		stack.add('A');
		setInStr(in);
		char nowc=getOneStack();
		char ch=pollChar();
		while(!((nowc==ch)&&nowc=='#'))
		{
			
			if(vn.contains(nowc))
		
			{
			
				if(findTable(nowc,ch))
				{
					
					String joinc=joinString(nowc, ch);
					String regular=relation.get(joinc);
					if(regular!=null)
					{
						String reverse=reverse(regular);
						stack.pop();
						putStackReverse(reverse);
						System.out.println(nowc+"->"+regular);
						
					}
				}
				
			}
			else if((vt.contains(nowc)))
			{
				if((nowc==ch)&&(ch!='#'))
				{
					stack.pop();
					this.strque.poll();
				}
				else if(nowc!=ch)
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
	public void setInStr(String in)
	{
		//int i=in.length();
		char c[]=in.toCharArray();
		for(char ch:c)
		{
			strque.offer(ch);
		}
		
		
	}
	char getOneStack()
	{
		char c=stack.peek();
		return c;
	}
	char pollChar()
	{
		char ch=strque.peek();
		return ch;
	}
	public boolean findTable(char nowc,char ch)
	{
		boolean flag=false;
		String joinc=joinString(nowc, ch);
		if(this.relation.containsKey(joinc)) flag=true;
		return flag;
	}
	public String joinString(char nowc,char ch)
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
	public void putStackReverse(String reverse)
	{
		int len=reverse.length();
		for(int i=0;i<len;i++)
		{
			stack.push(reverse.charAt(i));
		}
	}
}
