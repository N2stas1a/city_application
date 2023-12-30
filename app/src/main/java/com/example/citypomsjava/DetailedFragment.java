package com.example.citypomsjava;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.citypomsjava.databinding.FragmentDetailedBinding;

public class DetailedFragment extends Fragment {

    private FragmentDetailedBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString("name", "");
            String desc = args.getString("desc", "");
           // int desc = args.getInt("desc", R.string.MinskDesc);
            int image = args.getInt("image", R.drawable.minsk);

            binding.detailName.setText(name);
            binding.detailDesc.setText(desc);
            binding.detailImage.setImageResource(image);
        }
    }
}
