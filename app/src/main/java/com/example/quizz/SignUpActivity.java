package com.example.quizz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout mNameTextInputLayout;
    EditText mNameEditTextView;

    TextInputLayout mEmailTextInputLayout;
    EditText mEmailEditTextView;

    TextInputLayout mPasswordTextInputLayout;
    EditText mPasswordEditTextView;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    AppCompatButton mAppCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mAppCompatButton = (AppCompatButton) findViewById(R.id.registerAppCompatButton);

        mNameTextInputLayout = (TextInputLayout) findViewById(R.id.nameTextInputLayout);
        mNameEditTextView = (EditText) findViewById(R.id.nameEditTextView);

        mEmailTextInputLayout = (TextInputLayout) findViewById(R.id.emailLoginTextInputLayout);
        mEmailEditTextView = (EditText) findViewById(R.id.emailLoginEditTextView);

        mPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordLoginTextInputLayout);
        mPasswordEditTextView = (EditText) findViewById(R.id.passwordLoginEditTextView);


        mAppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditTextView.getText().toString().trim();
                String password = mPasswordEditTextView.getText().toString().trim();
                mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        else
                            Toast.makeText(SignUpActivity.this,"The Sign Up has failed!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
