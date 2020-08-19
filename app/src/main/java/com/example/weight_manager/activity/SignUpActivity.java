package com.example.weight_manager.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.weight_manager.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword, editTextPassword2;
    private TextView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        check = findViewById(R.id.password_Check);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextPassword = (EditText) findViewById(R.id.editText_passWord);
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!editTextPassword.getText().toString().equals(editTextPassword2.getText().toString())) {
                    // 두번 입력한 비밀번호가 서로 일치하지 않을 경우
                    check.setText("비밀번호가 일치하지 않습니다.");
                }
                else{
                    check.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPassword2 = (EditText) findViewById(R.id.editText_passWord2);
        editTextPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!editTextPassword.getText().toString().equals(editTextPassword2.getText().toString())) {
                    // 두번 입력한 비밀번호가 서로 일치하지 않을 경우
                    check.setText("비밀번호가 일치하지 않습니다.");
                }
                else{
                    check.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_join:
                if (!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")
                        && editTextPassword.getText().toString().equals(editTextPassword2.getText().toString())) {
                    // 이메일과 비밀번호가 공백이 아니고 두번 입력한 비밀번호가 일치할 경우 경우
                    createUser(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                } else if (!editTextPassword.getText().toString().equals(editTextPassword2.getText().toString())) {
                    // 두번 입력한 비밀번호가 서로 일치하지 않을 경우
                    Toast.makeText(SignUpActivity.this,"비밀번호를 다시 확인해주세요", Toast.LENGTH_LONG).show();
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(SignUpActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공시
                            Toast.makeText(SignUpActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // 계정이 중복된 경우
                            Toast.makeText(SignUpActivity.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}