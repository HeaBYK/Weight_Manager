package com.example.weight_manager.ui.weightlist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.weight_manager.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class ListFragment extends Fragment {
    private Button btnInsert,btnDate, btnSelect;
    int lastSelectedYear, lastSelectedMonth, lastSelectedDay, arrLength, deldatacnt = 0, calLength = 0, cnt = 0;
    Integer[] DayInt;
    Float[] WeightFloat;
    Float fweight;
    ArrayAdapter yearAdapter, monthAdapter, listviewAdapter;
    ArrayList<String> YearArrayList = new ArrayList<>();
    ArrayList<String> MonthArrayList = new ArrayList<>();
    ArrayList<String> ListViewArray = new ArrayList<>();
    ArrayList<Integer> FomatterArrayList = new ArrayList<Integer>();
    ArrayList<Integer> DayArrayList = new ArrayList<>();
    ArrayList<Float> WeightArrayList = new ArrayList<>();
    ArrayList<ILineDataSet> ChartArrayList = new ArrayList<>();
    ArrayList<Entry> values = new ArrayList<>();
    String year, month, day, spmonth, spday, spday_month;
    Date date;
    LineDataSet dataSet;
    LineData linedata;
    EditText Weight;
    TextView SwapText;
    Spinner yearSpinner, monthSpinner;
    LineChart chart;
    ListView listView;
    Switch Swap;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(),
            WeightListref = ref.child("WeightList").child(user.getUid()),
            Yearref = WeightListref.child("Year");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weightlist, container, false);

        Weight = view.findViewById(R.id.editWeight);
        chart = view.findViewById(R.id.linechart);
        chart.setVisibility(View.INVISIBLE);
        listView = view.findViewById(R.id.list_Weight);
        listView.setVisibility(View.INVISIBLE);
        Swap = view.findViewById(R.id.swap);
        SwapText = view.findViewById(R.id.swaptxt);

        btnDate = view.findViewById(R.id.btnDate);
        btnInsert = view.findViewById(R.id.btnInsert);
        btnSelect = view.findViewById(R.id.btnSelect);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });
        {
            btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    yearSpinner.setSelection(0);
                    String weightstr = Weight.getText().toString();
                    if (!weightstr.equals("")) {
                        fweight = Float.parseFloat(weightstr);
                    } else if (btnDate.getText().equals("날짜")) {
                        Toast.makeText(getContext(), "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "체중을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    year = Integer.toString(lastSelectedYear);
                    month = Integer.toString(lastSelectedMonth + 1);
                    day = Integer.toString(lastSelectedDay);

                    if (!year.equals("0") && !month.equals("1") && !day.equals("0") && !weightstr.equals("")) {
                        writeNewUser(user.getUid(), year, month, lastSelectedDay, fweight);
                        Weight.setText("");
                        Toast.makeText(getContext(), "입력이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                    btnDate.setText("날짜");
                }
            });
        }
        YearArrayList.add(0," ");
        SpinnerDateYear();

        yearSpinner = view.findViewById(R.id.spinner_year);
        yearAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, YearArrayList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpinnerDateMonth();
                MonthArrayList.clear();
                MonthArrayList.add(0," ");

                monthAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,MonthArrayList);
                monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                monthAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                monthSpinner.setAdapter(monthAdapter);
                monthSpinner.setSelection(0);

                monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spday = (String) adapterView.getItemAtPosition(i);
                        SpinnerDateDay(spday_month = yearSpinner.getSelectedItem().toString(), spday);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        monthSpinner = view.findViewById(R.id.spinner_month);


        {
            btnSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Swap.isChecked()) {
                        if (!monthSpinner.getSelectedItem().equals(" ") && !yearSpinner.getSelectedItem().equals(" ")) {
                            ListView();
                            chart.setVisibility(View.INVISIBLE);
                            SwapText.setText("체중 리스트");
                        } else {
                            Toast.makeText(getContext(), "확인하실 연도와 월을 선택해주세요. ", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if (!monthSpinner.getSelectedItem().equals(" ") && !yearSpinner.getSelectedItem().equals(" ")) {
                            LineChart();
                            listView.setVisibility(View.INVISIBLE);
                            SwapText.setText("체중 그래프");
                        } else {
                            Toast.makeText(getContext(), "확인하실 연도와 월을 선택해주세요. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        {
            Swap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if (!monthSpinner.getSelectedItem().equals(" ") && !yearSpinner.getSelectedItem().equals(" ")) {
                            ListView();
                            chart.setVisibility(View.INVISIBLE);
                            SwapText.setText("체중 리스트");
                        } else {
                            Toast.makeText(getContext(), "확인하실 연도와 월을 선택해주세요. ", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if (!monthSpinner.getSelectedItem().equals(" ") && !yearSpinner.getSelectedItem().equals(" ")) {
                            LineChart();
                            listView.setVisibility(View.INVISIBLE);
                            SwapText.setText("체중 그래프");
                        } else {
                            Toast.makeText(getContext(), "확인하실 연도와 월을 선택해주세요. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        return view;
    }

    public void buttonSelectDate(){
        final Calendar c = Calendar.getInstance();
        lastSelectedYear = c.get(Calendar.YEAR);
        lastSelectedMonth = c.get(Calendar.MONTH);
        lastSelectedDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    lastSelectedYear= year;
                    lastSelectedMonth = monthOfYear;
                    lastSelectedDay = dayOfMonth;
                    btnDate.setText(lastSelectedYear+"년 "+(lastSelectedMonth+1)+"월 "+lastSelectedDay+"일");
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        datePickerDialog.show();

    }

    private void writeNewUser (String Uid, String wyear, String wmonth, int wday, float wweight){
        WeightListref.child("Year").child(wyear).child("Month").child(wmonth).child("Day").child(String.valueOf(wday)).setValue(wweight);
    }

    private void SpinnerDateYear() {
        Yearref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                YearArrayList.add(key);
                Collections.sort(YearArrayList);
                yearSpinner.setSelection(0);
                yearAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                if (MonthArrayList.size() == 1){
                    yearSpinner.setSelection(0);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SpinnerDateMonth(){
        DatabaseReference Monthref = Yearref.child(yearSpinner.getSelectedItem().toString()).child("Month");
        Monthref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                MonthArrayList.add(key);
                Collections.sort(MonthArrayList);
                monthSpinner.setSelection(0);
                monthAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (DayArrayList.size() == 0){
                    monthSpinner.setSelection(0);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SpinnerDateDay(String spDay_Month, String spDay){
        DatabaseReference Dayref = Yearref.child(spDay_Month).child("Month").child(spDay).child("Day");
        deldatacnt = arrLength;
        cnt++;
        Dayref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DayArrayList.add(0,Integer.parseInt(snapshot.getKey()));
                WeightArrayList.add(0,Float.parseFloat(snapshot.getValue().toString()));

                arrLength = DayArrayList.size();
                calLength = arrLength-(arrLength-deldatacnt);
                if (cnt > 1) {
                    for (int i = 0; i < deldatacnt; i++) {
                        DayArrayList.remove(calLength-i);
                        WeightArrayList.remove(calLength-i);
                    }
                    deldatacnt = 0;
                    arrLength = DayArrayList.size();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String MonthString = Integer.toString((lastSelectedMonth+1));
                if(MonthArrayList.size()>1) {
                    monthSpinner.setSelection(MonthArrayList.indexOf(MonthString));
                }else{
                    monthSpinner.setSelection(0);
                }
                monthAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void LineChart(){
        ChartArrayList.clear();
        chart.invalidate();
        chart.setVisibility(View.VISIBLE);

        values.clear();

        DayInt = DayArrayList.toArray(new Integer[DayArrayList.size()]);
        WeightFloat = WeightArrayList.toArray(new Float[WeightArrayList.size()]);

        for (int i = 0; i<arrLength; i++) {
            if (!DayInt[i].equals("") && !WeightFloat[i].equals("")) {
                values.add(0,new Entry(DayInt[i], WeightFloat[i]));
                dataSet = new LineDataSet(values, "체중(kg)");
            }
        }

        ChartArrayList.add(dataSet);

        linedata = new LineData(ChartArrayList);

        dataSet.setLineWidth(3);
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setValueTextSize(10);
        for (int i=0;i<=31;i++){
            FomatterArrayList.add(i);
        }

        XAxis xAxis = chart.getXAxis();
        YAxis LyAxis = chart.getAxisLeft();
        YAxis RyAxis = chart.getAxisRight();
        xAxis.setTextSize(10);
        xAxis.setLabelCount(arrLength);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1.0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return FomatterArrayList.get((int)value % FomatterArrayList.size())+"일";
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        RyAxis.setDrawLabels(false);
        LyAxis.setTextSize(10);

        chart.setDescription(null);
        chart.setData(linedata);
    }

    private void ListView(){
        ListViewArray.clear();
        listView.invalidate();
        listView.setVisibility(View.VISIBLE);

        for (int i = 0; i < arrLength; i++) {
            ListViewArray.add("  " + String.format("%02d",DayArrayList.get(i))
                    + "일                                 "+ WeightArrayList.get(i) + "kg");
        }
        listviewAdapter = new ArrayAdapter<String>(getContext(), R.layout.list, ListViewArray);
        listviewAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listviewAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        Collections.reverse(ListViewArray);
        Collections.sort(ListViewArray);

        listView.setAdapter(listviewAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                String message = "데이터를 삭제하시겠습니까? <br />" +
                        "날짜 : " + DayArrayList.get(arrLength-1-position) +"일 <br />" +
                        "체중 : " + WeightArrayList.get(arrLength-1-position) + "kg <br />";
                DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // 선택된 아이템을 리스트에서 삭제한다.
                        ListViewArray.remove(position);
                        Yearref.child(yearSpinner.getSelectedItem().toString()).child("Month")
                                .child(monthSpinner.getSelectedItem().toString())
                                .child("Day").child(DayArrayList.get(arrLength-1-position).toString()).removeValue();
                        DayArrayList.remove(arrLength-1-position);
                        WeightArrayList.remove(arrLength-1-position);
                        listviewAdapter.notifyDataSetChanged();
                        arrLength--;
                        if (DayArrayList.size() == 0){
                            MonthArrayList.remove(Yearref.child(yearSpinner.getSelectedItem().toString()).child("Month")
                                    .child(monthSpinner.getSelectedItem().toString()).getKey());
                            monthSpinner.setSelection(0);
                        }
                        if (MonthArrayList.size() == 1){
                            YearArrayList.remove(Yearref.child(yearSpinner.getSelectedItem().toString()).getKey());
                            yearSpinner.setSelection(0);
                        }
                    }
                };
                new AlertDialog.Builder(getContext())
                        .setTitle("삭제")
                        .setMessage(Html.fromHtml(message))
                        .setPositiveButton("삭제", deleteListener)
                        .show();
                return true;
            }
        });
    }
}