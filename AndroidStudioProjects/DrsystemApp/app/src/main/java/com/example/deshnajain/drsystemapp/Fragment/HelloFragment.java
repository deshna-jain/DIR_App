package com.example.deshnajain.drsystemapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deshnajain.drsystemapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelloFragment extends Fragment {


    public HelloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

}
