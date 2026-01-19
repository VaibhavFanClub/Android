package com.example.studentdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText fn, mn, ln, dob, ad, e;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fn = findViewById(R.id.fn);
        mn = findViewById(R.id.mn);
        ln = findViewById(R.id.ln);
        dob = findViewById(R.id.dob);
        ad = findViewById(R.id.ad);
        e = findViewById(R.id.e);

        b = findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = fn.getText().toString();
                String m = mn.getText().toString();
                String l = ln.getText().toString();
                String d = dob.getText().toString();
                String a = ad.getText().toString();
                String em = e.getText().toString();

                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                i.putExtra("f", f);
                i.putExtra("m", m);
                i.putExtra("l", l);
                i.putExtra("d", d);
                i.putExtra("a", a);
                i.putExtra("em", em);
                startActivity(i);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}