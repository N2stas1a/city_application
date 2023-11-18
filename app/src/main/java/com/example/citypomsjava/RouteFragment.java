package com.example.citypomsjava;

import com.example.citypomsjava.AllRouteFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RouteFragment extends Fragment {
    CardView tramCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        tramCard = view.findViewById(R.id.tramCard);
        tramCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToAllRouteFragment();
            }
        });
        return view;
    }
    private void navigateToAllRouteFragment() {
        AllRouteFragment allRoutesFragment = new AllRouteFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.tram_route, allRoutesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}