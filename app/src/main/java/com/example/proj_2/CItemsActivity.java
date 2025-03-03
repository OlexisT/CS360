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

import com.example.proj_2.entities.Item;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CItemsActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editItem = findViewById(R.id.idEdtItem);
        EditText editQuantity = findViewById(R.id.idEdtQuantity);
        Item item = new Item();

        editItem.addTextChangedListener(new TextChangedListener(item::setItemName, editItem));
        editQuantity.addTextChangedListener(new TextChangedListener(item::setItemQuantity, editQuantity));


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "inventory").build();
        findViewById(R.id.idBtnAddItem).setOnClickListener(v -> db.itemDao().insert(item).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Intent openInv = new Intent(CItemsActivity.this, StockActivity.class);
                        startActivity(openInv);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));

                //Create on click listened for Users to go to Inventory
                findViewById(R.id.idBtnBack1).setOnClickListener(v -> {
                    Intent intent = new Intent(CItemsActivity.this, StockActivity.class);
                    startActivity(intent);
                });
    }

}