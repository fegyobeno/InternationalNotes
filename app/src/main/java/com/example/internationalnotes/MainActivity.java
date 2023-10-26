package com.example.internationalnotes;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //First comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = findViewById(R.id.myTextView); // Odnajduje TextView po identyfikatorze


        usersOpenHelper users_helper = new usersOpenHelper(this);
        SQLiteDatabase db = users_helper.getWritableDatabase();

        addUser(db, "Adrian", "admin", 2);
        addUser(db, "nasa42", "user", 1);
        addUser(db, "mathias420", "user", 1);

        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE user = 'Adrian'", null);

        if (cursor.moveToFirst()) {
            String password = cursor.getString(cursor.getColumnIndex("password"));
            myTextView.setText(password);
        }
        db.close();
    }

    private void addUser(SQLiteDatabase db, String username, String password, int permission) {
        ContentValues values = new ContentValues();
        values.put("user", username);
        values.put("password", password);
        values.put("permission", permission);

        db.insert("Users", null, values);
    }
}
