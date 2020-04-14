package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_Hinh;
    ImageView iv_Back;

    ImageView[] iv_Pet;

    ImageView iv_FirstPet;
    ImageView iv_FirstPet1;
    ImageView iv_FirstPet2;
    ImageView iv_FirstPet3;
    ImageView iv_FirstPet4;
    ImageView iv_FirstPet5;

    FrameLayout frameLayout;
    AnimationDrawable animationDrawable;
    ViewFlipper viewFlipper_TimeCounter;
    ViewFlipper viewFlipper_Signature;

    FrameLayout[] frameLayout_Pet;

    TextView tv_Cash;
    TextView tv_MainCash;
    Button btn_Reset;
    Button btn_Start;
    TextView[] tv_BetCash;
    TextView[] tv_BetMoney;
    Button[][] btn_PetMoney;
    TextView[] tv_Bonus;
    Button btn_explain;
    LinearLayout[] linearLayouts_Pet;

    EditText[] editText_PetMoney;
    Button[] btn_PetMoney_OK;
    int pref_Cash;

    DisplayMetrics displayMetrics = new DisplayMetrics();
    float widthScreen, heightScreen;

    float x_Max, x_Min;
    int[] petStatus_Arr = new int[6];
    int[] time_ToChangeStatus = new int[6];
    int STOP_CHANGINGSTATUS = 1000;
    int widthScreenBitmap = (int)widthScreen;
    boolean isEndCrop = false;
    int cropPoint = 60;

    int cash;
    int currCash;
    int[] betCash;
    int[] bonus;

    float[] petFinished;

    Bitmap bitmap_Road;
    Bitmap bitmap_Sky;

    private void Mapping(){
        iv_Pet = new ImageView[6];
        iv_Hinh = findViewById(R.id.iv_Hinh);
        iv_Back = findViewById(R.id.iv_Back);

        iv_Pet[0] = findViewById(R.id.iv_Pet);
        iv_Pet[1] = findViewById(R.id.iv_Pet1);
        iv_Pet[2] = findViewById(R.id.iv_Pet2);
        iv_Pet[3] = findViewById(R.id.iv_Pet3);
        iv_Pet[4] = findViewById(R.id.iv_Pet4);
        iv_Pet[5] = findViewById(R.id.iv_Pet5);

        iv_FirstPet =   findViewById(R.id.iv_FirstPet);
        iv_FirstPet1 =  findViewById(R.id.iv_FirstPet1);
        iv_FirstPet2 =  findViewById(R.id.iv_FirstPet2);
        iv_FirstPet3 =  findViewById(R.id.iv_FirstPet3);
        iv_FirstPet4 =  findViewById(R.id.iv_FirstPet4);
        iv_FirstPet5 =  findViewById(R.id.iv_FirstPet5);

        frameLayout =               findViewById(R.id.frameLayout);
        viewFlipper_TimeCounter =   findViewById(R.id.viewFlipper_TimeCounter);
        viewFlipper_Signature =     findViewById(R.id.viewFlipper_Signature);
        tv_MainCash =               findViewById(R.id.tv_MainCash);
        frameLayout_Pet =           new FrameLayout[6];
        tv_BetMoney =               new TextView[6];

        frameLayout_Pet[0] = findViewById(R.id.frameLayout_Pet);
        frameLayout_Pet[1] = findViewById(R.id.frameLayout_Pet1);
        frameLayout_Pet[2] = findViewById(R.id.frameLayout_Pet2);
        frameLayout_Pet[3] = findViewById(R.id.frameLayout_Pet3);
        frameLayout_Pet[4] = findViewById(R.id.frameLayout_Pet4);
        frameLayout_Pet[5] = findViewById(R.id.frameLayout_Pet5);

        tv_BetMoney[0] = findViewById(R.id.tv_BetMoney);
        tv_BetMoney[1] = findViewById(R.id.tv_BetMoney1);
        tv_BetMoney[2] = findViewById(R.id.tv_BetMoney2);
        tv_BetMoney[3] = findViewById(R.id.tv_BetMoney3);
        tv_BetMoney[4] = findViewById(R.id.tv_BetMoney4);
        tv_BetMoney[5] = findViewById(R.id.tv_BetMoney5);
    }

    private void DialogMapping(Dialog dialog){
        tv_BetCash =            new TextView[6];
        tv_Bonus =              new TextView[6];
        btn_PetMoney =          new Button[3][6];
        linearLayouts_Pet =     new LinearLayout[6];
        editText_PetMoney =     new EditText[6];
        btn_PetMoney_OK =       new Button[6];
        btn_explain =           dialog.findViewById(R.id.btn_explain);
        btn_Start =             dialog.findViewById(R.id.btn_Start);
        btn_Reset =             dialog.findViewById(R.id.btn_Reset);
        tv_Cash =               dialog.findViewById(R.id.tv_Cash);
        tv_BetCash[0] =         dialog.findViewById(R.id.tv_BetCash);
        tv_BetCash[1] =         dialog.findViewById(R.id.tv_BetCash1);
        tv_BetCash[2] =         dialog.findViewById(R.id.tv_BetCash2);
        tv_BetCash[3] =         dialog.findViewById(R.id.tv_BetCash3);
        tv_BetCash[4] =         dialog.findViewById(R.id.tv_BetCash4);
        tv_BetCash[5] =         dialog.findViewById(R.id.tv_BetCash5);
        tv_Bonus[0] =           dialog.findViewById(R.id.tv_Bonus);
        tv_Bonus[1] =           dialog.findViewById(R.id.tv_Bonus1);
        tv_Bonus[2] =           dialog.findViewById(R.id.tv_Bonus2);
        tv_Bonus[3] =           dialog.findViewById(R.id.tv_Bonus3);
        tv_Bonus[4] =           dialog.findViewById(R.id.tv_Bonus4);
        tv_Bonus[5] =           dialog.findViewById(R.id.tv_Bonus5);
        linearLayouts_Pet[0] =  dialog.findViewById(R.id.linearLayout_Pet);
        linearLayouts_Pet[1] =  dialog.findViewById(R.id.linearLayout_Pet1);
        linearLayouts_Pet[2] =  dialog.findViewById(R.id.linearLayout_Pet2);
        linearLayouts_Pet[3] =  dialog.findViewById(R.id.linearLayout_Pet3);
        linearLayouts_Pet[4] =  dialog.findViewById(R.id.linearLayout_Pet4);
        linearLayouts_Pet[5] =  dialog.findViewById(R.id.linearLayout_Pet5);
        btn_PetMoney[0][0] =    dialog.findViewById(R.id.btn_PetMoney_500);
        btn_PetMoney[1][0] =    dialog.findViewById(R.id.btn_PetMoney_200);
        btn_PetMoney[2][0] =    dialog.findViewById(R.id.btn_PetMoney_100);
        btn_PetMoney[0][1] =    dialog.findViewById(R.id.btn_PetMoney1_500);
        btn_PetMoney[1][1] =    dialog.findViewById(R.id.btn_PetMoney1_200);
        btn_PetMoney[2][1] =    dialog.findViewById(R.id.btn_PetMoney1_100);
        btn_PetMoney[0][2] =    dialog.findViewById(R.id.btn_PetMoney2_500);
        btn_PetMoney[1][2] =    dialog.findViewById(R.id.btn_PetMoney2_200);
        btn_PetMoney[2][2] =    dialog.findViewById(R.id.btn_PetMoney2_100);
        btn_PetMoney[0][3] =    dialog.findViewById(R.id.btn_PetMoney3_500);
        btn_PetMoney[1][3] =    dialog.findViewById(R.id.btn_PetMoney3_200);
        btn_PetMoney[2][3] =    dialog.findViewById(R.id.btn_PetMoney3_100);
        btn_PetMoney[0][4] =    dialog.findViewById(R.id.btn_PetMoney4_500);
        btn_PetMoney[1][4] =    dialog.findViewById(R.id.btn_PetMoney4_200);
        btn_PetMoney[2][4] =    dialog.findViewById(R.id.btn_PetMoney4_100);
        btn_PetMoney[0][5] =    dialog.findViewById(R.id.btn_PetMoney5_500);
        btn_PetMoney[1][5] =    dialog.findViewById(R.id.btn_PetMoney5_200);
        btn_PetMoney[2][5] =    dialog.findViewById(R.id.btn_PetMoney5_100);
        editText_PetMoney[0] =  dialog.findViewById(R.id.editText_PetMoney);
        editText_PetMoney[1] =  dialog.findViewById(R.id.editText_PetMoney1);
        editText_PetMoney[2] =  dialog.findViewById(R.id.editText_PetMoney2);
        editText_PetMoney[3] =  dialog.findViewById(R.id.editText_PetMoney3);
        editText_PetMoney[4] =  dialog.findViewById(R.id.editText_PetMoney4);
        editText_PetMoney[5] =  dialog.findViewById(R.id.editText_PetMoney5);
        btn_PetMoney_OK[0] =    dialog.findViewById(R.id.btn_PetMoney_OK);
        btn_PetMoney_OK[1] =    dialog.findViewById(R.id.btn_PetMoney_OK1);
        btn_PetMoney_OK[2] =    dialog.findViewById(R.id.btn_PetMoney_OK2);
        btn_PetMoney_OK[3] =    dialog.findViewById(R.id.btn_PetMoney_OK3);
        btn_PetMoney_OK[4] =    dialog.findViewById(R.id.btn_PetMoney_OK4);
        btn_PetMoney_OK[5] =    dialog.findViewById(R.id.btn_PetMoney_OK5);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Mapping();

        pref_Cash = Get_SharePreferences_Cash();
        if(pref_Cash == -1){
            Set_SharePreferences_Cash(5000);
            pref_Cash = Get_SharePreferences_Cash();
        }

        petFinished = new float[6];
        bonus = new int[6];

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int temp = 99999998;
        if(bundle != null) {
            temp = bundle.getInt("Cash", 99999998);
        }
        if(temp == 99999998){
            cash = pref_Cash;
        }
        else {
            cash = temp;
            Set_SharePreferences_Cash(cash);
        }

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthScreen = displayMetrics.widthPixels;
        heightScreen = displayMetrics.heightPixels;
        widthScreenBitmap = getWidthScreenBitmap();

        bitmap_Road = BitmapFactory.decodeResource(getResources(), R.drawable.foreground);
        bitmap_Sky = BitmapFactory.decodeResource(getResources(), R.drawable.galaxy);
        iv_Hinh.setImageBitmap(ForeBackgroundRepeat_Start(cropPoint,bitmap_Road));

        x_Max = displayMetrics.widthPixels/8*6;
        x_Min = -200 ;
        for(int i=0; i < petStatus_Arr.length; i++)
        {
            petStatus_Arr[i] = 3;
        }

        tv_MainCash.setText(cash + "$");

        ShowDialog();

        viewFlipper_Signature.startFlipping();

        for(int i = 0; i < iv_Pet.length; i++){
            final int index = i;
            iv_Pet[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Show_DialogInfomation(index, false);
                }
            });
        }
    }

    private void Set_SharePreferences_Cash(int cash){
        SharedPreferences sharePreferences_Cash = getSharedPreferences("share_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreferences_Cash.edit();
        editor.clear();
        editor.putInt("Cash", cash);
        editor.apply();
    }

    private int Get_SharePreferences_Cash(){
        SharedPreferences sharedPreferences_Cash = getSharedPreferences("share_preference", Context.MODE_PRIVATE);
        return sharedPreferences_Cash.getInt("Cash", -1);
    }

    @Override
    public void onBackPressed() { }

    private void ShowDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_pick_pet);

        DialogMapping(dialog);

        ProcessCashBonus(tv_Bonus);

        betCash = new int[6];
        tv_Cash.setText(cash + "$");
        currCash = cash;
        for(int i = 0; i < tv_BetCash.length; i++){
            tv_BetCash[i].setText("0$");
            betCash[i] = 0;
        }

        btn_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show_DialogExplain();
            }
        });

        btn_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Cash.setText(cash + "$");
                currCash = cash;
                for(int i = 0; i < tv_BetCash.length; i++){
                    tv_BetCash[i].setText("0$");
                    betCash[i] = 0;
                }
            }
        });

        for(int i = 0; i < linearLayouts_Pet.length; i++){
            final int index = i;
            linearLayouts_Pet[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Show_DialogInfomation(index, true);
                }
            });
        }

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter = 0;
                for (int i = 0; i < betCash.length; i++){
                    if(betCash[i] == 0){
                        counter++;
                    }
                }
                if(counter < 6) {
                    dialog.cancel();
                    MainActivity.this.tv_MainCash.setText(currCash + "$");
                    Start();
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược để bắt đầu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        for(int k = 0; k < 3; k++){
            for(int i = 0; i < 6; i++){
                final int var_i = i;
                final int var_k = k;
                int temp = 0;
                switch (var_k){
                    case 0:
                        temp = 500;
                        break;
                    case 1:
                        temp = 200;
                        break;
                    case 2:
                        temp = 100;
                        break;
                }
                final int money = temp;
                btn_PetMoney[var_k][var_i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (currCash - money >= 0) {
                            currCash -= money;
                            tv_Cash.setText(currCash + "$");
                            betCash[var_i] += money;
                            tv_BetCash[var_i].setText(betCash[var_i] + "$");
                        } else {
                            Toast.makeText(MainActivity.this, "Không đủ tiền đặt cược!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        for(int i = 0; i < btn_PetMoney_OK.length; i++){
            final int var_i = i;
            btn_PetMoney_OK[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(editText_PetMoney[var_i].getText().toString().equals("")){
                        Toast.makeText(MainActivity.this, "Vui lòng nhập vào số tiền để đặt cược!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int money = Integer.parseInt(editText_PetMoney[var_i].getText().toString());
                    if(money == 0){
                        Toast.makeText(MainActivity.this, "-_- Đặt nhiều tiền lên đêy", Toast.LENGTH_SHORT).show();
                    }
                    else if (currCash - money >= 0) {
                        currCash -= money;
                        tv_Cash.setText(currCash + "$");
                        betCash[var_i] += money;
                        tv_BetCash[var_i].setText(betCash[var_i] + "$");
                        editText_PetMoney[var_i].setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Không đủ tiền đặt cược!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        tv_Cash.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Set_SharePreferences_Cash(5000);
                pref_Cash = Get_SharePreferences_Cash();
                cash = pref_Cash;
                tv_Cash.setText(pref_Cash+"$");
                return false;
            }
        });

        dialog.show();
    }

    private void Show_DialogExplain(){
        Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_explain);
        dialog.show();
    }

    private void Show_DialogInfomation(int petIndex, boolean isInitial){
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
                if(isInitial){
                    tv_PetBetCashed.setText("");
                }
                else {
                    if (betCash[petIndex] > 0) {
                        tv_PetBetCashed.setText("Bạn đã đặt cược " + betCash[petIndex] + "$ vào bé này.");
                    } else {
                        tv_PetBetCashed.setText("Bạn không đặt cược cho bé này.");
                    }
                }
                tv_PetBonus.setText("Nếu bé này về nhất, bạn được nhận gấp " + bonus[petIndex] + " lần tiền đã cược.");
                break;
            case 1:
                iv_PetImage.setImageResource(R.drawable.pet1);
                tv_PetName.setText("Chuột tím");
                if(isInitial){
                    tv_PetBetCashed.setText("");
                }
                else {
                    if (betCash[petIndex] > 0) {
                        tv_PetBetCashed.setText("Bạn đã đặt cược " + betCash[petIndex] + "$ vào bé này.");
                    } else {
                        tv_PetBetCashed.setText("Bạn không đặt cược cho bé này.");
                    }
                }
                tv_PetBonus.setText("Nếu bé này về nhất, bạn được nhận gấp " + bonus[petIndex] + " lần tiền đã cược.");
                break;
            case 2:
                iv_PetImage.setImageResource(R.drawable.pet2);
                tv_PetName.setText("Gà biết bay");
                if(isInitial){
                    tv_PetBetCashed.setText("");
                }
                else {
                    if (betCash[petIndex] > 0) {
                        tv_PetBetCashed.setText("Bạn đã đặt cược " + betCash[petIndex] + "$ vào bé này.");
                    } else {
                        tv_PetBetCashed.setText("Bạn không đặt cược cho bé này.");
                    }
                }
                tv_PetBonus.setText("Nếu bé này về nhất, bạn được nhận gấp " + bonus[petIndex] + " lần tiền đã cược.");
                break;
            case 3:
                iv_PetImage.setImageResource(R.drawable.pet3);
                tv_PetName.setText("Đại bàng con");
                if(isInitial){
                    tv_PetBetCashed.setText("");
                }
                else {
                    if (betCash[petIndex] > 0) {
                        tv_PetBetCashed.setText("Bạn đã đặt cược " + betCash[petIndex] + "$ vào bé này.");
                    } else {
                        tv_PetBetCashed.setText("Bạn không đặt cược cho bé này.");
                    }
                }
                tv_PetBonus.setText("Nếu bé này về nhất, bạn được nhận gấp " + bonus[petIndex] + " lần tiền đã cược.");
                break;
            case 4:
                iv_PetImage.setImageResource(R.drawable.pet4);
                tv_PetName.setText("Pi cà chúu");
                if(isInitial){
                    tv_PetBetCashed.setText("");
                }
                else {
                    if (betCash[petIndex] > 0) {
                        tv_PetBetCashed.setText("Bạn đã đặt cược " + betCash[petIndex] + "$ vào bé này.");
                    } else {
                        tv_PetBetCashed.setText("Bạn không đặt cược cho bé này.");
                    }
                }
                tv_PetBonus.setText("Nếu bé này về nhất, bạn được nhận gấp " + bonus[petIndex] + " lần tiền đã cược.");
                break;
            case 5:
                iv_PetImage.setImageResource(R.drawable.pet5);
                tv_PetName.setText("Mãnh hổ");
                if(isInitial){
                    tv_PetBetCashed.setText("");
                }
                else {
                    if (betCash[petIndex] > 0) {
                        tv_PetBetCashed.setText("Bạn đã đặt cược " + betCash[petIndex] + "$ vào bé này.");
                    } else {
                        tv_PetBetCashed.setText("Bạn không đặt cược cho bé này.");
                    }
                }
                tv_PetBonus.setText("Nếu bé này về nhất, bạn được nhận gấp " + bonus[petIndex] + " lần tiền đã cược.");
                break;
        }

        dialog.show();
    }

    private void ProcessCashBonus(TextView[] tv_Bonus){
        Random rd = new Random();
        for(int i = 0; i < tv_Bonus.length; i++){
            bonus[i] = rd.nextInt(11) + 3;
            if(bonus[i] >= 5){
                if(rd.nextInt(4) != 3){
                    bonus[i] = rd.nextInt(3) + 3;
                }
            }
            tv_Bonus[i].setText("x" + bonus[i]);
        }
    }

    private void Start(){
        viewFlipper_TimeCounter.setVisibility(View.VISIBLE);
        viewFlipper_TimeCounter.startFlipping();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RacingStart(bitmap_Road);
                viewFlipper_TimeCounter.stopFlipping();
                viewFlipper_TimeCounter.setVisibility(View.GONE);
                RacingStart(bitmap_Road);
            }
        }, 3000);
    }

    private int getWidthScreenBitmap(){
        float result = (float)widthScreen/500*384;
        return (int)result;
    }

    private void RacingStart(final Bitmap bitmap){
        Pet_Start();
        new CountDownTimer(99999999, 10){

            int time1 = 0, time2 = 0;
            int numFirst, numLast;
            boolean is_Change_Status = false;

            int witdh_start_crop = 5;
            Bitmap result = Backgroud_Repeat(bitmap_Sky, witdh_start_crop);

            @Override
            public void onTick(long l) {
                result = Backgroud_Repeat(result, witdh_start_crop);
                iv_Back.setImageBitmap(result);

                cropPoint += 20;
                bitmap_Road = ForeBackgroundRepeat_Start(cropPoint, bitmap);
                iv_Hinh.setImageBitmap(bitmap_Road);

                time1++;
                time2++;

                if(time1 == 3){
                    DisplayBetMoney(tv_BetMoney);
                }
                if(time1 == 45){
                    HideBetMoney(tv_BetMoney);
                }

                if(isEndCrop){
                    countDownTimer_RacingEnd.start();
                    this.cancel();
                }

                if(!is_Change_Status){
                    is_Change_Status = true;
                    Time_ToChangeStatus_Init();
                    time2 = 0;
                }

                if(time2 >= time_ToChangeStatus[0]) {
                    PetMoving(frameLayout_Pet[0], true, 0);
                    time_ToChangeStatus[0] = STOP_CHANGINGSTATUS;
                    is_Change_Status = Check_IsChangeStatus();
                }
                else {
                    PetMoving(frameLayout_Pet[0], false, 0);
                }

                if(time2 >= time_ToChangeStatus[1]) {
                    PetMoving(frameLayout_Pet[1], true, 1);
                    time_ToChangeStatus[1] = STOP_CHANGINGSTATUS;
                    is_Change_Status = Check_IsChangeStatus();
                }
                else {
                    PetMoving(frameLayout_Pet[1], false, 1);
                }

                if(time2 >= time_ToChangeStatus[2]) {
                    PetMoving(frameLayout_Pet[2], true, 2);
                    time_ToChangeStatus[2] = STOP_CHANGINGSTATUS;
                    is_Change_Status = Check_IsChangeStatus();
                }
                else {
                    PetMoving(frameLayout_Pet[2], false, 2);
                }

                if(time2 >= time_ToChangeStatus[3]) {
                    PetMoving(frameLayout_Pet[3], true, 3);
                    time_ToChangeStatus[3] = STOP_CHANGINGSTATUS;
                    is_Change_Status = Check_IsChangeStatus();
                }
                else {
                    PetMoving(frameLayout_Pet[3], false, 3);
                }

                if(time2 >= time_ToChangeStatus[4]) {
                    PetMoving(frameLayout_Pet[4], true, 4);
                    time_ToChangeStatus[4] = STOP_CHANGINGSTATUS;
                    is_Change_Status = Check_IsChangeStatus();
                }
                else {
                    PetMoving(frameLayout_Pet[4], false, 4);
                }

                if(time2 >= time_ToChangeStatus[5]) {
                    PetMoving(frameLayout_Pet[5], true, 5);
                    time_ToChangeStatus[5] = STOP_CHANGINGSTATUS;
                    is_Change_Status = Check_IsChangeStatus();
                }
                else {
                    PetMoving(frameLayout_Pet[5], false, 5);
                }

                Find_FirstPet();
                numFirst = Count_NumberFirstPet();
                numLast = Count_NumberLastPet();
                if(numFirst > 1){
                    ProcessFirstPet(numFirst);
                }
                if(numLast > 1){
                    ProcessLastPet(numLast);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private int Count_NumberFirstPet(){
        int numberFirstPet = 0;
        float[] pet_Locate = new float[6];

        pet_Locate[0] = frameLayout_Pet[0].getTranslationX();
        pet_Locate[1] = frameLayout_Pet[1].getTranslationX();
        pet_Locate[2] = frameLayout_Pet[2].getTranslationX();
        pet_Locate[3] = frameLayout_Pet[3].getTranslationX();
        pet_Locate[4] = frameLayout_Pet[4].getTranslationX();
        pet_Locate[5] = frameLayout_Pet[5].getTranslationX();

        for(int i = 0; i < pet_Locate.length; i++){
            if(pet_Locate[i] >= x_Max){
                numberFirstPet++;
            }
        }

        return numberFirstPet;
    }

    private void ProcessFirstPet(int numberFirstPet){
        if(numberFirstPet < 2){
            return;
        }
        int[] firstPets = new int[numberFirstPet];
        Random rd = new Random();
        int newFirst = rd.nextInt(numberFirstPet);

        int k = 0;
        for (int i = 0; i < frameLayout_Pet.length; i++){
            if(frameLayout_Pet[i].getTranslationX() >= x_Max){
                firstPets[k] = i;
                k++;
            }
        }

        petStatus_Arr[firstPets[newFirst]] = 3;
        for (int i = 0; i < firstPets.length; i++) {
            if(i != newFirst){
                petStatus_Arr[firstPets[i]] = 0;
            }
        }
    }

    private int Count_NumberLastPet(){
        int numberLastPet = 0;

        for(int i = 0; i < frameLayout_Pet.length; i++){
            if(frameLayout_Pet[i].getTranslationX() <= x_Min){
                numberLastPet++;
            }
        }

        return numberLastPet;
    }

    private void ProcessLastPet(int numberLastPet){
        if(numberLastPet < 2){
            return;
        }
        int[] lastPets = new int[numberLastPet];
        Random rd = new Random();
        int newLast = rd.nextInt(numberLastPet);

        int k = 0;
        for (int i = 0; i < frameLayout_Pet.length; i++){
            if(frameLayout_Pet[i].getTranslationX() <= x_Min){
                lastPets[k] = i;
                k++;
            }
        }

        petStatus_Arr[lastPets[newLast]] = 0;
        for (int i = 0; i < lastPets.length; i++) {
            if(i != newLast){
                petStatus_Arr[lastPets[i]] = 3;
            }
        }
    }

    private void HideBetMoney(TextView[] tv_BetMoney){
        for(int i = 0; i < tv_BetMoney.length; i++){
            tv_BetMoney[i].setVisibility(View.GONE);
        }
    }

    private void DisplayBetMoney(TextView[] tv_BetMoney){
        for(int i = 0; i < tv_BetMoney.length; i++){
            if(betCash[i] > 0){
                tv_BetMoney[i].setVisibility(View.VISIBLE);
                tv_BetMoney[i].setText(betCash[i] + "$");
            }
        }
    }

    private void Finish(){
        Intent intent = new Intent(MainActivity.this, Finish.class);
        Bundle bundle = new Bundle();
        bundle.putFloatArray("PetFinished", petFinished);
        bundle.putInt("CurrentCash", currCash);
        bundle.putInt("StartCash", cash);
        bundle.putIntArray("BetCash", betCash);
        bundle.putIntArray("Bonus", bonus);
        intent.putExtras(bundle);
        startActivity(intent);
        MainActivity.this.finish();
    }

    CountDownTimer countDownTimer_RacingEnd = new CountDownTimer(99999999, 15) {

        int time = 0;
        @Override
        public void onTick(long l) {
            time++;
            PetEnd(frameLayout_Pet[0]);
            PetEnd(frameLayout_Pet[1]);
            PetEnd(frameLayout_Pet[2]);
            PetEnd(frameLayout_Pet[3]);
            PetEnd(frameLayout_Pet[4]);
            PetEnd(frameLayout_Pet[5]);
            if(time == 1){
                DisplayBetMoney(tv_BetMoney);
            }
            if(time >= 250){
                this.cancel();
                for(int i = 0; i < frameLayout_Pet.length; i++){
                    petFinished[i] = frameLayout_Pet[i].getTranslationX();
                    Finish();
                }
            }
        }

        @Override
        public void onFinish() {

        }
    };

    private int Check_FirstPet_Index(){
        int firstPet = 0;
        float firstPet_Locate = frameLayout_Pet[0].getTranslationX();
        float[] pet_Locate = new float[6];

        pet_Locate[1] = frameLayout_Pet[1].getTranslationX();
        pet_Locate[2] = frameLayout_Pet[2].getTranslationX();
        pet_Locate[3] = frameLayout_Pet[3].getTranslationX();
        pet_Locate[4] = frameLayout_Pet[4].getTranslationX();
        pet_Locate[5] = frameLayout_Pet[5].getTranslationX();

        for(int i = 1; i < 6; i++){
            if(firstPet_Locate < pet_Locate[i]){
                firstPet = i;
                firstPet_Locate = pet_Locate[i];
            }
        }

        return firstPet;
    }

    private void Find_FirstPet(){
        int firstPet = Check_FirstPet_Index();

        iv_FirstPet.setVisibility(View.GONE);
        iv_FirstPet1.setVisibility(View.GONE);
        iv_FirstPet2.setVisibility(View.GONE);
        iv_FirstPet3.setVisibility(View.GONE);
        iv_FirstPet4.setVisibility(View.GONE);
        iv_FirstPet5.setVisibility(View.GONE);

        switch (firstPet){
            case 0:
                iv_FirstPet.setVisibility(View.VISIBLE);
                break;
            case 1:
                iv_FirstPet1.setVisibility(View.VISIBLE);
                break;
            case 2:
                iv_FirstPet2.setVisibility(View.VISIBLE);
                break;
            case 3:
                iv_FirstPet3.setVisibility(View.VISIBLE);
                break;
            case 4:
                iv_FirstPet4.setVisibility(View.VISIBLE);
                break;
            case 5:
                iv_FirstPet5.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void Time_ToChangeStatus_Init(){
        Random rd = new Random();
        for(int i = 0; i < time_ToChangeStatus.length; i++){
            time_ToChangeStatus[i] = rd.nextInt(40) + 25;
        }
    }

    private boolean Check_IsChangeStatus(){
        int j = 0;
        for(int i = 0; i < time_ToChangeStatus.length; i++){
            if(time_ToChangeStatus[i] == STOP_CHANGINGSTATUS){
                j++;
            }
        }
        if (j == 6){
            return false;
        }
        else {
            return true;
        }
    }

    private void PetMoving(FrameLayout pet, boolean is_Change_Status, int petStatus_Index){
        int move, speed_Min, speed_Max;
        int petStatus = petStatus_Arr[petStatus_Index];
        Random rd = new Random();

        if(is_Change_Status){
            int status = rd.nextInt(4);
            petStatus = status;
            petStatus_Arr[petStatus_Index] = petStatus;
        }

        speed_Max = rd.nextInt(1) + 6;
        speed_Min = rd.nextInt(1) + 1;
        move = rd.nextInt(speed_Max-speed_Min)+speed_Min;
        if(petStatus >= 2) {
            if(pet.getTranslationX() < x_Max) {
                pet.setTranslationX(pet.getTranslationX() + move);
            }
        }
        else if(petStatus <= 1) {
            if(pet.getTranslationX() > x_Min) {
                pet.setTranslationX(pet.getTranslationX() - move);
            }
        }
        else {

        }
    }

    private void PetEnd(FrameLayout pet){
        pet.setTranslationX(pet.getTranslationX() + 5);
    }

    private Bitmap Backgroud_Repeat(Bitmap bitmap, int witdh_start_crop){
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        int w1,w2,h;
        h = bitmap.getHeight();
        w1 = bitmap.getWidth() - witdh_start_crop;
        w2 = witdh_start_crop;
        Bitmap bm1,bm2;
        bm1 = Bitmap.createBitmap(bitmap, witdh_start_crop, 0, w1, h);
        bm2 = Bitmap.createBitmap(bitmap, 0, 0, w2, h);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bm1,0,0,null);
        canvas.drawBitmap(bm2, w1,0,null);
        return result;
    }

    private Bitmap ForeBackgroundRepeat_Start(int cropPoint, Bitmap bitmap){
        int w;
        int height_Bimap = bitmap.getHeight();
        int width_Bitmap = bitmap.getWidth();
        Bitmap bm;

        Bitmap result = Bitmap.createBitmap(widthScreenBitmap,height_Bimap,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        w = width_Bitmap - cropPoint;
        if(w > widthScreenBitmap){
            bm = Bitmap.createBitmap(bitmap, cropPoint,0,widthScreenBitmap,height_Bimap);
            canvas.drawBitmap(bm,0,0,null);
        }
        else {
            cropPoint = width_Bitmap - widthScreenBitmap;
            bm = Bitmap.createBitmap(bitmap, cropPoint,0,widthScreenBitmap,height_Bimap);
            canvas.drawBitmap(bm,0,0,null);
            isEndCrop = true;
        }
        return result;
    }

    private void Pet_Start(){
        animationDrawable = (AnimationDrawable) iv_Pet[0].getDrawable();
        animationDrawable.start();
        animationDrawable = (AnimationDrawable) iv_Pet[1].getDrawable();
        animationDrawable.start();
        animationDrawable = (AnimationDrawable) iv_Pet[2].getDrawable();
        animationDrawable.start();
        animationDrawable = (AnimationDrawable) iv_Pet[3].getDrawable();
        animationDrawable.start();
        animationDrawable = (AnimationDrawable) iv_Pet[4].getDrawable();
        animationDrawable.start();
        animationDrawable = (AnimationDrawable) iv_Pet[5].getDrawable();
        animationDrawable.start();
    }
}
