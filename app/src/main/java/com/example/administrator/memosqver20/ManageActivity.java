package com.example.administrator.memosqver20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.memosqver20.Design.ManageInterface;

import java.util.UUID;

public class ManageActivity extends AppCompatActivity implements ManageInterface {

    public static final String DATA_KEY = "resId";

    DetailFragment detailFragment;
    ListFragment listFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        init();

    }

    public void init(){

        listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).commit();
    }

    // 그냥 보냄 -- 입력 창
    @Override
    public void changeFragment() {
        detailFragment = new DetailFragment(); // 아하!!! 이것을 다시 만들어 주지 않으면 계속 값이 저장되 있구나!!!!
        Bundle args =  new Bundle();  // 아하!!!!!!! 그냥이라도 getArgument 하려면 일단 bundle 에 값을 넣어 전달해야 하는구나!!
        detailFragment.setArguments(args); // 아하!!!!!! 애초에 그곳에서는 intent 에서 값을 빼내서 전달하는 방식아라 근본이 다름
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment).commit();
    }

    // 데이터를 담아 보냄 -- 상세보기 창
    public void changeFragment(UUID uuid){
        detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(DATA_KEY, uuid);
        detailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment).commit();
    }


}