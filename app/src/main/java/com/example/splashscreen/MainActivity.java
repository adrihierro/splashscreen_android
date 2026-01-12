package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView logo1;
    Animation fade1;
    Animation fade2;
    Animation spinin;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash);

        logo1 = findViewById(R.id.TextViewTopTitle);
        fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        logo1.startAnimation(fade1);

        spinin = AnimationUtils.loadAnimation(this,R.anim.custom_anim);

        LayoutAnimationController controller = new LayoutAnimationController(spinin);

        table = (TableLayout) findViewById(R.id.TableLayout01);

        for (int i = 0; i < table.getChildCount() ; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.setLayoutAnimation(controller);
        }

        TextView logoBottom = findViewById(R.id.TextViewBottomTitle);
        fade2 = AnimationUtils.loadAnimation(this,R.anim.fade_in2);

        fade2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this,IntroActivity.class));

                MainActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //vacio
            }

            @Override
            public void onAnimationStart(Animation animation) {
                //Vacio
            }
        });

        logoBottom.startAnimation(fade2);
    }

    @Override
    protected void onPause() {
        super.onPause();

        TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
        logo1.clearAnimation();

        TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
        logo2.clearAnimation();

        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.clearAnimation();
        }
    }
}
