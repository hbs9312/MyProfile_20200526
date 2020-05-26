package kr.co.tjoeun.myprofile_20200526;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import kr.co.tjoeun.myprofile_20200526.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setupEvent();
        setValues();
    }

    @Override
    public void setupEvent() {

        binding.profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, PhotoViewActivity.class);
                myIntent.putExtra("imgUrl", "https://img.insight.co.kr/static/2020/01/11/700/3x9v0a9je60h5r971fm4.jpg");
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void setValues() {

//        인터넷에 있는 이미지 불러오기 => 인터넷 연결 권한 필요
        Glide.with(mContext).load("https://img.insight.co.kr/static/2020/01/11/700/3x9v0a9je60h5r971fm4.jpg").into(binding.profileImg);


    }
}
