package example;

import java.util.List;

import lex.JavaAnayase;
import lex.JavaAnayaseExtend;
import lex.LexInterface;

import com.LLFunction;
import com.LLNatureFuntion;
import com.LLNatureFuntionTranslate;
import com.LLNatureFuntionTranslateJava;
import com.LLNatureFuntionTranslateJavaSentance;
import com.LLNatureFuntionTranslateJavaSentanceALL;
import com.LLWordListFunction;

import task.OneFileTask;
import unicom.WordUnit;

public class testNatureFunctionJavaSentance {

	/**
	 * @param args
	 * 使用resources下面的test.txt文件作为 输入分析
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="javaSentence.txt";
		LexInterface lex=new JavaAnayaseExtend ();//词法分析文件 
		OneFileTask file=new OneFileTask(lex);
		List<WordUnit> wordlist=file.reverseMd(path);//生成词法分析结果
		WordUnit one=new WordUnit();
		one.setValue("#");
		one.setNature("#");
		wordlist.add(one);
		for(WordUnit w:wordlist)
		{
			System.out.print(w.getNature()+" ");
			
		}
		System.out.println("停止");
		LLNatureFuntionTranslateJavaSentance test=new LLNatureFuntionTranslateJavaSentance();
		test.init();
		test.initVector();
		test.analyasis(wordlist);
		

	}

}
