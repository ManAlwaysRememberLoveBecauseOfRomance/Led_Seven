package com.example.elcapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.R;


public class MainActivity extends Activity implements OnSeekBarChangeListener {

    private SeekBar seekBar_red, seekBar_blue, seekBar_green, seekBar_bluegreen, seekBar_redgreen, seekBar_redblue, seekBar_redgreenblue;
    private SeekBar seekBar_red_left, seekBar_blue_left, seekBar_green_left, seekBar_bluegreen_left, seekBar_redgreen_left, seekBar_redblue_blue, seekBar_redblue_left, seekBar_redgreenblue_left;
    private Switch mSwitch;
    private static final int seek_red = 0xa1;
    private static final int seek_green = 0xa2;
    private static final int seek_blue = 0xa3;
    private static final int seek_green_blue = 0xa4;
    private static final int seek_red_blue = 0xa5;
    private static final int seek_red_green = 0xa6;
    private static final int seek_all = 0xa7;

    private static final int seek_red_left = 0xb1;
    private static final int seek_green_left = 0xb2;
    private static final int seek_blue_left = 0xb3;
    private static final int seek_green_blue_left = 0xb4;
    private static final int seek_red_blue_left = 0xb5;
    private static final int seek_red_green_left = 0xb6;
    private static final int seek_all_left = 0xb7;

    private static final int led_off = 20;
    private static final int led_on = 1;
    private static final int led_red = 2;
    private static final int led_blue = 3;
    private static final int led_green = 4;
    private static final int led_bluegreen = 5;
    private static final int led_redgreen = 6;
    private static final int led_redblue = 7;
    private static final int led_redgreemblue = 8;
    private static final int led_red_left = 9;
    private static final int led_blue_left = 10;
    private static final int led_green_left = 11;
    private static final int led_bluegreen_left = 12;
    private static final int led_redgreen_left = 13;
    private static final int led_redblue_left = 14;
    private static final int led_redgreemblue_left = 15;
    private int brightness;
    private int sb = 1;
    private static int ledCtlFlag = led_red;

