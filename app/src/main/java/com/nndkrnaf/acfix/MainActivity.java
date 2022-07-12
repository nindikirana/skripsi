package com.nndkrnaf.acfix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nndkrnaf.acfix.gejala.activity.GejalaActivity;
import com.nndkrnaf.acfix.hasil_deteksi.activity.HasilDeteksiActivity;
import com.nndkrnaf.acfix.kerusakan.activity.KerusakanActivity;
import com.nndkrnaf.acfix.pakar.PakarActivity;
import com.nndkrnaf.acfix.panduan.PanduanActivity;
import com.nndkrnaf.acfix.rules.activity.RuleActivity;
import com.nndkrnaf.acfix.tentang.TentangActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView deteksiCard, hasilDeteksiCard, gejalaCard, kerusakanCard, pakarCard, tentangCard;
    private TextView tvPanduan;
    private ImageView ivProfile;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        tvPanduan = (TextView) findViewById(R.id.tvPanduan);
        deteksiCard = (CardView) findViewById(R.id.cvDeteksi);
        hasilDeteksiCard = (CardView) findViewById(R.id.cvHasilDeteksi);
        gejalaCard = (CardView) findViewById(R.id.cvGejala);
        kerusakanCard = (CardView) findViewById(R.id.cvKerusakan);
        pakarCard = (CardView) findViewById(R.id.cvPakar);
        tentangCard = (CardView) findViewById(R.id.cvTentang);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);

        tvPanduan.setOnClickListener(this);
        deteksiCard.setOnClickListener(this);
        hasilDeteksiCard.setOnClickListener(this);
        gejalaCard.setOnClickListener(this);
        kerusakanCard.setOnClickListener(this);
        pakarCard.setOnClickListener(this);
        tentangCard.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.tvPanduan: i = new Intent(this, PanduanActivity.class);startActivity(i);break;
            case R.id.cvDeteksi: i = new Intent(this, RuleActivity.class);startActivity(i);break;
            case R.id.cvHasilDeteksi: i = new Intent(this, HasilDeteksiActivity.class);startActivity(i);break;
            case R.id.cvGejala: i = new Intent(this, GejalaActivity.class);startActivity(i);break;
            case R.id.cvKerusakan: i = new Intent(this, KerusakanActivity.class);startActivity(i);break;
            case R.id.cvPakar: i = new Intent(this, PakarActivity.class);startActivity(i);break;
            case R.id.cvTentang: i = new Intent(this, TentangActivity.class);startActivity(i);break;
            case R.id.ivProfile: i = new Intent(this, ProfileActivity.class);startActivity(i);break;
            default:break;
        }
    }
}