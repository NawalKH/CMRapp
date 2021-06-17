package collegeofmedicine.researcher.cmr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Hissa on 02/08/17.
 */

public class CalendarAdapter extends ArrayAdapter<event> {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<event> mEvents = null;
    TextView nameView ;
    TextView dateView;


    public CalendarAdapter(Context context, int resource, ArrayList<event> items) {
        super(context, resource, items);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mEvents = items;
    }

    @Override
    public event getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        //get the data from the data array
        event event1 = mEvents.get(position);
        //get a reference to the different view elements we wish to update

        Calendar cal = Calendar.getInstance();
        //setting the view to reflect the data we need to display
        nameView = (TextView) row.findViewById(R.id.ev_title);
        dateView = (TextView) row.findViewById(R.id.ev_day);

        /*if(!(event1.getStartDate().equals("OPEN")))
        {*/
            cal.setTime(event1.getDate());
            int day = cal.get(Calendar.DAY_OF_MONTH);

            String daay = "" + day;
            nameView.setText(event1.getName());
            dateView.setText(daay);
        //}
        //else
        //{
           // String daay = "OPEN";
           // nameView.setText(event1.getName());
           // dateView.setText(daay);
        //}

        return row;
    }

}
