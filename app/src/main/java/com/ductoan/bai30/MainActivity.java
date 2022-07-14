package com.ductoan.bai30;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  {
    RelativeLayout layout;
    TextView point,so1,so2,kq;
    ImageButton dung,sai;
    SeekBar time;
    int diem = 0;
    Timer timer;
    Boolean flag = false;
    int thoigian;

    ArrayList<Integer> bgColor = new ArrayList<>();
    Integer[] maunen = {
            Color.parseColor("#65CBCB"),
            Color.parseColor("#D75300"),
            Color.parseColor("#F93C11"),
            Color.parseColor("#293C4E"),
            Color.parseColor("#25AE60"),
            Color.parseColor("#6D0075"),
            Color.parseColor("#F34546"),
            Color.parseColor("#810081")

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        point = (TextView) findViewById(R.id.tvdiem);
        so1 = (TextView) findViewById(R.id.tvSo1);
        so2 = (TextView) findViewById(R.id.tvSo2);
        kq = (TextView) findViewById(R.id.tvKq);
        dung = (ImageButton) findViewById(R.id.imgDung);
        sai = (ImageButton) findViewById(R.id.imgSai);
        time = (SeekBar) findViewById(R.id.skThoigian);
        layout = (RelativeLayout) findViewById(R.id.layout);

        pheptoan();
        diem = 0;
        xlmaunen();
        thoigian = 100;
        time.setMax(100);
        time.setProgress(thoigian);

        point.setText(String.valueOf(diem));
        dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes();
            }
        });
        sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no();
            }
        });
    }
    public int getRandomMinMax(int min, int max){
        return (int) Math.floor(Math.random()* (max - min )) + min;

    }
    public void pheptoan(){
        int r1 =   getRandomMinMax(0,21);
        so1.setText(String.valueOf(r1));
        int r2 =   getRandomMinMax(0,21);
        so2.setText(String.valueOf(r2));

        int r3 = r1 + r2;
        int rKq = getRandomMinMax(r3 - 2,r3 + 2);
        kq.setText(String.valueOf(rKq));
    }
    public void yes(){
        int s1 = Integer.parseInt(so1.getText().toString());
        int s2 = Integer.parseInt(so2.getText().toString());
        int ketqua = Integer.parseInt(kq.getText().toString());

        if(s1 + s2 == ketqua){
            diem = diem + 1;
            point.setText(String.valueOf(diem));
            pheptoan();
            runThoiian();
            thoigian = 100;
        }
        else{
            thua();
        }

    }
    public void no(){
        int s1 = Integer.parseInt(so1.getText().toString());
        int s2 = Integer.parseInt(so2.getText().toString());
        int ketqua = Integer.parseInt(kq.getText().toString());

        if(s1 + s2 != ketqua){
            diem = diem + 1;
            point.setText(String.valueOf(diem));
            pheptoan();
            runThoiian();
            thoigian = 100;
        }else
            {
            thua ();
        }

    }
    public void thua(){
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Thông báo")
                .setMessage("thua rồi  ")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       init();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, " thoát  ", Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false);
        builder.create().show();

    }
    public void xlmaunen(){
        Collections.addAll(bgColor,maunen);
        int maunen =   getRandomMinMax(0,bgColor.size());
        layout.setBackgroundColor(bgColor.get(maunen));
    }
    public void runThoiian(){
        if(!flag ){
            timer = new Timer();

            RunRandum cv = new RunRandum();
            timer.scheduleAtFixedRate(cv,100,100);
            flag = true;
        }

    }
    class RunRandum extends TimerTask{
        @Override
        public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thoigian = thoigian - 1;
                        time.setProgress(thoigian);
                    }
                });
        }
    }


}
