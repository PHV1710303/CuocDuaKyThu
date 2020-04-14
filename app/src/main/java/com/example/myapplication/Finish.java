package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Finish extends AppCompatActivity {

    float[] petFinished;
    int[] petRank;
    int[] betCash;
    int[] bonus;
    int currCash;
    int startCash;
    int newCash;

    ImageView iv_FirstPet;
    ImageView iv_SecondPet;
    ImageView iv_ThirdPet;
    Button btn_explain;
    Button btn_Replay;
    TextView tv_Message;
    TextView tv_FinishCash;

    private void Mapping(){
        iv_FirstPet =   findViewById(R.id.iv_FirstPet);
        iv_SecondPet =  findViewById(R.id.iv_SecondPet);
        iv_ThirdPet =   findViewById(R.id.iv_ThirdPet);
        btn_explain =findViewById(R.id.btn_explain);
        btn_Replay =findViewById(R.id.btn_Replay);
        tv_Message =findViewById(R.id.tv_Message);
        tv_FinishCash =findViewById(R.id.tv_FinishCash);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_finish);
        Mapping();
        petFinished = new float[6];
        betCash = new int[6];
        petRank = new int[6];
        bonus = new int[6];

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            petFinished = bundle.getFloatArray("PetFinished");
            betCash = bundle.getIntArray("BetCash");
            currCash = bundle.getInt("CurrentCash", 99999999);
            startCash = bundle.getInt("StartCash", 99999999);
            bonus = bundle.getIntArray("Bonus");
        }

        petRank = ProcessRank(petFinished);

        ProcessLeaderBoard(iv_FirstPet, petRank[0]);
        ProcessLeaderBoard(iv_SecondPet, petRank[1]);
        ProcessLeaderBoard(iv_ThirdPet, petRank[2]);

        newCash = ProcessMoney(betCash, petRank, currCash);
        if(newCash < 0){
            newCash = 0;
        }
        int cashChanged = newCash - startCash;
        if(cashChanged == 0){
            tv_Message.setTextColor(Color.parseColor("#4CAF50"));
            tv_Message.setText("Hòa tiền\nKhông mất cũng không kiếm thêm được đồng nào.");
        }
        else if(cashChanged > 0){
            tv_Message.setTextColor(Color.parseColor("#4CAF50"));
            tv_Message.setText("Thắng cược\nBạn lời " + cashChanged + "$ so với ban đầu");
        }
        else {
            cashChanged = Math.abs(cashChanged);
            tv_Message.setTextColor(Color.parseColor("#FF4040"));
            tv_Message.setText("Thua cược\nBạn bị lỗ " + cashChanged + "$ so với ban đầu");
        }
        tv_FinishCash.setText(newCash + "$");

        iv_FirstPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show_DialogInfomation(petRank[0], 1);
            }
        });

        iv_SecondPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show_DialogInfomation(petRank[1], 2);
            }
        });

        iv_ThirdPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show_DialogInfomation(petRank[2], 3);
            }
        });

        btn_Replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Finish.this, "Loading...", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1 = new Intent(Finish.this, MainActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("Cash", newCash);
                        intent1.putExtras(bundle1);
                        startActivity(intent1);
                        Finish.this.finish();
                    }
                }, 200);
            }
        });

        btn_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show_DialogExplain();
            }
        });
    }

    private void Show_DialogExplain(){
        Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_explain);
        dialog.show();
    }

    private int[] ProcessRank(float[] petFinished){
        int[] petRank = new int[6];
        int fastest;
        for(int i = 0; i < petRank.length; i++){
            fastest = 0;
            for(int j = 1; j < petRank.length; j++){
                if(petFinished[j] > petFinished[fastest]){
                    fastest = j;
                }
            }
            petRank[i] = fastest;
            petFinished[fastest] = (float)0.0;
        }
        return petRank;
    }

    private void ProcessLeaderBoard(ImageView iv_Pet, int index){
        switch (index){
            case 0:
                iv_Pet.setImageResource(R.drawable.pet);
                break;
            case 1:
                iv_Pet.setImageResource(R.drawable.pet1);
                break;
            case 2:
                iv_Pet.setImageResource(R.drawable.pet2);
                break;
            case 3:
                iv_Pet.setImageResource(R.drawable.pet3);
                break;
            case 4:
                iv_Pet.setImageResource(R.drawable.pet4);
                break;
            case 5:
                iv_Pet.setImageResource(R.drawable.pet5);
                break;
        }
    }

    private int ProcessMoney(int[] betCash, int[] petRank, int currCash){
        int newCash = currCash;
        for(int i = 0; i < betCash.length; i++){
            int petIndex = petRank[i];
            if(betCash[petIndex] > 0){
                if(i == 0){
                    newCash = newCash + (betCash[petIndex] * bonus[petIndex]);
                }
                else if(i == 1){
                    newCash = newCash + betCash[petIndex];
                }
                else if(i == 2) {
                    newCash = newCash + (betCash[petIndex] / 2);
                }
            }
        }
        return newCash;
    }

    private String GetRankText(int petIndex, int rank){
        switch (rank){
            case 1:
                return "Về nhất (x" + bonus[petIndex] + ")";
            case 2:
                return "Về nhì (huề vốn)";
            case 3:
                return "Về ba (trừ một nửa số tiền đã đặt cược).";
        }
        return null;
    }

    private String GetBonusText(int petIndex, int rank){
        switch (rank){
            case 1:
                if(betCash[petIndex] > 0){
                    return "Nhận được " + (bonus[petIndex] * betCash[petIndex]) + "$";
                }
                else break;
            case 2:
                if(betCash[petIndex] > 0) {
                    return "Bạn không kiếm được tiền cũng không bị mất tiền.";
                }
                else break;
            case 3:
                if(betCash[petIndex] > 0) {
                    return "Bạn bị trừ " + (betCash[petIndex] / 2) + "$";
                }
                else break;
        }
        return "Bạn không đặt cược vào đây.";
    }

    private void Show_DialogInfomation(int petIndex, int rank){
        Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_infomation);

        TextView tv_PetName =       dialog.findViewById(R.id.tv_PetName);
        TextView tv_PetBetCashed =  dialog.findViewById(R.id.tv_PetBetCashed);
        TextView tv_PetBonus =      dialog.findViewById(R.id.tv_PetBonus);
        ImageView iv_PetImage =     dialog.findViewById(R.id.iv_PetImage);

        switch (petIndex){
            case 0:
                iv_PetImage.setImageResource(R.drawable.pet);
                tv_PetName.setText("Cào cào xanh");
                tv_PetBetCashed.setText(GetRankText(petIndex, rank));
                tv_PetBonus.setText(GetBonusText(petIndex, rank));
                break;
            case 1:
                iv_PetImage.setImageResource(R.drawable.pet1);
                tv_PetName.setText("Chuột tím");
                tv_PetBetCashed.setText(GetRankText(petIndex, rank));
                tv_PetBonus.setText(GetBonusText(petIndex, rank));
                break;
            case 2:
                iv_PetImage.setImageResource(R.drawable.pet2);
                tv_PetName.setText("Gà biết bay");
                tv_PetBetCashed.setText(GetRankText(petIndex, rank));
                tv_PetBonus.setText(GetBonusText(petIndex, rank));
                break;
            case 3:
                iv_PetImage.setImageResource(R.drawable.pet3);
                tv_PetName.setText("Đại bàng con");
                tv_PetBetCashed.setText(GetRankText(petIndex, rank));
                tv_PetBonus.setText(GetBonusText(petIndex, rank));
                break;
            case 4:
                iv_PetImage.setImageResource(R.drawable.pet4);
                tv_PetName.setText("Pi cà chúu");
                tv_PetBetCashed.setText(GetRankText(petIndex, rank));
                tv_PetBonus.setText(GetBonusText(petIndex, rank));
                break;
            case 5:
                iv_PetImage.setImageResource(R.drawable.pet5);
                tv_PetName.setText("Mãnh hổ");
                tv_PetBetCashed.setText(GetRankText(petIndex, rank));
                tv_PetBonus.setText(GetBonusText(petIndex, rank));
                break;
        }

        dialog.show();
    }

    @Override
    public void onBackPressed() { }
}
