package com.example.cpjjh.dots;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class GameFragment extends Fragment {
    private OnGameFragmentListener listener;

    private GameView gameView;
    private Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_game, container, false);

        gameView = (GameView) fragmentView.findViewById(R.id.game_view);
        gameView.setGame(game);
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (game.touch(event.getX(), event.getY())) {
                            listener.addScore(game.getCurrentLevel());
                        }
                        if (game.over()) {
                            listener.gameOver();
                        }
                        break;
                }
                return true;
            }
        });


        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        gameView.startAnimating();
    }

    @Override
    public void onPause() {
        super.onPause();
        gameView.stopAnimating();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameFragmentListener) {
            listener = (OnGameFragmentListener) context;
            listener.hide();

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener.show();
        listener = null;
    }

    interface OnGameFragmentListener {
        void hide();

        void show();

        void addScore(String score);

        void gameOver();
    }
}
