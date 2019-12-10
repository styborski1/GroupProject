package com.example.groupproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportViewHolder> {

    class ReportViewHolder extends RecyclerView.ViewHolder {
        private final TextView reportTitleView;
        private final TextView reportDescView;
        private final TextView reportCityView;

        private ReportViewHolder(View itemView) {
            super(itemView);
            reportTitleView = itemView.findViewById(R.id.titleView);
            reportDescView = itemView.findViewById(R.id.descView);
            reportCityView = itemView.findViewById(R.id.cityView);
        }

        private void setReportDetails(Report report) {
            reportTitleView.setText(report.getTitle());
            reportDescView.setText(report.getDescription());
            reportCityView.setText(report.getCity());
        }

    }

    private final LayoutInflater mInflater;
    private List<Report> mReports; // Cached copy of reports

    ReportListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        if (mReports != null) {
            Report current = mReports.get(position);
            holder.setReportDetails(current);

        } else {
            // Covers the case of data not being ready yet.
            holder.reportTitleView.setText("No Report");
        }
    }

    void setReports(List<Report> reports) {
        mReports = reports;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mReports != null)
            return mReports.size();
        else return 0;
    }
}

