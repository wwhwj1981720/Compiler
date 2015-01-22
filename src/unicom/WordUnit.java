package unicom;
/**
 * 定义存放关键字的类
 * 这个和 代码审核中的不一样
 * @author lenovo
 *
 */
public class WordUnit {
	String value;
	int ID;
	int row;
	String nature;// main  value   nature  是  id 
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}	
}
