package com.example.sqlitedatebasetest; 

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
* @author 作者 程其春
* @version 创建时间：2015-11-3 上午11:32:55 
* 类说明
* SQLiteOpenHelper是一个辅助类，用来管理数据库的创建和版本，它提供两个方面的功能
* 第一，getReadableDatebase和getWriteableDatebase 可以获得SQLiteDatebase对象，
* 通过该对象可以对数据库进行操作
* 第二，提供了onCreate(),onUpgrade两个回调函数，允许我们再创建和更新数据库时，可以进行自己的操作
 */
public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final int VERSION = 1;  
	
	/** 
     * 在SQLiteOpenHelper的子类当中，必须有该构造函数 
     * @param context   上下文对象 
     * @param name      数据库名称 
     * @param factory 
     * @param version   当前数据库的版本，值必须是整数并且是递增的状态 
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
	 * 该函数是在第一次创建的时候执行，实际上是第一次得到SQLitedatebase对象的时候才会调用这个方法。
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("create a datebase");
		//execSQL用于执行SQL语句  
		db.execSQL("create table user (id int,name verchar(20))");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		System.out.println("upgrade a database");
	}

}
 