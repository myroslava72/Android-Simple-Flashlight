package com.example.MAC;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by myroslavaleskiv on 6/25/14.
 */
public class SettingsLayout extends Activity implements View.OnClickListener {

    ImageButton btnMenu;
    Button btnShare;
    ToggleButton btnSound;
    Button btnLightSourceByDefault;
    Button btnLightSourceChange;
    ToggleButton btnAskOnQuit;
    Button btnPrivacyPolicy;
    ToggleButton btnScreenLightning;
    ToggleButton btnVibration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnSound = (ToggleButton) findViewById(R.id.btnSound);
        btnLightSourceByDefault = (Button) findViewById(R.id.btnLightSourceByDefault);
        btnLightSourceChange = (Button) findViewById(R.id.btnLightSourceChange);
        btnAskOnQuit = (ToggleButton) findViewById(R.id.btnAskOnQuit);
        btnPrivacyPolicy = (Button) findViewById(R.id.btnPrivacyPolicy);
        btnScreenLightning = (ToggleButton) findViewById(R.id.btnScreenLightning);
        btnVibration = (ToggleButton) findViewById(R.id.btnVibration);

        btnMenu.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        btnLightSourceByDefault.setOnClickListener(this);
        btnLightSourceChange.setOnClickListener(this);
        btnAskOnQuit.setOnClickListener(this);
        btnPrivacyPolicy.setOnClickListener(this);
        btnScreenLightning.setOnClickListener(this);
        btnVibration.setOnClickListener(this);

        registerForContextMenu(btnShare);
        registerForContextMenu(btnLightSourceByDefault);
        registerForContextMenu(btnLightSourceChange);

        Toast.makeText(this, "Установки", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMenu:
                super.finish();
                break;
            case R.id.btnShare:
            case R.id.btnSound:
            case R.id.btnLightSourceByDefault:
            case R.id.btnLightSourceChange:
            case R.id.btnAskOnQuit:
            case R.id.btnPrivacyPolicy:
            case R.id.btnScreenLightning:
            case R.id.btnVibration:
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.btnShare:
                menu.setHeaderIcon(R.drawable.smile);
                menu.setHeaderTitle("Share");
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.cm_share, menu);
                super.onCreateContextMenu(menu, v, menuInfo);
                break;
        }
    }
}
