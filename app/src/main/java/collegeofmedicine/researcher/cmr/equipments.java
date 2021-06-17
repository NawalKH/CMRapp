package collegeofmedicine.researcher.cmr;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;


public class equipments extends Fragment {



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.equipments,container, false);

        SwipeSelector swipeSelector = (SwipeSelector) rootView.findViewById(R.id.Flow);
        swipeSelector.setItems(
                new SwipeItem(0, "Immunology Res. Lab.", "(FACs LSRII)"),
                new SwipeItem(1, "Stem Cell Unit", "NAVIOS")
        );

        SwipeSelector swipeSelector2 = (SwipeSelector) rootView.findViewById(R.id.CellSorter);
        swipeSelector2.setItems(
                new SwipeItem(0, "Immunology Res. Lab.", "(ASTRIUOS II)"),
                new SwipeItem(1, "Stem Cell Unit", "(MOFLO ASTRIUOS)"),
                new SwipeItem(2, "Stem Cell Unit", "(MOFLO ASTRIUOS)")

        );


        SwipeSelector swipeSelector3 = (SwipeSelector) rootView.findViewById(R.id.DNA_genetic_analyzer);
        swipeSelector3.setItems(
                new SwipeItem(0, "Immunology Res. Lab.", "(ABI 3730)"),
                new SwipeItem(1, "Obesity Res. Center", "(ABI 3130)"),
                new SwipeItem(2, "Glaucoma Res. Center", "(ABI 3130 XI) & (ABI 3730)")

        );



        SwipeSelector swipeSelector4 = (SwipeSelector) rootView.findViewById(R.id.Real_Time_PCR);
        swipeSelector4.setItems(
                new SwipeItem(0, "Immunology Res. Lab.", "(FAST)"),
                new SwipeItem(1, "Obesity Res. Center", "(ABI 7500)"),
                new SwipeItem(2, "Stem Cell Unit", "(VII A7)"),
                new SwipeItem(3, "Glaucoma Res. Center ", "(ABI 7500)"),
                new SwipeItem(4, "Nasser Al-Rashid Chair of ophthalmology", "(ABI 7500)")
        );




        SwipeSelector swipeSelector5 = (SwipeSelector) rootView.findViewById(R.id.Thermal_cycler);
        swipeSelector5.setItems(
                new SwipeItem(0, "Immunology Res. Lab.", "(ABI  Veriti)"),
                new SwipeItem(1, "Obesity Res. Center", "(Bio RAD C1000)"),
                new SwipeItem(2, "Stem Cell Unit", "(GenAMP 9700)"),
                new SwipeItem(3, "Glaucoma Res. Center ", "(GenAMP 9700) & (ABI  Veriti) & (ABI  Fast 9800)"),
                new SwipeItem(4, "Nasser Al-Rashid Chair of ophthalmology", "(GenAMP 9700)")
        );



//=================================================================================


        SwipeSelector swipeSelector6 = (SwipeSelector) rootView.findViewById(R.id.ELISA_reader);
        swipeSelector6.setItems(
                new SwipeItem(0, "Immunology Res. Lab.", "(BioTech Epotech)"),
                new SwipeItem(1, "Stem Cell Unit", "(SPECTRAMAX M5)"),
                new SwipeItem(2, "Autism Res. Center", "(Elx800)"),
                new SwipeItem(3, "Glaucoma Res. Center", "(chemWell analyzer) & (FLUO Star Omega)"),
                new SwipeItem(3, "Nasser Al-Rashid Chair of ophthalmology", "(SPECTRAMAX GEMINI XPS)")
        );





        SwipeSelector swipeSelector7 = (SwipeSelector) rootView.findViewById(R.id.Microarray);
        swipeSelector7.setItems(
                new SwipeItem(0, "Stem Cell Unit", "(G2505C)")
        );



        SwipeSelector swipeSelector8 = (SwipeSelector) rootView.findViewById(R.id.Affymetrix_DNA_chip_analyzer);
        swipeSelector8.setItems(
                new SwipeItem(0, "Glaucoma Res. Center", "(GeneChip Scanner)")
        );



        SwipeSelector swipeSelector9 = (SwipeSelector) rootView.findViewById(R.id.Cytokine_array);
        swipeSelector9.setItems(
                new SwipeItem(0, "Immunology Res. Lab.", "(Luminex 100)")
        );


        SwipeSelector swipeSelector10 = (SwipeSelector) rootView.findViewById(R.id.Confocal_Microscope);
        swipeSelector10.setItems(
                new SwipeItem(0, "Nasser Al-Rashid Chair of ophthalmology", "-")
        );


        return rootView;
    }



}
