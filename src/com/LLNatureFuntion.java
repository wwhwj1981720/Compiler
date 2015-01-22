package com;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import unicom.WordUnit;
/**
 *   结合属性字进行 语法分析 
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
 *  strque 待匹配的语句的存放位置
 */
/**
 * @author Administrator
 *
 */
public class LLNatureFuntion extends LLWordListFunction {
	Queue<WordUnit> strque=new LinkedList<WordUnit>(); 
	/****
	 * 
	 *  词法分析的 函数
	 * 
	 * 
	 * ********/
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
					System.out.println(this.strque.poll().getValue());
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
	//从带匹配的队列中取当前词
	public WordUnit pollWordUnit()
	{
		WordUnit ch=strque.peek();
		return ch;
	}
	/*
	 * 查询 ll(k)分析表  看 是否有规则
	 * 
	 * */
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
	/****
	 * 
	 *     生成的规则查看
	 * 
	 * ****/
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
		grammarTranslate();
	}
	/**
	 * 语法制导翻译部分
	 * 
	 * 
	 * */
	public void grammarTranslate()
	{
		
	}
}
