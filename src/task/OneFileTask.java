package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import lex.LexInterface;

import unicom.FileInfo;
import unicom.ReadFileStartLex;
import unicom.WordUnit;

public class OneFileTask {
	
	LexInterface lex;
	
	public OneFileTask(LexInterface lex) {
		super();
		this.lex = lex;
	}

	public List<WordUnit> reverseMd(String path) 
	 {
		 System.out.println(path);
			//CountTotal ct=new CountTotal();
			FileInfo fi=new FileInfo();
			String fname="";
			fi.setPath(path);
			File fin=new File(path);
			if(fin!=null) fname=fin.getName(); 
			ReadFileStartLex startlex=new ReadFileStartLex(path);
			try {
				startlex.readFileContent();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!startlex.isTxt()) return null;//不是文本文件直接退出
			String content=startlex.getContent();
			lex.wordAnalysis(content);
			List<WordUnit> wordlist=lex.getWordlist();
			return wordlist;
	 }

}
