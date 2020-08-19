package com.example.weight_manager.ui.bmisimulator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.weight_manager.R;
import com.example.weight_manager.activity.NaviActivity;

public class BmiFragment extends Fragment implements NaviActivity.onKeyBackPressedListener{

    private EditText height;
    private EditText weight;
    private TextView bmiResult;
    private ImageView bmi_view, bmiLabel_image;
    private Button btnCalc,btnReset;
    private String bmiLabel = "";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bmisimulator,container,false);
        super.onCreate(savedInstanceState);
        height = (EditText) view.findViewById(R.id.height);
        weight = (EditText) view.findViewById(R.id.weight);
        bmiResult = (TextView) view.findViewById(R.id.result);
        bmi_view = (ImageView) view.findViewById(R.id.BMI_View);
        bmiLabel_image = (ImageView) view.findViewById(R.id.BMI_Label);
        bmi_view.setVisibility(View.INVISIBLE);
        bmiLabel_image.setVisibility(View.INVISIBLE);
        btnCalc = (Button) view.findViewById(R.id.btnCalc);
        btnReset = (Button) view.findViewById(R.id.btnReset);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();

                if (heightStr != null && !"".equals(heightStr)
                        && weightStr != null && !"".equals(weightStr)) {
                    float heightValue = Float.parseFloat(heightStr) / 100;
                    float weightValue = Float.parseFloat(weightStr);

                    float bmi = weightValue / (heightValue * heightValue);

                    displayBMI(bmi);
                    bmi_view.setVisibility(View.VISIBLE);
                    bmiLabel_image.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getActivity(), "키와 체중을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText(null);
                weight.setText(null);
                bmiResult.setText(null);
                bmiLabel="";
                bmi_view.setVisibility(View.INVISIBLE);
                bmiLabel_image.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    private void displayBMI(float bmi) {

        if (Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }
        String str_bmi = String.format("%.2f", bmi);

        bmiLabel = "BMI 수치 : " + str_bmi + "\n" + bmiLabel;
        bmiResult.setText(bmiLabel);
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