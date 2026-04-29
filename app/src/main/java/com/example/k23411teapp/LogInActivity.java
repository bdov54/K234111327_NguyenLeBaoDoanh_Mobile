package com.example.k23411teapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class LogInActivity extends AppCompatActivity {

    EditText edtUserName;

    EditText edtPassword;

    TextView txtMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews(){
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        txtMessage=findViewById(R.id.txtMessage);
    }

    public void loginSystem(View view) {
        String username=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        if (username.equalsIgnoreCase("admin") && pwd.equals("123"))
        {
            Intent intent= new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            txtMessage.setText(getString(R.string.str_login_sucessful));
        }
        else{
            txtMessage.setText(getString(R.string.str_login_fail));
        }
    }

    public void exitSystem(View view) {
    }
}