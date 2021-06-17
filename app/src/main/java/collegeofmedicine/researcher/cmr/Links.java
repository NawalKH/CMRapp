package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class Links extends AppCompatActivity {

    private ListView lListView;
    private LinksAdapter mLinkAdapter;
    ArrayList<link> mLinkArray;
    ProgressBar loaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        setTitle("Important Links");
        loaderView = (ProgressBar) findViewById(R.id.loading);
        show_Links();
    }





    private void show_Links() {
        mLinkArray = new ArrayList<link>();

        //*******************   LINKS HERE   ************

        mLinkArray.add(new link("Deanship of Scientific Research","http://dsrs.ksu.edu.sa/ar","w"));
        mLinkArray.add(new link("الحساب الرسمي لكلية الطب بجامعة الملك سعود","https://twitter.com/ksu_medicine","t"));
        mLinkArray.add(new link("الحساب الرسمي لعمادة البحث العلمي بجامعة الملك سعود","https://twitter.com/DSResearch_KSU","t"));
        mLinkArray.add(new link("مركز ابحاث كلية الطب بجامعة الملك سعود","https://twitter.com/COMResearchCMRC","t"));

        lListView = (ListView) findViewById(R.id.listview_L);
        mLinkAdapter = new LinksAdapter(this, R.layout.links_row, mLinkArray); //*********

        //Animation

        if (lListView != null) {
            lListView.setAdapter(mLinkAdapter);
            loaderView.setVisibility(View.GONE);
        }


//***************** WEP PAGE


        lListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                Intent intent = new Intent(Links.this, web.class);
                intent.putExtra("EXTRA_SESSION_ID",mLinkArray.get(i).getUrl());
                startActivity(intent);
            }

        });

    }
}
