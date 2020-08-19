package com.example.weight_manager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.weight_manager.R;

public class Splash extends Activity {

    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        // 1초 후에 3초간 핸들러 실행

        handler.postDelayed(new splashHandler(),2000);
    }

    private class splashHandler implements  Runnable{

        @Override
        public void run() {
            // 로딩이 끝난 뒤, 로그인 화면으로 이동
            startActivity(new Intent(getApplication(),LogInActivity.class));
            // 로딩페이지를 Activity Stack 에서 제거
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            Splash.this.finish();
        }
    }

    public void onBackPressed(){
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 작동 안하게 함
    }
}