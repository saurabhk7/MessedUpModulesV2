package com.messedup.messeduptry;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.bitmap;
import static android.R.attr.fillColor;

public class GoogleSignIn extends AppCompatActivity {

    private SignInButton mGoogleBtn;
    private ActionProcessButton btnSignIn;
    private static  final int RC_SIGN_IN=1;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG ="GOOGLE_SIGN_IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_signin);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                {
                    Intent gotoPhoneAuthInt=new Intent(GoogleSignIn.this,MainActivity.class);
                    startActivity(gotoPhoneAuthInt);
                }

            }
        };

        mGoogleBtn=(SignInButton)findViewById(R.id.googleBtn);

        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
       // you can display endless google like progress indicator
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);


// set progress > 0 to start progress indicator animation




        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient =new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(GoogleSignIn.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSignIn.setProgress(1);
                signIn();

            }
        });





    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();

                if(FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    Log.d("ONACTRES", "FIREBASE USER IS NULL");

                }
                else
                {
                    Log.d("ONACTRES", "NOT NULL FIREBASE USER IS "+FirebaseAuth.getInstance().getCurrentUser().getUid());

                }


                Toast.makeText(GoogleSignIn.this,account.getEmail(),Toast.LENGTH_SHORT).show();

                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        final AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
       /* PhoneNumberAuthentication obj=new PhoneNumberAuthentication(credential);*/

        Log.d("GOOGLESIGNIN", "sending cred");
        Log.d("GOOGLESIGNINCREDSENT", credential.getProvider());



        Intent gotoPhoneAuthInt=new Intent(GoogleSignIn.this,PhoneNumberAuthentication.class);
        gotoPhoneAuthInt.putExtra("GoogleCredential",credential);
        startActivity(gotoPhoneAuthInt);


        /*mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(GoogleSignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });*/
    }

    private void updateUI(FirebaseUser mUser)
    {
        if(mUser==null)
        {
            Toast.makeText(GoogleSignIn.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
