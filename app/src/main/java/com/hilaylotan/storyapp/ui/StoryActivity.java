package com.hilaylotan.storyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hilaylotan.storyapp.R;
import com.hilaylotan.storyapp.model.Page;
import com.hilaylotan.storyapp.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity
{

    public static final String TAG=StoryActivity.class.getSimpleName();

    private String name;
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private Stack<Integer> stack=new Stack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView=findViewById(R.id.storyImageView);
        storyTextView = (TextView)findViewById(R.id.storyTextView);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        choice2Button = (Button)findViewById(R.id.choice2Button);

        Intent intent=getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        if (name==null || name.isEmpty())
        {
            name="jojo";
        }
        Log.d(TAG,name);

        story=new Story();
        loadPage(0);
    }

    private void loadPage(final int pageNumber)
    {
        stack.push(pageNumber);

        final Page page=story.getPage(pageNumber);

        Drawable image= ContextCompat.getDrawable(this,page.getImageId());
        storyImageView.setImageDrawable(image);

        String pageText=getString(page.getTextId());
        //Add anme if placeholder exist if not it will remain the same
        pageText = String.format(pageText,name);
        storyTextView.setText(pageText);

        if(page.getIsFinalPage())
        {
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadPage(0);
                }
            });
        }
        else {
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPage(page.getChoice1().getNextPage());
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPage(page.getChoice2().getNextPage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        stack.pop();
        if (stack.isEmpty())
            super.onBackPressed();
        else
            loadPage(stack.pop());
    }
}
