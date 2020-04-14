package net.growdev.footballclub.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.growdev.footballclub.R;
import net.growdev.footballclub.data.model.TeamsItem;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private final Context context;
    private final List<TeamsItem> dataTeamList;

    public MainAdapter(Context context, List<TeamsItem> dataTeamList) {
        this.context = context;
        this.dataTeamList = dataTeamList;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team_footbal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        final TeamsItem teamData = dataTeamList.get(position);
        holder.txtNameTeam.setText(teamData.getStrTeam());
        Glide.with(context).load(teamData.getStrTeamBadge()).into(holder.imgLogoTeam);
    }

    @Override
    public int getItemCount() {
        return dataTeamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogoTeam;
        TextView txtNameTeam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogoTeam = itemView.findViewById(R.id.image_logo_team);
            txtNameTeam = itemView.findViewById(R.id.text_name_team);
        }
    }
}
