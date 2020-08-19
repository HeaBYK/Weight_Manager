package com.example.weight_manager.handlerAndAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.weight_manager.R;
import com.example.weight_manager.getterSetter.Kilocal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchAdapters extends BaseAdapter implements Filterable {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList. (원본 데이터 리스트)
    public ArrayList<Kilocal> listViewItemList = new ArrayList<Kilocal>() ;
    // 필터링된 결과 데이터를 저장하기 위한 ArrayList. 최초에는 전체 리스트 보유.
    private ArrayList<Kilocal> filteredItemList = listViewItemList ;

    private SearchAdapters adapter;
    Filter listFilter ;

    // ListViewAdapter의 생성자
    public SearchAdapters() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return filteredItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.kcal_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView workName = (TextView) convertView.findViewById(R.id.kcalWorkNameTxt) ;
        TextView kcalPerHour = (TextView) convertView.findViewById(R.id.kcalPerHourTxt) ;
        TextView timePer100Kcal = (TextView) convertView.findViewById(R.id.timePer100KcalTxt) ;

        // Data Set(filteredItemList)에서 position에 위치한 데이터 참조 획득
        Kilocal listViewItem = filteredItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        workName.setText(listViewItem.getWorkName());
        kcalPerHour.setText(listViewItem.getKcalPerHour());
        timePer100Kcal.setText(listViewItem.getTimePer100Kcal());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String workName, String kcalPerHour, String timePer100Kcal) {
        Kilocal item = new Kilocal();

        item.setWorkName(workName);
        item.setKcalPerHour(kcalPerHour);
        item.setTimePer100Kcal(timePer100Kcal);

        listViewItemList.add(item);
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }

        return listFilter ;
    }

    // TODO : filtering item.
    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = listViewItemList ;
                results.count = listViewItemList.size() ;
            } else {
                ArrayList<Kilocal> itemList = new ArrayList<Kilocal>() ;

                for (Kilocal item : listViewItemList) {
                    if (item.getWorkName().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.getKcalPerHour().toUpperCase().contains(constraint.toString().toUpperCase())||
                            item.getTimePer100Kcal().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // update listview by filtered data list.
            filteredItemList = (ArrayList<Kilocal>) results.values ;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }

    private final static Comparator<Kilocal> compNameAsc = new Comparator<Kilocal>() {
        @Override
        public int compare(Kilocal o1, Kilocal o2) {
            return o1.getWorkName().compareTo(o2.getWorkName());
        }
    };
    private final static Comparator<Kilocal> compNameDesc = new Comparator<Kilocal>() {
        @Override
        public int compare(Kilocal o1, Kilocal o2) {
            return o2.getWorkName().compareTo(o1.getWorkName());
        }
    };

    private final static Comparator<Kilocal> compHourAsc = new Comparator<Kilocal>() {
        @Override
        public int compare(Kilocal o1, Kilocal o2) {
            Integer o1KpH = Integer.parseInt(o1.getKcalPerHour().replace("Kcal",""));
            Integer o2KpH = Integer.parseInt(o2.getKcalPerHour().replace("Kcal",""));

            return Integer.compare(o1KpH,o2KpH);
        }
    }; private final static Comparator<Kilocal> compHourDesc = new Comparator<Kilocal>() {
        @Override
        public int compare(Kilocal o1, Kilocal o2) {
            Integer o1KpH = Integer.parseInt(o1.getKcalPerHour().replace("Kcal",""));
            Integer o2KpH = Integer.parseInt(o2.getKcalPerHour().replace("Kcal",""));

            return Integer.compare(o2KpH,o1KpH);
        }
    };

    private final static Comparator<Kilocal> comp100Asc = new Comparator<Kilocal>() {
        @Override
        public int compare(Kilocal o1, Kilocal o2) {
            Integer o1Time = Integer.parseInt(o1.getTimePer100Kcal().replace("분",""));
            Integer o2Time = Integer.parseInt(o2.getTimePer100Kcal().replace("분",""));

            return Integer.compare(o1Time,o2Time);
        }
    };private final static Comparator<Kilocal> comp100Desc = new Comparator<Kilocal>() {
        @Override
        public int compare(Kilocal o1, Kilocal o2) {
            Integer o1Time = Integer.parseInt(o1.getTimePer100Kcal().replace("분",""));
            Integer o2Time = Integer.parseInt(o2.getTimePer100Kcal().replace("분",""));

            return Integer.compare(o2Time,o1Time);
        }
    };

    public void sortNameAsc(){
        Collections.sort(listViewItemList,compNameAsc);
        this.notifyDataSetChanged();
    }
    public void sortNameDesc(){
        Collections.sort(listViewItemList,compNameDesc);
        this.notifyDataSetChanged();
    }

    public void sortHourAsc(){
        Collections.sort(listViewItemList,compHourAsc);
        this.notifyDataSetChanged();
    }
    public void sortHourDesc(){
        Collections.sort(listViewItemList,compHourDesc);
        this.notifyDataSetChanged();
    }

    public void sort100Asc(){
        Collections.sort(listViewItemList,comp100Asc);
        this.notifyDataSetChanged();
    }
    public void sort100Desc(){
        Collections.sort(listViewItemList,comp100Desc);
        this.notifyDataSetChanged();
    }
}
