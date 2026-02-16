package com.example.optionmenu;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Withdraw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_withdraw);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem i){
        int itemId = i.getItemId();
        switch (itemId){
            case R.id.o1:
                Intent w = new Intent(getApplicationContext(), Withdraw.class);
                startActivity(w);
                return true;
            case R.id.o2:
                Intent d = new Intent(getApplicationContext(), Deposit.class);
                startActivity(d);
                return true;
            case R.id.o3:
                Intent b = new Intent(getApplicationContext(), Balance.class);
                startActivity(b);
                return true;
            default:
                return super.onOptionsItemSelected(i);
        }
    }
}
