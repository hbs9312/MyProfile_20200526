package kr.co.tjoeun.myprofile_20200526;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

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

        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener pl = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
//                         허가된 상황 => 실제 건화 걸기

//                        실제 전화걸기 => 권한허가가 X, 앱이 강제 종료 => Tedpermission 권한 획득


                        String phoneNum = binding.phoneNumTxt.getText().toString();
                        Uri myUri = Uri.parse(String.format("tel:%s", phoneNum));
                        Intent myIntent = new Intent(Intent.ACTION_CALL,myUri);
                        startActivity(myIntent);

                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
//                      최종 거부되었을 때 처리할 행동
                        Toast.makeText(mContext, "권한이 거부되어 통화가 불가능합니다.", Toast.LENGTH_SHORT).show();

                    }
                };

//                권한 X => 얼럿으로 허용할지?
//                권한 이전 허용 => 곧바로 Granted 실행.

                TedPermission.with(mContext)
                        .setPermissionListener(pl)
                        .setDeniedMessage("거부하면 통화가 불가능함.\n설정에서 권한을 켜주세요.")
                        .setPermissions(Manifest.permission.CALL_PHONE)
                        .check();

            }
        });

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
