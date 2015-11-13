package ardev.assessment.vf.flickrConsumer.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ardev.assessment.vf.flickrConsumer.R;
import ardev.assessment.vf.flickrConsumer.listeners.OnItemClickListener;
import ardev.assessment.vf.flickrConsumer.model.FlickrImage;
import ardev.assessment.vf.flickrConsumer.view.ItemHolder;

/**
 * Created by RowanTarek on 28/10/2015.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemHolder>{
    ArrayList<FlickrImage> itemsList;
    public ItemsAdapter(ArrayList<FlickrImage> itemsList) {
        this.itemsList =  itemsList;
    }

    @Override
    public ItemHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_preview, parent, false);

        return new ItemHolder(itemView);
    }//end onCreateViewHolder

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        final FlickrImage curItem = itemsList.get(position);
        holder.itemTitle.setText(curItem.getTitle());

        if(curItem.getImageUrl() != null && !curItem.getImageUrl().equals("")){
            Picasso.with(holder.itemImg.getContext()).load(curItem.getImageUrl()).into(holder.itemImg);

        }//end if --? img url available
        if(holder.itemImg.getContext() instanceof OnItemClickListener) {
            holder.setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((OnItemClickListener)holder.itemImg.getContext()).onItemClick(curItem.getOwner());
                }
            });
        }//end if --? listener to be supplied
    }//end onBindViewHolder

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}//end FeedItemAdapter
