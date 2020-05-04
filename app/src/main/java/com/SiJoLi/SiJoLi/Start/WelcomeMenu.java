package com.SiJoLi.SiJoLi.Start;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.SiJoLi.SiJoLi.Chatting.ui.activities.RegisterActivity;
import com.SiJoLi.SiJoLi.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeMenu extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button btnselanjutnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_menu);

        selanjutnya();

    }

    private void selanjutnya() {
        btnselanjutnya = findViewById(R.id.btn_lanjutkan);
        btnselanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(WelcomeMenu.this, LengkapiData.class));
                RegisterActivity.startActivity(WelcomeMenu.this);
                finish();
            }
        });
    }
}
