package com.example.administrator.memosqver20.View;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.administrator.memosqver20.LauncherActivity;
import com.example.administrator.memosqver20.R;


public class LauncherView {



    public EditText txt_email, txt_password;
    public CheckBox check_remember;
    public Button button;
    LauncherActivity activity;

    public LauncherView(LauncherActivity activity) {
        this.activity = activity;
    }

    public void init(){
        findView();  // 1. findViewById
        listener();  // 2. 리스너
                     // 3. 어차피 메소드 실행은 1, 2 메소드처럼 거기서 될 것인데, 다만 데이터 값을 받아야 해서 단체로 말고 혼자 갔다.
    }

//--------------------------------------------------------------------------------------------------
//   1. findViewById
//--------------------------------------------------------------------------------------------------

    public void findView(){
        txt_email = (EditText) activity.findViewById(R.id.txt_email);
        txt_password = (EditText) activity.findViewById(R.id.txt_pw);
        check_remember = (CheckBox) activity.findViewById(R.id.check_remember);
        button = (Button) activity.findViewById(R.id.btn_login);
    }

//--------------------------------------------------------------------------------------------------
//   2. 리스너 관리 - 플로팅 버튼
//--------------------------------------------------------------------------------------------------

    public void listener(){
        button.setOnClickListener(clickListener);
        check_remember.setOnCheckedChangeListener(checkListener);
    }

    // 2.1 리스너-클릭
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO 기존의 값과 비교하여 같으면 다음 페이지로 넘어가고 아니면 토스트 메시지 반환
            activity.login();
        }
    };

    // 2.2 리스너-체크
    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                //TODO 기억하는 메소드
                activity.remember();
            } else {
                //TODO 기억한 내용을 삭제하는 메소드
                activity.delete();
            }
        }
    };

//--------------------------------------------------------------------------------------------------
//   3. set 함수 관리
//--------------------------------------------------------------------------------------------------

    // 3.1 저장 혹은 삭제한 내용을 다음 로그인 할 때 반영한다.
    public void load(String email, String password, boolean isChecked){
//        여기서 이것을 안 해주는 이유는 뷰에서는 값만 받아서 출력해주는 역할을 해주기 때문이다.
//        String email = sharedPreferences.getString(E_MAIL, "");
//        String password = sharedPreferences.getString(PASSWORD, "");
//        boolean isChecked = sharedPreferences.getBoolean(CHECKED, false);

        txt_email.setText(email);
        txt_password.setText(password);
        check_remember.setChecked(isChecked);
    }

}
