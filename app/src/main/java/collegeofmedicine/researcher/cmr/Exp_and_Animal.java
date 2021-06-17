package collegeofmedicine.researcher.cmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

public class Exp_and_Animal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_and__animal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SwipeSelector swipeSelector = (SwipeSelector) findViewById(R.id.swipeSelector);
        swipeSelector.setItems(
                new SwipeItem(0, "experimental operating suite", "an experimental operating suite which is equipped with necessary materials and equipment to carry out most types of scientific research involving animal surgery."),
                new SwipeItem(1, "isolation room", "an isolation room for animals with suction system that maintains proper temperature and humidity levels."),
                new SwipeItem(2, "reverse-isolation room", "a reverse-isolation room for animals with immune deficiency that requires sterilization periodically."),
                new SwipeItem(3,"laboratory animal rooms","laboratory animal rooms that offer independent ventilation cages that are in compliance with the international standards and regulations for breeding environment and laboratory room of mice and rats."),
                new SwipeItem(4,"ten-bed operations theatre","a ten-bed operations theatre with medical devices a large area suitable for workshops in various subjects and that accommodate a large number of members based on the workshop."),
                new SwipeItem(5,"administrative and technical staff","an integrated administrative and technical staff to help the researcher in conducting workshops."),
                new SwipeItem(6,"support facilities","some support facilities include a small animal imaging core and biohazard housing at ABSL2 levels.")
        );

    }
}
