package tutor.ajakteman;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WelcomeScreenSiswa extends AppCompatActivity {
    private Button buttonRegister, buttonMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen_siswa);
        buttonRegister = findViewById(R.id.BtnRegisterSiswa);
        buttonMasuk = findViewById(R.id.BtnLoginSiswa);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenSiswa.this, RegisterSiswaActivity.class));
            }
        });
        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenSiswa.this, LoginActivity.class));
            }
        });
    }


}
