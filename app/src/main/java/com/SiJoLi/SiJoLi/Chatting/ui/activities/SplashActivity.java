//package com.SiJoLi.SiJoLi.Chatting.ui.activities;
//
//import android.os.Bundle;
//import android.os.Handler;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.SiJoLi.SiJoLi.R;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class SplashActivity extends AppCompatActivity {
//    private static final int SPLASH_TIME_MS = 2000;
//    private Handler mHandler;
//    private Runnable mRunnable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        mHandler = new Handler();
//
//        mRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // check if user is already logged in or not
//                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//                    // if logged in redirect the user to user listing activity
//                    UserListingActivity.startActivity(SplashActivity.this);
//                } else {
//                    // otherwise redirect the user to login activity
//                    LoginActivity.startIntent(SplashActivity.this);
//                }
//                finish();
//            }
//        };
//
//        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
//    }
//
//    /*@Override
//    protected void onPause() {
//        super.onPause();
//        mHandler.removeCallbacks(mRunnable);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
//    }*/
//}
