package com.example.sqlitedatebasetest; 

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
* @author ���� ���䴺
* @version ����ʱ�䣺2015-11-3 ����11:32:55 
* ��˵��
* SQLiteOpenHelper��һ�������࣬�����������ݿ�Ĵ����Ͱ汾�����ṩ��������Ĺ���
* ��һ��getReadableDatebase��getWriteableDatebase ���Ի��SQLiteDatebase����
* ͨ���ö�����Զ����ݿ���в���
* �ڶ����ṩ��onCreate(),onUpgrade�����ص����������������ٴ����͸������ݿ�ʱ�����Խ����Լ��Ĳ���
 */
public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final int VERSION = 1;  
	
	/** 
     * ��SQLiteOpenHelper�����൱�У������иù��캯�� 
     * @param context   �����Ķ��� 
     * @param name      ���ݿ����� 
     * @param factory 
     * @param version   ��ǰ���ݿ�İ汾��ֵ���������������ǵ�����״̬ 
     */  
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseHelper(Context context,String name,int version){
		this(context,name,null,version);
	}
	
	public DatabaseHelper(Context context, String name){
		this(context,name,VERSION);
	}
	/**
	 * �ú������ڵ�һ�δ�����ʱ��ִ�У�ʵ�����ǵ�һ�εõ�SQLitedatebase�����ʱ��Ż�������������
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("create a datebase");
		//execSQL����ִ��SQL���  
		db.execSQL("create table user (id int,name verchar(20))");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		System.out.println("upgrade a database");
	}

}
 