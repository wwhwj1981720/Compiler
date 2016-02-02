package translate;

import java.util.List;

import lex.JavaAnayase;
import lex.JavaAnayaseExtend;
import lex.LexInterface;

import com.LLFunction;
import com.LLNatureFuntion;
import com.LLNatureFuntionTranslate;
import com.LLWordListFunction;
import com.NatureFuntionTranslateMath;

import task.OneFileTask;
import translate.tree.MutiTree;
import translate.tree.PrintTree;
import unicom.WordUnit;

public class testMath {

	/**
	 * @param args
	 * 使用resources下面的test.txt文件作为 输入分析
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="math.txt";
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
		NatureFuntionTranslateMath_Regular test=new NatureFuntionTranslateMath_Regular();
		test.init();
		test.initVector();
		test.analyasis(wordlist);
		MutiTree.travelTreeFinalNode(test.tree.root,new PrintTree<String>() {

			@Override
			public void printNode(String data) {
				// TODO Auto-generated method stub
				if(ContainSubStr(data,"@"))
				{
					System.out.println(data);
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
