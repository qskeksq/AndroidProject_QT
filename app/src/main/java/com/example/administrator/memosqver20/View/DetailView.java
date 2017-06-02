package com.example.administrator.memosqver20.View;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.memosqver20.DetailFragment;
import com.example.administrator.memosqver20.Model.DataLab;
import com.example.administrator.memosqver20.Model.Memo;
import com.example.administrator.memosqver20.R;

public class DetailView implements View.OnClickListener, View.OnLongClickListener {

    Toolbar toolbar;
    TextView date;
    public EditText txt_detail_week, txt_detail_qt, txt_detail_thanks, txt_detail_prayer, txt_detail_journal;
    ImageView btn_add_thanks, btn_add_prayer;
    FloatingActionButton fab;
    public CheckBox check_week, check_qt, check_thanks, check_prayer, check_journal;
    View view;
    DetailFragment fragment;

    boolean status = true;

    public DetailView(View view, DetailFragment fragment){

        this.view = view;
        this.fragment = fragment;
    }

    public void init(){
        findAddress();
        set();
        manageListener();
    }

//--------------------------------------------------------------------------------------------------
//  1. findViewById
//--------------------------------------------------------------------------------------------------

    public void findAddress(){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        date = (TextView) view.findViewById(R.id.txt_detail_date);
        txt_detail_week = (EditText) view.findViewById(R.id.txt_detail_week);
        txt_detail_qt = (EditText) view.findViewById(R.id.txt_detail_qt);
        txt_detail_thanks = (EditText) view.findViewById(R.id.txt_detail_thanks);
        txt_detail_prayer = (EditText) view.findViewById(R.id.txt_detail_prayer);
        txt_detail_journal = (EditText) view.findViewById(R.id.txt_detail_journal);
        btn_add_thanks = (ImageView) view.findViewById(R.id.btn_add_thanks);
        btn_add_prayer = (ImageView) view.findViewById(R.id.btn_add_prayer);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        check_week = (CheckBox) view.findViewById(R.id.check_week);
        check_qt = (CheckBox) view.findViewById(R.id.check_qt);
        check_thanks =(CheckBox) view.findViewById(R.id.check_thanks);
        check_prayer = (CheckBox) view.findViewById(R.id.check_prayer);
        check_journal = (CheckBox) view.findViewById(R.id.check_journal);

    }

//--------------------------------------------------------------------------------------------------
//   2. 리스너
//--------------------------------------------------------------------------------------------------

    public void manageListener(){

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 툴바 <- 버튼 리스너
                fragment.onBackPressed();
            }
        });
        btn_add_thanks.setOnClickListener(this);     // 감사 추가 버튼 리스너
        btn_add_prayer.setOnClickListener(this);     // 기도 추가 버튼 리스너
        fab.setOnClickListener(this);                // 클립보드로 복사 리스너
        fab.setOnLongClickListener(this);            // 클립보드 복사 취소 리스너
        txt_detail_week.addTextChangedListener(weekListener);
        txt_detail_qt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragment.setQTData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_detail_thanks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragment.setThanksData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_detail_prayer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragment.setPrayerData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_detail_journal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragment.setJournalData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private TextWatcher weekListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.setWeekData(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    // 2.1      -- 콜백 클릭 메소드
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_add_thanks:
                fragment.addThanks();
                break;
            case R.id.btn_add_prayer:
                fragment.addPrayer();
                break;
            case R.id.fab:
                fragment.copy();
                setClipBoardDisable();
                break;
        }
    }

    // 2.2      -- 콜백 롱클릭 메소드
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                setClipBoardDisable();
        }
        return false;
    }

