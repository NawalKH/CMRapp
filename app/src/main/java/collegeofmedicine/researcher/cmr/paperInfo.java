package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class paperInfo extends AppCompatActivity {

    paper paper1;


    public void onBackPressed(){
        Intent g = new Intent(paperInfo.this, paperlist.class);
        g.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(g);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_info);
        setTitle("Paper Details");


        paper1 = (paper) getIntent().getSerializableExtra("paper");







        TextView title = (TextView) findViewById(R.id.title);
        title.setText(paper1.getTitle());
        TextView desc = (TextView) findViewById(R.id.desc);
        desc.setText(paper1.getDescription());
        TextView year = (TextView) findViewById(R.id.date);
        year.setText(paper1.getDate());
        TextView Author = (TextView) findViewById(R.id.author);
        Author.setText(paper1.getAuthor());
        TextView src = (TextView) findViewById(R.id.source);
        src.setText(paper1.getSourceTitle());




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.web);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(paperInfo.this, web.class);
                intent.putExtra("EXTRA_SESSION_ID", paper1.getUrl());
                startActivity(intent);


                /*
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(paper1.getSource()));
                    startActivity(intent);
                */

            }
        });

    }

}



