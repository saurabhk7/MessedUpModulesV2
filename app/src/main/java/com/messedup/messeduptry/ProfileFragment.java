package com.messedup.messeduptry;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    private TextView mNameTxtView,mEmailTxtView,mContactTxtView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ProfileView=inflater.inflate(R.layout.activity_profile_fragment, container, false);

        mNameTxtView=(TextView)ProfileView.findViewById(R.id.NameTxtView);
        mEmailTxtView=(TextView)ProfileView.findViewById(R.id.EmailTxtView);
        mContactTxtView=(TextView)ProfileView.findViewById(R.id.ContactTxtView);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            int i=0;
            FirebaseUser CurrentUser=FirebaseAuth.getInstance().getCurrentUser();

            for (UserInfo profile : CurrentUser.getProviderData()) {

                i++;
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                Log.d("-----PROVIDER "+i+" ----- ","---- "+providerId+" ----");
                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();

                if(providerId.equals("google.com"))
                    Log.d("-----PROVIDER "+i+" ----- ",name);

                if(providerId.equals("google.com"))
                   mNameTxtView.setText(name);
                String email = profile.getEmail();
                Log.d("-----PROVIDER "+i+" ----- "+i,email);


                if(providerId.equals("google.com")) {


                    final Uri photoUrl = profile.getPhotoUrl();
                    final ImageView ProfilePic=(ImageView)ProfileView.findViewById(R.id.ProfilePicImg);

                    Log.d("-----PROVIDER " + i + " ----- ", photoUrl.toString());
                    try {
                        Picasso.with(inflater.getContext()).load(photoUrl).transform(new CircleTransform()).networkPolicy(NetworkPolicy.OFFLINE).into(ProfilePic, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                                Picasso.with(inflater.getContext()).load(photoUrl).transform(new CircleTransform()).into(ProfilePic);

                            }
                        });
                    } catch (Exception e) {
                        Log.v("E_VALUE", e.getMessage());
                    }
                }

                Log.d("-----PROVIDER "+i+" ----- ","----------------");


                // Log.d("PROVIDER: ",photoUrl.toString());

            };



            mEmailTxtView.setText(CurrentUser.getEmail());
            mContactTxtView.setText(CurrentUser.getPhoneNumber());


        }






        return ProfileView;


    }


}
