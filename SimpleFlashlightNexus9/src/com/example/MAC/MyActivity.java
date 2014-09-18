package com.example.MAC;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import java.util.Random;

public class MyActivity extends Activity implements View.OnClickListener, View.OnTouchListener {

    ImageButton btnFlash;
    ImageButton btnMenu;
    ImageButton btnGoMain;
    ImageButton btnGoTrafficLights;
    ImageButton btnGoLightbulb;
    ImageButton btnGoPalette;
    ImageButton btnGoPolice;
    ImageButton btnGoSettings;
    ImageButton btnGoDownloads;
    ImageView trafficLightsTop;
    ImageView trafficLightsBottom;
    ImageView lightbulbTop;
    ImageView lightbulbBottom;
    ImageView lightbulbLit;
    ImageView policeLayout;

    AnimationDrawable trafficLightsTopAnim;
    AnimationDrawable trafficLightsBottomAnim;
    AnimationDrawable policeLayoutAnim;

    RelativeLayout main;

    boolean btnFlashFlag;
    boolean btnMenuFlag;
    boolean btnGoMainFlag = true;
    boolean btnGoTrafficLightsFlag;
    boolean btnGoLightbulbFlag;
    boolean btnGoPaletteFlag;
    boolean btnGoPoliceFlag;
    boolean btnGoSettingsFlag;
    boolean btnGoDownloadsFlag;

    int layoutColor;
    int layoutColorNumber;

    int xCurrent;
    int xAfterMove;
    int dx;
    int yCurrent;
    int yAfterMove;
    int dy;

    int lightbulbLayoutColor;
    int paletteLayoutColor;

    int[] colors = {R.color.LightSlateGray, R.color.SteelBlue, R.color.BlueJay, R.color.MidnightBlue,
            R.color.NavyBlue, R.color.CornflowerBlue, R.color.CobaltBlue, R.color.BlueberryBlue, R.color.BlueOrchid,
            R.color.OceanBlue, R.color.SeaGreen, R.color.HazelGreen, R.color.VenomGreen, R.color.FernGreen,
            R.color.SeaweedGreen, R.color.JungleGreen, R.color.SpringGreen, R.color.LimeGreen, R.color.GreenSnake,
            R.color.AlienGreen, R.color.Green, R.color.EmeraldGreen, R.color.Chartreuse, R.color.SlimeGreen,
            R.color.RubberDuckyYellow, R.color.Saffron, R.color.Beer, R.color.Gold, R.color.Caramel, R.color.Bronze,
            R.color.Cinnamon, R.color.PumpkinOrange, R.color.ConstructionConeOrange, R.color.DarkOrange,
            R.color.BasketBallOrange, R.color.LightCoral, R.color.ShockingOrange, R.color.Red, R.color.Scarlet,
            R.color.FerrariRed, R.color.FireEngineRed, R.color.LavaRed, R.color.LoveRed, R.color.Grapefruit,
            R.color.ChestnutRed, R.color.Mahogany, R.color.ChilliPepper, R.color.Cranberry, R.color.RedWine,
            R.color.Burgundy, R.color.Firebrick, R.color.Maroon, R.color.PlumPie, R.color.PlumVelvet, R.color.VioletRed,
            R.color.DeepPink, R.color.NeonPink, R.color.DimorphothecaMagenta, R.color.MediumVioletRed,
            R.color.RoguePink, R.color.BurntPink, R.color.CarnationPink, R.color.PurpleIris, R.color.Indigo,
            R.color.PurpleMonster, R.color.PurpleAmethyst, R.color.LovelyPurple, R.color.Purple, R.color.JasminePurple,
            R.color.PurpleDaffodil, R.color.TyrianPurple, R.color.PurpleMimosa};

    float startBrightnessValue;
    float lightbulbAndPaletteLayoutBrightnessValue;
    boolean brightnessFlag;
    float lightbulbTransparency;

