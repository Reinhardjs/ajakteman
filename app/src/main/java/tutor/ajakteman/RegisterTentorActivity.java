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

import tutor.ajakteman.data.Tentor;

public class RegisterTentorActivity extends AppCompatActivity {
    private EditText editTextNama, editTextAlamat, editTextEmail, editTextPass, editTextDesk, editTextPengalaman1, editTextPengalaman2, editTextPendidikan1, editTextPendidikan2,editTextSkill, editTextSkill2;
    private Button buttonRegister;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tentor);
        editTextNama = findViewById(R.id.ETRegisterTutorNama);
        editTextAlamat = findViewById(R.id.ETRegisterTutorAlamat);
        editTextEmail = findViewById(R.id.ETRegisterTutorEmail);
        editTextPass = findViewById(R.id.ETRegisterTutorPassword);
        editTextDesk = findViewById(R.id.ETRegisterTutorDeskripsi);
        editTextPengalaman1 = findViewById(R.id.ETRegisterTutorPengalaman1);
        editTextPengalaman2 = findViewById(R.id.ETRegisterTutorPengalaman2);
        editTextPendidikan1 = findViewById(R.id.ETRegisterTutorPendidikan1);
        editTextPendidikan2 = findViewById(R.id.ETRegisterTutorPendidikan2);
        editTextSkill = findViewById(R.id.ETRegisterTutorSkill1);
        editTextSkill2 = findViewById(R.id.ETRegisterTutorSkill2);
        buttonRegister = findViewById(R.id.BtnRegisterTutor);

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
        String email = editTextEmail.getText().toString().trim() ,
                password = editTextPass.getText().toString().trim(),
                nama = editTextNama.getText().toString().trim(),
                alamat = editTextAlamat.getText().toString().trim(),
                deskripsi = editTextDesk.getText().toString().trim(),
                pengalaman1 = editTextPengalaman1.getText().toString().trim(),
                pengalaman2 = editTextPengalaman2.getText().toString().trim(),
                pendidikan1 = editTextPendidikan1.getText().toString().trim(),
                pendidikan2 = editTextPendidikan2.getText().toString().trim(),
                skill1 = editTextSkill.getText().toString().trim(),
                skill2 = editTextSkill2.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nama)){
            Toast.makeText(this,"Name can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(alamat)){
            Toast.makeText(this,"Alamat can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(deskripsi)){
            Toast.makeText(this,"Deskripsi can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pengalaman1)){
            Toast.makeText(this,"Pengalaman can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pendidikan1)){
            Toast.makeText(this,"Pendidikan can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(skill1)){
            Toast.makeText(this,"Skill can not be Empty",Toast.LENGTH_SHORT).show();
            return;
        }

        final Tentor tentor = new Tentor(nama, alamat,deskripsi,pengalaman1,pengalaman2,pendidikan1,pendidikan2,skill1,skill2);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterTentorActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Log in");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
                    databaseReference.child(firebaseUser.getUid()).setValue(tentor);
                    startActivity(new Intent(getApplicationContext(),AfterTentorRegistrationActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password can not be Empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
