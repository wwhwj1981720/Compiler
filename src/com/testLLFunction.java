package com;

import java.util.ArrayList;
import java.util.List;

public class testLLFunction {

	/**
	 * @param args
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
