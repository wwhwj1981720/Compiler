package translate.base.java;

import java.util.List;

import lex.JavaAnayase;
import lex.JavaAnayaseExtend;
import lex.LexInterface;



import task.OneFileTask;
import translate.tree.MutiNode;
import translate.tree.MutiTree;
import translate.tree.PrintTree;
import unicom.WordUnit;

public class testTranslateJavaSentanceALL {

	/**
	 * @param args
	 * 使用resources下面的test.txt文件作为 输入分析
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="main.txt";
		LexInterface lex=new JavaAnayaseExtend ();//词法分析文件 
		OneFileTask file=new OneFileTask(lex);
		List<WordUnit> wordlist=file.reverseMd(path);//生成词法分析结果
		WordUnit one=new WordUnit();
		one.setValue("#");
		one.setNature("#");
		wordlist.add(one);
		for(WordUnit w:wordlist)
		{
			System.out.print(w.getNature());
			
		}
		System.out.println("停止");
		TranslateJavaSentanceALL test=new TranslateJavaSentanceALL();
		test.init();
		test.initVector();
		test.analyasis(wordlist);
		MutiTree.travelTree(test.tree.root,new PrintTree<MutiNode>() {

			public void printNode(MutiNode node) {
				// TODO Auto-generated method stub
//				if(ContainSubStr(data,"@"))
//				{
//					System.out.println(data);
//				}
				if(node.data.equals("P"))//是否是函数
				{
					//继续以非叶子节偶为树，输出所有的终端节点
					MutiTree.travelTreeFinalNode(node,new PrintTree<String>() 
					{

							public void printNode(String data) 
							{
								// TODO Auto-generated method stub
								System.out.print(data);
									
							}
					}
					);
					System.out.println(" ");
				}
			}
			public boolean ContainSubStr(String str,String sub)
			{
				boolean flag=false;
				//String str="@+";  //待判断的字符串
				String reg=".*"+sub+".*";  //判断字符串中是否含有特定字符串ll
				if(str.matches(reg)) flag=true;
				return flag;
			}

			
		});
		

	}

}
