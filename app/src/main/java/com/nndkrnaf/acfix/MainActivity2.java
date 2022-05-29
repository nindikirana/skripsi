package com.nndkrnaf.acfix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nndkrnaf.acfix.admin.gejala.AdminGejalaActivity;
import com.nndkrnaf.acfix.admin.hasildeteksi.AdminHasilDeteksiActivity;
import com.nndkrnaf.acfix.admin.kerusakan.AdminKerusakanActivity;
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.pengetahuan.AdminPengetahuanActivity;
import com.nndkrnaf.acfix.admin.user.AdminUserActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private CardView pengetahuanCard, hasilDeteksiCard, gejalaCard, kerusakanCard, userCard, levelUserCard;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
//        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
//
//        animationDrawable.setEnterFadeDuration(5000);
//        animationDrawable.setExitFadeDuration(2000);

        pengetahuanCard = (CardView) findViewById(R.id.cv_A_Pengetahuan);
        hasilDeteksiCard = (CardView) findViewById(R.id.cv_A_Hasil_Deteksi);
        gejalaCard = (CardView) findViewById(R.id.cv_A_Gejala);
        kerusakanCard = (CardView) findViewById(R.id.cv_A_Kerusakan);
        userCard = (CardView) findViewById(R.id.cv_A_User);
        levelUserCard = (CardView) findViewById(R.id.cv_A_Level_User);

        pengetahuanCard.setOnClickListener(this);
        hasilDeteksiCard.setOnClickListener(this);
        gejalaCard.setOnClickListener(this);
        kerusakanCard.setOnClickListener(this);
        userCard.setOnClickListener(this);
        levelUserCard.setOnClickListener(this);
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
            case R.id.cv_A_Pengetahuan: i = new Intent(this, AdminPengetahuanActivity.class);startActivity(i);break;
            case R.id.cv_A_Hasil_Deteksi: i = new Intent(this, AdminHasilDeteksiActivity.class);startActivity(i);break;
            case R.id.cv_A_Gejala: i = new Intent(this, AdminGejalaActivity.class);startActivity(i);break;
            case R.id.cv_A_Kerusakan: i = new Intent(this, AdminKerusakanActivity.class);startActivity(i);break;
            case R.id.cv_A_User: i = new Intent(this, AdminUserActivity.class);startActivity(i);break;
            case R.id.cv_A_Level_User: i = new Intent(this, AdminLevelUserActivity.class);startActivity(i);break;
            default:break;
        }
    }
}