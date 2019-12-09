package com.hilaylotan.storyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hilaylotan.storyapp.R;

public class MainActivity extends AppCompatActivity{

    private EditText nameField;
    private Button startButton;
    private String saveName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            nameField = (EditText)findViewById(R.id.nameEditText);
            startButton=(Button)findViewById(R.id.startButton);

            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name= nameField.getText().toString();
                    startStory(name);
                }
            });
        }

    @Override
    protected void onResume() {
        super.onResume();
        nameField.setText("");

    }

    private void startStory(String name) {
        Intent intet=new Intent(this, StoryActivity.class);
        Resources resources=getResources();
        String key=resources.getString(R.string.key_name);
        intet.putExtra(key, name);
        startActivity(intet);
    }
}