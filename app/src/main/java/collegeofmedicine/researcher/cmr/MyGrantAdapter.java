package collegeofmedicine.researcher.cmr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Nawal on 8/10/2017.
 */


public class MyGrantAdapter extends ArrayAdapter<grant> {


    Context mContext;
    int mLayoutResourceId;
    ArrayList<grant> mGrant = null;

    /*private static class ViewHolder {
        private TextView itemView;
    }*/

    public MyGrantAdapter(Context context, int resource, ArrayList<grant> items) {
        super(context, resource, items);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mGrant = items;
    }

    @Override
    public grant getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        //get the data from the data array
        grant grant1 = mGrant.get(position);

        TextView title = (TextView) row.findViewById(R.id.title);
        TextView desc = (TextView) row.findViewById(R.id.desc);
        TextView year = (TextView) row.findViewById(R.id.AuthorAndDate);

        // TextView src = (TextView) row.findViewById(R.id.Source);


        String AuthorAndDate ="Start Date : "+grant1.getSdate()+" \nDeadline : "+grant1.getEdate();
        if(grant1.getSdate().equals("open"))
        {
            AuthorAndDate=" ";
        }
        title.setText(grant1.getName());
        desc.setText(grant1.getUrl());
        year.setText(AuthorAndDate);

        return row;
    }

}
