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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAcActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    TextView tv_backlogin;
    Button btn_signup;
    EditText edt_mail, edt_password;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        tv_backlogin = (TextView)findViewById(R.id.tv_backtologin);
        btn_signup   = (Button) findViewById(R.id.btn_signup);
        edt_mail     = (EditText)findViewById(R.id.edt_email);
        edt_password = (EditText)findViewById(R.id.edt_password);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_mail.getText().toString();
                password = edt_password.getText().toString();

                if(email.isEmpty()|| password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields!",Toast.LENGTH_SHORT).show();

                }else {
                    UserRegister(email, password);
                }
            }
        });

    }

    public void UserRegister(String memail, String mpassword){
        firebaseAuth.createUserWithEmailAndPassword(memail, mpassword).addOnCompleteListener(CreateAcActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(CreateAcActivity.this, RecipesActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
