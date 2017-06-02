package com.example.administrator.memosqver20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.memosqver20.View.LauncherView;

public class LauncherActivity extends AppCompatActivity {

    public static final String KEY = "settings";
    public static final String E_MAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CHECKED = "checked";

    LauncherView view;
    String email, password;
    boolean isChecked;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        // 이게 여기 있는 이유는 데이터 관리는 뷰의 영역이 아니기 때문. 프레젠터가 담당한다.
        sharedPreferences = getSharedPreferences(KEY, MODE_PRIVATE);

        view = new LauncherView(this);
        view.init();
        getValue();
        view.load(email, password, isChecked);

    }

    // 체크 될 때 프리퍼런스에 저장한다.   (체크리스너)
    public void remember(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(E_MAIL, view.txt_email.getText().toString());
        editor.putString(PASSWORD, view.txt_password.getText().toString());
        editor.putBoolean(CHECKED, view.check_remember.isChecked());
        editor.commit();
    }

    // 체크가 풀릴 때 데이터에서 삭제한다. (체크리스너)
    public void delete(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.clear();
    }

    // 버튼이 눌릴 때 로그인 메소드        (클릭리스너)
    public void login(){
        String email = view.txt_email.getText().toString();
        String password = view.txt_password.getText().toString();
        if(email.equals("qskeksq@gmail.com") && password.equals("tmdfl0530")){
            Intent intent = new Intent(this, ManageActivity.class);
            startActivity(intent);
        } else if(!email.equals("qskeksq@gmail.com")){
            Toast.makeText(getBaseContext(), "이메일 주소 틀림", Toast.LENGTH_SHORT).show();
        } else if(!password.equals("tmdfl0530")){
            Toast.makeText(getBaseContext(), "비밀번호 틀림", Toast.LENGTH_SHORT).show();
        }
    }

    // 저장된 값을 불러온다               (load 메소드)
    // load 에서 띄워줄 값을 불러오는 역할은 Presenter 의 역할!!!!)
    public void getValue(){
        email = sharedPreferences.getString(E_MAIL, "");
        password = sharedPreferences.getString(PASSWORD, "");
        isChecked = sharedPreferences.getBoolean(CHECKED, false);
    }

}
