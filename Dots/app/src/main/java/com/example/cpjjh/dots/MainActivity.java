package com.example.cpjjh.dots;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GameFragment.OnGameFragmentListener {

    private FragmentManager fragmentManager;
    private ActionBar actionBar;
    private TextView play;
    private ScoresDAOHelper scoresDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        actionBar = getSupportActionBar();
        play = (TextView) findViewById(R.id.play);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        scoresDAO = new ScoresDAOHelper(this);
        updateScore();
    }

    private void updateScore() {
        Cursor cursor = scoresDAO.getReadableDatabase()
                .rawQuery("select * from scores", null);
        StringBuilder builder = new StringBuilder();
        builder.append("scores: ");
        while (cursor.moveToNext()) {
            System.out.println("id: " + cursor.getString(0)
                    + " value: " + cursor.getString(1));
            builder.append(cursor.getString(1)).append(" ");
        }
        cursor.close();

        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(builder.toString().trim());
    }

    @Override
    protected void onDestroy() {
        scoresDAO.close();
        super.onDestroy();
    }

    public void play(View view) {
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_parent, new GameFragment())
                .addToBackStack("Game")
                .commit();
    }

    @Override
    public void hide() {
        if (actionBar != null) {
            actionBar.hide();
            play.setAlpha(0);
        }
    }

    @Override
    public void show() {
        if (actionBar != null) {
            actionBar.show();
            play.setAlpha(1);
        }
    }

    @Override
    public void addScore(String score) {
        SQLiteDatabase db = scoresDAO.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", score);
        db.insert("scores", null, contentValues);

        updateScore();
    }

    @Override
    public void gameOver() {
        System.out.println("yay!!!!");
    }
}
