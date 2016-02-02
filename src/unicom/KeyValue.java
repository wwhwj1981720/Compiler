package unicom;

import java.util.HashSet;
import java.util.TreeSet;
/*
*/
import java.util.Set;

public class KeyValue {
	public static Set<String> key=new HashSet<String>();
	public static Set<String> refunTypeKey=new TreeSet<String>();//函数返回关键字
	public static Set<String> typeset=new HashSet<String>();//java 中的类型 int void double boolean float byte
	private static String keyword="abstract  break      case   catch      class "

	+"const   continue   default   do      else   extends   false "

	+"final   finally      for   goto   if   implements   import "

	+"instanceof      interface      native   new   null   package "

	+"private   protected   public   return      static   super   switch "

	+"synchronized   this   throw   throws   transient   true   try    "

	+"volatile   while";
	private static String type="char boolean byte double float long int short void type String Long Integer Boolean";
	static 
	{
  

		
		//String []returnfunction={"void","int"};
		
		String keyarr[]=keyword.split("\\s{1,}");
		for(String s:keyarr)
		{
			key.add(s);
			//if(s.equals("void")||s.equals("boolean")||s.equals("int")||s.equals("float")||s.equals("double"))
				refunTypeKey.add(s);	
		}
		String typearr[]=type.split("\\s{1,}");
		for(String s:typearr)
		{
			typeset.add(s);
			
		}
	}
	

}
