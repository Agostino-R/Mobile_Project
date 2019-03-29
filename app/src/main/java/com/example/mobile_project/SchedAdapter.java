package com.example.mobile_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_project.Model.AnimeInSchedList;
import com.example.mobile_project.View.AnimeDescActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SchedAdapter extends RecyclerView.Adapter<SchedAdapter.ViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(AnimeInSchedList item);
    }

    private List<AnimeInSchedList> values;
    private final SchedAdapter.OnItemClickListener listener;
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

        public void bind(final AnimeInSchedList item, final SchedAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent anime_desc_activity = new Intent(context, AnimeDescActivity.class);
                    anime_desc_activity.putExtra("SelectedAnimeId", item.getMal_id());
                    context.startActivity(anime_desc_activity);
                }
            });
        }
    }

    public void add(int position, AnimeInSchedList item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SchedAdapter(List<AnimeInSchedList> myDataset, SchedAdapter.OnItemClickListener listener, Context context) {
        values = myDataset;
        this.listener = listener;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SchedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SchedAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        AnimeInSchedList currentAnimeInSchedList = values.get(position);
        final String name = currentAnimeInSchedList.getTitle();
        holder.bind(values.get(position), listener);

        holder.txtHeader.setText(name);
        Picasso.get()
                .load(currentAnimeInSchedList.getImage_url())
                .resize(110, 180)
                .into(holder.loadedImage);
        holder.txtFooter.setText("Rank: " + currentAnimeInSchedList.getScore());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(values!=null) {
            return values.size();
        }
        return 0;
    }
}
