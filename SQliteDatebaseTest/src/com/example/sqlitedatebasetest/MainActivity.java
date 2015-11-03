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
		// ���ݿؼ�id�����Ӧ�Ŀؼ�����
		btnCreateDatabase = (Button) this.findViewById(R.id.btn_createDatebase);
		btnUpdateDatabase = (Button) this.findViewById(R.id.btn_updateDatebase);
		btnInsert = (Button) this.findViewById(R.id.btn_insert);
		btnUpdate = (Button) this.findViewById(R.id.btn_update);
		btnQuery = (Button) this.findViewById(R.id.btn_query);
		btnDelete = (Button) this.findViewById(R.id.btn_delete);
		// Ϊ��ť���ü�����
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
		// btn_createDatebase����¼�������
		case R.id.btn_createDatebase:
			// ������һ��DatebaseHelper����ִֻ����仰�ǲ��ᴴ��������ӵ�
			DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
					"test01_db");
			// ֻ�е�����DatabaseHelper��getWritableDatabase()��������getReadableDatabase()����֮�󣬲Żᴴ�����һ������
			SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
			break;

		// btn_updateDatebase����¼�������
		case R.id.btn_updateDatebase:
			DatabaseHelper dbHelper2 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			// �õ�һ��ֻ����SQLiteDatabase����
			SQLiteDatabase sqLiteDatabase2 = dbHelper2.getReadableDatabase();
			break;

		// btn_insert����¼�������
		case R.id.btn_insert:
			// ����ContentValues����
			ContentValues values = new ContentValues();
			// ��ö����в����ֵ�ԣ����м���������ֵ��ϣ�����뵽��һ�е�ֵ��ֵ��������ݿ⵱�е���������һ��
			values.put("id", 1);
			values.put("name", "zhangsan");
			// ����DatabaseHelper����
			DatabaseHelper dbHelper3 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			// �õ�һ����д��SQLiteDatabase����
			SQLiteDatabase sqLiteDatabase3 = dbHelper3.getWritableDatabase();
			// ����insert�������Ϳ��Խ����ݲ��뵽���ݿ⵱��
			// ��һ������:������
			// �ڶ���������SQl������һ�����У����ContentValues�ǿյģ���ô��һ�б���ȷ��ָ��ΪNULLֵ
			// ������������ContentValues����
			sqLiteDatabase3.insert("test01_db", null, values);
			break;

		// btn_update����¼�������
		case R.id.btn_update:
			// ����һ��DatabaseHelper����
			DatabaseHelper dbHelper4 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			// �õ�һ����д��SQLiteDatabase����
			SQLiteDatabase sqliteDatabase4 = dbHelper4.getWritableDatabase();
			// ����һ��ContentValues����
			ContentValues values2 = new ContentValues();
			values2.put("name", "lisi");
			// ����update����
			// ��һ������String������
			// �ڶ�������ContentValues��ContentValues����
			// ����������String��where�־䣬�൱��sql���where�������䣬������ռλ��
			// ���ĸ�����String[]��ռλ����ֵ
			sqliteDatabase4.update("user", values2, "id=?",
					new String[] { "1" });
			System.out.println("-----------update------------");
			break;

		// btn_query����¼�������
		case R.id.btn_query:
			String id = null;
			String name = null;
			DatabaseHelper dbHelper5 = new DatabaseHelper(MainActivity.this,
					"test01_db", 2);
			SQLiteDatabase sqLiteDatabase5 = dbHelper5.getReadableDatabase();
			// ����SQLiteDatabase�����query�������в�ѯ������һ��Cursor���������ݿ��ѯ���صĽ��������
			// ��һ������String������
			// �ڶ�������String[]:Ҫ��ѯ������
			// ����������String����ѯ����
			// ���ĸ�����String[]����ѯ�����Ĳ���
			// ���������String:�Բ�ѯ�Ľ�����з���
			// ����������String���Է���Ľ����������
			// ���߸�����String���Բ�ѯ�Ľ����������
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

		// btn_delete����¼�������
		case R.id.btn_delete:
			//����DatabaseHelper����  
            DatabaseHelper dbHelper6 = new DatabaseHelper(MainActivity.this,"test01_db",2);  
            //��ÿ�д��SQLiteDatabase����  
            SQLiteDatabase sqliteDatabase6 = dbHelper6.getWritableDatabase();  
            //����SQLiteDatabase�����delete��������ɾ������  
            //��һ������String������  
            //�ڶ�������String���������  
            //����������String[]������ֵ  
            sqliteDatabase6.delete("user", "id=?", new String[]{"1"});  
            System.out.println("----------delete----------"); 
			break;
		default:
			break;
		}
	}

}
