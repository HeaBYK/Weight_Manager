package com.example.weight_manager.ui.workkcal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;
import com.example.weight_manager.R;
import com.example.weight_manager.handlerAndAdapter.SearchAdapters;

public class WorkKcalFragment extends Fragment {
    View view;
    SearchAdapters adapter;
    EditText searchTxt;
    Button kcalItemsBtn,kcalPerHourBtn,timePer100Btn;
    ListView lv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workkcallist, container, false);
        kcalItemsBtn = view.findViewById(R.id.kcalItemsBtn);
        kcalItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if(on){
                    adapter.sortNameAsc();
                }
                else{
                    adapter.sortNameDesc();
                }
            }
        });
        kcalPerHourBtn = view.findViewById(R.id.kcalPerHourBtn);
        kcalPerHourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    adapter.sortHourAsc();
                } else {
                    adapter.sortHourDesc();
                }
            }
        });
        timePer100Btn = view.findViewById(R.id.timePer100Btn);
        timePer100Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if(on){
                    adapter.sort100Asc();
                }
                else{
                    adapter.sort100Desc();
                }
            }
        });
        lvSetAdapter();     // 리스트뷰 생성 및 어댑터 연결
        searchItems();      // 어댑터에 아이템 삽입
        makeKcalItems();    // 리스트뷰에서 아이템 검색
        return view;
    }
    // 리스트뷰 생성 및 어댑터 연결
    private void lvSetAdapter(){
        lv = view.findViewById(R.id.lv);
        lv.setTextFilterEnabled(true);
        adapter = new SearchAdapters() ;
        lv.setAdapter(adapter);
    }

    // 어댑터에 아이템 삽입
    private void makeKcalItems(){
        adapter.addItem("목욕", "120Kcal", "50분");
        adapter.addItem("다림질", "140Kcal", "43분");
        adapter.addItem("수면", "50Kcal", "120분");
        adapter.addItem("신문 보기", "70Kcal", "86분");
        adapter.addItem("구두 닦기", "120Kcal", "50분");
        adapter.addItem("TV 보기", "70Kcal", "86분");
        adapter.addItem("세안 화장", "30Kcal", "200분");
        adapter.addItem("옷 입기", "102Kcal", "50분");
        adapter.addItem("빨래", "170Kcal", "35분");
        adapter.addItem("식사 준비", "170Kcal", "55분");
        adapter.addItem("식사", "80Kcal", "75분");
        adapter.addItem("잡담", "70Kcal", "80분");
        adapter.addItem("청소", "160Kcal", "38분");
        adapter.addItem("물건사기", "160Kcal", "50분");
        adapter.addItem("이불 널기", "310Kcal", "19분");
        adapter.addItem("이불 개기", "210Kcal", "29분");
        adapter.addItem("계단 오르기", "380Kcal", "35분");
        adapter.addItem("줄넘기", "280Kcal", "21분");
        adapter.addItem("테니스", "350Kcal", "17분");
        adapter.addItem("하이킹", "210Kcal", "29분");
        adapter.addItem("탁구", "250Kcal", "24분");
        adapter.addItem("걷기", "210Kcal", "29분");
        adapter.addItem("사이클", "240Kcal", "25분");
        adapter.addItem("조깅", "420Kcal", "14분");
        adapter.addItem("스키", "370Kcal", "16분");
        adapter.addItem("등산","196Kcal","31분");
        adapter.addItem("수영(평영)","273Kcal","22분");
        adapter.addItem("수영(자유형)","518Kcal","12분");
        adapter.addItem("에어로빅","126Kcal","48분");
        adapter.addItem("농구","200Kcal","30분");
        adapter.addItem("배구","200Kcal","30분");
        adapter.addItem("볼링","90Kcal","67분");
        adapter.addItem("수상스키","200Kcal","30분");
        adapter.addItem("조깅","196Kcal","31분");
        adapter.addItem("파도타기","176Kcal","34분");
        adapter.addItem("소프트볼","90Kcal","67분");
        adapter.addItem("야구","180Kcal","34분");
        adapter.addItem("피구","102Kcal","59분");
    }

    // 리스트뷰에서 아이템 검색
    private void searchItems(){
        searchTxt = view.findViewById(R.id.searchTxt);
        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString();
                if (filterText.length() > 0) {
                    lv.setFilterText(filterText);
                } else {
                    lv.clearTextFilter() ;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }
}
