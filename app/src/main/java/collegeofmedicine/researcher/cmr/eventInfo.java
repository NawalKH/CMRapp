package collegeofmedicine.researcher.cmr;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.TimeZone;


public class eventInfo extends AppCompatActivity {

    event event1;

    public static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 123;
    Context context = eventInfo.this;

    public void onBackPressed(){
        Intent g = new Intent(eventInfo.this, EventsActivity.class);
        g.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(g);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        event1 = (event) getIntent().getSerializableExtra("event");




        String startDate = event1.getStartDate();
        String endDate = event1.getEndDate();

        //Start date
        String [] dateParts = startDate.split("-");
        String y = dateParts[0];
        String m = dateParts[1];
        String d = dateParts[2];

        int monthNum = Integer.parseInt(m);

        switch (monthNum)
        {
            case 1:
                m="Jan";
                break;
            case 2:
                m="Feb";
                break;
            case 3:
                m="March";
                break;
            case 4:
                m="Apr";
                break;
            case 5:
                m="May";
                break;
            case 6:
                m="June";
                break;
            case 7:
                m="July";
                break;
            case 8:
                m="Aug";
                break;
            case 9:
                m="Sep";
                break;
            case 10:
                m="Oct";
                break;
            case 11:
                m="Nov";
                break;
            case 12:
                m="Dec";
                break;
        }

        startDate = d+" "+m+" "+y;




        //:End Date

        String [] enddate = startDate.split("-");
        String y2 = dateParts[0];
        String m2 = dateParts[1];
        String d2 = dateParts[2];

        int monthNum2 = Integer.parseInt(m2);

        switch (monthNum2)
        {
            case 1:
                m2="Jan";
                break;
            case 2:
                m2="Feb";
                break;
            case 3:
                m2="March";
                break;
            case 4:
                m2="Apr";
                break;
            case 5:
                m2="May";
                break;
            case 6:
                m2="June";
                break;
            case 7:
                m2="July";
                break;
            case 8:
                m2="Aug";
                break;
            case 9:
                m2="Sep";
                break;
            case 10:
                m2="Oct";
                break;
            case 11:
                m2="Nov";
                break;
            case 12:
                m2="Dec";
                break;
        }


        endDate = d2+" "+m2+" "+y2;


        TextView evename = (TextView) findViewById(R.id.title);
        evename.setText(event1.getName());
        TextView eventt = (TextView) findViewById(R.id.t);
        eventt.setText(event1.getName());
        TextView edesc = (TextView) findViewById(R.id.edscrip);
        edesc.setText(event1.getDescrip());

        /*
        TextView gh = (TextView) findViewById(R.id.h);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            gh.setText(Html.fromHtml(event1.getLocation(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            gh.setText(Html.fromHtml(event1.getLocation()));
        }
        */


        TextView edate = (TextView) findViewById(R.id.date);
        String dd= startDate+", "+event1.getStartTime()+" - "+endDate+", "+event1.getEndTime();
        edate.setText(dd);
        TextView eloca = (TextView) findViewById(R.id.eloc);
        eloca.setText(event1.getLocation());

        setSupportActionBar(toolbar);


        ImageView thumbnail = (ImageView) findViewById(R.id.eventThumb);
        //Context context = parent.getContext();
        Picasso.with(eventInfo.this).load(event1.getPic()).fit().into(thumbnail);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = event1.getName()+" is held at "+event1.getLocation()+", on "+event1.getStartDate();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });
    }


    public void OnButtonClick(View view) {
        if (view.getId() == R.id.attend) {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent.setType("vnd.android.cursor.item/email");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{event1.getOrgEmail()});
            emailIntent.putExtra(android.content.Intent.EXTRA_CC,new String[]{event1.getOrgEmail()});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, event1.getName() + " Registration");
            startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
        }

        if(view.getId() == R.id.ticket)
        {
            Intent intent = new Intent(eventInfo.this, ticketForm.class);
            intent.putExtra("event", event1);
            startActivity(intent);

        }

        if(view.getId()==R.id.add_to_c){
            boolean result = checkPermission();
            if(result)
                writeCalendarEvent();
        }
    }

    private void writeCalendarEvent() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        // time event starts
        cal.setTime(event1.getS_date());


        long begin = cal.getTimeInMillis();// starting time in milliseconds
        // time it ends
        cal.setTime(event1.getE_date());
        long end = cal.getTimeInMillis();// ending time in milliseconds
        String[] proj =
                new String[]{
                        CalendarContract.Instances._ID,
                        CalendarContract.Instances.BEGIN,
                        CalendarContract.Instances.END,
                        CalendarContract.Instances.EVENT_ID};
        Cursor cursor =
                CalendarContract.Instances.query(getContentResolver(), proj, begin, end, "\""+event1.getName()+"\"");

        /** Checking if the event already exists before adding it; to avoid duplicates**/
        if (cursor.getCount() > 0) {
            // deal with conflict
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this,R.style.MyDialogTheme);
            dlgAlert.setTitle("Events");
            dlgAlert.setMessage("Event Already exists");
            dlgAlert.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    }).show();
        }
        else {
            final ContentValues event = new ContentValues();
            event.put(CalendarContract.Events.CALENDAR_ID, 1);

            event.put(CalendarContract.Events.TITLE, event1.getName());
            event.put(CalendarContract.Events.DESCRIPTION, "Event added by College of Medicine Researcher App");
            event.put(CalendarContract.Events.EVENT_LOCATION, event1.getLocation());
            // time event starts
            cal.setTime(event1.getS_date());
            event.put(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
            // time it ends
            cal.setTime(event1.getE_date());
            event.put(CalendarContract.Events.DTEND, cal.getTimeInMillis());
            event.put(CalendarContract.Events.ALL_DAY, 0);   // 0 for false, 1 for true
            event.put(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true

            String timeZone = TimeZone.getDefault().getID();
            event.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);

            Uri baseUri;
            if (Build.VERSION.SDK_INT >= 8) {
                baseUri = Uri.parse("content://com.android.calendar/events");
            } else {
                baseUri = Uri.parse("content://calendar/events");
            }

            getApplicationContext().getContentResolver().insert(baseUri, event);
            Toast.makeText(getApplicationContext(), "Event Created", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkPermission()
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_CALENDAR)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Write calendar permission is necessary to add event to your calendar!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity)context, new String[]{android.Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_CALENDAR:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    writeCalendarEvent();
                } else {
                    //code for deny
                    Toast.makeText(context,"Did not add event to calendar",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
