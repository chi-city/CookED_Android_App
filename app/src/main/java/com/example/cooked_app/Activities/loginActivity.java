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
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooked_app.MyApp;
import com.example.cooked_app.R;

public class loginActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton, signupButton;
    SQLiteDatabase sqLiteDatabase;
    SQLiteOpenHelper dbHelper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", new String[]{username, password});
                if(cursor.getCount() > 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    MyApp myApp = (MyApp) getApplication();
                    myApp.global_name = username;
                    myApp.global_email = username + "@example.com";
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username or Password!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, signupActivity.class);
                startActivity(intent);
            }
        });
    }
}