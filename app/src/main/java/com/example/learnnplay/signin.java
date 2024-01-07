package com.example.learnnplay;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    private DatabaseHelper helper = new DatabaseHelper(this);
    private ImageView signinback;
    private View mContentView;
    private EditText username;
    private EditText password;
    private TextView signin;
    Boolean hidden = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.signin);
        mContentView = findViewById(R.id.fullscreen_content);
        hide();
        signin = findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //========================================================
                EditText usrnm = findViewById(R.id.INusername);
                String username = usrnm.getText().toString();
                EditText pswrd = findViewById(R.id.INpassword);
                String pass = pswrd.getText().toString();

                String password = helper.searchPass(username);
                if(pass.equals(password))
                {
                    Users user=new Users();
                    user.setUname(helper.GetColumn(username,"Username"));
                    user.setProfilepic(helper.GetColumn(user.getUname(),"ProfilePic"));
                    user.setMathscore(Integer.parseInt(helper.GetColumn(user.getUname(),"MathScore")));
                    user.setEngscore(Integer.parseInt(helper.GetColumn(user.getUname(),"EngScore")));
                    Intent i = new Intent(signin.this,welcome.class);
                    i.putExtra("Users", user);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast errorlog = Toast.makeText(signin.this,"Username and Password not matching!", Toast.LENGTH_SHORT);
                    errorlog.show();
                }
                //========================================================================
            }
        });
        signinback = findViewById(R.id.signinback);
        signinback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signin.this,main.class);
                finish();
                startActivity(it);
            }
        });
        username = findViewById(R.id.INusername);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hidden = false ;
                }
            }
        });
        password = findViewById(R.id.INpassword);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hidden = false ;
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        hide();
    }

    @Override
    public void onBackPressed() {
        if(!hidden) {
            hide();
        }else{
            super.onBackPressed();
            Intent it = new Intent(signin.this,main.class);
            finish();
            startActivity(it);
        }
    }

    private void edits(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    private void hide() {
        mContentView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        hidden = true;
    }
}
