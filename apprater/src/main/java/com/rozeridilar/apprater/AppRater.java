package com.rozeridilar.apprater;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AppRater {

    private String oneStarMessage = "Naah.";
    private String twoStarMessage = "Hard to use.";
    private String threeStarMessage = "Ok.";
    private String fourStarMessage = "Good!";
    private String fiveStarMessage = "Fantastic!!";
    private String cancelButtonTitle = "cancel";
    private String sendButtonTitle = "send";
    private String raterTitleMessage = "Do you love our app?";
    private String raterStartDescription = "Ok.";

    private static Context context;

    private AppRater(){ }

    private static class Holder {
        private static final AppRater INSTANCE = new AppRater();
    }

    public static AppRater getInstance(Context context) {
        AppRater.context = context;
        return Holder.INSTANCE;
    }


    public static void addButtonEffect(View button) {
        createButtonEffect(button);
    }

    public static void addButtonEffect(View button, int color) {
        createButtonEffectWithSpecificColor(button, color);
    }

    private static void createButtonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
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

    private static void createButtonEffectWithSpecificColor(View button, final int color) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
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

    //if user gives more than 3 stars, it will direct user to play store.
    public void showAppRaterDialog(String message, String appId) {
        AppRaterDialog(message, appId);
    }

    private void animateButton(ImageButton button) {

        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        AppRaterBounceInterpolator interpolator = new AppRaterBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

    }

    private void AppRaterDialog(String message, final String appId) {
        final int[] rate = {0};
        final Dialog MyDialog;
        MyDialog = new Dialog(context);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.rater_dialog);
        MyDialog.setTitle(message);
        Button cancelButton, sendButton;

        final ImageButton image1, image2, image3, image4, image5;

        image1 = (ImageButton) MyDialog.findViewById(R.id.imageButton1);
        image2 = (ImageButton) MyDialog.findViewById(R.id.imageButton2);
        image3 = (ImageButton) MyDialog.findViewById(R.id.imageButton3);
        image4 = (ImageButton) MyDialog.findViewById(R.id.imageButton4);
        image5 = (ImageButton) MyDialog.findViewById(R.id.imageButton5);

        final TextView textViewRaterDescription = (TextView) MyDialog.findViewById(R.id.textViewRaterDescription);

        textViewRaterDescription.setText(raterStartDescription);


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
                textViewRaterDescription.setText(oneStarMessage);
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
                textViewRaterDescription.setText(twoStarMessage);
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
                textViewRaterDescription.setText(threeStarMessage);
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
                textViewRaterDescription.setText(fourStarMessage);
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
                textViewRaterDescription.setText(fiveStarMessage);
                rate[0] = 5;

            }
        });

        cancelButton = (Button) MyDialog.findViewById(R.id.cancel);
        sendButton = (Button) MyDialog.findViewById(R.id.send);

        cancelButton.setEnabled(true);
        sendButton.setEnabled(true);

        cancelButton.setText(cancelButtonTitle);
        sendButton.setText(sendButtonTitle);

        TextView raterTextView = (TextView) MyDialog.findViewById(R.id.textViewRaterMessage);
        raterTextView.setText(raterTitleMessage);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
                if (rate[0] > 3) {
                    //direct to play store
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appId));
                    int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
                    if (Build.VERSION.SDK_INT >= 21) {
                        flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
                    } else {
                        flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
                    }
                    try {
                        intent.addFlags(flags);
                        context.startActivity(intent);

                    } catch (ActivityNotFoundException e) {
                        Intent rateIntent = rateIntentForUrl(appId);
                        context.startActivity(rateIntent);
                    }

                } else {
                    showSorryMessage();
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

    public void showSorryMessage() {
        Toast.makeText(context, "We're sorry! What can we do to ensure that you love our app? We appreciate your constructive feedback. ", Toast.LENGTH_LONG).show();
    }

    public void setOneStarMessage(String oneStarMessage) {
        this.oneStarMessage = oneStarMessage;
    }

    public void setTwoStarMessage(String twoStarMessage) {
        this.twoStarMessage = twoStarMessage;
    }

    public void setThreeStarMessage(String threeStarMessage) {
        this.threeStarMessage = threeStarMessage;
    }

    public void setFourStarMessage(String fourStarMessage) {
        this.fourStarMessage = fourStarMessage;
    }

    public void setFiveStarMessage(String fiveStarMessage) {
        this.fiveStarMessage = fiveStarMessage;
    }

    public void setCancelButtonTitle(String cancelButtonTitle) {
        this.cancelButtonTitle = cancelButtonTitle;
    }

    public void setSendButtonTitle(String sendButtonTitle) {
        this.sendButtonTitle = sendButtonTitle;
    }

    public void setRaterTitleMessage(String raterTitleMessage) {
        this.raterTitleMessage = raterTitleMessage;
    }

    public void setRaterStartDescription(String raterStartDescription) {
        this.raterStartDescription = raterStartDescription;
    }

    public String getOneStarMessage() {
        return oneStarMessage;
    }

    public String getTwoStarMessage() {
        return twoStarMessage;
    }

    public String getThreeStarMessage() {
        return threeStarMessage;
    }

    public String getFourStarMessage() {
        return fourStarMessage;
    }

    public String getFiveStarMessage() {
        return fiveStarMessage;
    }

    public String getCancelButtonTitle() {
        return cancelButtonTitle;
    }

    public String getSendButtonTitle() {
        return sendButtonTitle;
    }

    public String getRaterTitleMessage() {
        return raterTitleMessage;
    }

    public String getRaterStartDescription() {
        return raterStartDescription;
    }
}
