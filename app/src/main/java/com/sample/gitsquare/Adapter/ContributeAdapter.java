package com.sample.gitsquare.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.sample.gitsquare.Model.ContributeModel;
import com.sample.gitsquare.R;
import com.sample.gitsquare.utills.Commons;

public class ContributeAdapter extends RecyclerView.Adapter<ContributeAdapter.viewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<ContributeModel> models;
    private Context context;

    public ContributeAdapter(Context c, List<ContributeModel> list) {
        this.models = list;
        this.context = c;
        this.mLayoutInflater = LayoutInflater.from(c);
    }
    public void addModels(List<ContributeModel> list){
        this.models.clear();
        this.models = list;
        this.notifyDataSetChanged();
    }
    public void filter_contributes(final boolean flag){
        if(flag){
            Collections.sort(this.models, new Comparator<ContributeModel>() {
                @Override
                public int compare(ContributeModel o1, ContributeModel o2) {
                    if (o1.getContributions() > o2.getContributions()) {
                        return 1;
                    } else if (o1.getContributions() <  o2.getContributions()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        }else {
            Collections.sort(this.models, new Comparator<ContributeModel>() {
                @Override
                public int compare(ContributeModel o1, ContributeModel o2) {
                    if (o1.getContributions() < o2.getContributions()) {
                        return 1;
                    } else if (o1.getContributions() >  o2.getContributions()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        }
        this.notifyDataSetChanged();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private TextView txt_username,txt_contribute_repos,txt_total_contribution;
        private CircleImageView iv_avtar;

        public viewHolder(View itemView) {
            super(itemView);
            txt_username = itemView.findViewById(R.id.txt_username);
            txt_contribute_repos = itemView.findViewById(R.id.txt_contribute_repos);
            txt_total_contribution = itemView.findViewById(R.id.txt_total_contribution);
            iv_avtar = itemView.findViewById(R.id.iv_avtar);
        }
    }
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_item, parent, false);
        return new viewHolder(view);
    }
    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        final ContributeModel model = models.get(position);
        holder.txt_username.setText(model.getUsername());
        holder.txt_total_contribution.setText("Contributions "+model.getContributions());
        Picasso.with(context).load(model.getAvtar_url()).into(holder.iv_avtar);
        holder.iv_avtar.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_launcher));
        holder.txt_contribute_repos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Commons.gotoURL(context,model.getRepos_url());}
        });
        holder.txt_contribute_repos.setPaintFlags(holder.txt_contribute_repos.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
}
