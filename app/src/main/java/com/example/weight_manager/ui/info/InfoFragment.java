package com.example.weight_manager.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.weight_manager.R;
import com.example.weight_manager.activity.ChangeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoFragment extends Fragment {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(),
            Accounts = ref.child("Accounts"),
            Sname = Accounts.child(user.getUid()).child("wname"),
            Sbirth = Accounts.child(user.getUid()).child("wbirth"),
            Sgender = Accounts.child(user.getUid()).child("wgender"),
            Sheight = Accounts.child(user.getUid()).child("wheight"),
            Sweight = Accounts.child(user.getUid()).child("wweight"),
            Ssetting_weight = Accounts.child(user.getUid()).child("wsetting_weight"),
            Skcal = Accounts.child(user.getUid()).child("wkcal"),
            Sbmi = Accounts.child(user.getUid()).child("wbmi");
    Button btnEdit;
    TextView taccount, tEmail, tname, tbirth, tgender, theight, tweight, tsetting_weight, tkcal, tbmi;
    String bmiLabel = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        super.onCreate(savedInstanceState);
        tEmail = (TextView) view.findViewById(R.id.info_Email);
        tname = (TextView) view.findViewById(R.id.info_Name);
        tbirth = (TextView) view.findViewById(R.id.info_Birth);
        tgender = (TextView) view.findViewById(R.id.info_Gender);
        theight = (TextView) view.findViewById(R.id.info_Height);
        tweight = (TextView) view.findViewById(R.id.info_Weight);
        tsetting_weight = (TextView) view.findViewById(R.id.info_Setting_Weight);
        tkcal = (TextView) view.findViewById(R.id.info_Kcal);
        tbmi = (TextView) view.findViewById(R.id.info_Bmi);
        taccount = (TextView) view.findViewById(R.id.info_Account);
        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        taccount.setVisibility(View.INVISIBLE);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeActivity.class);
                startActivity(intent);
            }
        });
        if (user != null) {
            String email = user.getEmail();

            String[] splitText = email.split("\\.");
            StringBuilder Account = null;
            for (int i = 1; i < splitText.length; i++) {
                Account = new StringBuilder(splitText[0]);
                Account.append(splitText[i]);
            }
            taccount.setText(Account);
            tEmail.setText(email);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        {
            Sname.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wname = snapshot.getValue(String.class);
                    tname.setText(wname);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sbirth.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wbirth = snapshot.getValue(String.class);
                    tbirth.setText(wbirth);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sgender.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wgender = snapshot.getValue(String.class);
                    tgender.setText(wgender);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sheight.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wheight = snapshot.getValue(String.class);
                    theight.setText(wheight);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sweight.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wweight = snapshot.getValue(String.class);
                    tweight.setText(wweight);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Ssetting_weight.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wsetting_weight = snapshot.getValue(String.class);
                    tsetting_weight.setText(wsetting_weight);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Skcal.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wkcal = snapshot.getValue(String.class);
                    tkcal.setText(wkcal);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Sbmi.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String wbmi = snapshot.getValue(String.class);
                    tbmi.setText(wbmi);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}