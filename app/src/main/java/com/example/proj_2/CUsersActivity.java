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

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CUsersActivity extends AppCompatActivity  {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cusers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editFirst = findViewById(R.id.idEdtFirstName);
        EditText editLast = findViewById(R.id.idEdtLastName);
        EditText editUser = findViewById(R.id.idEdtUserName);
        EditText editPass = findViewById(R.id.idEdtPassword);
        User user = new User();

        editFirst.addTextChangedListener(new TextChangedListener(user::setFirstName, editFirst));
        editLast.addTextChangedListener(new TextChangedListener(user::setLastName, editLast));
        editUser.addTextChangedListener(new TextChangedListener(user::setUsername, editUser));
        editPass.addTextChangedListener(new TextChangedListener(user::setPassword, editPass));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "inventory").build();
        findViewById(R.id.idBtnLogin).setOnClickListener(v -> db.userDao().insert(user).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Intent logins = new Intent(CUsersActivity.this, LoginActivity.class);
                        startActivity(logins);
                    }

                    @Override
                    public void onError(Throwable e) {
                        editUser.setError("Choose a new Username");
                    }
                }));

        // Create on click listener for Back button to Login Page
        findViewById(R.id.idBtnBack).setOnClickListener(v -> {
            Intent back = new Intent(CUsersActivity.this, LoginActivity.class);
            startActivity(back);
        });

    }

}