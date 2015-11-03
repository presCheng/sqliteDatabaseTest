package com.example.sqlitedatebasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	private Button btnCreateDatabase = null;
	private Button btnUpdateDatabase = null;
	private Button btnInsert = null;
	private Button btnUpdate = null;
	private Button btnQuery = null;
	private Button btnDelete = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 根据控件id获得相应的控件对象
		btnCreateDatabase = (Button) this.findViewById(R.id.btn_createDatebase);
		btnUpdateDatabase = (Button) this.findViewById(R.id.btn_updateDatebase);
		btnInsert = (Button) this.findViewById(R.id.btn_insert);
		btnUpdate = (Button) this.findViewById(R.id.btn_update);
		btnQuery = (Button) this.findViewById(R.id.btn_query);
		btnDelete = (Button) this.findViewById(R.id.btn_delete);
		// 为按钮设置监听器
		btnCreateDatabase.setOnClickListener(this);
		btnUpdateDatabase.setOnClickListener(this);
		btnInsert.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
		btnQuery.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// btn_createDatebase点击事件监听器
		case R.id.btn_createDatebase:
			// 创建了一个DatebaseHelper对象，只执行这句话是不会创建或打开连接的
			DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
					"test01_db");
			// 只有调用了DatabaseHelper的getWritableDatabase()方法或者getReadableDatabase()方法之后，才会创建或打开一个连接
			SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
			break;

		// btn_updateDatebase点击事件监听器
		case R.id.btn_updateDatebase:
			DatabaseHelper dbHelper2 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			// 得到一个只读的SQLiteDatabase对象
			SQLiteDatabase sqLiteDatabase2 = dbHelper2.getReadableDatabase();
			break;

		// btn_insert点击事件监听器
		case R.id.btn_insert:
			// 创建ContentValues对象
			ContentValues values = new ContentValues();
			// 向该对象中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
			values.put("id", 1);
			values.put("name", "zhangsan");
			// 创建DatabaseHelper对象
			DatabaseHelper dbHelper3 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			// 得到一个可写的SQLiteDatabase对象
			SQLiteDatabase sqLiteDatabase3 = dbHelper3.getWritableDatabase();
			// 调用insert方法，就可以将数据插入到数据库当中
			// 第一个参数:表名称
			// 第二个参数：SQl不允许一个空列，如果ContentValues是空的，那么这一列被明确的指明为NULL值
			// 第三个参数：ContentValues对象
			sqLiteDatabase3.insert("test01_db", null, values);
			break;

		// btn_update点击事件监听器
		case R.id.btn_update:
			// 创建一个DatabaseHelper对象
			DatabaseHelper dbHelper4 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			// 得到一个可写的SQLiteDatabase对象
			SQLiteDatabase sqliteDatabase4 = dbHelper4.getWritableDatabase();
			// 创建一个ContentValues对象
			ContentValues values2 = new ContentValues();
			values2.put("name", "lisi");
			// 调用update方法
			// 第一个参数String：表名
			// 第二个参数ContentValues：ContentValues对象
			// 第三个参数String：where字句，相当于sql语句where后面的语句，？号是占位符
			// 第四个参数String[]：占位符的值
			sqliteDatabase4.update("user", values2, "id=?",
					new String[] { "1" });
			System.out.println("-----------update------------");
			break;

		// btn_query点击事件监听器
		case R.id.btn_query:
			String id = null;
			String name = null;
			DatabaseHelper dbHelper5 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			SQLiteDatabase sqLiteDatabase5 = dbHelper5.getReadableDatabase();
			// 调用SQLiteDatabase对象的query方法进行查询，返回一个Cursor对象：由数据库查询返回的结果集对象
			// 第一个参数String：表名
			// 第二个参数String[]:要查询的列名
			// 第三个参数String：查询条件
			// 第四个参数String[]：查询条件的参数
			// 第五个参数String:对查询的结果进行分组
			// 第六个参数String：对分组的结果进行限制
			// 第七个参数String：对查询的结果进行排序
			Cursor cursor = sqLiteDatabase5.query("user", new String[] { "id",
					"name" }, "id=?", new String[] { "1" }, null, null, null);
			while(cursor.moveToNext()){
				id = cursor.getString(cursor.getColumnIndex("id"));  
                name = cursor.getString(cursor.getColumnIndex("name"));  
			}
			System.out.println("-------------query------------");  
            System.out.println("id: "+id);  
            System.out.println("name: "+name);
			break;

		// btn_delete点击事件监听器
		case R.id.btn_delete:
			//创建DatabaseHelper对象  
            DatabaseHelper dbHelper6 = new DatabaseHelper(MainActivity.this,"test01_db",2);  
            //获得可写的SQLiteDatabase对象  
            SQLiteDatabase sqliteDatabase6 = dbHelper6.getWritableDatabase();  
            //调用SQLiteDatabase对象的delete方法进行删除操作  
            //第一个参数String：表名  
            //第二个参数String：条件语句  
            //第三个参数String[]：条件值  
            sqliteDatabase6.delete("user", "id=?", new String[]{"1"});  
            System.out.println("----------delete----------"); 
			break;
		default:
			break;
		}
	}

}
