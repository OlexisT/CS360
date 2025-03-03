package com.example.proj_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.proj_2.entities.User;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "inventory").build();
        EditText newUser = findViewById(R.id.idEdtUserName);
        EditText newPass = findViewById(R.id.idEdtPassword);
        findViewById(R.id.idBtnLogin).setOnClickListener(v -> db.userDao().login(newUser.getText().toString(), newPass.getText().toString()).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new SingleObserver<>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        Intent open = new Intent(LoginActivity.this, StockActivity.class);
                        startActivity(open);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));

        // Create on click listener for Users to Create Login
        findViewById(R.id.idBtnCreateLogin).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CUsersActivity.class);
            startActivity(intent);
        });

    }
}