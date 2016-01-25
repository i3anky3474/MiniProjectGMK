package com.gmk.miniproject.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmk.miniproject.app.R;
import com.gmk.miniproject.app.model.ArticleModel;
import com.gmk.miniproject.app.service.ServiceHelper;
import com.gmk.miniproject.app.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 1/23/16 AD.
 */
public class ArticleAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;
    private ArrayList<ArticleModel> arrayListArticles;

    public ArticleAdapter(Activity activityX,ArrayList<ArticleModel> arrayListArticles) {
        activity = activityX;

        // Set To ListNews
        this.arrayListArticles = arrayListArticles;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {

        if(this.arrayListArticles!=null)
        {
            return this.arrayListArticles.size();
        }
        else
        {
            return 0;
        }
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public ImageView imvArticle;
        public TextView tvTitle;
        public TextView tvDescription;
        public LinearLayout linearLayoutItemArticle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Create View
        View view = convertView;
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();

            // Create View
            view = inflater.inflate(R.layout.item_article, null);
            holder = new ViewHolder();
            holder.imvArticle = (ImageView) view.findViewById(R.id.imvArticle);
            holder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            holder.tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            holder.linearLayoutItemArticle = (LinearLayout)view.findViewById(R.id.linearLayoutItemArticle);
            holder.imvArticle.setVisibility(View.GONE);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        //Set Object inUse
        ArticleModel articleModelPosition = arrayListArticles.get(position);

        //Set Value
        holder.tvTitle.setText(articleModelPosition.getTitle());
        holder.tvDescription.setText(articleModelPosition.getShort_description());

        //Check Image Path
        if(Utils.checkStringNullEmpty(articleModelPosition.getImage())) {
            Picasso.with(activity).load(ServiceHelper.BASE_URL+articleModelPosition.getImage()).resize(60, 60).into(holder.imvArticle);
            holder.imvArticle.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.imvArticle.setVisibility(View.GONE);
        }

        return view;
    }
}
