package com.example.cooked_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cooked_app.R;

public class signupActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button signupButton;
    SQLiteDatabase sqLiteDatabase;
    SQLiteOpenHelper dbHelper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        context = this;

        usernameEditText = findViewById(R.id.newUsernameEditText);
        passwordEditText = findViewById(R.id.newPasswordEditText);
        signupButton = findViewById(R.id.signupButton);

        dbHelper = new SQLiteOpenHelper(this, "UserDB", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE Users (username TEXT, password TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS Users");
                onCreate(db);
            }
        };
        sqLiteDatabase = dbHelper.getWritableDatabase();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE username=?", new String[]{username});
                if (cursor.getCount() > 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    sqLiteDatabase.execSQL("INSERT INTO Users (username, password) VALUES (?, ?)", new String[]{username, password});
                    Toast toast = Toast.makeText(getApplicationContext(), "Signup successful!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}