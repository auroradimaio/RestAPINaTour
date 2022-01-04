package com.example.natour21.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.natour21.Adapter.ChatListAdapter;
import com.example.natour21.Adapter.ReportListAdapter;
import com.example.natour21.Controller.chatController;
import com.example.natour21.Controller.reportController;
import com.example.natour21.Entity.Report;
import com.example.natour21.R;

import java.util.List;

public class ReportsFragment extends Fragment {

    private static RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.show();
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        recyclerView = view.findViewById(R.id.reportListRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ReportListAdapter());

        return view;
    }

    public static void updateUI(List<Report> reports) {
        ((ReportListAdapter) recyclerView.getAdapter()).update(reports);
    }

    @Override
    public void onResume() {
        super.onResume();
        reportController.reportListActivity = getActivity();
        chatController.onChatList = false;
        chatController.onSingleChat = false;
        reportController.onReportList = true;
        reportController.getReportList();
    }
}