    static Handler mhandler = new Handler();
    private static boolean ledrunflag = true;
    private static Runnable mTask = new Runnable() {       //Main函数中的循环，与开机广播不同。
        public void run() {
            if (ledrunflag = true) {
                if (ledCtlFlag == led_red) {
                    ledCtlFlag = led_blue;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_red, 10);
                    jnielc.ledseek(seek_red_left, 10);
                    jnielc.seekstop();
                } else if (ledCtlFlag == led_blue) {
                    ledCtlFlag = led_green;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_blue, 10);
                    jnielc.ledseek(seek_blue_left, 10);
                    jnielc.seekstop();
                } else if (ledCtlFlag == led_green) {
                    ledCtlFlag = led_bluegreen;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_green, 10);
                    jnielc.ledseek(seek_green_left, 10);
                    jnielc.seekstop();
                }
                else if (ledCtlFlag == led_bluegreen) {
                    ledCtlFlag = led_redgreen;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_green_blue, 10);
                    jnielc.ledseek(seek_green_blue_left, 10);
                    jnielc.seekstop();
                }
                else if (ledCtlFlag == led_redgreen) {
                    ledCtlFlag = led_redblue;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_red_green, 10);
                    jnielc.ledseek(seek_red_green_left, 10);
                    jnielc.seekstop();
                }
                else if (ledCtlFlag == led_redblue) {
                    ledCtlFlag = led_redgreemblue;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_red_blue, 10);
                    jnielc.ledseek(seek_red_blue_left, 10);
                    jnielc.seekstop();
                }
                else if (ledCtlFlag == led_redgreemblue) {
                    ledCtlFlag = led_red;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_all, 10);
                    jnielc.ledseek(seek_all_left, 10);
                    jnielc.seekstop();
                }
                mhandler.postDelayed(mTask, 5000);
            }
        }
    };

    private static final String pwm1 = "ledbrightness_zx";
    private static final String pwm2 = "ledbrightness_ml";

    private static final String led_right = "ledcolor_kp";
    private static final String led_left = "ledcolor_rd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sampler.getInstance().stop();
        seekBar_red = (SeekBar) findViewById(R.id.SeekBar_red);
        seekBar_blue = (SeekBar) findViewById(R.id.SeekBar_blue);
        seekBar_green = (SeekBar) findViewById(R.id.SeekBar_green);
        seekBar_bluegreen = (SeekBar) findViewById(R.id.SeekBar_bluegreen);
        seekBar_redgreen = (SeekBar) findViewById(R.id.SeekBar_redgreen);
        seekBar_redblue = (SeekBar) findViewById(R.id.SeekBar_redblue);
        seekBar_redgreenblue = (SeekBar) findViewById(R.id.SeekBar_redbluegreen);
        seekBar_red.setOnSeekBarChangeListener(this);
        seekBar_blue.setOnSeekBarChangeListener(this);
        seekBar_green.setOnSeekBarChangeListener(this);
        seekBar_bluegreen.setOnSeekBarChangeListener(this);
        seekBar_redgreen.setOnSeekBarChangeListener(this);
        seekBar_redblue.setOnSeekBarChangeListener(this);
        seekBar_redgreenblue.setOnSeekBarChangeListener(this);

        seekBar_red_left = (SeekBar) findViewById(R.id.SeekBar_red_left);
        seekBar_blue_left = (SeekBar) findViewById(R.id.SeekBar_blue_left);
        seekBar_green_left = (SeekBar) findViewById(R.id.SeekBar_green_left);
        seekBar_bluegreen_left = (SeekBar) findViewById(R.id.SeekBar_bluegreen_left);
        seekBar_redgreen_left = (SeekBar) findViewById(R.id.SeekBar_redgreen_left);
        seekBar_redblue_left = (SeekBar) findViewById(R.id.SeekBar_redblue_left);
        seekBar_redgreenblue_left = (SeekBar) findViewById(R.id.SeekBar_redbluegreen_left);
        seekBar_red_left.setOnSeekBarChangeListener(this);
        seekBar_blue_left.setOnSeekBarChangeListener(this);
        seekBar_green_left.setOnSeekBarChangeListener(this);
        seekBar_bluegreen_left.setOnSeekBarChangeListener(this);
        seekBar_redgreen_left.setOnSeekBarChangeListener(this);
        seekBar_redblue_left.setOnSeekBarChangeListener(this);
        seekBar_redgreenblue_left.setOnSeekBarChangeListener(this);
        mSwitch = (Switch) findViewById(R.id.switch1);
        mSwitch.setChecked(true);
        sb=1;
        mhandler.post(mTask);
        set_led_color(led_on,led_left);
        set_led_color(led_on,led_right);
        Log.i("mled", "get_led_color(led_right)=" + get_led_color(led_right));
        Log.i("mled", "get_led_color(led_left)=" + get_led_color(led_left));

        Log.i("mled", "get_led_brightness(pwm1)=" + get_led_brightness(pwm1));
        Log.i("mled", "get_led_brightness(pwm2)=" + get_led_brightness(pwm2));
        //fb=jnielc.open();
        switch (get_led_color(led_right)) {
            case led_red:
                seekBar_red.setProgress(get_led_brightness(pwm1));
                seekBar_blue.setProgress(0);
                seekBar_green.setProgress(0);
                seekBar_bluegreen.setProgress(0);
                seekBar_redgreen.setProgress(0);
                seekBar_redblue.setProgress(0);
                seekBar_redgreenblue.setProgress(0);
                break;
            case led_blue:
                seekBar_blue.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_green.setProgress(0);
                seekBar_bluegreen.setProgress(0);
                seekBar_redgreen.setProgress(0);
                seekBar_redblue.setProgress(0);
                seekBar_redgreenblue.setProgress(0);
                break;
            case led_green:
                seekBar_green.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_blue.setProgress(0);
                seekBar_bluegreen.setProgress(0);
                seekBar_redgreen.setProgress(0);
                seekBar_redblue.setProgress(0);
                seekBar_redgreenblue.setProgress(0);
                break;
            case led_bluegreen:
                seekBar_bluegreen.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_blue.setProgress(0);
                seekBar_green.setProgress(0);
                seekBar_redgreen.setProgress(0);
                seekBar_redblue.setProgress(0);
                seekBar_redgreenblue.setProgress(0);
                break;
            case led_redblue:
                seekBar_redblue.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_blue.setProgress(0);
                seekBar_green.setProgress(0);
                seekBar_redgreen.setProgress(0);
                seekBar_bluegreen.setProgress(0);
                seekBar_redgreenblue.setProgress(0);
                break;
            case led_redgreen:
                seekBar_redgreen.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_blue.setProgress(0);
                seekBar_green.setProgress(0);
                seekBar_redblue.setProgress(0);
                seekBar_bluegreen.setProgress(0);
                seekBar_redgreenblue.setProgress(0);
                break;
            case led_redgreemblue:
                seekBar_redgreenblue.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_blue.setProgress(0);
                seekBar_green.setProgress(0);
                seekBar_redblue.setProgress(0);
                seekBar_bluegreen.setProgress(0);
                seekBar_redgreen.setProgress(0);
                break;
            default:
                break;
        }
        switch (get_led_color(led_left)) {
            case led_red_left:
                seekBar_red_left.setProgress(get_led_brightness(pwm2));
                seekBar_blue_left.setProgress(0);
                seekBar_green_left.setProgress(0);
                seekBar_bluegreen_left.setProgress(0);
                seekBar_redgreen_left.setProgress(0);
                seekBar_redblue_left.setProgress(0);
                seekBar_redgreenblue_left.setProgress(0);
                break;
            case led_blue_left:
                seekBar_blue_left.setProgress(get_led_brightness(pwm2));
                seekBar_red_left.setProgress(0);
                seekBar_green_left.setProgress(0);
                seekBar_bluegreen_left.setProgress(0);
                seekBar_redgreen_left.setProgress(0);
                seekBar_redblue_left.setProgress(0);
                seekBar_redgreenblue_left.setProgress(0);
                break;
            case led_green_left:
                seekBar_green_left.setProgress(get_led_brightness(pwm2));
                seekBar_red_left.setProgress(0);
                seekBar_blue_left.setProgress(0);
                seekBar_bluegreen_left.setProgress(0);
                seekBar_redgreen_left.setProgress(0);
                seekBar_redblue_left.setProgress(0);
                seekBar_redgreenblue_left.setProgress(0);
                break;
            case led_bluegreen_left:
                seekBar_bluegreen_left.setProgress(get_led_brightness(pwm2));
                seekBar_red_left.setProgress(0);
                seekBar_blue_left.setProgress(0);
                seekBar_green_left.setProgress(0);
                seekBar_redgreen_left.setProgress(0);
                seekBar_redblue_left.setProgress(0);
                seekBar_redgreenblue_left.setProgress(0);
                break;
            case led_redblue_left:
                seekBar_redblue_left.setProgress(get_led_brightness(pwm2));
                seekBar_red_left.setProgress(0);
                seekBar_blue_left.setProgress(0);
                seekBar_green_left.setProgress(0);
                seekBar_redgreen_left.setProgress(0);
                seekBar_bluegreen_left.setProgress(0);
                seekBar_redgreenblue_left.setProgress(0);
                break;
            case led_redgreen_left:
                seekBar_redgreen_left.setProgress(get_led_brightness(pwm2));
                seekBar_red_left.setProgress(0);
                seekBar_blue_left.setProgress(0);
                seekBar_green_left.setProgress(0);
                seekBar_redblue_left.setProgress(0);
                seekBar_bluegreen_left.setProgress(0);
                seekBar_redgreenblue_left.setProgress(0);
                break;
            case led_redgreemblue_left:
                seekBar_redgreenblue_left.setProgress(get_led_brightness(pwm2));
                seekBar_red_left.setProgress(0);
                seekBar_blue_left.setProgress(0);
                seekBar_green_left.setProgress(0);
                seekBar_redblue_left.setProgress(0);
                seekBar_bluegreen_left.setProgress(0);
                seekBar_redgreen_left.setProgress(0);
                break;
            default:
                break;
        }


        /*if (get_led_color(led_right) == led_on || get_led_color(led_left) == led_on) {
            mSwitch.setChecked(true);
        } else {
            mSwitch.setChecked(false);
        }*/

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    set_led_color(led_on, led_right);
                    set_led_color(led_on, led_left);
                    //if (Sampler.getInstance().getledflag() != true) {
                    // Sampler.getInstance().init(getBaseContext(), 5000L);
                    //   Sampler.getInstance().start();
                    //}
                    mhandler.post(mTask);
                    ledrunflag = true;
                    sb = 1;
                    Log.d("Jessica_sb", String.valueOf(sb));
                    seekBar_green.setProgress(0);
                    seekBar_red.setProgress(0);
                    seekBar_blue.setProgress(0);
                    seekBar_bluegreen.setProgress(0);
                    seekBar_redblue.setProgress(0);
                    seekBar_bluegreen.setProgress(0);
                    seekBar_redgreenblue.setProgress(0);

                    seekBar_green_left.setProgress(0);
                    seekBar_red_left.setProgress(0);
                    seekBar_blue_left.setProgress(0);
                    seekBar_bluegreen_left.setProgress(0);
                    seekBar_redgreen_left.setProgress(0);
                    seekBar_redblue_left.setProgress(0);
                    seekBar_redgreenblue_left.setProgress(0);
                        /*Intent intent = new Intent("android.intent.action.ledctl");
                        intent.putExtra("led", led_on);
                        intent.putExtra("ledbrightness", 50);
                        sendBroadcast(intent);*/
                    // SystemProperteisProxy.set("persist.demo.ledswitch", "1");
                   // Toast.makeText(MainActivity.this, "LED FALSH !!!!!", Toast.LENGTH_SHORT).show();

                } else {
                    // SystemProperteisProxy.set("persist.demo.ledswitch", "0");
                    if (get_led_color(led_right) == led_on || get_led_color(led_left) == led_on) {
                        set_led_color(led_off, led_right);
                        set_led_color(led_off, led_left);
                        mhandler.removeCallbacks(mTask);
                        Sampler.getInstance().stop();
                        ledrunflag = false;
                        /*Intent intent1 = new Intent("android.intent.action.ledctl");
                        intent1.putExtra("led", led_off);
                        sendBroadcast(intent1);*/

                    /*    seekBar_green.setProgress(0);
                        seekBar_red.setProgress(0);
                        seekBar_blue.setProgress(0);
                        seekBar_bluegreen.setProgress(0);
                        seekBar_redblue.setProgress(0);
                        seekBar_bluegreen.setProgress(0);
                        seekBar_redgreenblue.setProgress(0);
                        seekBar_green_left.setProgress(0);
                        seekBar_red_left.setProgress(0);
                        seekBar_blue_left.setProgress(0);
                        seekBar_bluegreen_left.setProgress(0);
                        seekBar_redgreen_left.setProgress(0);
                        seekBar_redblue_left.setProgress(0);
                        seekBar_redgreenblue_left.setProgress(0);*/
                        //Toast.makeText(MainActivity.this, "LED off !!!!!", Toast.LENGTH_SHORT).show();
                        jnielc.seekstart();
                        if (sb == 1) {
                            jnielc.ledoff();
                            Log.d("Jessica_sb","111111111111111111111111");
                        }
                            jnielc.seekstop();
                            Log.d("Jessica_wai","111111111111111111111111");


                    }

                }
            }

        });

    }

    //数值改变
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
        //if(get_led_color()==led_on){
        if (seekBar == seekBar_red) {
            jnielc.ledseek(seek_red, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_green) {
            jnielc.ledseek(seek_green, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_blue) {
            jnielc.ledseek(seek_blue, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_bluegreen) {
            jnielc.ledseek(seek_green_blue, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_redgreen) {
            jnielc.ledseek(seek_red_green, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_redblue) {
            jnielc.ledseek(seek_red_blue, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_redgreenblue) {
            jnielc.ledseek(seek_all, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_red_left) {
            jnielc.ledseek(seek_red_left, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_green_left) {
            jnielc.ledseek(seek_green_left, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_blue_left) {
            jnielc.ledseek(seek_blue_left, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_bluegreen_left) {
            jnielc.ledseek(seek_green_blue_left, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_redgreen_left) {
            jnielc.ledseek(seek_red_green_left, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_redblue_left) {
            jnielc.ledseek(seek_red_blue_left, progress);
            brightness = progress;
        }
        if (seekBar == seekBar_redgreenblue_left) {
            jnielc.ledseek(seek_all_left, progress);
            brightness = progress;
        }
        // }
    }

    //开始拖动
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        jnielc.seekstart();
        // mhandler.removeCallbacks(mTask);
        Sampler.getInstance().stop();
        ledrunflag = false;
    }

    //停止拖动
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        jnielc.seekstop();
        if (seekBar == seekBar_red) {
            sb=0;
            set_led_brightness(brightness, pwm1);
            set_led_color(led_red, led_right);
            seekBar_blue.setProgress(0);
            seekBar_green.setProgress(0);
            seekBar_bluegreen.setProgress(0);
            seekBar_redgreen.setProgress(0);
            seekBar_redblue.setProgress(0);
            seekBar_redgreenblue.setProgress(0);
        }
        if (seekBar == seekBar_green) {
            sb=0;
            set_led_brightness(brightness, pwm1);
            set_led_color(led_green, led_right);
            seekBar_blue.setProgress(0);
            seekBar_red.setProgress(0);
            seekBar_bluegreen.setProgress(0);
            seekBar_redgreen.setProgress(0);
            seekBar_redblue.setProgress(0);
            seekBar_redgreenblue.setProgress(0);
        }
        if (seekBar == seekBar_blue) {
            sb=0;
            set_led_brightness(brightness, pwm1);
            set_led_color(led_blue, led_right);
            seekBar_green.setProgress(0);
            seekBar_red.setProgress(0);
            seekBar_bluegreen.setProgress(0);
            seekBar_redgreen.setProgress(0);
            seekBar_redblue.setProgress(0);
            seekBar_redgreenblue.setProgress(0);
        }
        if (seekBar == seekBar_bluegreen) {
            sb=0;
            set_led_brightness(brightness, pwm1);
            set_led_color(led_bluegreen, led_right);
            seekBar_green.setProgress(0);
            seekBar_red.setProgress(0);
            seekBar_blue.setProgress(0);
            seekBar_redgreen.setProgress(0);
            seekBar_redblue.setProgress(0);
            seekBar_redgreenblue.setProgress(0);
        }
        if (seekBar == seekBar_redgreen) {
            sb=0;
            set_led_brightness(brightness, pwm1);
            set_led_color(led_redgreen, led_right);
            seekBar_green.setProgress(0);
            seekBar_red.setProgress(0);
            seekBar_blue.setProgress(0);
            seekBar_bluegreen.setProgress(0);
            seekBar_redblue.setProgress(0);
            seekBar_redgreenblue.setProgress(0);
        }
        if (seekBar == seekBar_redblue) {
            sb=0;
            set_led_brightness(brightness, pwm1);
            set_led_color(led_redblue, led_right);
            seekBar_green.setProgress(0);
            seekBar_red.setProgress(0);
            seekBar_blue.setProgress(0);
            seekBar_bluegreen.setProgress(0);
            seekBar_redgreen.setProgress(0);
            seekBar_redgreenblue.setProgress(0);
        }
        if (seekBar == seekBar_redgreenblue) {
            sb=0;
            set_led_brightness(brightness, pwm1);
            set_led_color(led_redgreemblue, led_right);
            seekBar_green.setProgress(0);
            seekBar_red.setProgress(0);
            seekBar_blue.setProgress(0);
            seekBar_bluegreen.setProgress(0);
            seekBar_redgreen.setProgress(0);
            seekBar_redblue.setProgress(0);
        }
        if (seekBar == seekBar_red_left) {
            sb=0;
            set_led_brightness(brightness, pwm2);
            set_led_color(led_red_left, led_left);
            seekBar_blue_left.setProgress(0);
            seekBar_green_left.setProgress(0);
            seekBar_bluegreen_left.setProgress(0);
            seekBar_redblue_left.setProgress(0);
            seekBar_redgreen_left.setProgress(0);
            seekBar_redgreenblue_left.setProgress(0);
        }
        if (seekBar == seekBar_green_left) {
            sb=0;
            set_led_brightness(brightness, pwm2);
            set_led_color(led_green_left, led_left);
            seekBar_blue_left.setProgress(0);
            seekBar_red_left.setProgress(0);
            seekBar_bluegreen_left.setProgress(0);
            seekBar_redblue_left.setProgress(0);
            seekBar_redgreen_left.setProgress(0);
            seekBar_redgreenblue_left.setProgress(0);
        }
        if (seekBar == seekBar_blue_left) {
            sb=0;
            set_led_brightness(brightness, pwm2);
            set_led_color(led_blue_left, led_left);
            seekBar_green_left.setProgress(0);
            seekBar_red_left.setProgress(0);
            seekBar_bluegreen_left.setProgress(0);
            seekBar_redblue_left.setProgress(0);
            seekBar_redgreen_left.setProgress(0);
            seekBar_redgreenblue_left.setProgress(0);
        }
        if (seekBar == seekBar_bluegreen_left) {
            sb=0;
            set_led_brightness(brightness, pwm2);
            set_led_color(led_bluegreen_left, led_left);
            seekBar_green_left.setProgress(0);
            seekBar_red_left.setProgress(0);
            seekBar_blue_left.setProgress(0);
            seekBar_redblue_left.setProgress(0);
            seekBar_redgreen_left.setProgress(0);
            seekBar_redgreenblue_left.setProgress(0);
        }
        if (seekBar == seekBar_redgreen_left) {
            sb=0;
            set_led_brightness(brightness, pwm2);
            set_led_color(led_redgreen_left, led_left);
            seekBar_green_left.setProgress(0);
            seekBar_red_left.setProgress(0);
            seekBar_blue_left.setProgress(0);
            seekBar_bluegreen_left.setProgress(0);
            seekBar_redblue_left.setProgress(0);
            seekBar_redgreenblue_left.setProgress(0);
        }
        if (seekBar == seekBar_redblue_left) {
            sb=0;
            set_led_brightness(brightness, pwm2);
            set_led_color(led_redblue_left, led_left);
            seekBar_green_left.setProgress(0);
            seekBar_red_left.setProgress(0);
            seekBar_blue_left.setProgress(0);
            seekBar_bluegreen_left.setProgress(0);
            seekBar_redgreen_left.setProgress(0);
            seekBar_redgreenblue_left.setProgress(0);
        }
        if (seekBar == seekBar_redgreenblue_left) {
            sb=0;
            set_led_brightness(brightness, pwm2);
            set_led_color(led_redgreemblue_left, led_left);
            seekBar_green_left.setProgress(0);
            seekBar_red_left.setProgress(0);
            seekBar_blue_left.setProgress(0);
            seekBar_bluegreen_left.setProgress(0);
            seekBar_redgreen_left.setProgress(0);
            seekBar_redblue_left.setProgress(0);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwitch.isChecked()) {
                    mSwitch.setChecked(false);
                }
            }
        }, 500);
    }

    private void set_led_color(int freq, String name) {
        SharedPreferences save_par = getSharedPreferences("addata", 0);
        SharedPreferences.Editor save_editor = save_par.edit();
        save_editor.putString(name, String.valueOf(freq));
        save_editor.commit();
    }

    private int get_led_color(String name) {
        int value = 0;
        try {
            SharedPreferences save_par = getSharedPreferences("addata", 0);
            value = Integer.parseInt(save_par.getString(name, "0"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return value;
    }

    private void set_led_brightness(int freq, String name) {
        SharedPreferences save_par = getSharedPreferences("addata", 0);
        SharedPreferences.Editor save_editor = save_par.edit();
        save_editor.putString(name, String.valueOf(freq));
        save_editor.commit();
    }

    private int get_led_brightness(String name) {
        int value = 0;
        try {
            SharedPreferences save_par = getSharedPreferences("addata", 0);
            value = Integer.parseInt(save_par.getString(name, "0"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return value;
    }
}