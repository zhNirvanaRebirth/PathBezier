package com.onektower.bezier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button quad = (Button) findViewById(R.id.quad);
        quad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quadIntent = new Intent(MainActivity.this, QuadActivity.class);
                startActivity(quadIntent);
            }
        });
    }
}
