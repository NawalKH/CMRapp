package collegeofmedicine.researcher.cmr;

/**
 * Created by Hissa on 24/07/17.
 */

import android.content.Context;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridAdapter extends ArrayAdapter {
    private static final String TAG = GridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    public Calendar currentDate;
    private ArrayList<event> allEvents;



    public GridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate, ArrayList<event> allEvents) {
        super(context, R.layout.single_cell_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;

        if(view == null){
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }
        if(displayMonth == currentMonth && displayYear == currentYear){
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }else{
            view.setBackgroundColor(Color.parseColor("#cccccc"));
        }
        //Add day to activity_custom_calendar
        TextView cellNumber = (TextView)view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));
        //Add events to the activity_custom_calendar
        TextView eventIndicator = (TextView)view.findViewById(R.id.event_id);
        Calendar eventCalendar = Calendar.getInstance();
        for(int i = 0; i < allEvents.size(); i++){
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
/*            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(allEvents.get(i).getDate());
                allEvents.get(i).setDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
  */
            eventCalendar.setTime(allEvents.get(i).getDate());

            //********COLORING EVENT DAYS*******

            if(dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)){
                eventIndicator.setBackgroundColor(Color.parseColor("#e39ea4"));
            }
        }
        return view;
    }
    @Override
    public int getCount() {
        return monthlyDates.size();
    }
    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }
    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }
}
