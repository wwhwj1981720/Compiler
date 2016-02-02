package translate;

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

import translate.tree.MutiNode;
import translate.tree.MutiTree;
import unicom.CFile;
import unicom.WordUnit;
/**
 * @author Administrator
 *    本项目研究  java 算术表达式的语义分析
 *  stack 是词法分析栈
 *  strque 是输入串存贮的栈
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
	
									
B->TB'
B'->+TB'|-TB'|ε
T->HT'
T'->*HT'|/HT'|ε
H->num|id|(B)

 *   
 *   
 *   
 */
public class NatureFuntionTranslateMath_Regular  {
	//Queue<WordUnit> strque=new LinkList<WordUnit>(); 
	Deque<WordUnit> strque=new ArrayDeque<WordUnit>();//存储词法分析的结果
	CFile cf=new CFile("d:\\mid.txt");//结果输出文件
	Map<String,Integer> vn=new HashMap<String,Integer>();
	Map<String,Integer> vt=new HashMap<String,Integer>();
	Map<String,String> relation=new HashMap<String,String>();
	Stack<String>   stack=new Stack<String>();//语法分析栈
	
	Deque<String> treeque=new ArrayDeque<String>();//建立语法树需要的队列
	
	MutiTree tree=new MutiTree();//语法分析树
	MutiNode stacktop=null;
	//Queue<String> strque=new LinkedList<String>(); 
	static int M=5;
	static int N=9;
	Regular v[][]=new Regular[M][N];//ll(1)分析标表的 规则存储
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
		v[0][0]=new Regular();
		v[0][0].stack.add("T");
		v[0][0].stack.add("B'");
		
		v[0][0].transatev.add("T");
		v[0][0].transatev.add("B'");
		
		v[0][1]=new Regular();
		v[0][1].stack.add("T");
		v[0][1].stack.add("B'");
		
		v[0][1].transatev.add("T");
		v[0][1].transatev.add("B'");
		
		
		v[0][2]=new Regular();
		v[0][2].stack.add("T");
		v[0][2].stack.add("B'");
		
		
		v[0][2].transatev.add("T");
		v[0][2].transatev.add("B'");
		
		v[1][3]=new Regular();
		v[1][3].stack.add("ε");
		v[1][3].transatev.add("ε");
		
		
		v[1][6]=new Regular();
		v[1][6].stack.add("+");
		v[1][6].stack.add("T");
		v[1][6].stack.add("B'");
		
		v[1][6].transatev.add("+");
		v[1][6].transatev.add("T");
		v[1][6].transatev.add("@+");
		v[1][6].transatev.add("B'");
		
		
		v[1][7]=new Regular();
		v[1][7].stack.add("-");
		v[1][7].stack.add("T");
		v[1][7].stack.add("B'");
		
		
		v[1][7].transatev.add("-");
		v[1][7].transatev.add("T");
		v[1][7].transatev.add("@-");
		v[1][7].transatev.add("B'");
		
		
		
		v[1][8]=new Regular();
		v[1][8].stack.add("ε");
		v[1][8].transatev.add("ε");
		
		v[2][0]=new Regular();
		v[2][0].stack.add("H");
		v[2][0].stack.add("T'");
		
		v[2][0].transatev.add("H");
		v[2][0].transatev.add("T'");
		
		
		
		v[2][1]=new Regular();
		v[2][1].stack.add("H");
		v[2][1].stack.add("T'");
		
		
		v[2][1].transatev.add("H");
		v[2][1].transatev.add("T'");
		
		v[2][2]=new Regular();
		v[2][2].stack.add("H");
		v[2][2].stack.add("T'");
		
		
		
		v[2][2].transatev.add("H");
		v[2][2].transatev.add("T'");
		
		v[3][3]=new Regular();
		v[3][3].stack.add("ε");
		v[3][3].transatev.add("ε");
		
		//v[1][0].add("S");
		v[3][4]=new Regular();
		v[3][4].stack.add("*");
		v[3][4].stack.add("H");
		v[3][4].stack.add("T'");
		
		v[3][4].transatev.add("*");
		v[3][4].transatev.add("H");
		v[3][4].transatev.add("@*");
		v[3][4].transatev.add("T'");
		
		
		
		v[3][5]=new Regular();
		v[3][5].stack.add("/");
		v[3][5].stack.add("H");
		v[3][5].stack.add("T'");
		
