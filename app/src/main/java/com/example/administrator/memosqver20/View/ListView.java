package com.example.administrator.memosqver20.View;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.memosqver20.ListFragment;
import com.example.administrator.memosqver20.R;
import com.example.administrator.memosqver20.RecyclerAdapter;

public class ListView {

    FloatingActionButton fab;
    Toolbar toolbar;
    RecyclerView recyclerView;
    View view;
    ListFragment fragment;

    public ListView(View fragmentView, ListFragment fragment){ // TODO 프래그먼트 전체를 넘기지 않는 방법
        view = fragmentView;
        this.fragment = fragment;
    }

    public void init(){
        viewAddress();     // 1. findViewById
        manageListener();  // 2. 리스너
        setToolbar();      // 3. set 메소드 관리
    }
//--------------------------------------------------------------------------------------------------
//   1. findViewById
//--------------------------------------------------------------------------------------------------

    public void viewAddress(){
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

//--------------------------------------------------------------------------------------------------
//   2. 리스너 관리 - 플로팅 버튼
//--------------------------------------------------------------------------------------------------

    public void manageListener(){
        fab.setOnClickListener(listener);
    }

    // 2.1 fab 리스너
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragment.to_DetailFrag();
        }
    };

//--------------------------------------------------------------------------------------------------
//   3. set 함수 관리
//--------------------------------------------------------------------------------------------------

    // 3.1 툴바 초기값
    public void setToolbar(){
        ((AppCompatActivity)view.getContext()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)view.getContext()).getSupportActionBar().hide();
    }

    // 3.2 리사이클러뷰
    public void setRecyclerView(RecyclerAdapter adapter){
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }


}
