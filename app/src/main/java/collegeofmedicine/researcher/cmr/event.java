package collegeofmedicine.researcher.cmr;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Hissa on 20/07/17.
 */

public class event implements Serializable, Comparable<event>{

    private String name, descrip, startTime, EndTime ,location, startDate,endDate,key;
    private String pic,nDate,orgEmail;
    private Date date, s_date,e_date,n_date;


    public event()
    {
        name = "None";
        descrip = "None";
        startDate = "dd/mm/yyyy";
        endDate = "dd/mm/yyyy";
        startTime = "00:00";
        EndTime = "00:00";
        location = "none";


    }

    public event(String nam, String descr, String stim, String etim, String locatio, String dat, String enddate){
        name = nam;
        descrip = descr;
        startTime = stim;
        EndTime=etim;
        location = locatio;
        startDate = dat;
        endDate = enddate;
        s_date = getS_date(); // star time in date format
        e_date = getE_date(); // end time in date format
    }

    @Override
    public int compareTo(event o) {//to sort the list of events by date
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(String date) {
        this.startDate = date;
    }

    public void setEndDate(String date)
    {
        this.endDate= date;
    }

    public void setPic(String img){
        pic=img;
    }

    public String getName(){
        return name;
    }

    public String getDescrip(){
        return descrip;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return EndTime;
    }

    public String getLocation(){
        return location;
    }

  public String getStartDate()
  {return  startDate;}

    public String getEndDate(){
        return endDate;
    }

    public String getPic() {
        return pic;
    }


    public void setDate(Date date){
        this.date=date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDate()
    {

        try {
            if(startDate.equals("OPEN"))
                date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-12");
            else
             date = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public Date getS_date() {
        String stime = ""+startDate+" "+startTime+":00";

        if(startDate.equals("OPEN"))
             stime = "2000-12-12"+" "+startTime+":00";

        try {
            s_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return s_date;
    }


    public Date getE_date() {

        String etime = ""+endDate+" "+EndTime+":00";

        if(startDate.equals("OPEN"))
            etime = "2000-12-12"+" "+EndTime+":00";

        try {
            e_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(etime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return e_date;
    }

    public Date getN_date() {

        return n_date;
    }

    public void setnDate(String nDate) {

        this.nDate = nDate;
        try {
            n_date = new SimpleDateFormat("yyyy-MM-dd").parse(nDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgEmail() {
        return orgEmail;
    }
}