		v[3][5].transatev.add("/");
		v[3][5].transatev.add("H");
		v[3][5].transatev.add("@/");
		v[3][5].transatev.add("T'");
		
		
		v[3][6]=new Regular();
		v[3][6].stack.add("ε");
		v[3][6].transatev.add("ε");
		
		v[3][7]=new Regular();
		v[3][7].stack.add("ε");
		v[3][7].transatev.add("ε");
		
		
		v[3][8]=new Regular();
		v[3][8].stack.add("ε");
		v[3][8].transatev.add("ε");
	
		v[4][0]=new Regular();
		v[4][0].stack.add("num");
		v[4][0].transatev.add("num");
		v[4][0].transatev.add("@num");
		
		v[4][1]=new Regular();
		v[4][1].stack.add("id");
		v[4][1].transatev.add("id");
		v[4][1].transatev.add("@id");
		
		
		v[4][2]=new Regular();
		v[4][2].stack.add("(");	
		v[4][2].stack.add("B");
		v[4][2].stack.add(")");
		
		v[4][2].transatev.add("(");	
		v[4][2].transatev.add("B");
		v[4][2].transatev.add(")");
		
		
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
		
		tree.init();
		
		stack.add("B");
		tree.CreateTree("B");
		//tree与stack中的元素对应
		setInStr(in);
		String nowc=getOneStack();//词法分析栈中的词
		WordUnit ch=pollWordUnit();//待匹配串中的词
		while(!(nowc.equals(ch.getNature())&&nowc.equals("#")))
		{
			
			if(vn.containsKey(nowc))//nowc 词法分析栈中当前串 
			{
			
				if(findTable(nowc,ch))
				{
					Regular regular=v[i][j];
					if(regular!=null)
					{
						stacktop=tree.stack.peek();//当前的栈顶元素
						stack.pop();
						MutiTree.stack.pop();
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
					
					//stacktop=tree.stack.peek();//当前的栈顶元素
					stack.pop();
					MutiNode popnode=MutiTree.stack.pop();
					//从栈中弹出  终端字符
					//this.strque.poll();//将匹配字符串中的结果也弹出
					try {
						popnode.data=this.strque.peek().getValue();
						
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
	/**
	 * 
	 * 取栈当前的栈顶元素
	 * **/
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
	/**
	 * 根据栈当前元素和队列中的头元素查找规则表
	 * **/
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
	/*
	 * Stack<String> regular 可以使用的规则
	 * String nowc 词法分析栈被弹出的能够找到规则的词
	 * now 在语法分析中是 父节点，Stack<String> regular是子节点
	 * 
	 * **/
	public void putStackReverse(Stack<String> regular,String nowc)
	{
		int len=regular.size();//规则的长度
		StringBuffer regularstr = new StringBuffer();
		StringBuffer regulararr[] = new StringBuffer[len];
		String str=null;
		int pos=0;
		
		for(int i=0;i<len;i++)
		{
			pos=len-1-i;
			str=regular.get(pos);
			if(!str.equals("ε"))  //注意 ε不用往stack中添加
			{
				stack.push(str);
				
			}
			//treeque.push(str);
			regulararr[pos]=new StringBuffer();
			regulararr[pos].append(str);
			regularstr.insert(0, str);
		}
		stacktop.insertNode(regulararr);
		//regularstr.reverse();
		System.out.println(nowc+"->"+regularstr.toString());
		grammarTranslate();
	}
	public void putStackReverse(Regular regular,String nowc)
	{
		int len=regular.stack.size();//规则的长度
		StringBuffer regularstr = new StringBuffer();
		//StringBuffer regulararr[] = new StringBuffer[len];
		String str=null;
		int pos=0;
		
		for(int i=0;i<len;i++)
		{
			pos=len-1-i;
			str=regular.stack.get(pos);
			if(!str.equals("ε"))  //注意 ε不用往stack中添加
			{
				stack.push(str);
				
			}
			//treeque.push(str);
			//regulararr[pos]=new StringBuffer();
			//regulararr[pos].append(str);
			regularstr.insert(0, str);
		}
		stacktop.insertNode(regular.transatev);
		//regularstr.reverse();
		System.out.println(nowc+"->"+regularstr.toString());
		grammarTranslate();
	}
	
	public void grammarTranslate()
	{
		
	}
}
