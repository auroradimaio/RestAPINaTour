package com.example.natour21.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.Controller.ReportController;
import com.example.natour21.Item.Report;
import com.example.natour21.R;
import com.example.natour21.Utils.ImagePicker;

import java.util.ArrayList;
import java.util.List;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportListViewHolder> {

    private List<Report> reports;

    public ReportListAdapter(){
        reports = new ArrayList<Report>();
    }

    @NonNull
    @Override
    public ReportListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_layout, parent, false);
        ReportListViewHolder chatListViewHolder = new ReportListViewHolder(view);
        return chatListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportListViewHolder holder, int position) {

        Report report = reports.get(position);

        if(report.getSender().equals(AuthenticationController.user_username))
        {

            holder.userImage.setImageResource(ImagePicker.getImage(AuthenticationController.user_username));
            holder.username.setText(report.getTitle());
            if(report.getResponse().equals(""))
            {
                holder.description.setText(report.getDescription());
            }else
            {
                holder.description.setText(report.getPostOwner() + " ha risposto alla tua segnalazione");
            }
        }
        else
        {
            holder.userImage.setImageResource(ImagePicker.getImage(report.getSender()));
            holder.username.setText(report.getSender());
            holder.description.setText("Ha segnalato il tuo post '" + report.getPostTitle() +  "'");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportController.openSingleReport((Activity) holder.itemView.getContext(), report);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ReportListViewHolder extends RecyclerView.ViewHolder {

        public TextView username,description;
        public ImageView userImage;

        public ReportListViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            description = itemView.findViewById(R.id.report_description);
            userImage = itemView.findViewById(R.id.userImage);
        }
    }

    public void update(List<Report> newlist) {
        reports.clear();
        reports.addAll(newlist);
        this.notifyDataSetChanged();
    }
}
