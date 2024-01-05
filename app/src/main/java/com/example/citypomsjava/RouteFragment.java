package com.example.citypomsjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class RouteFragment extends Fragment {
    CardView tramCard;
    CardView busCard;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        tramCard = view.findViewById(R.id.tramCard);
        busCard = view.findViewById(R.id.busCard);
//        subwayCard = view.findViewById(R.id.subwayCard);

        if (tramCard != null) {
            tramCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent transition_to_trams = new Intent(requireContext(), TramsList.class);
                    startActivity(transition_to_trams);

                }
            });

            if (busCard != null) {
                    busCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent transition_to_buses = new Intent(requireContext(), BusActivity.class);
                            startActivity(transition_to_buses);

                        }
                    });
                }
            }
        return view;
    }
}


