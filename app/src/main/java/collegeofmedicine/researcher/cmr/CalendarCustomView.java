package collegeofmedicine.researcher.cmr;

/**
 * Created by Hissa on 24/07/17.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import collegeofmedicine.researcher.ResearchApp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CalendarCustomView extends LinearLayout{
    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    public GridView calendarGridView;

    private static final int MAX_CALENDAR_COLUMN = 42;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    public GridAdapter mAdapter;
    ArrayList<event> mEvents = new ArrayList<event>();
    private ListView eListView;
    private CalendarAdapter mEventAdapter;

    //arraylist of events ** to be filled from DATABASE **
    ArrayList<event> mEventArray = new ArrayList<event>();
    LayoutInflater inflater;
    View view;
    Calendar eventCalendar = Calendar.getInstance();
    retrieveEvents rE = new retrieveEvents();

    public CalendarCustomView(Context context) {
        super(context);
    }
    public CalendarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        mEvents = rE.eventData();

        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        //setGridCellClickEvents();
    }



    public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initializeUILayout(){
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_calendar, this);

        previousButton = (ImageView)view.findViewById(R.id.previous_month);
        nextButton = (ImageView)view.findViewById(R.id.next_month);
        currentDate = (TextView)view.findViewById(R.id.display_current_date);
        calendarGridView = (GridView)view.findViewById(R.id.calendar_grid);


    }
    private void setPreviousButtonClickEvent(){
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, -1);
                setUpCalendarAdapter();
            }
        });
    }
    private void setNextButtonClickEvent(){
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, 1);
                setUpCalendarAdapter();
            }
        });
    }

    // **********   This is for clicking on a date, not very helpful, only toasts

 /*   private void setGridCellClickEvents(){
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(context, "Clicked " + position, Toast.LENGTH_LONG).show();

            }
        });
    }*/



    private void setUpCalendarAdapter(){
        List<Date> dayValueInCells = new ArrayList<Date>();

        Calendar mCal = (Calendar)cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        //Checking for Events in the month
        TextView eve1 = (TextView) view.findViewById(R.id.event_show);
        eListView = (ListView) findViewById(R.id.listC);
        mEventArray.clear();
        eve1.setText("");
        //mEvents.clear();
        //mEvents = rE.eventData();
        int i;
        for(i=0;i<mEvents.size();i++){

                eventCalendar.setTime(mEvents.get(i).getDate());

                if (cal.get(Calendar.MONTH) + 1 == eventCalendar.get(Calendar.MONTH) + 1 && cal.get(Calendar.YEAR) == eventCalendar.get(Calendar.YEAR)) {
                    if (!mEventArray.contains(mEvents.get(i)))
                        mEventArray.add(mEvents.get(i));
                    mEventAdapter = new CalendarAdapter(ResearchApp.getAppContext(), R.layout.row2, mEventArray);

                    if (eListView != null) {
                        eListView.setAdapter(null);
                        eListView.setAdapter(mEventAdapter);
                    }
                }



        }
        if(mEventArray.size()==0){
            eve1.setText("No events this month");
            eListView.setAdapter(null);
        }

        Log.d(TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
        currentDate.setText(sDate);
        mAdapter = new GridAdapter(context, dayValueInCells, cal, mEvents);
        calendarGridView.setAdapter(mAdapter);




    }
}
