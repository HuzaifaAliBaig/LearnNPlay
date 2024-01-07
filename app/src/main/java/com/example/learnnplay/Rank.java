package com.example.learnnplay;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Rank extends AppCompatActivity {
    private DatabaseHelper helper = new DatabaseHelper(this);
    private TextView mrank;
    private TextView erank;
    private TextView mname;
    private TextView ename;
    private TextView mscore;
    private TextView escore;
    private Users user;
    private View mContentView;
    private TextView returne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.rank);
        user = (Users) getIntent().getParcelableExtra("Users");
        mrank = findViewById(R.id.mrank);
        erank = findViewById(R.id.erank);
        mname = findViewById(R.id.mname);
        ename = findViewById(R.id.ename);
        mscore = findViewById(R.id.mscore);
        escore = findViewById(R.id.escore);
        mContentView = findViewById(R.id.fullscreen_content);
        helper.GetRank(6, erank, ename, escore, user.getUname());
        helper.GetRank(5, mrank, mname, mscore, user.getUname());
        returne = findViewById(R.id.Rankreturn);

        returne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Rank.this, profile.class);
                it.putExtra("Users", user);
                finish();
                startActivity(it);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(Rank.this, profile.class);
        it.putExtra("Users", user);
        finish();
        startActivity(it);
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
}
