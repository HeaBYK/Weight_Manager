package com.example.weight_manager.ui.videoview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.weight_manager.R;
import com.example.weight_manager.activity.NaviActivity;
import com.example.weight_manager.getterSetter.Work;
import com.example.weight_manager.getterSetter.WorkMenu;
import com.example.weight_manager.handlerAndAdapter.MyExpandableAdapter;
import java.util.ArrayList;

public class VideoViewFragment extends Fragment implements ExpandableListView.OnChildClickListener, NaviActivity.onKeyBackPressedListener{

    ExpandableListView elvMyListView;
    MyExpandableAdapter adapter;
    ArrayList<WorkMenu> menuList = new ArrayList<>();
    ArrayList<Work> warmingUpItem = new ArrayList<>();
    ArrayList<Work> cardioItem = new ArrayList<>();
    ArrayList<Work> chestItem = new ArrayList<>();
    ArrayList<Work> backItem = new ArrayList<>();
    ArrayList<Work> shoulderItem = new ArrayList<>();
    ArrayList<Work> armItem = new ArrayList<>();
    ArrayList<Work> legItem = new ArrayList<>();
    ArrayList<Work> absItem = new ArrayList<>();
    ArrayList<Work> fullBodyItem = new ArrayList<>();
    ArrayList<Work> WorkInfoAndTipsItem = new ArrayList<>();
    WorkMenu warmingUp,cardio,chest,back,shoulder,arm,leg,abs,fullBody,WorkInfoAndTips;
    Uri blogUri;
    String blogUrl;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videoview, container, false);
        elvMyListView = view.findViewById(R.id.elvMyListView);
        WorkMenuItemList();
        adapter = new MyExpandableAdapter(getContext(), menuList);
        elvMyListView.setAdapter(adapter);
        elvMyListView.setOnChildClickListener(this);
        return view;
    }

    private void createWorkMenu(){
        warmingUp = new WorkMenu(1, "워밍업 / 스트레칭", warmingUpItem);
        cardio = new WorkMenu(2, "유산소 운동", cardioItem);
        chest = new WorkMenu(3, "가슴 운동", chestItem);
        back = new WorkMenu(4, "등 운동", backItem);
        shoulder = new WorkMenu(5, "어깨 운동", shoulderItem);
        arm = new WorkMenu(6, "팔 운동", armItem);
        leg = new WorkMenu(7, "하체 운동", legItem);
        abs = new WorkMenu(8, "복근 운동", absItem);
        fullBody = new WorkMenu(9, "전신 운동",fullBodyItem);
        WorkInfoAndTips = new WorkMenu(10, "운동 팁",WorkInfoAndTipsItem);
    }

    private void insertWarmingUpItem() {
        // 워밍업 / 스트레칭
        warmingUpItem.add(new Work("운동 전 최고의 스트레칭! 10분만 따라해도 운동효과 대박!","https://www.youtube.com/watch?v=yyjOhsNEqtE",  "https://img.youtube.com/vi/yyjOhsNEqtE/0.jpg"));
        warmingUpItem.add(new Work("운동전 '워밍업 필라테스'로 부상방지, 운동집중도 높이기 (feat. 워밍업 실전편)","https://www.youtube.com/watch?v=6oM-IUsouMo",  "https://img.youtube.com/vi/6oM-IUsouMo/0.jpg"));
        warmingUpItem.add(new Work("운동 전 필수 워밍업!! 운동 효율이 뿜뿜!!","https://www.youtube.com/watch?v=TWgJvoG9OU4", "https://img.youtube.com/vi/TWgJvoG9OU4/0.jpg"));
        warmingUpItem.add(new Work("초간단! 전신 스트레칭 끝판왕","https://www.youtube.com/watch?v=2LyDkE7sDec", "https://img.youtube.com/vi/2LyDkE7sDec/0.jpg"));
        warmingUpItem.add(new Work("스쿼트 전 필수! 웜업 스트레칭(부상방지)","https://www.youtube.com/watch?v=UBVPNC1FBgc","https://img.youtube.com/vi/UBVPNC1FBgc/0.jpg"));
        menuList.add(warmingUp);
    }

    private void insertCardioItem() {
        // 유산소 운동
        cardioItem.add(new Work("집에서 칼로리 불태우는 최고의 유산소운동","https://www.youtube.com/watch?v=lKwZ2DU4P-A","https://img.youtube.com/vi/lKwZ2DU4P-A/0.jpg"));
        cardioItem.add(new Work("집에서 하는 유산소운동 다이어트","https://www.youtube.com/watch?v=VNQpP6C1fJg","https://img.youtube.com/vi/VNQpP6C1fJg/0.jpg"));
        cardioItem.add(new Work("전신 다이어트 유산소운동 [홈트레이닝]","https://www.youtube.com/watch?v=3VouSaW_LPw","https://img.youtube.com/vi/3VouSaW_LPw/0.jpg"));
        cardioItem.add(new Work("층간소음 없는 전신유산소 20분","https://www.youtube.com/watch?v=GQ_Dt7_Jfk8","https://img.youtube.com/vi/GQ_Dt7_Jfk8/0.jpg"));
        menuList.add(cardio);
    }

    private void insertChestItem() {
        // 가슴 운동
        chestItem.add(new Work("운동 전 필수! 상체 워밍업","https://www.youtube.com/watch?v=FsTkLYaUh_o&t","https://img.youtube.com/vi/FsTkLYaUh_o/0.jpg"));
        chestItem.add(new Work("김계란이 추천하는 가장 효율적인 상체 워밍업 루틴","https://www.youtube.com/watch?v=Xs-oEIPQPI8","https://img.youtube.com/vi/Xs-oEIPQPI8/0.jpg"));
        chestItem.add(new Work("가슴운동 20분 실전편 (겨드랑이 군살제거, 상체근력, 말린어깨, 굽은등)","https://www.youtube.com/watch?v=wZdrFoMKTwo","https://img.youtube.com/vi/wZdrFoMKTwo/0.jpg"));
        chestItem.add(new Work("10분 만에 집에서 가슴 작살내는 루틴","https://www.youtube.com/watch?v=c_5ENJWekbQ","https://img.youtube.com/vi/c_5ENJWekbQ/0.jpg"));
        chestItem.add(new Work("완벽한 벤치프레스 강의 [운동의 정석]","https://www.youtube.com/watch?v=0DsXTSHo3lU","https://img.youtube.com/vi/0DsXTSHo3lU/0.jpg"));
        menuList.add(chest);
    }

    private void insertBackItem() {
        // 등 운동
        backItem.add(new Work("최고의 등 운동, 완벽한 바벨로우 강의 (운동의 정석)","https://www.youtube.com/watch?v=EEqGCoTuYfQ","https://img.youtube.com/vi/EEqGCoTuYfQ/0.jpg"));
        backItem.add(new Work("덤벨없이 딱 14분! 집에서 등 화형시키는 루틴! 따라만하세요","https://www.youtube.com/watch?v=7SpV_ZJue68","https://img.youtube.com/vi/7SpV_ZJue68/0.jpg"));
        backItem.add(new Work("등 운동 입문하기 Step1 (feat.군살제거/탄탄탄 등 라인 만들기)","https://www.youtube.com/watch?v=8VTDS4tBjpA","https://img.youtube.com/vi/8VTDS4tBjpA/0.jpg"));
        backItem.add(new Work("기구없이 등운동 끝내기","https://www.youtube.com/watch?v=xQVQn6l6Iu4","https://img.youtube.com/vi/xQVQn6l6Iu4/0.jpg"));
        backItem.add(new Work("시티드 로우 l 가장 기본이 되는 등 운동","https://www.youtube.com/watch?v=pkKfWeQ9APQ","https://img.youtube.com/vi/pkKfWeQ9APQ/0.jpg"));
        menuList.add(back);
    }

    private void insertShoulderItem() {
        // 어깨 운동
        shoulderItem.add(new Work("밀리터리 프레스 기초 | 가장 기본이 되는 어깨 운동","https://www.youtube.com/watch?v=rRlMZzoutJw","https://img.youtube.com/vi/rRlMZzoutJw/0.jpg"));
        shoulderItem.add(new Work("사이드 레터럴 레이즈 기초 | 어깨를 더 넓어 보이게 만들어 봅시다","https://www.youtube.com/watch?v=iNgwwI3WBTo","https://img.youtube.com/vi/iNgwwI3WBTo/0.jpg"));
        shoulderItem.add(new Work("헬스장 어깨운동 루틴, 승모근 개입없이 삼각근만 불태우기!!!!","https://www.youtube.com/watch?v=kyyRxfmAUhY","https://img.youtube.com/vi/kyyRxfmAUhY/0.jpg"));
        shoulderItem.add(new Work("어깨 운동 이걸로 끝!!! 상체라인 예쁘게 만드는 방법","https://www.youtube.com/watch?v=yAv5Q_tEfkI","https://img.youtube.com/vi/yAv5Q_tEfkI/0.jpg"));
        shoulderItem.add(new Work("어깨운동 루틴 (초보자를위한, 6가지어깨운동)","https://www.youtube.com/watch?v=8COp_SJ64Ug","https://img.youtube.com/vi/8COp_SJ64Ug/0.jpg"));
        menuList.add(shoulder);
    }

    private void insertArmItem() {
        // 팔 운동
        armItem.add(new Work("여자 팔뚝살(안녕살) 완전 제거하는 운동 | 심으뜸 팔운동 루틴","https://www.youtube.com/watch?v=ePkyqQT3Enk","https://img.youtube.com/vi/ePkyqQT3Enk/0.jpg"));
        armItem.add(new Work("여자 팔뚝살(안녕살) 제거운동 5분 따라하기 | 실전편","https://www.youtube.com/watch?v=ckmG7ZF_lZQ","https://img.youtube.com/vi/ckmG7ZF_lZQ/0.jpg"));
        armItem.add(new Work("팔뚝 다이어트 운동 베스트4","https://www.youtube.com/watch?v=UYHfk45Yi2c","https://img.youtube.com/vi/UYHfk45Yi2c/0.jpg"));
        armItem.add(new Work("집에서 팔(이두,삼두) 작살내는 루틴 (덤벨필요x)","https://www.youtube.com/watch?v=aUaafm7Q8pQ","https://img.youtube.com/vi/aUaafm7Q8pQ/0.jpg"));
        armItem.add(new Work("바벨 컬 기초 | 팔을 굵게 만들고 싶으면 하세요","https://www.youtube.com/watch?v=Dlg0W_5mq98","https://img.youtube.com/vi/Dlg0W_5mq98/0.jpg"));
        menuList.add(arm);
    }

    private void insertLegItem() {
        // 다리 운동
        legItem.add(new Work("하체살 다리살 폭파 운동","https://www.youtube.com/watch?v=dDq9WEyEg3Q","https://img.youtube.com/vi/dDq9WEyEg3Q/0.jpg"));
        legItem.add(new Work("13분만에 하체 마비시키는 루틴! 근육통100% 옵니다. (누구나 집에서 가능)","https://www.youtube.com/watch?v=KXYi6bI-UPE","https://img.youtube.com/vi/KXYi6bI-UPE/0.jpg"));
        legItem.add(new Work("상하체불균형 : 하체비만 운동법","https://www.youtube.com/watch?v=UaX5G9pHvUM","https://img.youtube.com/vi/UaX5G9pHvUM/0.jpg"));
        legItem.add(new Work("허벅지 종아리 최고의 운동 BEST","https://www.youtube.com/watch?v=EDq180TqRIc","https://img.youtube.com/vi/EDq180TqRIc/0.jpg"));
        legItem.add(new Work("하루 10분 런지 (feat. 중둔근 강화, 힙업, 하체다이어트)","https://www.youtube.com/watch?v=CaT6kHxngJE","https://img.youtube.com/vi/CaT6kHxngJE/0.jpg"));
        legItem.add(new Work("하체비만 탈출 6분 프로그램","https://www.youtube.com/watch?v=dBtk6T-aWQ4","https://img.youtube.com/vi/dBtk6T-aWQ4/0.jpg"));
        menuList.add(leg);
    }

    private void insertAbsItem() {
        // 복부 운동
        absItem.add(new Work("[15분 뱃살 빼는 운동] 레전드 뱃살 돌려깎기 복부 운동으로 겹겹이 뱃살 굿빠이-!","https://www.youtube.com/watch?v=hDuUR2WhPNY","https://img.youtube.com/vi/hDuUR2WhPNY/0.jpg"));
        absItem.add(new Work("누워서하는 5분 복부운동!! 효과보장!","https://www.youtube.com/watch?v=7TLk7pscICk","https://img.youtube.com/vi/7TLk7pscICk/0.jpg"));
        absItem.add(new Work("무.조.건! 뱃살 빠지는 운동 베스트5","https://www.youtube.com/watch?v=iOSYLKBk894","https://img.youtube.com/vi/iOSYLKBk894/0.jpg"));
        absItem.add(new Work("초보자를 위한 하루 5분 복근 루틴","https://www.youtube.com/watch?v=dDd1hVjqxEo","https://img.youtube.com/vi/dDd1hVjqxEo/0.jpg"));
        absItem.add(new Work("완벽한 복근을 만들기 위한 8분 루틴! 따라만하세요! (누구나 집에서도 가능)","https://www.youtube.com/watch?v=jj6ze_eqmYI","https://img.youtube.com/vi/jj6ze_eqmYI/0.jpg"));
        absItem.add(new Work("지금부터 딱 3개월!! 하루 10분 복근운동","https://www.youtube.com/watch?v=d8rs6er8Hq0","https://img.youtube.com/vi/d8rs6er8Hq0/0.jpg"));
        menuList.add(abs);
    }

    private void insertFullBodyItem() {
        // 전신 운동
        fullBodyItem.add(new Work("살 빠지는 전신운동 루틴 with 피지컬갤러리","https://www.youtube.com/watch?v=s14NQ6Cz4QE","https://img.youtube.com/vi/s14NQ6Cz4QE/0.jpg"));
        fullBodyItem.add(new Work("8MIN TABATA WORKOUT | 전신 고강도 타바타 8분","https://www.youtube.com/watch?v=H7QollPZa9Q","https://img.youtube.com/vi/H7QollPZa9Q/0.jpg"));
        fullBodyItem.add(new Work("전신 다이어트 최고의 운동","https://www.youtube.com/watch?v=CTojmKLkWTo","https://img.youtube.com/vi/CTojmKLkWTo/0.jpg"));
        fullBodyItem.add(new Work("무조건 살빠지는 전신 홈트레이닝 6가지 (층간소음NO)","https://www.youtube.com/watch?v=94T_uKY2Vb8","https://img.youtube.com/vi/94T_uKY2Vb8/0.jpg"));
        fullBodyItem.add(new Work("맨몸 전신운동 홈트레이닝 7가지! 초보자분들, 딱 4주만 따라해보세요!","https://www.youtube.com/watch?v=zSJYAyoojdw","https://img.youtube.com/vi/zSJYAyoojdw/0.jpg"));
        menuList.add(fullBody);
    }

    private void insertWorkInfoAndTipsItem() {
        // 정보 / 팁
        WorkInfoAndTipsItem.add(new Work("운동프로그램 짜는 법 [기초 핵심]","https://www.youtube.com/watch?v=QCN6jSXkhR8",  "https://img.youtube.com/vi/QCN6jSXkhR8/0.jpg"));
        WorkInfoAndTipsItem.add(new Work("다이어트에 실패할 수밖에 없는 이유 (다이어트 식단의 실체)","https://www.youtube.com/watch?v=YEMth6JmLD4",  "https://img.youtube.com/vi/YEMth6JmLD4/0.jpg"));
        WorkInfoAndTipsItem.add(new Work("[비싼 상식] 몸무게 정체기인 사람 꼭 보세요!","https://www.youtube.com/watch?v=8BVGonKPWGY", "https://img.youtube.com/vi/8BVGonKPWGY/0.jpg"));
        WorkInfoAndTipsItem.add(new Work("김계란이 알려주는 '식단'에 따른 운동방법","https://www.youtube.com/watch?v=4lEm4pDL8ns", "https://img.youtube.com/vi/4lEm4pDL8ns/0.jpg"));
        WorkInfoAndTipsItem.add(new Work("생존.. 적게먹어도 살이 안빠지는 이유","https://www.youtube.com/watch?v=tVyEqBp9mSk","https://img.youtube.com/vi/tVyEqBp9mSk/0.jpg"));
        menuList.add(WorkInfoAndTips);
    }

    private void WorkMenuItemList() {

        createWorkMenu();
        insertWarmingUpItem();
        insertCardioItem();
        insertChestItem();
        insertBackItem();
        insertShoulderItem();
        insertArmItem();
        insertLegItem();
        insertAbsItem();
        insertFullBodyItem();
        insertWorkInfoAndTipsItem();
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
        blogUrl = adapter.getChild(groupPosition,childPosition).getUrl();
        openURL();
        return false;
    }

    public void openURL(){
        blogUri = Uri.parse(blogUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(blogUrl));
        this.startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //메인뷰 액티비티의 뒤로가기 callback 붙이기
        ((NaviActivity)context).setOnKeyBackPressedListener(this);
    }

    @Override
    public void onBackKey() {
        NaviActivity activity = (NaviActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        //액티비티의 콜백을 직접호출
        activity.onBackPressed();
    }
}
