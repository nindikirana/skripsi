package com.nndkrnaf.acfix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nndkrnaf.acfix.deteksi.DeteksiActivity;
import com.nndkrnaf.acfix.gejala.activity.GejalaActivity;
import com.nndkrnaf.acfix.kerusakan.activity.KerusakanActivity;
import com.nndkrnaf.acfix.pakar.PakarActivity;
import com.nndkrnaf.acfix.panduan.PanduanActivity;
import com.nndkrnaf.acfix.tentang.TentangActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView deteksiCard, panduanCard, gejalaCard, kerusakanCard, pakarCard, tentangCard;
    //AnimationDrawable animationDrawable;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
//        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
//
//        animationDrawable.setEnterFadeDuration(5000);
//        animationDrawable.setExitFadeDuration(2000);

        deteksiCard = (CardView) findViewById(R.id.cvDeteksi);
        panduanCard = (CardView) findViewById(R.id.cvPanduan);
        gejalaCard = (CardView) findViewById(R.id.cvGejala);
        kerusakanCard = (CardView) findViewById(R.id.cvKerusakan);
        pakarCard = (CardView) findViewById(R.id.cvPakar);
        tentangCard = (CardView) findViewById(R.id.cvTentang);

        deteksiCard.setOnClickListener(this);
        panduanCard.setOnClickListener(this);
        gejalaCard.setOnClickListener(this);
        kerusakanCard.setOnClickListener(this);
        pakarCard.setOnClickListener(this);
        tentangCard.setOnClickListener(this);
    }

//    @Override
//    protected void onPause(){
//        super.onPause();
//
//        if(animationDrawable != null && animationDrawable.isRunning()){
//            animationDrawable.stop();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (animationDrawable != null && !animationDrawable.isRunning()){
//            animationDrawable.start();
//        }
//    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.cvDeteksi: i = new Intent(this, DeteksiActivity.class);startActivity(i);break;
            case R.id.cvPanduan: i = new Intent(this, PanduanActivity.class);startActivity(i);break;
            case R.id.cvGejala: i = new Intent(this, GejalaActivity.class);startActivity(i);break;
            case R.id.cvKerusakan: i = new Intent(this, KerusakanActivity.class);startActivity(i);break;
            case R.id.cvPakar: i = new Intent(this, PakarActivity.class);startActivity(i);break;
            case R.id.cvTentang: i = new Intent(this, TentangActivity.class);startActivity(i);break;
            default:break;
        }
    }
}