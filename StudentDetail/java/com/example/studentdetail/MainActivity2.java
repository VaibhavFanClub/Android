package com.example.studentdetail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView fn, mn, ln, dob, ad, e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        fn = findViewById(R.id.fn);
        mn = findViewById(R.id.mn);
        ln = findViewById(R.id.ln);
        dob = findViewById(R.id.dob);
        ad = findViewById(R.id.ad);
        e = findViewById(R.id.e);

        Intent i = getIntent();
        String f = i.getStringExtra("f");
        String l = i.getStringExtra("l");
        String m = i.getStringExtra("m");
        String d = i.getStringExtra("d");
        String a = i.getStringExtra("a");
        String em = i.getStringExtra("em");

        fn.setText(f);
        mn.setText(m);
        ln.setText(l);
        dob.setText(d);
        ad.setText(a);
        e.setText(em);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}