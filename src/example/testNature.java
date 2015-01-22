package example;

import java.util.List;

import lex.JavaAnayase;
import lex.JavaAnayaseExtend;
import lex.LexInterface;

import com.LLFunction;
import com.LLNatureFuntion;
import com.LLNatureFuntionTranslate;
import com.LLWordListFunction;

import task.OneFileTask;
import unicom.WordUnit;

public class testNature {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="test.txt";
		LexInterface lex=new JavaAnayaseExtend ();
		OneFileTask file=new OneFileTask(lex);
		List<WordUnit> wordlist=file.reverseMd(path);
		WordUnit one=new WordUnit();
		one.setValue("#");
		one.setNature("#");
		wordlist.add(one);
		for(WordUnit w:wordlist)
		{
			System.out.print(w.getNature());
			
		}
		System.out.println("停止");
		LLWordListFunction test=new LLNatureFuntion();
		test.init();
		test.initVector();
		test.analyasis(wordlist);
		

	}

}
