package com.example.citypomsjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class RouteFragment extends Fragment {
    CardView tramCard;
    CardView busCard;
    CardView subwayCard;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        tramCard = view.findViewById(R.id.tramCard);
        busCard = view.findViewById(R.id.busCard);
        subwayCard = view.findViewById(R.id.subwayCard);

        if (tramCard != null) {
            tramCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        AllRouteFragment allRouteFragment = new AllRouteFragment();
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, allRouteFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
            if (busCard != null) {
                busCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getActivity() != null) {
                            AllSubwayFragment AllSubwayFragment = new AllSubwayFragment();
                            getParentFragmentManager().beginTransaction()
                                    .replace(R.id.frame_layout, AllSubwayFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
                if (subwayCard != null) {
                    subwayCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (getActivity() != null) {
                                AllBusFragment AllBusFragment = new AllBusFragment();
                                getParentFragmentManager().beginTransaction()
                                        .replace(R.id.frame_layout, AllBusFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                    });
                }
            }
        }
        return view;
    }
}


