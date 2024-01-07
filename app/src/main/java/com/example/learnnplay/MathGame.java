package com.example.learnnplay;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MathGame extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Users user;
    private View mContentView;
    private LinearLayout returne;
    private TextView Operand1;
    private TextView Operand2;
    private TextView Operator;
    private TextView Equal;
    private TextView Result;
    private TextView mChoice1;
    private TextView mChoice2;
    private TextView mChoice3;
    private TextView mChoice4;
    private TextView lifes;
    private TextView score;
    private int unknwnvalue;
    private String unknown;
    private int Gamescore = 0;
    private int life = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.mathgame);
        user = getIntent().getParcelableExtra("Users");
        mContentView = findViewById(R.id.fullscreen_content);
        Operand1 = findViewById(R.id.Operand1);
        Operand2 = findViewById(R.id.Operand2);
        Operator = findViewById(R.id.Operator);
        Equal = findViewById(R.id.Equal);
        Result = findViewById(R.id.Result);
        mChoice1 = findViewById(R.id.mChoice1);
        mChoice2 = findViewById(R.id.mChoice2);
        mChoice3 = findViewById(R.id.mChoice3);
        mChoice4 = findViewById(R.id.mChoice4);
        lifes = findViewById(R.id.life);
        lifes.setText(String.valueOf(life));
        score = findViewById(R.id.score);
        score.setText(String.valueOf(Gamescore));
        Equal.setText("=");
        unknown = unknownRand();
        System.out.println("Unknown :" + unknown);
        unknwnvalue = equationGen(unknown);
        System.out.println("unknown Value:" + unknwnvalue);
        ChoicesAssign(unknwnvalue, unknown);
        mChoice1.setOnClickListener(this);
        mChoice2.setOnClickListener(this);
        mChoice3.setOnClickListener(this);
        mChoice4.setOnClickListener(this);
        returne = findViewById(R.id.mathreturn);
        returne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MathGame.this, Categories.class);
                int oldscore = user.getMathScore();
                int newscore = user.getMathScore() + Gamescore;
                user.setMathscore(newscore);
                helper.Update_MathScore(user.getUname(), oldscore, newscore);
                it.putExtra("Users", user);
                startActivity(it);
                finish();
            }
        });
    }

    int equationGen(String unknown) {
        int operand1, operand2, result;
        char operator;
        while (true) {
            operand1 = RandOperand();
            operand2 = RandOperand();
            operator = RandOperator();
            if (Result(operand1, operand2, operator) != -100) {
                result = Result(operand1, operand2, operator);
                break;
            }
        }
        Operand1.setText(String.valueOf(operand1));
        Operand2.setText(String.valueOf(operand2));
        Operator.setText(String.valueOf(operator));
        Result.setText(String.valueOf(result));
        switch (unknown) {
            case "Operand1":
                Operand1.setText("_");
                return operand1;
            case "Operand2":
                Operand2.setText("_");
                return operand2;
            case "Result":
                Result.setText("_");
                return result;
        }
        return result;
    }

    String unknownRand() {
        Random r = new Random();
        int i = r.nextInt(4 - 1) + 1;
        switch (i) {
            case 1:
                return "Operand1";
            case 2:
                return "Operand2";
            case 3:
                return "Result";
            default:
                return "Result";
        }
    }

    int choicesGen(String unknown) {
        Random r = new Random();
        if (("Operand1".equals(unknown)) || ("Operand2".equals(unknown))) {
            return r.nextInt(10 - 0) + 0;
        } else {
            return r.nextInt(82 - 0) + 0;
        }
    }

    void ChoicesAssign(int result, String unknown) {
        Random r = new Random();
        int resultchoice = r.nextInt(4) + 1;
        if (resultchoice == 1) {
            mChoice1.setText(String.valueOf(result));
        } else if (resultchoice == 2) {
            mChoice2.setText(String.valueOf(result));
        } else if (resultchoice == 3) {
            mChoice3.setText(String.valueOf(result));
        } else {
            mChoice4.setText(String.valueOf(result));
        }
        otherchoises(resultchoice);
    }

    void otherchoises(int resultchoice) {
        switch (resultchoice) {
            case 1:
                mChoice2.setText(String.valueOf(choicescheck()));
                mChoice3.setText(String.valueOf(choicescheck()));
                mChoice4.setText(String.valueOf(choicescheck()));
                break;
            case 2:
                mChoice1.setText(String.valueOf(choicescheck()));
                mChoice3.setText(String.valueOf(choicescheck()));
                mChoice4.setText(String.valueOf(choicescheck()));
                break;
            case 3:
                mChoice1.setText(String.valueOf(choicescheck()));
                mChoice2.setText(String.valueOf(choicescheck()));
                mChoice4.setText(String.valueOf(choicescheck()));
                break;
            case 4:
                mChoice1.setText(String.valueOf(choicescheck()));
                mChoice2.setText(String.valueOf(choicescheck()));
                mChoice3.setText(String.valueOf(choicescheck()));
                break;
        }
    }

    int choicescheck() {
        int number = 0;
        while (true) {
            number = choicesGen(unknown);
            if (number != Integer.parseInt(mChoice1.getText().toString())) {
                if (number != Integer.parseInt(mChoice2.getText().toString())) {
                    if (number != Integer.parseInt(mChoice3.getText().toString())) {
                        if (number != Integer.parseInt(mChoice4.getText().toString())) {
                            return number;
                        }
                    }
                }
            }
        }
    }

    char RandOperator() {
        Random r = new Random();
        int randoperator = r.nextInt(5 - 1) + 1;
        switch (randoperator) {
            case 1:
                return '+';
            case 2:
                return '-';
            case 3:
                return '×';
            case 4:
                return '÷';
        }
        return '+';
    }

    int RandOperand() {
        Random r = new Random();
        return r.nextInt(10 - 0) + 0;
    }

    int Result(int operand1, int operand2, char operator) {
        int Result = 0;
        switch (operator) {
            case '+':
                Result = operand1 + operand2;
                break;
            case '-':
                Result = operand1 - operand2;
                break;
            case '×':
                if (operand1 == 0 || operand2 == 0) {
                    Result = -100;
                    break;
                } else Result = operand1 * operand2;
                break;
            case '÷':
                if (operand1 == 0 || operand2 == 0) {
                    Result = -100;
                    break;
                } else if ((operand1 % operand2) != 0)
                    Result = -100;
                else Result = operand1 / operand2;
                break;
        }
        return Result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(MathGame.this, Categories.class);
        int oldscore = user.getMathScore();
        int newscore = user.getMathScore() + Gamescore;
        user.setMathscore(newscore);
        helper.Update_MathScore(user.getUname(), oldscore, newscore);
        it.putExtra("Users", user);
        finish();
        startActivity(it);
    }

    @Override
    public void onClick(View view) {
        if (life == 0) {
            Toast Death = Toast.makeText(MathGame.this, "You ran out of lifes", Toast.LENGTH_SHORT);
            Death.show();
        }
        int choice;
        if (view.getId() == R.id.mChoice1) {
            choice = Integer.parseInt(mChoice1.getText().toString());
            if (choice == unknwnvalue) {
                CorrectAns(mChoice1);
            } else {
                IncorrectAns(mChoice1);
            }
        } else if (view.getId() == R.id.mChoice2) {
            choice = Integer.parseInt(mChoice2.getText().toString());
            if (choice == unknwnvalue) {
                CorrectAns(mChoice2);
            } else {
                IncorrectAns(mChoice2);
            }
        } else if (view.getId() == R.id.mChoice3) {
            choice = Integer.parseInt(mChoice3.getText().toString());
            if (choice == unknwnvalue) {
                CorrectAns(mChoice3);
            } else {
                IncorrectAns(mChoice3);
            }
        } else if (view.getId() == R.id.mChoice4) {
            choice = Integer.parseInt(mChoice4.getText().toString());
            if (choice == unknwnvalue) {
                CorrectAns(mChoice4);
            } else {
                IncorrectAns(mChoice4);
            }
        }

    }


    public void IncorrectAns(TextView choice) {
        choice.setTextColor(Color.RED);
        life--;
        if (life == 0) {
            Toast Death = Toast.makeText(MathGame.this, "You ran out of lifes", Toast.LENGTH_SHORT);
            Death.show();
            Intent it = new Intent(MathGame.this, Categories.class);
            int oldscore = user.getMathScore();
            int newscore = user.getMathScore() + Gamescore;
            user.setMathscore(newscore);
            helper.Update_MathScore(user.getUname(), oldscore, newscore);
            it.putExtra("Users", user);
            finish();
            startActivity(it);
        } else {
            Toast wrong = Toast.makeText(MathGame.this, "Wrong Answer! " + life + " lives left!", Toast.LENGTH_SHORT);
            wrong.show();
            newQuestion(choice);

            lifes.setText(String.valueOf(life));
        }
    }

    public void CorrectAns(TextView choice) {
        Gamescore++;
        score.setText(String.valueOf(Gamescore));
        choice.setTextColor(Color.GREEN);
        newQuestion(choice);
    }

    private void newQuestion(TextView choice) {
        switch (unknown) {
            case "Operand1":
                Operand1.setText(choice.getText().toString());
                break;
            case "Operand2":
                Operand2.setText(choice.getText().toString());
                break;
            case "Result":
                Result.setText(choice.getText().toString());
                break;
        }
        final TextView choice1 = choice;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ResetAllChoices();
                choice1.setTextColor(Color.WHITE);
                lifes.setText(String.valueOf(life));
                unknown = unknownRand();
                System.out.println("Unknown :" + unknown);
                unknwnvalue = equationGen(unknown);
                System.out.println("unknown Value:" + unknwnvalue);
                ChoicesAssign(unknwnvalue, unknown);
            }
        }, 500);
        choice = choice1;
    }

    private void ResetAllChoices() {
        mChoice1.setPaintFlags(mChoice1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        mChoice2.setPaintFlags(mChoice2.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        mChoice3.setPaintFlags(mChoice3.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        mChoice4.setPaintFlags(mChoice4.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        mChoice1.setClickable(true);
        mChoice2.setClickable(true);
        mChoice3.setClickable(true);
        mChoice4.setClickable(true);
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
