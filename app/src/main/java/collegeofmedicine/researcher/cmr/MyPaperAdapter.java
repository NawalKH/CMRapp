package collegeofmedicine.researcher.cmr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Hissa on 20/07/17.
 */

public class MyPaperAdapter extends ArrayAdapter<paper> {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<paper> mPaper = null;

    /*private static class ViewHolder {
        private TextView itemView;
    }*/

    public MyPaperAdapter(Context context, int resource, ArrayList<paper> items) {
        super(context, resource, items);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mPaper = items;
    }

    @Override
    public paper getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        //get the data from the data array
        paper paper1 = mPaper.get(position);

        TextView title = (TextView) row.findViewById(R.id.title);
        TextView desc = (TextView) row.findViewById(R.id.desc);
        TextView year = (TextView) row.findViewById(R.id.AuthorAndDate);

       // TextView src = (TextView) row.findViewById(R.id.Source);





        String AuthorAndDate = paper1.getAuthor()+"--"+paper1.getDate();;

        title.setText(paper1.getTitle());
        desc.setText(paper1.getDescription());
        year.setText(AuthorAndDate);
        //src.setText(paper1.getSource());


        return row;
    }

}
