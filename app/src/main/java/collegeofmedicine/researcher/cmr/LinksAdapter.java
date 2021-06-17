package collegeofmedicine.researcher.cmr;
/**
 * Created by Hissa on 11/08/17.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Hissa on 20/07/17.
 */

public class LinksAdapter extends ArrayAdapter<link> {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<link> urls = null;


    public LinksAdapter(Context context, int resource, ArrayList<link> items) {
        super(context, resource, items);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.urls = items;
    }

    @Override
    public link getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        //get the data from the data array
        link url1 = urls.get(position);

        TextView title = (TextView) row.findViewById(R.id.l_title);
        TextView desc = (TextView) row.findViewById(R.id.desc);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);

        title.setText(url1.getU_title());
        desc.setText(url1.getUrl());

        if(url1.getImg().equals("w")) //if web page
            icon.setImageResource(R.mipmap.link);
        if(url1.getImg().equals("t")) //if twitter page
            icon.setImageResource(R.drawable.twt);
        return row;
    }

}
