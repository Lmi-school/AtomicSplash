package com.example.tester.atomicsplash;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView current;
    int curPic = 0, lvl = 0;
    ArrayList<Integer> draws = new ArrayList<Integer>();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        draws.add(R.drawable.circle_button);
        draws.add(R.drawable.square_button);
        draws.add(R.drawable.triangle_button);
        draws.add(R.drawable.rhombus_button);
        ids.add(R.id.iv0); ids.add(R.id.iv1); ids.add(R.id.iv2); ids.add(R.id.iv3); ids.add(R.id.iv4); ids.add(R.id.iv5); ids.add(R.id.iv6);

        setContentView(R.layout.activity_main);

        current = (ImageView)findViewById(R.id.current);
        ViewAnimator
            .animate(current)
            .repeatCount(2000000000)
            .translationX(0, -1000)
            .duration(1000)
            .onStop(changeState())
            .start();

        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickCircle(View view) {
        if (curPic != 0 || !isPointInsideView(current.getX(), current.getY(), (ImageView)findViewById(R.id.imageView2))) addState();
    }

    public void clickSquare(View view) {
        if (curPic != 1 || !isPointInsideView(current.getX(), current.getY(), (ImageView)findViewById(R.id.imageView2))) addState();
    }

    public void clickTriangle(View view) {
        if (curPic != 2 || !isPointInsideView(current.getX(), current.getY(), (ImageView)findViewById(R.id.imageView2))) addState();
    }

    public void clickRhombus(View view) {
        if (curPic != 3 || !isPointInsideView(current.getX(), current.getY(), (ImageView)findViewById(R.id.imageView2))) addState();
    }

    public AnimationListener.Stop changeState() {
//        Log.e("ASD", "HASHD0");
        return new AnimationListener.Stop() {
            @Override
            public void onStop() {
                curPic = rand.nextInt(4);
                current.setBackground(getResources().getDrawable(draws.get(curPic)));
            }
        };
    }

    public static boolean isPointInsideView(float x, float y, View view){
        Log.e("ASD", "HASHD0");
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        if(Math.hypot(view.getX() - x, view.getY() - y) < 100){
            return true;
        } else {
            return false;
        }
    }

    public void addState(){

        ImageView img = (ImageView)findViewById(ids.get(lvl));
        img.setBackground(getResources().getDrawable(R.color.colorAccent));
        lvl++;
    }
}
