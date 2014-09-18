package com.example.MAC;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by myroslavaleskiv on 6/25/14.
 */
public class AppsLayout extends Activity implements View.OnClickListener {

    ImageButton btnMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps);

        btnMenu = (ImageButton) findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(this);

        Toast.makeText(this, "Apps", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMenu:
                super.finish();
                break;
        }
    }
}
