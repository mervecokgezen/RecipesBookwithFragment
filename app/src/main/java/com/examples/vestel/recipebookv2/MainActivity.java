package com.examples.vestel.recipebookv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {

    EditText edt_mail, edt_password;
    TextView tv_createac;
    Button btn_login;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private String mail, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        if (firebaseUser != null) { // daha önce kullanıcı girişi yapılmışsa tekrar giriş yapmadan anasayfadan devam et.
            for(UserInfo profile : firebaseUser.getProviderData()){
                String mail = profile.getEmail();
            }
            startActivity(new Intent(MainActivity.this, RecipesActivity.class));
        } else {
            Toast.makeText(MainActivity.this, "Kullanıcı girişi yapınız!..", Toast.LENGTH_LONG).show();
        }

        edt_mail     = (EditText)findViewById(R.id.edt_maill);
        edt_password = (EditText)findViewById(R.id.edt_passwordd);
        btn_login    = (Button)findViewById(R.id.btn_login);
        tv_createac  = (TextView)findViewById(R.id.tv_createac);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = edt_mail.getText().toString();
                pass = edt_password.getText().toString();

                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Mail veya Şifre alanı boş olamaz!..", Toast.LENGTH_LONG).show();
                }else{
                    LoginFunc(); //butona tıklanınca eğer mail ve şifre alanı boş değilse loginFunc'ı çalıştır.
                }}
        });

        tv_createac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAcActivity.class));
            }
        });
    }

    private void LoginFunc(){
        firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, RecipesActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
