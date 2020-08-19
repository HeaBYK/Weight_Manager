package com.example.weight_manager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_manager.R;
import com.example.weight_manager.getterSetter.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class ChangeActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(),
            Accounts = ref.child("Accounts"),
            Sname = Accounts.child(user.getUid()).child("wname"),
            Sbirth = Accounts.child(user.getUid()).child("wbirth"),
            Sgender = Accounts.child(user.getUid()).child("wgender"),
            Sheight = Accounts.child(user.getUid()).child("wheight"),
            Sweight = Accounts.child(user.getUid()).child("wweight"),
            Ssetting_weight = Accounts.child(user.getUid()).child("wsetting_weight");

    Button btnSave;
    EditText eName, eBirth, eGender, eHeight, eWeight, eSetting_Weight;
    TextView eAccount, Email;
    String account, name, birth, gender, height, weight, setting_weight, kcal, bmi;
    String bmiLabel = "";
    float year = Calendar.getInstance().get(Calendar.YEAR);
    String birth_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        btnSave = findViewById(R.id.btnSave);
        eName = findViewById(R.id.txt_sName);
        eBirth = findViewById(R.id.txt_sBirth);
        eGender = findViewById(R.id.txt_sGender);
        eHeight = findViewById(R.id.txt_sHeight);
        eWeight = findViewById(R.id.txt_sWeight);
        eSetting_Weight = findViewById(R.id.txt_sSetting_Weight);
        eAccount = findViewById(R.id.txt_Account);
        Email = findViewById(R.id.email);
        eAccount.setVisibility(View.INVISIBLE);

        if (user != null) {
            String email = user.getEmail();

            String[] splitText = email.split("\\.");
            StringBuilder Account = null;
            for (int i = 1; i < splitText.length; i++) {
                Account = new StringBuilder(splitText[0]);
                Account.append(splitText[i]);
            }
            eAccount.setText(Account);
            Email.setText(email);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sname.setValue(eName.getText().toString());
                Sbirth.setValue(eBirth.getText().toString());
                Sgender.setValue(eGender.getText().toString());
                Sheight.setValue(eHeight.getText().toString());
                Sweight.setValue(eWeight.getText().toString());
                Ssetting_weight.setValue(eSetting_Weight.getText().toString());

                account = eAccount.getText().toString();
                name = eName.getText().toString();
                birth = eBirth.getText().toString();
                gender = eGender.getText().toString();
                height = eHeight.getText().toString();
                weight = eWeight.getText().toString();
                setting_weight = eSetting_Weight.getText().toString();

                if (!birth.equals("")) {
                    birth_year = birth.substring(0, 4);
                    {
                        String heightStr = eHeight.getText().toString();
                        String weightStr = eWeight.getText().toString();
                        String genderStr = eGender.getText().toString();
                        String birthStr = eBirth.getText().toString();
                        {
                            //kcal
                            if (genderStr != null && !"".equals(genderStr)
                                    && weightStr != null && !"".equals(weightStr)
                                    && heightStr != null && !"".equals(heightStr)
                                    && birthStr != null && !"".equals(birthStr)) {
                                float height_kcal = Float.parseFloat(heightStr);
                                float weight_kcal = Float.parseFloat(weightStr);
                                float birth_kcal = Float.parseFloat(birth_year);

                                double result_mkcal = (66.47 + (13.75 * weight_kcal) + (5 * height_kcal) - (6.76 * (year - birth_kcal + 1)));
                                double result_wkcal = (655.1 + (9.56 * weight_kcal) + (1.85 * height_kcal) - (4.68 * (year - birth_kcal + 1)));

                                displaykcal(result_mkcal, result_wkcal);
                            }
                        }
                        {
                            //bmi
                            if (heightStr != null && !"".equals(heightStr)
                                    && weightStr != null && !"".equals(weightStr)) {
                                float heightValue = Float.parseFloat(heightStr) / 100;
                                float weightValue = Float.parseFloat(weightStr);

                                float result = weightValue / (heightValue * heightValue);

                                displayBMI(result);
                            }
                        }
                    } //kcal, bmi
                }
                HashMap result = new HashMap<>();
                result.put("Account", account);
                result.put("Name", name);
                result.put("Birth", birth);
                result.put("Gender", gender);
                result.put("Height", height);
                result.put("Weight", weight);
                result.put("Setting_Weight", setting_weight);

                writeNewUser(user.getUid(), account, name, birth, gender, height, weight, setting_weight, kcal, bmi);
                System.out.println(kcal);
            }
        });
        {
            Sname.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wname = snapshot.getValue(String.class);
                    eName.setText(wname);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sbirth.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wbirth = snapshot.getValue(String.class);
                    eBirth.setText(wbirth);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sgender.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wgender = snapshot.getValue(String.class);
                    eGender.setText(wgender);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sheight.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wheight = snapshot.getValue(String.class);
                    eHeight.setText(wheight);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sweight.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wweight = snapshot.getValue(String.class);
                    eWeight.setText(wweight);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Ssetting_weight.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wsetting_weight = snapshot.getValue(String.class);
                    eSetting_Weight.setText(wsetting_weight);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void displaykcal(Double result_mkcal, Double result_wkcal){
        String str_mkcal = String.format("%.2f", result_mkcal);
        String str_wkcal = String.format("%.2f", result_wkcal);

        if(gender.equals("남")) {
            kcal = str_mkcal;
        }else if (gender.equals("여")){
            kcal = str_wkcal;
        }
    }

    private void displayBMI(float result) {

        if (Float.compare(result, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(result, 18.5f) > 0 && Float.compare(result, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(result, 25f) > 0 && Float.compare(result, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(result, 30f) > 0 && Float.compare(result, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(result, 35f) > 0 && Float.compare(result, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }
        String str_bmi = String.format("%.2f", result);

        bmiLabel = "BMI 수치 : " + str_bmi + "\n" + bmiLabel;
        bmi = bmiLabel;
    }

    private void writeNewUser (String Uid, String waccount, String wname, String wbirth, String
            wgender, String wheight, String wweight, String wsetting_weight, String wkcal, String wbmi){
        User Account = new User(waccount, wname, wbirth, wgender, wheight, wweight, wsetting_weight, wkcal, wbmi);

        ref.child("Accounts").child(Uid).setValue(Account)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}