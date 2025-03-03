package com.example.proj_2;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.function.Consumer;

public class TextChangedListener implements TextWatcher {

    Consumer<String> setter;
    EditText editText;

    TextChangedListener(Consumer<String> setter, EditText editText){
        this.setter = setter;
        this.editText = editText;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            // write a possible error
            this.setter.accept(s.toString());
        } catch (IllegalArgumentException e) {
            //runs if error happens
            editText.setError(e.getMessage());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
