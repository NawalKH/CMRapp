package collegeofmedicine.researcher.cmr;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Hissa on 20/07/17.
 */

public class MyEventAdapter extends ArrayAdapter<event> implements Callback {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<event> mEvents = null;
    View loaderView;
    ImageView thumbnail;
    Context context;
    event event1;


    /*private static class ViewHolder {
        private TextView itemView;
    }*/

    public MyEventAdapter(Context context, int resource, ArrayList<event> items) {
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
         event1 = mEvents.get(position);
         //get a reference to the different view elements we wish to update

         //setting the view to reflect the data we need to display
         TextView nameView = (TextView) row.findViewById(R.id.nameTextView);
         //TextView dateView = (TextView) row.findViewById(R.id.zipcodeTextView);
         TextView desc = (TextView) row.findViewById(R.id.edscrip);
         TextView day = (TextView) row.findViewById(R.id.day);
         TextView month = (TextView) row.findViewById(R.id.month);
          thumbnail = (ImageView) row.findViewById(R.id.thumbnail);
         loaderView= (View)  row.findViewById(R.id.progress);
         //ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
        String date = event1.getStartDate();

         if(date.equals("OPEN"))
         {
             day.setText(" ");
             month.setText("OPEN");
         }
         else {

             String[] dateParts = date.split("-");
             String y = dateParts[0];
             String m = dateParts[1];
             String d = dateParts[2];


             int monthNum = Integer.parseInt(m);
             int dayNum = Integer.parseInt(d);
             d = dayNum + "";


             switch (monthNum) {
                 case 1:
                     m = "Jan";
                     break;
                 case 2:
                     m = "Feb";
                     break;
                 case 3:
                     m = "March";
                     break;
                 case 4:
                     m = "Apr";
                     break;
                 case 5:
                     m = "May";
                     break;
                 case 6:
                     m = "June";
                     break;
                 case 7:
                     m = "July";
                     break;
                 case 8:
                     m = "Aug";
                     break;
                 case 9:
                     m = "Sep";
                     break;
                 case 10:
                     m = "Oct";
                     break;
                 case 11:
                     m = "Nov";
                     break;
                 case 12:
                     m = "Dec";
                     break;
             }

             day.setText(d);
             month.setText(m);
         }


         nameView.setText(event1.getName());
         desc.setText(event1.getDescrip());



         // show The Image in a ImageView
         context = parent.getContext();
         //:check the size

         //Picasso.with(context).load(event1.getPic()).resize(300,300).placeholder( R.drawable.progress_animation ).into(thumbnail);
         //new DownloadImageTask(thumbnail).execute(event1.getPic());
         Uri uri= Uri.parse(event1.getPic());
         loadImage(uri);


         return row;
      }


    private synchronized void loadImage(Uri uri)
    {
        Picasso.with(context).load(uri).resize(300,300).into(thumbnail,this);
    }

    @Override
    public void onSuccess()
    {
        // hide the loader and show the imageview
        loaderView.setVisibility(View.GONE);
        thumbnail.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError()
    {
        // hide the loader and show the imageview which shows the error icon already
        loaderView.setVisibility(View.GONE);
        thumbnail.setVisibility(View.VISIBLE);
    }

}
