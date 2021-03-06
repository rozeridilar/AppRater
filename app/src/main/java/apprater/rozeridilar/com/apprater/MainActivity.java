package apprater.rozeridilar.com.apprater;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.rozeridilar.apprater.*;

import static com.rozeridilar.apprater.AppRater.addButtonEffect;

public class MainActivity extends AppCompatActivity {

    private Button _btnRateYourApp;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        _btnRateYourApp = (Button) findViewById(R.id._btnRateYourApp);
        _btnRateYourApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppRater.getInstance(context).addButtonEffect(_btnRateYourApp);
                AppRater.getInstance(context).showAppRaterDialog(AppRater.getInstance(context).getRaterTitleMessage(), "someAppId");
            }
        });
    }
}