package com.example.natour21.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.natour21.Controller.ReportController;
import com.example.natour21.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class NewReportFragment extends Fragment {

    TextView pathName;
    Button newReportBtn;
    EditText titleEd, descriptionEd;
    Date currentTime;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Inserisci Segnalazione");
        actionBar.setDisplayHomeAsUpEnabled(false);
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(navView.GONE);
        actionBar.show();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_report, container, false);

        Bundle bundle = this.getArguments();
        String title = bundle.getString("TitoloSentiero");
        String postUser = bundle.getString("PostUser");
        int id = bundle.getInt("IdPost");




        pathName = view.findViewById(R.id.pathNameReport_textView);
        pathName.setText(title);

        titleEd = view.findViewById(R.id.reportTitle_EditText);
        descriptionEd = view.findViewById(R.id.reportDescription_EditText);

        newReportBtn = view.findViewById(R.id.publishReport_Button);
        newReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportController.InsertReport(getActivity(),titleEd.getText().toString(),descriptionEd.getText().toString(),id,postUser);
            }
        });



        return view;
    }
}