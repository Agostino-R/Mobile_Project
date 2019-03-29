package com.example.mobile_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_project.Model.AnimeInUpcomingList;
import com.example.mobile_project.View.AnimeDescActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomAdapter extends RecyclerView.Adapter<UpcomAdapter.ViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(AnimeInUpcomingList item);
    }

    private List<AnimeInUpcomingList> values;
    private final UpcomAdapter.OnItemClickListener listener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public ImageView loadedImage;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            loadedImage = (ImageView) v.findViewById(R.id.anime_image);
        }

        public void bind(final AnimeInUpcomingList item, final UpcomAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent anime_desc_activity = new Intent(context, AnimeDescActivity.class);
                    anime_desc_activity.putExtra("SelectedAnimeId", item.getMal_id());
                    context.startActivity(anime_desc_activity);
                }
            });
        }
    }

    public void add(int position, AnimeInUpcomingList item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public UpcomAdapter(List<AnimeInUpcomingList> myDataset, UpcomAdapter.OnItemClickListener listener, Context context) {
        values = myDataset;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public UpcomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UpcomAdapter.ViewHolder holder, final int position) {
        AnimeInUpcomingList currentUpcomAnimeInUpcomListListStruct = values.get(position);
        final String name = currentUpcomAnimeInUpcomListListStruct.getTitle();
        holder.bind(values.get(position), listener);

        holder.txtHeader.setText(name);
        Picasso.get()
                .load(currentUpcomAnimeInUpcomListListStruct.getImage_url())
                .resize(110, 180)
                .into(holder.loadedImage);
    }

    @Override
    public int getItemCount() {
        if(values!=null) {
            return values.size();
        }
        return 0;
    }
}
