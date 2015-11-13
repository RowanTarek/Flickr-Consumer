package ardev.assessment.vf.flickrConsumer.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ardev.assessment.vf.flickrConsumer.R;


/**
 * Created by RowanTarek on 28/10/2015.
 */
public class ItemHolder extends RecyclerView.ViewHolder{
    public  ImageView itemImg;
    public TextView itemTitle;
    private final View itemView;

    public ItemHolder(View itemView) {
        super(itemView);
        itemImg = (ImageView) itemView.findViewById(R.id.feedPreviewImg);
        itemTitle = (TextView) itemView.findViewById(R.id.feedPreviewTitle);
        this.itemView = itemView;
    }//end holder constructor

    public void setClickListener(View.OnClickListener viewClickListener){
        itemView.setOnClickListener(viewClickListener);
    }
}//end FeedItemHolder
