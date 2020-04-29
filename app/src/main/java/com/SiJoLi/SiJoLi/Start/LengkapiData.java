package com.SiJoLi.SiJoLi.Start;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LengkapiData extends AppCompatActivity {

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Button btn_get_datetime, btn_lanjutkanpengaturan;
    EditText et_nama, tgl, et_pekerjaan, et_agama, et_status, et_alamat;
    RadioButton rb_pria, rb_wanita;
    FirebaseAuth auth;
    RadioGroup rg;
    String jeniskelamin;
    Spinner spinner;
    int ds,ms,de,me;
    String ageS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_data);

        auth = FirebaseAuth.getInstance();

        et_nama = findViewById(R.id.et_nama);
        et_pekerjaan = findViewById(R.id.et_pekerjaan);
        et_agama = findViewById(R.id.et_agama);
        et_status = findViewById(R.id.et_status);
        et_alamat = findViewById(R.id.et_alamat);
        rb_pria = findViewById(R.id.rb_pria);
        rb_wanita = findViewById(R.id.rb_wanita);
        rg = findViewById(R.id.rg_jeniskelamin);
        btn_lanjutkanpengaturan = findViewById(R.id.btn_lanjutkanpengaturan);
        spinner = findViewById(R.id.spin_zodiak);

        if (rb_pria.isChecked()){
            jeniskelamin="Pria";
        }else if (rb_wanita.isChecked()){
            jeniskelamin="Wanita";
        }

        tgl = (EditText) findViewById(R.id.et_tgllahir);
//        txt_jam = (EditText) findViewById(R.id.txt_jam);
//        btn_get_datetime = (Button) findViewById(R.id.btn_get_datetime);

        datetimepicker();
        spinnerzodiak();

        btnclick();
    }

    private void spinnerzodiak() {
        Spinner staticSpinner = findViewById(R.id.spin_zodiak);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.zodiak_array,
                        android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

    }

    private void btnclick() {
        btn_lanjutkanpengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser currentUser = auth.getCurrentUser();
                String uid = currentUser.getUid();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserAccount");

//                myRef.child(uid).child("UserID").setValue(user);
                myRef.child(uid).child("Nama").setValue(et_nama.getText().toString());
                myRef.child(uid).child("TanggalLahir").setValue(tgl.getText().toString());
                myRef.child(uid).child("JenisKelamin").setValue(jeniskelamin);
                myRef.child(uid).child("Pekerjaan").setValue(et_pekerjaan.getText().toString());
                myRef.child(uid).child("Agama").setValue(et_agama.getText().toString());
                myRef.child(uid).child("Status").setValue(et_status.getText().toString());
                myRef.child(uid).child("Alamat").setValue(et_alamat.getText().toString());
                myRef.child(uid).child("Zodiak").setValue(spinner.getSelectedItem().toString());
                myRef.child(uid).child("Umur").setValue(ageS);

                Intent i = new Intent(LengkapiData.this, MapsPermission.class);
                startActivity(i);

            }
        });
    }

    private void datetimepicker() {
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };


        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(LengkapiData.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tgl.setText(sdf.format(myCalendar.getTime()));

        Calendar dob = myCalendar;
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
      ageS = ageInt.toString();
      Toast.makeText(LengkapiData.this,"Age: "+ageS,Toast.LENGTH_LONG).show();

    }
}

