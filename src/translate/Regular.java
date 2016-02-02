package translate;

import java.util.Stack;
import java.util.Vector;
/*
 * 
 * 用来存储原始规则和翻译之后的规则
 * transatev 用来存储添加翻译之后的规则比如  *HT  翻译成为 *H@*T
 * 
 * **/
public class Regular {
	public Stack<String> stack=new Stack();
	public Vector transatev=new Vector();

}
