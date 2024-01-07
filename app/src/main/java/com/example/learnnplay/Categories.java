package com.example.learnnplay;
import com.example.learnnplay.welcome;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Categories extends AppCompatActivity {
    private Users user;
    private LinearLayout back;
    private View mContentView;
    private TextView englishTextView; // Change MyTextView to TextView
    private TextView mathTextView;    // Change MyTextView to TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.categories);
        mContentView = findViewById(R.id.fullscreen_content);
        englishTextView = findViewById(R.id.english);
        mathTextView = findViewById(R.id.math);
        user = getIntent().getParcelableExtra("Users");
        hide();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWelcome();
            }
        });

        mathTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMathGame();
            }
        });

        englishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToEnglishGame();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateToWelcome();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hide();
    }

    private void edits() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    private void hide() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void navigateToWelcome() {
        Intent it = new Intent(Categories.this, welcome.class);
        it.putExtra("Users", user);
        startActivity(it);
        finish();
    }

    private void navigateToMathGame() {
        Intent it = new Intent(Categories.this, MathGame.class);
        it.putExtra("Users", user);
        startActivity(it);
        finish();
    }

    private void navigateToEnglishGame() {
        Intent it = new Intent(Categories.this, EnglishGame.class);
        it.putExtra("Users", user);
        startActivity(it);
        finish();
    }
}
