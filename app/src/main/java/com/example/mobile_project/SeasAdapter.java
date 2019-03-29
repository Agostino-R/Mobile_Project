package com.example.mobile_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_project.Model.AnimeInSeasList;
import com.example.mobile_project.View.AnimeDescActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeasAdapter extends RecyclerView.Adapter<SeasAdapter.ViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(AnimeInSeasList item);
    }

    private List<AnimeInSeasList> values;
    private final SeasAdapter.OnItemClickListener listener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView loadedImage;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            loadedImage = (ImageView) v.findViewById(R.id.anime_image);
        }

        public void bind(final AnimeInSeasList item, final SeasAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent anime_desc_activity = new Intent(context, AnimeDescActivity.class);
                    anime_desc_activity.putExtra("SelectedAnimeId", item.getMal_id());
                    context.startActivity(anime_desc_activity);
                }
            });
        }
    }

    public void add(int position, AnimeInSeasList item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public SeasAdapter(List<AnimeInSeasList> myDataset, SeasAdapter.OnItemClickListener listener, Context context) {
        values = myDataset;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public SeasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_layout, parent, false);
        SeasAdapter.ViewHolder vh = new SeasAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SeasAdapter.ViewHolder holder, final int position) {
        AnimeInSeasList currentSeasAnimeInSeasListListStruct = values.get(position);
        final String name = currentSeasAnimeInSeasListListStruct.getTitle();
        holder.bind(values.get(position), listener);

        holder.txtHeader.setText(name);
        Picasso.get()
                .load(currentSeasAnimeInSeasListListStruct.getImage_url())
                .resize(110, 180)
                .into(holder.loadedImage);
        holder.txtFooter.setText("Score: " + currentSeasAnimeInSeasListListStruct.getScore());
    }

    @Override
    public int getItemCount() {
        if(values!=null) {
            return values.size();
        }
        return 0;
    }
}