    WindowManager.LayoutParams layout;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toast.makeText(this, "Підсвітка екрану", Toast.LENGTH_SHORT).show();

        layoutColor = getResources().getColor(R.color.Black);
        setActivityBackgroundColor(layoutColor);

        btnFlash = (ImageButton) findViewById(R.id.btnFlash);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);

        btnGoMain = (ImageButton) findViewById(R.id.btnGoMain);
        btnGoTrafficLights = (ImageButton) findViewById(R.id.btnGoTrafficLights);
        btnGoLightbulb = (ImageButton) findViewById(R.id.btnGoLightbulb);
        btnGoPalette = (ImageButton) findViewById(R.id.btnGoPalette);
        btnGoPolice = (ImageButton) findViewById(R.id.btnGoPolice);
        btnGoSettings = (ImageButton) findViewById(R.id.btnGoSettings);
        btnGoDownloads = (ImageButton) findViewById(R.id.btnGoDownloads);

        trafficLightsTop = (ImageView) findViewById(R.id.trafficLightsTop);
        trafficLightsBottom = (ImageView) findViewById(R.id.trafficLightsBottom);
        lightbulbTop = (ImageView) findViewById(R.id.lightbulbTop);
        lightbulbBottom = (ImageView) findViewById(R.id.lightbulbBottom);
        lightbulbLit = (ImageView) findViewById(R.id.lightbulbLit);
        policeLayout = (ImageView) findViewById(R.id.policeLayout);

        trafficLightsTop.setBackgroundResource(R.drawable.trafficlightstop_anim);
        trafficLightsBottom.setBackgroundResource(R.drawable.trafficlightsbottom_anim);
        policeLayout.setBackgroundResource(R.drawable.poice_anim);

        btnFlash.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
        btnGoMain.setOnClickListener(this);
        btnGoTrafficLights.setOnClickListener(this);
        btnGoLightbulb.setOnClickListener(this);
        btnGoPalette.setOnClickListener(this);
        btnGoPolice.setOnClickListener(this);
        btnGoSettings.setOnClickListener(this);
        btnGoDownloads.setOnClickListener(this);

        trafficLightsTopAnim = (AnimationDrawable) trafficLightsTop.getBackground();
        trafficLightsBottomAnim = (AnimationDrawable) trafficLightsBottom.getBackground();
        policeLayoutAnim = (AnimationDrawable) policeLayout.getBackground();

        main = (RelativeLayout) findViewById(R.id.main);
        main.setOnTouchListener(this);

        Random randomNumbers = new Random();
        lightbulbLayoutColor = randomNumbers.nextInt(colors.length);
        paletteLayoutColor = randomNumbers.nextInt(colors.length);

        layout = getWindow().getAttributes();
        startBrightnessValue = layout.screenBrightness;
        lightbulbAndPaletteLayoutBrightnessValue = 1;
    }

    @Override
    public void onRestart() {
        super.onRestart();
        onClick(btnMenu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMenu:

                if (btnMenuFlag) {
                    if (btnGoMainFlag) {
                        goMain();
                    } else if (btnGoTrafficLightsFlag) {
                        goTrafficLights();
                    } else if (btnGoLightbulbFlag) {
                        goLightbulb();
                    } else if (btnGoPaletteFlag) {
                        goPalette();
                    } else if (btnGoPoliceFlag) {
                        goPolice();
                    } else if (btnGoSettingsFlag) {
                        goSettings();
                    } else if (btnGoDownloadsFlag) {
                        goDownloads();
                    }

                } else {
                    layoutColor = getResources().getColor(R.color.Black);
                    setActivityBackgroundColor(layoutColor);
                    btnMenu.setImageResource(R.drawable.sidebtn2);
                    btnFlash.setVisibility(View.INVISIBLE);
                    trafficLightsTop.setVisibility(View.INVISIBLE);
                    trafficLightsBottom.setVisibility(View.INVISIBLE);
                    lightbulbTop.setVisibility(View.INVISIBLE);
                    lightbulbBottom.setVisibility(View.INVISIBLE);
                    lightbulbLit.setVisibility(View.INVISIBLE);
                    policeLayout.setVisibility(View.INVISIBLE);

                    btnGoMain.setVisibility(View.VISIBLE);
                    btnGoTrafficLights.setVisibility(View.VISIBLE);
                    btnGoLightbulb.setVisibility(View.VISIBLE);
                    btnGoPalette.setVisibility(View.VISIBLE);
                    btnGoPolice.setVisibility(View.VISIBLE);
                    btnGoSettings.setVisibility(View.VISIBLE);
                    btnGoDownloads.setVisibility(View.VISIBLE);

                    layout.screenBrightness = startBrightnessValue;
                    getWindow().setAttributes(layout);
                }
                btnMenuFlag = !btnMenuFlag;
                break;
            case R.id.btnFlash:
                if (btnFlashFlag) {
                    layoutColor = getResources().getColor(R.color.Black);
                    setActivityBackgroundColor(layoutColor);
                    btnFlash.setImageResource(R.drawable.btnon);
                } else {
                    layoutColor = getResources().getColor(R.color.White);
                    setActivityBackgroundColor(layoutColor);
                    btnFlash.setImageResource(R.drawable.btnoff);
                }
                btnFlashFlag = !btnFlashFlag;
                break;
            case R.id.btnGoMain:
                goMain();
                btnMenuFlag = !btnMenuFlag;
                break;

            case R.id.btnGoTrafficLights:
                goTrafficLights();
                btnMenuFlag = !btnMenuFlag;
                break;
            case R.id.btnGoLightbulb:
                goLightbulb();
                btnMenuFlag = !btnMenuFlag;
                break;
            case R.id.btnGoPalette:
                goPalette();
                btnMenuFlag = !btnMenuFlag;
                break;
            case R.id.btnGoPolice:
                goPolice();
                btnMenuFlag = !btnMenuFlag;
                break;
            case R.id.btnGoSettings:
                goSettings();
                btnMenuFlag = !btnMenuFlag;
                break;
            case R.id.btnGoDownloads:
                goDownloads();
                btnMenuFlag = !btnMenuFlag;
                break;
        }
    }

    public void goMain() {
        btnMenu.setImageResource(R.drawable.sidebtn1);
        turnMenuButtonsOff();
        trafficLightsTop.setVisibility(View.INVISIBLE);
        trafficLightsBottom.setVisibility(View.INVISIBLE);
        lightbulbTop.setVisibility(View.INVISIBLE);
        lightbulbBottom.setVisibility(View.INVISIBLE);
        lightbulbLit.setVisibility(View.INVISIBLE);
        policeLayout.setVisibility(View.INVISIBLE);

        btnFlash.setVisibility(View.VISIBLE);
        if (btnFlashFlag) {
            layoutColor = getResources().getColor(R.color.White);
            setActivityBackgroundColor(layoutColor);
            btnFlash.setImageResource(R.drawable.btnoff);
        } else {
            layoutColor = getResources().getColor(R.color.Black);
            setActivityBackgroundColor(layoutColor);
            btnFlash.setImageResource(R.drawable.btnon);
        }

        Toast.makeText(this, "Підсвітка екрану", Toast.LENGTH_SHORT).show();

        findOutWhereToGoFromMenuButton(btnGoMain);
    }

    public void goTrafficLights() {
        btnMenu.setImageResource(R.drawable.sidebtn1);
        turnMenuButtonsOff();
        btnFlash.setVisibility(View.INVISIBLE);
        lightbulbTop.setVisibility(View.INVISIBLE);
        lightbulbBottom.setVisibility(View.INVISIBLE);
        lightbulbLit.setVisibility(View.INVISIBLE);
        policeLayout.setVisibility(View.INVISIBLE);

        trafficLightsTop.setVisibility(View.VISIBLE);
        trafficLightsBottom.setVisibility(View.VISIBLE);
        trafficLightsTopAnim.start();
        trafficLightsBottomAnim.start();

        Toast.makeText(this, "Сигналізація", Toast.LENGTH_SHORT).show();

        findOutWhereToGoFromMenuButton(btnGoTrafficLights);
    }

    public void goLightbulb() {
        btnMenu.setImageResource(R.drawable.sidebtn1);
        turnMenuButtonsOff();
        btnFlash.setVisibility(View.INVISIBLE);
        trafficLightsTop.setVisibility(View.INVISIBLE);
        trafficLightsBottom.setVisibility(View.INVISIBLE);
        lightbulbTop.setVisibility(View.VISIBLE);
        lightbulbBottom.setVisibility(View.VISIBLE);
        lightbulbLit.setVisibility(View.VISIBLE);
        policeLayout.setVisibility(View.INVISIBLE);

        layoutColor = getResources().getColor(colors[lightbulbLayoutColor]);
        setActivityBackgroundColor(layoutColor);

        if (!brightnessFlag) {
            layout.screenBrightness = 1;
        } else {
            layout.screenBrightness = lightbulbAndPaletteLayoutBrightnessValue;
        }

        getWindow().setAttributes(layout);
        brightnessFlag = true;

        Toast.makeText(this, "Лампочка", Toast.LENGTH_SHORT).show();

        findOutWhereToGoFromMenuButton(btnGoLightbulb);
    }

    public void goPalette() {
        btnMenu.setImageResource(R.drawable.sidebtn1);
        turnMenuButtonsOff();
        btnFlash.setVisibility(View.INVISIBLE);
        trafficLightsTop.setVisibility(View.INVISIBLE);
        trafficLightsBottom.setVisibility(View.INVISIBLE);
        lightbulbTop.setVisibility(View.INVISIBLE);
        lightbulbBottom.setVisibility(View.INVISIBLE);
        lightbulbLit.setVisibility(View.INVISIBLE);
        policeLayout.setVisibility(View.INVISIBLE);

        layoutColor = getResources().getColor(colors[paletteLayoutColor]);
        setActivityBackgroundColor(layoutColor);

        if (!brightnessFlag) {
            layout.screenBrightness = 1;
        } else {
            layout.screenBrightness = lightbulbAndPaletteLayoutBrightnessValue;
        }

        getWindow().setAttributes(layout);
        brightnessFlag = true;

        Toast.makeText(this, "Кольорове підсвічування", Toast.LENGTH_SHORT).show();

        findOutWhereToGoFromMenuButton(btnGoPalette);
    }

    public void goPolice() {
        btnMenu.setImageResource(R.drawable.sidebtn1);
        turnMenuButtonsOff();
        btnFlash.setVisibility(View.INVISIBLE);
        trafficLightsTop.setVisibility(View.INVISIBLE);
        trafficLightsBottom.setVisibility(View.INVISIBLE);
        lightbulbTop.setVisibility(View.INVISIBLE);
        lightbulbBottom.setVisibility(View.INVISIBLE);
        lightbulbLit.setVisibility(View.INVISIBLE);

        policeLayout.setVisibility(View.VISIBLE);
        policeLayoutAnim.start();
        btnMenu.bringToFront();

        Toast.makeText(this, "Поліцейський маяк", Toast.LENGTH_SHORT).show();

        findOutWhereToGoFromMenuButton(btnGoPolice);
    }

    public void goSettings() {
        findOutWhereToGoFromMenuButton(btnGoSettings);

        Intent settingsIntent = new Intent(this, SettingsLayout.class);
        startActivityForResult(settingsIntent, 0);
    }

    public void goDownloads() {
        findOutWhereToGoFromMenuButton(btnGoDownloads);

        Intent appsIntent = new Intent(this, AppsLayout.class);
        startActivity(appsIntent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xCurrent = (int) event.getX();
                yCurrent = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                xAfterMove = (int) event.getX();
                yAfterMove = (int) event.getY();
                dx = xAfterMove - xCurrent;
                dy = yAfterMove - yCurrent;

                if ((layoutColor != Color.BLACK) && (layoutColor != Color.WHITE)) {
                    if (btnGoLightbulbFlag || btnGoPaletteFlag) {

                        for (int i = 0; i < colors.length; i++) {
                            if (layoutColor == colors[i]) {
                                layoutColorNumber = i;
                            }
                        }

                        layoutColorNumber += dx / 500;

                        if (layoutColorNumber < 0) {
                            layoutColorNumber = colors.length - 1;
                        } else if (layoutColorNumber > colors.length - 1) {
                            layoutColorNumber = 0;
                        }

                        layoutColor = getResources().getColor(colors[layoutColorNumber]);
                        setActivityBackgroundColor(layoutColor);

                        if (btnGoLightbulbFlag) {
                            lightbulbLayoutColor = layoutColorNumber;
                        } else if (btnGoPaletteFlag) {
                            paletteLayoutColor = layoutColorNumber;
                        }

                        if (layout.screenBrightness != -1) {
                            if (((int) Math.floor(layout.screenBrightness) >= 0) &&
                                    ((int) Math.ceil(layout.screenBrightness) <= 1)) {
                                layout.screenBrightness -= (float) dy / 7000;
                            }

                            if ((int) Math.floor(layout.screenBrightness) < 0) {
                                layout.screenBrightness = 0;
                            } else if ((int) Math.ceil(layout.screenBrightness) > 1) {
                                layout.screenBrightness = 1;
                            }

                            lightbulbAndPaletteLayoutBrightnessValue = layout.screenBrightness;
                            getWindow().setAttributes(layout);
                        }

                        lightbulbTransparency = layout.screenBrightness;
                        lightbulbLit.setAlpha(lightbulbTransparency);
                    }
                }
                break;
        }
        return true;
    }

    public void findOutWhereToGoFromMenuButton(View v) {
        btnGoMainFlag = false;
        btnGoTrafficLightsFlag = false;
        btnGoLightbulbFlag = false;
        btnGoPaletteFlag = false;
        btnGoPoliceFlag = false;
        btnGoSettingsFlag = false;
        btnGoDownloadsFlag = false;

        switch (v.getId()) {
            case R.id.btnGoMain:
                btnGoMainFlag = true;
                break;
            case R.id.btnGoTrafficLights:
                btnGoTrafficLightsFlag = true;
                break;
            case R.id.btnGoLightbulb:
                btnGoLightbulbFlag = true;
                break;
            case R.id.btnGoPalette:
                btnGoPaletteFlag = true;
                break;
            case R.id.btnGoPolice:
                btnGoPoliceFlag = true;
                break;
            case R.id.btnGoSettings:
                btnGoSettingsFlag = true;
                break;
            case R.id.btnGoDownloads:
                btnGoDownloadsFlag = true;
                break;
        }
    }

    public void turnMenuButtonsOff() {
        btnGoMain.setVisibility(View.INVISIBLE);
        btnGoTrafficLights.setVisibility(View.INVISIBLE);
        btnGoLightbulb.setVisibility(View.INVISIBLE);
        btnGoPalette.setVisibility(View.INVISIBLE);
        btnGoPolice.setVisibility(View.INVISIBLE);
        btnGoSettings.setVisibility(View.INVISIBLE);
        btnGoDownloads.setVisibility(View.INVISIBLE);
    }

    public void setActivityBackgroundColor(int color) {
        RelativeLayout main = (RelativeLayout) findViewById(R.id.main);
        main.setBackgroundColor(color);
    }
}