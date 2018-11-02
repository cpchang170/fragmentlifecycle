package com.askey.hahow.atm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    private EditText eduserid;
    private EditText edpwd;

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eduserid = findViewById(R.id.ed_account);
        edpwd = findViewById(R.id.ed_pwd);
    }
    public void login_function(View v) {
        String useridstr = eduserid.getText().toString();
        String pwdstr = edpwd.getText().toString();
        FirebaseDatabase.getInstance().getReference("usr").child(useridstr).child("passwd")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();
                        if (pw.equals("1234")) {
                            setResult(RESULT_OK);
                            finish();
                        }
                        else{
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("login result")
                                    .setMessage("login fail")
                                    .setPositiveButton("OK",null)
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        //if ("jack".equals(useridstr) && "1234".equals(pwdstr)){
        //    setResult(RESULT_OK);
        //    finish();
        //}
    }
}
