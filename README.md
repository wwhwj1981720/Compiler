# Compiler
java 简单编译器
自己写的java 编译器，实现词法分析，语法分析，得到了语法树。

1、testTranslateJavaSentanceToMasm 是 解析 语法树的测试类。
可以分析 java 的简单语法

public class type{
public void main(String arg[]){	
int a;
a=1;
while(a<3) a=a+1;
k=m;


}
}
输出是 ：

 main_while.txt
publicclasstype{publictypeid(typeid){typeid;id=num;while(id<num)id=id+num;id=id;}}#停止
S->publicclasstype{Z}
Z->PZ
P->Wtypeid(V){G}
W->public
V->typeO
O->idN
N->ε
G->LG
L->I
I->typeid;
G->LG
L->A
A->id=B;
B->TB'
T->HT'
H->num
T'->ε
B'->ε
G->LG
L->F
F->MJ
M->while(E)
E->BU
B->TB'
T->HT'
H->id
T'->ε
B'->ε
U-><B
B->TB'
T->HT'
H->num
T'->ε
B'->ε
J->L
L->A
A->id=B;
B->TB'
T->HT'
H->id
T'->ε
B'->+TB'
T->HT'
H->num
T'->ε
B'->ε
G->LG
L->A
A->id=B;
B->TB'
T->HT'
H->id
T'->ε
B'->ε
G->ε
Z->ε
success
@id
@num
@=
@id
@num
@<
@BF
@id
@id
@num
@+
@=
@BL
@id
@id
@=

