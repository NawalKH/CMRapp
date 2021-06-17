package collegeofmedicine.researcher.cmr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Nawal on 8/12/2017.
 */

public class MyFormAdapter  extends ArrayAdapter<Form> {


    Context mContext;
    int mLayoutResourceId;
    ArrayList<Form> mForm = null;

    /*private static class ViewHolder {
        private TextView itemView;
    }*/

    public MyFormAdapter(Context context, int resource, ArrayList<Form> items) {
        super(context, resource, items);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mForm = items;
    }

    @Override
    public Form getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        //get the data from the data array
        Form form = mForm.get(position);

        TextView title = (TextView) row.findViewById(R.id.title);
        // TextView src = (TextView) row.findViewById(R.id.Source);

        title.setText(form.getTitle());

        return row;
    }


}

