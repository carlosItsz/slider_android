package com.carloscampos.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);


        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        imageSwitcher.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length)
                            posicion = 0;
                    }
                });
            }
        }, 0, DURACION);
    }

    private ImageSwitcher imageSwitcher;
    private int[] galeria = { R.drawable.portada, R.drawable.portada2, R.drawable.portada3 };
    private int posicion;
    private static final int DURACION = 9000;
    private Timer timer = null;
}


//https://uberbits.wordpress.com/2017/04/04/tutorial-como-crear-un-slider-con-imageswitcher-en-android-studio/