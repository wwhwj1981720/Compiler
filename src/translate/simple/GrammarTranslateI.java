package translate.simple;

import unicom.WordUnit;

public class GrammarTranslateI implements GrammarTransInterface {

	/**局部变量定义的语句
	 * @param args
	 */
	WordUnit type;
	WordUnit id;
	String tomasmstr;
	public void getRegularParam(WordUnit wtype,WordUnit wid)
	{
		type=wtype;
		id=wid;
	}
	public void ToMasm()
	{
		tomasmstr=type.getValue()+id.getValue();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