//--------------------------------------------------------------------------------------------------
//   3. set 메소드
//--------------------------------------------------------------------------------------------------

    // 3.1 초기화
    public void set() {
        setEditDisabled();
        setClipBoardDisable();
        setThanksInvisible();
        setPrayerInvisible();
        setToolbar();
    }


    // 3.2 편집 불가 메소드
    public void setEditDisabled(){
        txt_detail_week.setEnabled(false);
        txt_detail_qt.setEnabled(false);
        txt_detail_thanks.setEnabled(false);
        txt_detail_prayer.setEnabled(false);
        txt_detail_journal.setEnabled(false);
        btn_add_thanks.setEnabled(false);
        btn_add_prayer.setEnabled(false);
    }

    // 3.2 편집 가능 메소드
    public void setEditable(){
        txt_detail_week.setEnabled(true);
        txt_detail_qt.setEnabled(true);
        txt_detail_thanks.setEnabled(true);
        btn_add_prayer.setEnabled(true);
        txt_detail_journal.setEnabled(true);
        // 어차피 편집 가능 모드에서 + 버튼을 누를 수 있으니까 안 보여도 됨
        btn_add_thanks.setEnabled(true);
        btn_add_prayer.setEnabled(true);
    }


    // 3.3 클립보드 사용
    public void setClipBoardEnabled(){

        check_week.setVisibility(View.VISIBLE);
        check_qt.setVisibility(View.VISIBLE);
        check_thanks.setVisibility(View.VISIBLE);
        check_prayer.setVisibility(View.VISIBLE);
        check_journal.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);

    }

    // 3.4 클립보드 완료 -- 복사 or 취소
    public void setClipBoardDisable(){
        check_week.setVisibility(View.GONE);
        check_qt.setVisibility(View.GONE);
        check_thanks.setVisibility(View.GONE);
        check_prayer.setVisibility(View.GONE);
        check_journal.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

    }


    // 3.5 감사 추가 보이기 & 안보이기

    public void setThanksInvisible(){
        txt_detail_thanks.setVisibility(View.GONE);
    }

    public void setThanksVisible(){
        txt_detail_prayer.setVisibility(View.VISIBLE);
    }

    // 3.6 기도 추가 보이기 & 안보이기
    public void setPrayerInvisible(){
        txt_detail_prayer.setVisibility(View.GONE);
    }

    public void setPrayerVisible(){
        txt_detail_prayer.setVisibility(View.VISIBLE);
    }

    // 3.7 아이콘 바꾸기
    public void changeEditableIcon(MenuItem item){
        switch (item.getItemId()){
            case R.id.read:
                if(status == true) {
                    item.setIcon(R.drawable.ic_action_edit);
                    status = false;
                } else {
                    item.setIcon(R.drawable.ic_action_read);
                    status = true;
                }
                break;
        }
    }

    // 3.8 툴바 관리
    public void setToolbar(){

        ((AppCompatActivity)fragment.getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)fragment.getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    // 3.9 대화상자 관리
    // 삭제 여부 묻기
    public void askDelete(final Memo memo){
        AlertDialog.Builder dialog = new AlertDialog.Builder(fragment.getContext());
        dialog.setTitle("삭제하시겠습니까");
        dialog.setNegativeButton("아니오", null);
        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataLab.getLab(view.getContext()).delete(memo); // TODO 뺴주기
                fragment.to_ListFragment();
            }
        });
        dialog.show();
    }

    // 저장 여부 묻기
    public void askSave(final Memo memo){
        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
        dialog.setTitle("저장하시겠습니까?");
        dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fragment.to_ListFragment();
            }
        });
        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataLab.getLab(view.getContext()).add(memo); // TODO 뺴주기
                fragment.to_ListFragment();
            }
        });
        dialog.show();
    }


//--------------------------------------------------------------------------------------------------
//    4. 데이터 받기
//--------------------------------------------------------------------------------------------------

    public void setWeekView(String week){
        txt_detail_week.setText(week);
    }

    public void setQTView(String qt){
        txt_detail_qt.setText(qt);
    }

    public void setThanksView(String thanks){
        txt_detail_thanks.setText(thanks);
    }

    public void setPrayerView(String prayer){
        txt_detail_prayer.setText(prayer);
    }

    public void setJournalView(String journal){
        txt_detail_journal.setText(journal);
    }

    public void setDate(String time){
        date.setText(time);
    }



}
