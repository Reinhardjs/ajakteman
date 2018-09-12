package tutor.ajakteman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button btnWelcomeScreenSiswa, btnWelcomeScreenTutor;

        btnWelcomeScreenSiswa = findViewById(R.id.BtnWelcomeScreenSiswa);
        btnWelcomeScreenTutor = findViewById(R.id.BtnWelcomeScreenTentor);

        btnWelcomeScreenSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this,WelcomeScreenSiswa.class));
            }
        });
        btnWelcomeScreenTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, WelcomeScreenTentor.class));
            }
        });
    }

}
