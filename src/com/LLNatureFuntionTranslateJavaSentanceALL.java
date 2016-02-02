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
 * @author Administrator  LLNatureFuntionTranslateJavaSentanceALl 接近真实java
 *  
 *    Sjava程序语言的开始  java
 *   Z->DZ|PZ|ε  Z是语句块s
	 D 类定义 变量
 *   P 类函数
 *   V 是参数列表
 *   L 语句
*    A赋值语句
 *   C条件语句
 *   F当型循环语句ε
     I 是局部变量定义语句
	 B是表达式
     T是项
     H是因子
 *   E条件
     U是关系运算符
     
     S->publicclasstype{Z}
    Z->DZ|PZ  Z是语句块
 *  D->typeid;
	//P->publictypeid(V){L}  
	 P->Wtypeid(V){L}
	 W->public|private|protected|ε
	 V->type id|type id,V| ε
 *   L->A|C|F|AL|I|IL
 *   A->id=B;
     C->if(E)L
 *   F->while(E)L
 *   E->BUB
	U-> ==|>=|>|<
	I->typeid; 
	B->B+T|B-T|T
 *  T->T*H|T/H|H
 *  H->num|id|(B)
 *  I->typeid;
     
 *  select(S->publicclasstype{Z})=first(publicclasstype{Z}) public
 	select (Z->DZ)=first(DZ)=type 
	select (Z->PZ)=first(PZ)=public,private,protected
	select (Z->ε)=follow(Z) }
 *  select(D->typeid;)=first(typeid;) type
 *  
 *   //select(P->publictype(V){L})=firest(publictype(type){L}) public
 *   P->Wtype(V){L}
 *   select(P->Wtype(V){L})=firest(P->Wtype(V){L})= public,private,protected
	 W->public|private|protected|ε
	 select( W->public|private|protected|ε)=first(W->public|private|protected)=public,private,protected
	 select(W->ε)=follow(W->ε)=type
 *   V->type id|type id,V| ε
 *   V->typeO
 *   O->idN
 *   N->,V| ε  
 *   select(V->typeO)=first(typeO)=type
 *   select(O->idN)=first(O->idN)=id
 *   select(N->,V| ε)
 *   select(N->,v)=first(N->,V)=,
 *   select(N->ε)=follow(N-> ε)=)
 *   select(L->A|C|F|AL)=first(A) id
 *              first(C) if
 *              first(F) while
               
 *	
      A->id=B; 
      select (A->id=B;) first(id=B) id
      L->AL
      	分解为 L->AM M->L| ε
      L->A
      L->AM
      M->L| ε
      select(M->ε) follow(M->ε) }
      select(L->AM) first(AM)= id
     select(M->L) first(M->L) =id,while,if
     
	L->I|IL 分解为  L->LM M->L| ε
	L->IM
	select(L->IM) first(L->IM)=type
	M->L| ε
	select(M->ε) follow(M->ε) }
 *  select(c->if(E)L) first(if(E)L) if
 *  select(F->while(E)L) 	 first(while(E)L) while
 *  select(E->BU)      first(BU) num,id,(
 *  select(U->==B) first(==B) ==
 *  select(U->>B)  first(>B)  >
*  select(U-><idB)  first(<B)  <
B->TB'
select(B->TB')=first(TB')  num,id,(
B'->+TB'|-TB'|ε
select(B'->+TB') =first(+TB') +
select(B'->ε) follow(B'->ε) <,	==,	>,	},(,#;
T->HT'
select(T->HT')=first(T->HT') =num,id,(
T'->*HT'|/HT'|ε
select(T'->*HT')=first(*HT') =*
select(T'->/HT')=first(/HT') =/
select(T'->ε)=follow(T'->ε) <,	==,	>,	},+	,-	,#	,;,
H->num|id|(B)

 * 

 *   
 */
public class LLNatureFuntionTranslateJavaSentanceALL  {
	Queue<WordUnit> strque=new LinkedList<WordUnit>(); 
	CFile cf=new CFile("d:\\mid.txt");//结果输出文件
	Map<String,Integer> vn=new HashMap<String,Integer>();
	Map<String,Integer> vt=new HashMap<String,Integer>();
	Map<String,String> relation=new HashMap<String,String>();
	Stack<String>   stack=new Stack<String>();
	//Queue<String> strque=new LinkedList<String>(); 
	static int M=21;
	static int N=25;
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
		vn.put("I",4);
		vn.put("U",5);
		vn.put("D",6);
		vn.put("M",7);
		//vn.put("N",8);
		vn.put("P",9);
		vn.put("L",10);
		vn.put("Z", 11);
		
		//tianjia math
		
		vn.put("B",12);
		vn.put("B'",13);
		vn.put("T",14);
		vn.put("T'",15);
		vn.put("H",16);
		vn.put("E",17);
		//函数参数
		vn.put("V",18);
		vn.put("O",19);
		vn.put("W",20);
		vn.put("N",8);
		
		
		
		
		vt.put("id",0);
		vt.put("if",1);
		vt.put("while",2);
		vt.put("<",3);
		vt.put("==",4);
		vt.put(">",5);
		vt.put("=",6);
//		vt.put("(",7);
//		vt.put(")",8);
		vt.put("protected",7);
		vt.put("private",8);
		vt.put("public",9);
		vt.put("type",10);
		vt.put("class",11);
		vt.put("{",12);
		vt.put("}",13);
		//vt.put("#",14);
		
		//添加算术表达式
		vt.put("num",14);
		//vt.put("id",15);
		vt.put("(",16);
		vt.put(")",17);
		vt.put("*",18);
		vt.put("/",19);
		vt.put("+",20);
		vt.put("-",21);
		vt.put("#",22);
		vt.put(";",23);
		vt.put(",",24);
		//

//		
		
	}
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
		v[0][9].add("Z");
		v[0][9].add("}");
		
		v[1][0]=new Stack();
		v[1][0].add("id");
		v[1][0].add("=");
		v[1][0].add("B");
		v[1][0].add(";");
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
		v[3][2].add("{");
		v[3][2].add("L");
		v[3][2].add("}");
		
		v[4][10]=new Stack();
		v[4][10].add("type");
		v[4][10].add("id");
		v[4][10].add(";");
		
		//U-><B
		v[5][3]=new Stack();
		v[5][3].add("<");
		v[5][3].add("B");
		//U->==B
		
		//v[5][3].add("id");
		v[5][4]=new Stack();
		v[5][4].add("==");
		v[5][4].add("B");
		//v[5][4].add("id");
		//U->>B
		v[5][5]=new Stack();
		v[5][5].add(">");
		v[5][5].add("B");
		//v[5][5].add("id");
		
		//D->typeid;
		
		v[6][10]=new Stack();
		v[6][10].add("type");
		v[6][10].add("id");
		v[6][10].add(";");
		
		//M->L
		v[7][0]=new Stack();
		v[7][0].add("L");
		//M->L
		v[7][1]=new Stack();
		v[7][1].add("L");
		//M->L
		v[7][2]=new Stack();
		v[7][2].add("L");
		
		//M->L
		v[7][10]=new Stack();
		v[7][10].add("L");
		//M->ε
		v[7][13]=new Stack();
		v[7][13].add("ε");
		

		
		
		//P->Wtypeid(V){L}
		v[9][7]=new Stack();
		v[9][7].add("W");
		v[9][7].add("type");
		v[9][7].add("id");
		v[9][7].add("(");
		v[9][7].add("V");
		v[9][7].add(")");
		v[9][7].add("{");
		v[9][7].add("L");
		v[9][7].add("}");
		
		v[9][8]=new Stack();
		v[9][8].add("W");
		v[9][8].add("type");
		v[9][8].add("id");
		v[9][8].add("(");
		v[9][8].add("V");
		v[9][8].add(")");
		v[9][8].add("{");
		v[9][8].add("L");
		v[9][8].add("}");
		
		v[9][9]=new Stack();
		v[9][9].add("W");
		v[9][9].add("type");
		v[9][9].add("id");
		v[9][9].add("(");
		v[9][9].add("V");
		v[9][9].add(")");
		v[9][9].add("{");
		v[9][9].add("L");
		v[9][9].add("}");
		//publictypeid(type){L}
		
		v[10][0]=new Stack();
		v[10][0].add("A");
		v[10][0].add("M");
		
		v[10][1]=new Stack();
		v[10][1].add("C");
		v[10][2]=new Stack();
		v[10][2].add("F");
		
		//L->IM
		v[10][10]=new Stack();
		v[10][10].add("I");
		v[10][10].add("M");
		
		//Z->PZ
		v[11][7]=new Stack();
		v[11][7].add("P");
		v[11][7].add("Z");
		v[11][8]=new Stack();
		v[11][8].add("P");
		v[11][8].add("Z");
		
		v[11][9]=new Stack();
		v[11][9].add("P");
		v[11][9].add("Z");
		
		v[11][10]=new Stack();
		v[11][10].add("D");
		v[11][10].add("Z");
		v[11][13]=new Stack();
		v[11][13].add("ε");
		
		
		/*添加算术表达式与关系
		 * 
		 * **/
		
		
		
		v[12][14]=new Stack();
		v[12][14].add("T");
		v[12][14].add("B'");
		v[12][0]=new Stack();
		v[12][0].add("T");
		v[12][0].add("B'");
		
		v[12][16]=new Stack();
		v[12][16].add("T");
		v[12][16].add("B'");
		
		v[13][3]=new Stack();
		v[13][3].add("ε");
		v[13][4]=new Stack();
		v[13][4].add("ε");
		
		v[13][5]=new Stack();
		v[13][5].add("ε");
		
		
		
		v[13][13]=new Stack();
		v[13][13].add("ε");
		
		
		v[13][17]=new Stack();
		v[13][17].add("ε");
		
		
		v[13][20]=new Stack();
		v[13][20].add("+");
		v[13][20].add("T");
		v[13][20].add("B'");
		
		v[13][21]=new Stack();
		v[13][21].add("-");
		v[13][21].add("T");
		v[13][21].add("B'");
		
		
		
		v[13][22]=new Stack();
		v[13][22].add("ε");
		
		v[13][23]=new Stack();
		v[13][23].add("ε");
		
		v[14][14]=new Stack();
		v[14][14].add("H");
		v[14][14].add("T'");
		
		v[14][0]=new Stack();
		v[14][0].add("H");
		v[14][0].add("T'");
		
		v[14][16]=new Stack();
		v[14][16].add("H");
		v[14][16].add("T'");
		
		
		v[15][3]=new Stack();
		v[15][3].add("ε");
		v[15][4]=new Stack();
		v[15][4].add("ε");
		
		v[15][5]=new Stack();
		v[15][5].add("ε");
		
		v[15][13]=new Stack();
		v[15][13].add("ε");
		
		
		v[15][17]=new Stack();
		v[15][17].add("ε");
		
		//v[1][0].add("S");
		v[15][18]=new Stack();
		v[15][18].add("*");
		v[15][18].add("H");
		v[15][18].add("T'");
		
		
		
		v[15][19]=new Stack();
		v[15][19].add("/");
		v[15][19].add("H");
		v[15][19].add("T'");
		
		v[15][20]=new Stack();
		v[15][20].add("ε");
		
		v[15][21]=new Stack();
		v[15][21].add("ε");
		
		
		v[15][22]=new Stack();
		v[15][22].add("ε");
		v[15][23]=new Stack();
		v[15][23].add("ε");
	
		v[16][14]=new Stack();
		v[16][14].add("num");
		
		v[16][0]=new Stack();
		v[16][0].add("id");
		
		
		v[16][16]=new Stack();
		v[16][16].add("(");	
		v[16][16].add("B");
		v[16][16].add(")");
		
		//E->BUB
		v[17][14]=new Stack();
		v[17][14].add("B");
		v[17][14].add("U");
		//v[17][0].add("B");
		
		v[17][0]=new Stack();
		v[17][0].add("B");
		v[17][0].add("U");
		//v[17][0].add("B");
		
		
		v[17][16]=new Stack();
		v[17][16].add("B");	
		v[17][16].add("U");
		//v[17][16].add("B");
		
		//函数参数
		v[18][10]=new Stack();
		v[18][10].add("type");	
		v[18][10].add("O");
		v[18][17]=new Stack();
		v[18][17].add("ε");	
		
		
		v[19][0]=new Stack();
		v[19][0].add("id");	
		v[19][0].add("N");
		
		//N->ε
		v[8][17]=new Stack();
		v[8][17].add("ε");
	
		//N->,V
		v[8][24]=new Stack();
		v[8][24].add(",");
		v[8][24].add("V");
		
		v[20][7]=new Stack();
		v[20][7].add("protected");
		v[20][8]=new Stack();
		v[20][8].add("private");
		v[20][9]=new Stack();
		v[20][9].add("public");
		
		v[20][10]=new Stack();
		v[20][10].add("ε");
		
//		v[8][13]=new Stack();
//		v[8][13].add("ε");
		
		
		
		
		
		
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
					System.out.println("ll 分析表中没有 匹配的"+nowc+" "+ch.getValue());
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
					System.out.println("分析栈中的 字符与当前串中的字符不匹配"+nowc+" "+ch.getValue());
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
