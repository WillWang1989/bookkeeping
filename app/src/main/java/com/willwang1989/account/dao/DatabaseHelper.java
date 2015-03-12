package com.willwang1989.account.dao;

import java.util.ArrayList;

import com.willwang1989.account.bean.AccountInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "t"; // 数据库名称
	private static final int version = 1; // 数据库版本
	private static DatabaseHelper database = null;// 这段代码放到Activity类中才用this
	private static SQLiteDatabase db = null;

	private DatabaseHelper(Context context) {
		super(context, DB_NAME, null, version);

	}

	public static DatabaseHelper Init(Context context) {
		if (database == null) {
			database = new DatabaseHelper(context);
			database = new DatabaseHelper(context);
			db = database.getWritableDatabase();
		}
		return database;
	}

	public long Insert(AccountInfo data) {
		ContentValues cv = new ContentValues();
		cv.put("Item", data.getItem());
		cv.put("Remarks", data.getRemarks());
		cv.put("Amount", data.getAmount());
		cv.put("CreateTime", data.getCreateTime());
		cv.put("IsPersonal", data.getIsPersonal());
		cv.put("Uid", data.getUid());
		return db.insert("trans", null, cv);
	}

	public long TransCount() {
		String sql = "select count(1) from [trans]";
		SQLiteStatement ss = db.compileStatement(sql);
		return ss.simpleQueryForLong();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE [trans] ([id] INTEGER PRIMARY KEY AUTOINCREMENT, [Uid] INTEGER, [Item] VARCHAR (150), [Amount] NUMERIC, [IsPersonal] INTEGER, [CreateTime] INTEGER, [Remarks] VARCHAR (200));";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public ArrayList<AccountInfo> GetTransList() {
		ArrayList<AccountInfo> ls = new ArrayList<AccountInfo>();
		String sql = "select * from [trans]";
		Cursor c = db.rawQuery(sql, null);
		if (c.moveToFirst()) {// 判断游标是否为空
			for (int i = 0; i < c.getCount(); i++) {
				AccountInfo model = new AccountInfo();
				c.move(i);// 移动到指定记录
				Long id = c.getLong(c.getColumnIndex("id"));
				int uid = c.getInt(c.getColumnIndex("Uid"));
				String item = c.getString(c.getColumnIndex("Item"));
				double amount = c.getDouble(c.getColumnIndex("Amount"));
				int isPersonal = c.getInt(c.getColumnIndex("IsPersonal"));
				long createTime = c.getLong(c.getColumnIndex("CreateTime"));
				String remarks = c.getString(c.getColumnIndex("Remarks"));
				model.setId(id);
				model.setUid(uid);
				model.setItem(item);
				model.setAmount(amount);
				model.setIsPersonal(isPersonal);
				model.setCreateTime(createTime);
				model.setRemarks(remarks);
				ls.add(model);
			}
		}
		return ls;
	}

	public void DeleteTrans(long tid) {
		String sql = "DELETE FROM [trans] where id=" + tid;
		db.execSQL(sql);
	}
}
