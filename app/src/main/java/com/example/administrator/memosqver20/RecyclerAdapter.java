package com.example.administrator.memosqver20;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.memosqver20.Design.ManageInterface;
import com.example.administrator.memosqver20.Model.Memo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MemoHolder> {

    List<Memo> datas;
    ManageInterface mi;

    // 0. 생성자
    public RecyclerAdapter(List<Memo> datas, ManageInterface mi){
        this.datas = datas;
        this.mi = mi;
    }

    // 1. 뷰 리턴
    @Override
    public MemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false); // 이거 뭐냐
        return new MemoHolder(view);
    }

    // 2. 데이터 연결
    @Override
    public void onBindViewHolder(MemoHolder holder, int position) {
        Memo memo = datas.get(position);
        holder.setNo(position+1);
        holder.setTitle(memo.getQt());
        holder.setDate(sdf(memo.getDate()));
        holder.setUUID(memo.getUuid());
    }

    // 3. 개수 리턴
    @Override
    public int getItemCount() {
        return datas.size();
    }

    // 날짜 가공 메소드
    public String sdf(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yy년 MM월 dd일 E요일");
        String result = sdf.format(date);
        return result;
    }


    // 홀더 클래스
    class MemoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // 위젯
        TextView txt_item_no, txt_item_title, txt_item_date;
        View convertView;
        // 받아오는 값
        UUID resId;


        // 0. 생성자
        public MemoHolder(View convertView){
            super(convertView);
            this.convertView = convertView;
            convertView.setOnClickListener(this);
            findAddress();
        }

        // 1. findViewById -- 좀 귀찮지만 일단 함수로 빼냈다
        public void findAddress(){
            txt_item_no = (TextView) convertView.findViewById(R.id.txt_item_no);
            txt_item_title = (TextView) convertView.findViewById(R.id.txt_item_title);
            txt_item_date = (TextView) convertView.findViewById(R.id.txt_item_date);
        }

        // 2. 리스너
        public void onClick(View view){
            // + 버튼에서 호출하는 메소드 오버로딩, 값을 받을 수 있도록 한다.
            mi.changeFragment(resId);
        }

        // 3. set 함수
        public void setNo(int number){
            txt_item_no.setText(number+"");
        }

        public void setTitle(String title){
            txt_item_title.setText(title);
        }

        public void setDate(String date){
            txt_item_date.setText(date);
        }

        public void setUUID(UUID uuid){
            resId = uuid;
        }

    }


}
