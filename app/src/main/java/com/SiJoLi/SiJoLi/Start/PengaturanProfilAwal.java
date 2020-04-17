package com.SiJoLi.SiJoLi.Start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.florescu.android.rangeseekbar.RangeSeekBar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.bendik.simplerangeview.SimpleRangeView;

public class PengaturanProfilAwal extends AppCompatActivity {

    TextView tvjarak;
    SeekBar sbjarak;
    FirebaseAuth auth;
    String progressjarak;
    String booleanswitch;
    String gender;
    Spinner spinner;
    int maksimumumur, minimumumur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_profil);

        tvjarak = findViewById(R.id.textView7);
        sbjarak= findViewById(R.id.sb_jarak);
        spinner = findViewById(R.id.spin_kelamin);

        auth = FirebaseAuth.getInstance();

        seekjarak();
        spinnerkelamin();
//        rangeseekbar();
        simplerangebar();
        switchbutton();
//        genderspinner();
        btnclick();

    }



    private void switchbutton() {
        SwitchCompat sc;
        sc = findViewById(R.id.switchcompat);
        if (sc.isEnabled()){
            booleanswitch = "1";
        }else{
            booleanswitch ="0";
        }

    }

    private void btnclick() {
        Button simpan=findViewById(R.id.btn_simpanpengaturan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = auth.getCurrentUser();
                String uid = currentUser.getUid();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserAccount");

//                myRef.child(uid).child("UserID").setValue(user);
                myRef.child(uid).child("Pengaturan").child("Jarak").setValue(tvjarak.getText().toString());
                myRef.child(uid).child("Pengaturan").child("Otomatiscakupan").setValue(booleanswitch);
                myRef.child(uid).child("Pengaturan").child("Tampilkan").setValue(spinner.getSelectedItem());
                myRef.child(uid).child("Pengaturan").child("Usiaminimum").setValue(minimumumur);
                myRef.child(uid).child("Pengaturan").child("Usiamaksimum").setValue(maksimumumur);

Toast.makeText(PengaturanProfilAwal.this,"Pengatran jarak: "+tvjarak.getText().toString(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(PengaturanProfilAwal.this, TambahkanFoto.class);
                startActivity(i);



            }
        });
    }

    private void simplerangebar() {

        SimpleRangeView rangebar;
        final TextView tv;
        tv = findViewById(R.id.tv_usia);
        rangebar = findViewById(R.id.range_bar);
        rangebar.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i, int i1) {
                int a = i+18;
                int b=  i1+18;
//                int c =
                if (b==67){
                    tv.setText(String.valueOf(a)+"-"+String.valueOf(b)+"+");
                }else {
                    tv.setText(String.valueOf(a)+"-"+String.valueOf(b));
                }

                minimumumur = a;
                maksimumumur = b;

            }
        });

        rangebar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                int a = i+18;
                tv.setText(String.valueOf(a));
            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                int a = i+18;
                tv.setText(String.valueOf(a));

            }
        });

        rangebar.setOnRangeLabelsListener(new SimpleRangeView.OnRangeLabelsListener() {
            @Nullable
            @Override
            public String getLabelTextForPosition(@NotNull SimpleRangeView simpleRangeView, int i, @NotNull SimpleRangeView.State state) {
                return null;
            }
        });

    }


//    private void rangeseekbar() {
//        RangeSeekBar rangeSeekBar = findViewById(R.id.rangeseek);
//        int thumbNormal = R.drawable.icongoogle;
//        rangeSeekBar.setSelectedMaxValue(50);
//        rangeSeekBar.setSelectedMinValue(18);
//        rangeSeekBar.setImageDrawable(R.drawable.iconfacebook);
//        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
//                Number min_value = bar.getSelectedMinValue();
//                Number max_value = bar.getSelectedMaxValue();
//
//                int min=(int)min_value;
//                int max=(int)max_value;
//
//
//                Toast.makeText(getApplicationContext(),"Min="+min+"\n"+"Max="+max,Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    private void spinnerkelamin() {
        Spinner staticSpinner = findViewById(R.id.spin_kelamin);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
                        android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

//        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spin_kelamin2);
//        String[] items = new String[] { "Pria", "Wanita" };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, items);
//        dynamicSpinner.setAdapter(adapter);
//        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.v("item", (String) parent.getItemAtPosition(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });
    }

    private void seekjarak() {

        tvjarak.setText(sbjarak.getProgress() + "/" + sbjarak.getMax());
        sbjarak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
                progressjarak = String.valueOf(pval);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                tvjarak.setText(pval + "/" + seekBar.getMax());
                tvjarak.setText(pval+" km");
            }
        });
    }

}

