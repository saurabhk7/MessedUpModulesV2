package com.messedup.messeduptry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChooserActivity extends AppCompatActivity {

    Button PhoneAuthWithoutUIBtn,PhoneAuthWithUIBtn,toMainActBtn,GoogleSignBtn ,LoadBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);



        PhoneAuthWithoutUIBtn=(Button)findViewById(R.id.PhoneAuthWithoutUIBtn);
        LoadBtn=(Button)findViewById(R.id.LoadinBtn);
        PhoneAuthWithUIBtn=(Button)findViewById(R.id.PhoneAuthWithUIBtn);
        toMainActBtn=(Button)findViewById(R.id.MainActBtn);
        GoogleSignBtn=(Button)findViewById(R.id.GoogleSignBtn);


        LoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(ChooserActivity.this,CardViewActivity.class));

            }
        });

        GoogleSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toGoogleSignInInt=new Intent(ChooserActivity.this,SplashScreen.class);
                startActivity(toGoogleSignInInt);
            }
        });



        toMainActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainActInt=new Intent(ChooserActivity.this,MainActivity.class);
                startActivity(toMainActInt);
            }
        });


        PhoneAuthWithoutUIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoPhoneAuthInt=new Intent(ChooserActivity.this,PhoneAuthActivity.class);
                startActivity(gotoPhoneAuthInt);
            }
        });
        PhoneAuthWithUIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoPhoneAuthUIInt=new Intent(ChooserActivity.this,PhoneNumberAuthentication.class);
                startActivity(gotoPhoneAuthUIInt);
            }
        });


                startIntro();

    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void startIntro()
    {
       TapTargetSequence sequence= new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.MainActBtn), "Main Activity Button", "Tap to go to Main Activity!\n(Skip Sign In :P)")
                                .outerCircleColor(R.color.colorPrimary)      // Specify a color for the outer circle
                                .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                                .targetCircleColor(R.color.white)   // Specify a color for the target circle
                                .titleTextSize(20)                  // Specify the size (in sp) of the title text
                                .titleTextColor(R.color.white)      // Specify the color of the title text
                                .descriptionTextSize(18)            // Specify the size (in sp) of the description text
                                .descriptionTextColor(R.color.blue)  // Specify the color of the description text
                                .textColor(R.color.white)            // Specify a color for both the title and description text
                                .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                                .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(false)                   // Whether to tint the target view's color
                                .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                                // .icon(Drawable)                     // Specify a custom drawable to draw as the target
                                .targetRadius(65) ,                 // Specify the target radius (in dp),


                        TapTarget.forView(findViewById(R.id.PhoneAuthWithUIBtn), "Phone Auth with Firebase UI", "Tap to see Firebase Phone Auth UI!")
                                .outerCircleColor(R.color.colorPrimary)      // Specify a color for the outer circle
                                .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                                .targetCircleColor(R.color.white)   // Specify a color for the target circle
                                .titleTextSize(20)                  // Specify the size (in sp) of the title text
                                .titleTextColor(R.color.white)      // Specify the color of the title text
                                .descriptionTextSize(18)            // Specify the size (in sp) of the description text
                                .descriptionTextColor(R.color.blue)  // Specify the color of the description text
                                .textColor(R.color.white)            // Specify a color for both the title and description text
                                .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                                .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(false)                   // Whether to tint the target view's color
                                .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                                // .icon(Drawable)                     // Specify a custom drawable to draw as the target
                                .targetRadius(90)  ,                // Specify the target radius (in dp)
                        TapTarget.forView(findViewById(R.id.GoogleSignBtn), "Google Sign In", "Tap to Sign In with your Google Account and Phone Verification! \n(finalised)")
                                .outerCircleColor(R.color.colorPrimary)      // Specify a color for the outer circle
                                .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                                .targetCircleColor(R.color.white)   // Specify a color for the target circle
                                .titleTextSize(20)                  // Specify the size (in sp) of the title text
                                .titleTextColor(R.color.white)      // Specify the color of the title text
                                .descriptionTextSize(18)            // Specify the size (in sp) of the description text
                                .descriptionTextColor(R.color.blue)  // Specify the color of the description text
                                .textColor(R.color.white)            // Specify a color for both the title and description text
                                .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                                .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(false)                   // Whether to tint the target view's color
                                .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                                // .icon(Drawable)                     // Specify a custom drawable to draw as the target
                                .targetRadius(70)                 // Specify the target radius (in dp),


                )
               .listener(new TapTargetSequence.Listener() {
                   // This listener will tell us when interesting(tm) events happen in regards
                   // to the sequence
                   @Override
                   public void onSequenceFinish() {

                   }

                   @Override
                   public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                   }


                   @Override
                   public void onSequenceCanceled(TapTarget lastTarget) {
                       // Boo
                   }
               });

        sequence.start();

    }
}
