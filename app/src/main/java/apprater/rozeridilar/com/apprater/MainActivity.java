package apprater.rozeridilar.com.apprater;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button _btnRateYourApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btnRateYourApp = (Button) findViewById(R.id._btnRateYourApp);

        _btnRateYourApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonEffect(_btnRateYourApp);
                showDialog();
            }
        });
    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    private void showDialog() {
        MyCustomAlertDialog();
    }

    public void animateButton(ImageButton button) {

        final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

    }

    public void MyCustomAlertDialog(){
        final int[] rate = {0};
        final Dialog MyDialog;
        MyDialog = new Dialog(MainActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.rater_dialog);
        MyDialog.setTitle("Uygulamamız Hakkında Ne Düşünüyorsunuz?");
        Button hello,close;

        final ImageButton image1, image2, image3, image4, image5;

        image1 = (ImageButton) MyDialog.findViewById(R.id.imageButton1);
        image2 = (ImageButton) MyDialog.findViewById(R.id.imageButton2);
        image3 = (ImageButton) MyDialog.findViewById(R.id.imageButton3);
        image4 = (ImageButton) MyDialog.findViewById(R.id.imageButton4);
        image5 = (ImageButton) MyDialog.findViewById(R.id.imageButton5);

        final TextView textViewRaterDescription = (TextView) MyDialog.findViewById(R.id.textViewRaterDescription);

        image1.setImageResource(R.drawable.filled_r);
        image2.setImageResource(R.drawable.filled_r);
        image3.setImageResource(R.drawable.filled_r);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setImageResource(R.drawable.filled_r);
                image2.setImageResource(R.drawable.empty_r);
                image3.setImageResource(R.drawable.empty_r);
                image4.setImageResource(R.drawable.empty_r);
                image5.setImageResource(R.drawable.empty_r);
                animateButton(image1);
                textViewRaterDescription.setText("Beğenmedim.");
                rate[0] = 1;
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setImageResource(R.drawable.filled_r);
                image2.setImageResource(R.drawable.filled_r);
                image3.setImageResource(R.drawable.empty_r);
                image4.setImageResource(R.drawable.empty_r);
                image5.setImageResource(R.drawable.empty_r);
                animateButton(image2);
                textViewRaterDescription.setText("Kullanımı zor.");
                rate[0] = 2;
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setImageResource(R.drawable.filled_r);
                image2.setImageResource(R.drawable.filled_r);
                image3.setImageResource(R.drawable.filled_r);
                image4.setImageResource(R.drawable.empty_r);
                image5.setImageResource(R.drawable.empty_r);
                animateButton(image3);
                textViewRaterDescription.setText("Eksikleri var.");
                rate[0] = 3;
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setImageResource(R.drawable.filled_r);
                image2.setImageResource(R.drawable.filled_r);
                image3.setImageResource(R.drawable.filled_r);
                image4.setImageResource(R.drawable.filled_r);
                image5.setImageResource(R.drawable.empty_r);
                animateButton(image4);
                textViewRaterDescription.setText("Güzel!");
                rate[0] = 4;
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setImageResource(R.drawable.filled_r);
                image2.setImageResource(R.drawable.filled_r);
                image3.setImageResource(R.drawable.filled_r);
                image4.setImageResource(R.drawable.filled_r);
                image5.setImageResource(R.drawable.filled_r);
                animateButton(image5);
                textViewRaterDescription.setText("Harika!!");
                rate[0] = 5;

            }
        });

        hello = (Button)MyDialog.findViewById(R.id.hello);
        close = (Button)MyDialog.findViewById(R.id.close);

        hello.setEnabled(true);
        close.setEnabled(true);

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Toast.makeText(getContext(), "tesekkur metni ", Toast.LENGTH_LONG).show();
                MyDialog.cancel();

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
                if(rate[0]>3){
                    //direct to play store
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "APP_NAME"));
                    int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
                    if (Build.VERSION.SDK_INT >= 21) {
                        flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
                    } else {
                        //noinspection deprecation
                        flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
                    }
                    try {
                        intent.addFlags(flags);
                        startActivity(intent);

                    } catch (ActivityNotFoundException e) {
                        Intent rateIntent = rateIntentForUrl("YOUR_APP_ID");
                        startActivity(rateIntent);
                    }

                } else {
                    //direct to other
                }

            }
        });

        MyDialog.show();
    }
    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, "")));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

}
class MyBounceInterpolator implements android.view.animation.Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 3;

    public MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}