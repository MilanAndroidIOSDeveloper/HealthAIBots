package org.botlibre.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.botlibre.sdk.R;


public class InitsActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    Button resetButton;
    Button btnSignup;
    ProgressBar progressBar;




    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        firebaseAuth = FirebaseAuth.getInstance();

        //auto login process
        //move to main activity if user already sign in
        if (firebaseAuth.getCurrentUser() != null) {
            // User is logged in
            startActivity(new Intent(InitsActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_inits);
        //ButterKnife.bind(this);

        //firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);

        password = findViewById(R.id.password);

        loginButton = findViewById(R.id.login_button);

        resetButton = findViewById(R.id.reset_button);

        btnSignup= findViewById(R.id.btn_signup);

        progressBar= findViewById(R.id.progressBar);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitsActivity.this, SignupActivity.class));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitsActivity.this, ResetActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();

                if (TextUtils.isEmpty(useremail)) {
                    Toast.makeText(InitsActivity.this.getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userpassword)) {
                    Toast.makeText(InitsActivity.this.getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //login user
                firebaseAuth.signInWithEmailAndPassword(useremail,userpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {

                                    if (userpassword.length() < 6) {
                                        password.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(InitsActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    startActivity(new Intent(InitsActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });


    }
}
