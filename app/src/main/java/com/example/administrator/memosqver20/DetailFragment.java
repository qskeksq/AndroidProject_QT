package com.example.administrator.memosqver20;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.memosqver20.Model.DataLab;
import com.example.administrator.memosqver20.Model.Memo;
import com.example.administrator.memosqver20.View.DetailView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DetailFragment extends Fragment {

    int countSaved = 0;    // 처음 작성할 때의 상태. 저장을 누른 후에는 수정으로 바뀜.
    boolean status = true;
    int thankCount = 1;
    String thankTemp = "";
    int prayerCount = 1;
    String prayerTemp = "";

    Memo memo;
    DetailView detailView;

//--------------------------------------------------------------------------------------------------
//    실행 영역
//--------------------------------------------------------------------------------------------------

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        checkArguments();
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        detailView = new DetailView(view, this);
        detailView.init();
        out();
        return view;
    }

    public void onPause(){
        super.onPause();
        DataLab.getLab(getContext()).update(memo);
    }
//--------------------------------------------------------------------------------------------------
//    메뉴
//--------------------------------------------------------------------------------------------------

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save:
                save();
                break;
            case R.id.read:
                read();
                detailView.changeEditableIcon(item);
                break;
            case R.id.copy:
                detailView.setClipBoardEnabled();
                break;
            case R.id.delete:
                delete(memo);
                break;
        }
        return true;
    }

//--------------------------------------------------------------------------------------------------
//    메소드 영역
//--------------------------------------------------------------------------------------------------

    // 데이터 넣어주기
    public void out(){
        detailView.setWeekView(memo.getWeek());
        detailView.setQTView(memo.getQt());
        detailView.setThanksView(memo.getThanks());
        detailView.setPrayerView(memo.getPrayer());
        detailView.setJournalView(memo.getJournal());
        detailView.setDate(sdf(memo.getDate()));
    }

    // save
    public void save(){
        if(getArguments().isEmpty()) {
            if (countSaved == 0) {
                DataLab.getLab(getContext()).add(memo);
                Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                countSaved++;
            } else {
                // TODO update 해줘야 함
                Toast.makeText(getContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
            }
        } else if(!getArguments().isEmpty()){
            Toast.makeText(getContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
        }
    }

    // read
    public void read(){
        if(status){
            detailView.setEditable();
            status = false;
            Toast.makeText(getContext(), "편집 모드", Toast.LENGTH_SHORT).show();
        } else {
            detailView.setEditDisabled();
            status = true;
            Toast.makeText(getContext(), "읽기 모드", Toast.LENGTH_SHORT).show();
        }
    }

    // copy
    public void copy(){
        String clipped = "";    // TODO 왜 public 으로 해 줘야 하는거냐
        if(detailView.check_week.isChecked()){
            clipped = clipped + "- 이번 주 말씀 \n" + detailView.txt_detail_week.getText().toString() + "\n";
        }
        if(detailView.check_qt.isChecked()){
            clipped = clipped + "- 오늘 말씀 \n" + detailView.txt_detail_qt.getText().toString() +"\n";
        }
        if(detailView.check_thanks.isChecked()){
            clipped = clipped + "- 감사 \n" + detailView.txt_detail_thanks.getText().toString() + "\n";
        }
        if(detailView.check_prayer.isChecked()){
            clipped = clipped + "- 기도 \n" + detailView.txt_detail_prayer.getText().toString() + "\n";
        }
        if(detailView.check_journal.isChecked()){
            clipped = clipped + "- 일기 \n" + detailView.txt_detail_journal.getText().toString();
        }

        if(!clipped.equals("")){
            ClipData clip = ClipData.newPlainText("text", clipped);
            ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(clip);
            Toast.makeText(getContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "클립보드가 비었습니다", Toast.LENGTH_SHORT).show();
        }

    }

    // delete
    public void delete(Memo memo){
        detailView.askDelete(memo);
    }

    // back
    public void onBackPressed(){
        if(getArguments().isEmpty()){
            if(countSaved == 0){
                detailView.askSave(memo);
            } else {
                to_ListFragment();
            }
        } else {
            to_ListFragment();
        }
    }

    // exit
    public void to_ListFragment(){ //TODO 인터페이스 사용 가능 여부와 비교하자
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).commit();
    }

    // 입력창 vs 읽기창
    public void checkArguments(){
        // 입력 모드
        if(getArguments().isEmpty()){
            memo = new Memo();
        }
        // 상세보기 모드
        else if(!getArguments().isEmpty()) {
            UUID resId = (UUID) getArguments().getSerializable(ManageActivity.DATA_KEY);
            memo = DataLab.getLab(getContext()).getMemo(resId);
        }
    }

    // 추가 버튼
    public void addThanks(){
        detailView.setThanksVisible();
        detailView.txt_detail_thanks.setEnabled(true);
        if(thankCount == 1){
            thankTemp = detailView.txt_detail_thanks.getText().toString() + thankCount + ".";
            detailView.txt_detail_thanks.setText(thankTemp);
            thankCount++;
        } else if(thankCount > 1){
            thankTemp = detailView.txt_detail_thanks.getText().toString() + "\n" +thankCount + ".";
            detailView.txt_detail_thanks.setText(thankTemp);
            thankCount++;
        }
    }

    public void addPrayer(){
        detailView.setPrayerVisible();
        detailView.txt_detail_prayer.setEnabled(true);
        if(prayerCount == 1){
            prayerTemp = detailView.txt_detail_prayer.getText().toString() + prayerCount + ".";
            detailView.txt_detail_prayer.setText(prayerTemp);
            prayerCount++;
        } else if(prayerCount > 1){
            thankTemp = detailView.txt_detail_prayer.getText().toString() + "\n" +prayerCount + ".";
            detailView.txt_detail_prayer.setText(prayerTemp);
            prayerCount++;
        }
    }


    public String sdf(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yy년 MM월 dd일 E요일");
        String result = sdf.format(date);
        return result;
    }

//--------------------------------------------------------------------------------------------------
//    뷰가 가지고 있다가 데이터를 보내주는 것임
//--------------------------------------------------------------------------------------------------

    public void setWeekData(String week){
        memo.setWeek(week);
    }

    public void setQTData(String qt){
        memo.setQt(qt);
    }

    public void setThanksData(String thanks){
        memo.setThanks(thanks);
    }

    public void setPrayerData(String prayer){
        memo.setPrayer(prayer);
    }

    public void setJournalData(String journal){
        memo.setJournal(journal);
    }


}
