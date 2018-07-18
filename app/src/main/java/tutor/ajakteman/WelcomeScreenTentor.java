package tutor.ajakteman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeScreenTentor extends AppCompatActivity {
    Button buttonRegistTentor, buttonLoginTentor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen_tentor);


        buttonRegistTentor = findViewById(R.id.BtnRegisterTentor);
        buttonLoginTentor = findViewById(R.id.BtnLoginTentor);
        buttonRegistTentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenTentor.this, RegisterTentorActivity.class));
            }
        });
        buttonLoginTentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenTentor.this, LoginActivity.class));
            }
        });
    }


}
