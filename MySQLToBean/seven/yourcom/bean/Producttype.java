package com.yourcom.bean;

	/**
	*������MySQLToBean�����Զ�����
	*��ע(���ݱ��comment�ֶ�)���ޱ�ע��Ϣ
	*@author childlikeman@gmail.com,http://t.qq.com/lostpig
	*@since 2014-09-28 01:21:24
	*/

	public class Producttype{
	private int id;
	private int typeorder;
	private String typename;
	private int ispass;
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getTypeorder(){
		return this.typeorder;
	}
	public void setTypeorder(int typeorder){
		this.typeorder=typeorder;
	}
	public String getTypename(){
		return this.typename;
	}
	public void setTypename(String typename){
		this.typename=typename;
	}
	public int getIspass(){
		return this.ispass;
	}
	public void setIspass(int ispass){
		this.ispass=ispass;
	}

}