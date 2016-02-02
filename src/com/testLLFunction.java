package com;

import java.util.ArrayList;
import java.util.List;

public class testLLFunction {

	/**
	 * @param args
	 * 使用 LLFunction分析 一个 词法输出的结果
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LLFunction test=new LLFunction();
		
		List<String> inputlist=new ArrayList<String>();
		//test.analyasis("abbedc#");
//		inputlist.add("id");
//		inputlist.add("=");
//		inputlist.add("id");
//		inputlist.add("#");
		inputlist.add("while");
		inputlist.add("(");
		inputlist.add("id");
		inputlist.add(">");
		inputlist.add("id");
		inputlist.add(")");
		inputlist.add("id");
		inputlist.add("=");
		inputlist.add("id");
		inputlist.add("#");
		test.init();
		test.initVector();
		test.analyasis(inputlist);

	}

}
