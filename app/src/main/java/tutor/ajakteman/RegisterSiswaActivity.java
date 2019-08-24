package tutor.ajakteman;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tutor.ajakteman.data.Siswa;

public class RegisterSiswaActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText nama, alamat, email, kataSandi, jenjangPendidikan;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_siswa);

        buttonRegister = findViewById(R.id.BtnBuatAkun);
        nama = findViewById(R.id.ETNamaRegister);
        alamat = findViewById(R.id.ETAlamatRegister);
        email = findViewById(R.id.ETEmailRegister);
        kataSandi = findViewById(R.id.ETPassRegister);
        jenjangPendidikan = findViewById(R.id.ETJenjangPendRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        final String email = this.email.getText().toString().trim() ,
                password = this.kataSandi.getText().toString().trim(),
                nama = this.nama.getText().toString().trim(),
                alamat = this.alamat.getText().toString().trim(),
                jenjang = this.jenjangPendidikan.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nama)){
            Toast.makeText(this,"Nama can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(alamat)){
            Toast.makeText(this,"Alamat can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(jenjang)){
            Toast.makeText(this,"Jenjang can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(RegisterSiswaActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                    String userID = firebaseUser.getUid();

                    Siswa siswa = new Siswa(userID, nama, alamat, jenjang);

                    databaseReference.child("users").child("siswa").child(userID).setValue(siswa);

                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Creating account Error! Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
