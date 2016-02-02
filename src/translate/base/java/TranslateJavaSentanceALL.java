package translate.base.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import translate.Regular;
import translate.tree.MutiNode;
import translate.tree.MutiTree;
import unicom.CFile;
import unicom.WordUnit;
/**
 * @author Administrator  LLNatureFuntionTranslateJavaSentanceALl 接近真实java
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
	V->type|type,V
 *   L->A|C|F|AL
 *   A->id=B;
     C->if(E)L
 *   F->while(E)L
 *   E->BU
	U-> ==B|>=B|>B|<B
	 
	B->B+T|B-T|T
 *  T->T*H|T/H|H
 *  H->num|id|(B)
 *  I->typeid|typeidL
     
 *   select(S->publicclasstype{Z})=first(publicclasstype{Z}) public
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
 *   first(N->,V)=,
 *   follow(N-> ε)=)
 *   select(L->A|C|F|AL)=first(A) id
 *              first(C) if
 *              first(F) while
               
 *	
      A->id=B; select (A->id=B;) first(id=B) id
      L->AL
      L->A
      L->AM
      M->L| ε
      follow(M->ε) }
      select(L->AM) first(AK)= id
     select(M->L) first(M->L) =id,while,if

 *  select(c->if(E)L) first(if(E)L) if
 *  select(F->while(E)L) 	 first(while(E)L) while
 *  select(E->BU)      first(BU) num,id,(
 *  select(U->==B) first(==b) ==
 *  select(U->>B)  first(>B)  >
*  select(U-><B)  first(<B)  <
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
public class TranslateJavaSentanceALL  {
	Queue<WordUnit> strque=new LinkedList<WordUnit>(); 
	CFile cf=new CFile("d:\\mid.txt");//结果输出文件
	Map<String,Integer> vn=new HashMap<String,Integer>();
	Map<String,Integer> vt=new HashMap<String,Integer>();
	Map<String,String> relation=new HashMap<String,String>();
	Stack<String>   stack=new Stack<String>();
	//Queue<String> strque=new LinkedList<String>(); 
	
	MutiTree tree=new MutiTree();//语法分析树
	MutiNode stacktop=null;
	
	static int M=21;
	static int N=25;
	//Stack v[][]=new Stack[M][N];//ll(1)分析标表的 规则存储
	Regular v[][]=new Regular[M][N];//ll(1)分析标表的 规则存储
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
		//S->publicclasstype{Z}
		//v[0][9]=new Stack();
		v[0][9]=new Regular();
		v[0][9].stack.add("public");
		v[0][9].stack.add("class");
		v[0][9].stack.add("type");
		v[0][9].stack.add("{");
		v[0][9].stack.add("Z");
		v[0][9].stack.add("}");
		
		v[0][9].transatev.add("public");
		v[0][9].transatev.add("class");
		v[0][9].transatev.add("type");
		v[0][9].transatev.add("{");
		v[0][9].transatev.add("Z");
		v[0][9].transatev.add("}");
		
		//A->id=B;
		v[1][0]=new Regular();
		
		
		v[1][0].stack.add("id");
		v[1][0].stack.add("=");
		v[1][0].stack.add("B");
		v[1][0].stack.add(";");
		
		v[1][0].transatev.add("id");
		v[1][0].transatev.add("@id");
		v[1][0].transatev.add("=");
		v[1][0].transatev.add("B");
		v[1][0].transatev.add("@=");
		v[1][0].transatev.add(";");
		
		//C->if(E)L
		v[2][1]=new Regular();
		v[2][1].stack.add("if");
		v[2][1].stack.add("(");
		v[2][1].stack.add("E");
		v[2][1].stack.add(")");
		v[2][1].stack.add("L");
		
		v[2][1].transatev.add("if");
		v[2][1].transatev.add("(");
		v[2][1].transatev.add("E");
		v[2][1].transatev.add(")");
		v[2][1].transatev.add("L");
		
		//L->while(E){L}
		v[3][2]=new Regular();
		v[3][2].stack.add("while");
		v[3][2].stack.add("(");
		v[3][2].stack.add("E");
		v[3][2].stack.add(")");
		//v[3][2].stack.add("{");
		v[3][2].stack.add("L");
		//v[3][2].stack.add("}");
		
		v[3][2].transatev.add("while");
		v[3][2].transatev.add("(");
		v[3][2].transatev.add("E");
		v[3][2].transatev.add(")");
		//v[3][2].transatev.add("{");
		v[3][2].transatev.add("L");
		//v[3][2].transatev.add("}");
		
		//I->typeid;
		v[4][10]=new Regular();
		v[4][10].stack.add("type");
		v[4][10].stack.add("id");
		v[4][10].stack.add(";");
		
		v[4][10].transatev.add("type");
		v[4][10].transatev.add("id");
		v[4][10].transatev.add(";");
		//U-><
		v[5][3]=new Regular();
		v[5][3].stack.add("<");
		v[5][3].transatev.add("<");
		
		//U->==
		v[5][4]=new Regular();
		v[5][4].stack.add("==");
		v[5][4].transatev.add("==");
		
		//U->>
		v[5][5]=new Regular();
		v[5][5].stack.add(">");
		v[5][5].transatev.add(">");
		
		//D->typeid;
		v[6][10]=new Regular();
		v[6][10].stack.add("type");
		v[6][10].stack.add("id");
		v[6][10].stack.add(";");
		
		v[6][10].transatev.add("type");
		v[6][10].transatev.add("id");
		v[6][10].transatev.add(";");
		
		//M->L
		v[7][0]=new Regular();
		v[7][0].stack.add("L");
		v[7][0].transatev.add("L");
		
		v[7][1]=new Regular();
		v[7][1].stack.add("L");
		v[7][1].transatev.add("L");
		v[7][2]=new Regular();
		v[7][2].stack.add("L");
		v[7][2].transatev.add("L");
		
		v[7][10]=new Regular();
		v[7][10].stack.add("L");
		v[7][10].transatev.add("L");
		
		v[7][13]=new Regular();
		v[7][13].stack.add("ε");
		v[7][13].transatev.add("ε");
		

		
		
		//P->Wtypeid(V){L}
		v[9][7]=new Regular();
		v[9][7].stack.add("W");
		v[9][7].stack.add("type");
		v[9][7].stack.add("id");
		v[9][7].stack.add("(");
		v[9][7].stack.add("V");
		v[9][7].stack.add(")");
		v[9][7].stack.add("{");
		v[9][7].stack.add("L");
		v[9][7].stack.add("}");
		
		v[9][7].transatev.add("W");
		v[9][7].transatev.add("type");
		v[9][7].transatev.add("id");
		v[9][7].transatev.add("(");
		v[9][7].transatev.add("V");
		v[9][7].transatev.add(")");
		v[9][7].transatev.add("{");
		v[9][7].transatev.add("L");
		v[9][7].transatev.add("}");
		
		//P->Wtypeid(V){L}
		v[9][8]=new Regular();
		v[9][8].stack.add("W");
		v[9][8].stack.add("type");
		v[9][8].stack.add("id");
		v[9][8].stack.add("(");
		v[9][8].stack.add("V");
		v[9][8].stack.add(")");
		v[9][8].stack.add("{");
		v[9][8].stack.add("L");
		v[9][8].stack.add("}");
		
		v[9][8].transatev.add("W");
		v[9][8].transatev.add("type");
		v[9][8].transatev.add("id");
		v[9][8].transatev.add("(");
		v[9][8].transatev.add("V");
		v[9][8].transatev.add(")");
		v[9][8].transatev.add("{");
		v[9][8].transatev.add("L");
		v[9][8].transatev.add("}");
		
		//P->Wtypeid(V){L}
		v[9][9]=new Regular();
		v[9][9].stack.add("W");
		v[9][9].stack.add("type");
		v[9][9].stack.add("id");
		v[9][9].stack.add("(");
		v[9][9].stack.add("V");
		v[9][9].stack.add(")");
		v[9][9].stack.add("{");
		v[9][9].stack.add("L");
		v[9][9].stack.add("}");
		
		//v[9][9]=new Regular();
		v[9][9].transatev.add("W");
		v[9][9].transatev.add("type");
		v[9][9].transatev.add("id");
		v[9][9].transatev.add("(");
		v[9][9].transatev.add("V");
		v[9][9].transatev.add(")");
		v[9][9].transatev.add("{");
		v[9][9].transatev.add("L");
		v[9][9].transatev.add("}");
		//publictypeid(type){L}
		
		//L->AM
		v[10][0]=new Regular();
		v[10][0].stack.add("A");
		v[10][0].stack.add("M");
		
		v[10][0].transatev.add("A");
		v[10][0].transatev.add("M");
		
		//L->C
		v[10][1]=new Regular();
		v[10][1].stack.add("C");
		v[10][1].transatev.add("C");
		
		//L->F
		v[10][2]=new Regular();
		v[10][2].stack.add("F");
		v[10][2].transatev.add("F");
		
		//L->IM
		v[10][10]=new Regular();
		
		
		v[10][10].stack.add("I");
		v[10][10].stack.add("M");
		
		v[10][10].transatev.add("I");
		v[10][10].transatev.add("M");
		
		//Z->PZ
		v[11][7]=new Regular();
		v[11][7].stack.add("P");
		v[11][7].stack.add("Z");
		
		v[11][7].transatev.add("P");
		v[11][7].transatev.add("Z");
		
		v[11][8]=new Regular();
		v[11][8].stack.add("P");
		v[11][8].stack.add("Z");
		
		v[11][8].transatev.add("P");
		v[11][8].transatev.add("Z");
		
		v[11][9]=new Regular();
		v[11][9].stack.add("P");
		v[11][9].stack.add("Z");
		
		v[11][9].transatev.add("P");
		v[11][9].transatev.add("Z");
		
		//Z-DZ
		v[11][10]=new Regular();
		v[11][10].stack.add("D");
		v[11][10].stack.add("Z");
		
		v[11][10].transatev.add("D");
		v[11][10].transatev.add("Z");
		
		//Z->ε
		v[11][13]=new Regular();
		v[11][13].stack.add("ε");
		v[11][13].transatev.add("ε");
		
		
		/*添加算术表达式与关系
		 * 
		 * **/
		
		
		//B->TB'
		v[12][14]=new Regular();
		v[12][14].stack.add("T");
		v[12][14].stack.add("B'");
		
		v[12][14].transatev.add("T");
		v[12][14].transatev.add("B'");
		
		v[12][0]=new Regular();
		v[12][0].stack.add("T");
		v[12][0].stack.add("B'");
		
		v[12][0].transatev.add("T");
		v[12][0].transatev.add("B'");
		
		v[12][16]=new Regular();
		v[12][16].stack.add("T");
		v[12][16].stack.add("B'");
		
		
		v[12][16].transatev.add("T");
		v[12][16].transatev.add("B'");
		
		v[13][3]=new Regular();
		v[13][3].stack.add("ε");
		
		v[13][3].transatev.add("ε");
		
		v[13][4]=new Regular();
		v[13][4].stack.add("ε");
		v[13][4].transatev.add("ε");
		
		v[13][5]=new Regular();
		v[13][5].stack.add("ε");
		v[13][5].transatev.add("ε");
		
		
		
		v[13][13]=new Regular();
		v[13][13].stack.add("ε");
		
		v[13][13].transatev.add("ε");
		
		
		v[13][17]=new Regular();
		v[13][17].stack.add("ε");
		v[13][17].transatev.add("ε");
		
		
		v[13][20]=new Regular();
		v[13][20].stack.add("+");
		v[13][20].stack.add("T");
		v[13][20].stack.add("B'");
		
		v[13][20].transatev.add("+");
		v[13][20].transatev.add("T");
		v[13][20].transatev.add("@+");
		v[13][20].transatev.add("B'");
		
		v[13][21]=new Regular();
		v[13][21].stack.add("-");
		v[13][21].stack.add("T");
		v[13][21].stack.add("B'");
		
		v[13][21].transatev.add("-");
		v[13][21].transatev.add("T");
		v[13][21].transatev.add("@-");
		v[13][21].transatev.add("B'");
		
		
		
		v[13][22]=new Regular();
		v[13][22].stack.add("ε");
		
		v[13][22].transatev.add("ε");
		
		v[13][23]=new Regular();
		v[13][23].stack.add("ε");
		
		v[13][23].transatev.add("ε");
		
		v[14][14]=new Regular();
		v[14][14].stack.add("H");
		v[14][14].stack.add("T'");
		
		v[14][14].transatev.add("H");
		v[14][14].transatev.add("T'");
		
		v[14][0]=new Regular();
		v[14][0].stack.add("H");
		v[14][0].stack.add("T'");
		
		v[14][0].transatev.add("H");
		v[14][0].transatev.add("T'");
		
		v[14][16]=new Regular();
		v[14][16].stack.add("H");
		v[14][16].stack.add("T'");
		
		v[14][16].transatev.add("H");
		v[14][16].transatev.add("T'");
		
		
		v[15][3]=new Regular();
		
		v[15][3].stack.add("ε");
		
		v[15][3].transatev.add("ε");
		
		v[15][4]=new Regular();
		v[15][4].stack.add("ε");
		v[15][4].transatev.add("ε");
		
		v[15][5]=new Regular();
		v[15][5].stack.add("ε");
		v[15][5].transatev.add("ε");
		
		v[15][13]=new Regular();
		v[15][13].stack.add("ε");
		v[15][13].transatev.add("ε");
		
		
		v[15][17]=new Regular();
		v[15][17].stack.add("ε");
		v[15][17].transatev.add("ε");
		
		//v[1][0].add("S");
		v[15][18]=new Regular();
		v[15][18].stack.add("*");
		v[15][18].stack.add("H");
		v[15][18].stack.add("T'");
		
		v[15][18].transatev.add("*");
		v[15][18].transatev.add("H");
		v[15][18].transatev.add("@*");
		v[15][18].transatev.add("T'");
		
		
		
		v[15][19]=new Regular();
		v[15][19].stack.add("/");
		v[15][19].stack.add("H");
		v[15][19].stack.add("T'");
		
		v[15][19].transatev.add("/");
		v[15][19].transatev.add("H");
		v[15][19].transatev.add("@/");
		v[15][19].transatev.add("T'");
		
		v[15][20]=new Regular();
		v[15][20].stack.add("ε");
		
		v[15][20].transatev.add("ε");
		
		v[15][21]=new Regular();
		v[15][21].stack.add("ε");
		v[15][21].transatev.add("ε");
		
		
		v[15][22]=new Regular();
		v[15][22].stack.add("ε");
		v[15][22].transatev.add("ε");
		v[15][23]=new Regular();
		v[15][23].stack.add("ε");
		v[15][23].transatev.add("ε");
		//H->num
		v[16][14]=new Regular();
		v[16][14].stack.add("num");
		v[16][14].transatev.add("num");
		v[16][14].transatev.add("@num");
		//H->id
		v[16][0]=new Regular();
		v[16][0].stack.add("id");
		v[16][0].transatev.add("id");
		v[16][0].transatev.add("@id");
		
		
		v[16][16]=new Regular();
		v[16][16].stack.add("(");	
		v[16][16].stack.add("B");
		v[16][16].stack.add(")");
		
		v[16][16].transatev.add("(");	
		v[16][16].transatev.add("B");
		v[16][16].transatev.add(")");
		
		//E->BUB
		v[17][14]=new Regular();
		v[17][14].stack.add("num");
		v[17][14].transatev.add("num");
		
		v[17][0]=new Regular();
		v[17][0].stack.add("B");
		v[17][0].stack.add("U");
		v[17][0].stack.add("B");
		
		v[17][0].transatev.add("B");
		v[17][0].transatev.add("U");
		v[17][0].transatev.add("B");
		
		
		v[17][16]=new Regular();
		v[17][16].stack.add("(");	
		v[17][16].stack.add("B");
		v[17][16].stack.add(")");
		
		
		v[17][16].transatev.add("(");	
		v[17][16].transatev.add("B");
		v[17][16].transatev.add(")");
		
		//函数参数
		//V->typeO
		v[18][10]=new Regular();
		v[18][10].stack.add("type");	
		v[18][10].stack.add("O");
		
		v[18][10].transatev.add("type");	
		v[18][10].transatev.add("O");
		//V->ε
		v[18][17]=new Regular();
		v[18][17].stack.add("ε");	
		v[18][17].transatev.add("ε");	
		
		//O->idN
		v[19][0]=new Regular();
		v[19][0].stack.add("id");	
		v[19][0].stack.add("N");
		
		v[19][0].transatev.add("id");	
		v[19][0].transatev.add("N");
		
		//N->ε
		v[8][17]=new Regular();
		v[8][17].stack.add("ε");
		v[8][17].transatev.add("ε");
	
		//N->,V
		v[8][24]=new Regular();
		v[8][24].stack.add(",");
		v[8][24].stack.add("V");
		
		v[8][24].transatev.add(",");
		v[8][24].transatev.add("V");
		
		v[20][7]=new Regular();
		v[20][7].stack.add("protected");
		v[20][7].transatev.add("protected");
		
		v[20][8]=new Regular();
		v[20][8].stack.add("private");
		v[20][8].transatev.add("private");
		v[20][9]=new Regular();
		v[20][9].stack.add("public");
		v[20][9].transatev.add("public");
		
		v[20][10]=new Regular();
		v[20][10].stack.add("ε");
		v[20][10].transatev.add("ε");
		
//		v[8][13]=new Regular();
//		v[8][13].stack.add("ε");
		
		
		
		
		
		
	}
	public boolean analyasis(List<WordUnit> in)
	{

		boolean result=false;
		stack.clear();
		stack.add("#");
		
		tree.init();
		
		stack.add("S");
		
		tree.CreateTree("S");
		
		setInStr(in);
		String nowc=getOneStack();//词法分析栈中的词
		WordUnit ch=pollWordUnit();//待匹配串中的词
		while(!(nowc.equals(ch.getNature())&&nowc.equals("#")))
		{
			
			if(vn.containsKey(nowc))//nowc 词法分析栈中当前串 
			{
			
				if(findTable(nowc,ch))
				{
					//Stack regular=v[i][j];
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
					MutiNode popnode=MutiTree.stack.pop();
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
	/*
	 * 语法分析方法 旧的 
	 * */
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
	/*
	 *语义分析的方法 新 
	 ***/
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
