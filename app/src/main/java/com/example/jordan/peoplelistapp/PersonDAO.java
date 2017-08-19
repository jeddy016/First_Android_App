package com.example.jordan.peoplelistapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO extends SQLiteOpenHelper{

    public PersonDAO(Context context) {
        super(context, "Person", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE person (id INTEGER PRIMARY KEY, name TEXT NOT NULL, address TEXT, email TEXT, phone TEXT, website TEXT, rating REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(Person person) {
        ContentValues data = person.toContentValues();
        SQLiteDatabase db = getWritableDatabase();
        db.insert("person", null, data);
    }

    public List<Person> listAll() {
        List<Person> people = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM person", null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String website = cursor.getString(cursor.getColumnIndex("website"));
            double rating = cursor.getDouble(cursor.getColumnIndex("rating"));

            Person person = new Person(id, name, address, email, phone, website, rating);
            people.add(person);
        }

        return people;
    }

    public void remove(Person person) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {person.getId() + ""};
        db.delete("person", "id = ?", params);
    }

    public void update(Person person, int id) {
        ContentValues data = person.toContentValues();
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {id + ""};
        db.update("person", data, "id = ?", params);
    }
}
