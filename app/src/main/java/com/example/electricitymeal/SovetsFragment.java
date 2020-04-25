package com.example.electricitymeal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class SovetsFragment extends Fragment {
    public TextView textView;
    private Button backbutton;
    private SovetsFragment fragment;
    // TODO: Rename and change types of parameters
    private Toolbar toolbar;
    private String resultation;
    private SovetsFragment.OnFragmentInteractionListener mListener;

    public SovetsFragment() {
        // Required empty public constructor
    }

    public void setFragment(SovetsFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TipsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SovetsFragment newInstance() {
        SovetsFragment fragment = new SovetsFragment();
        fragment.setFragment(fragment);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sovets, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        backbutton=(Button)view.findViewById(R.id.backbutton1);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                toolbar.getMenu().getItem(1).setVisible(true);
                getFragmentManager().beginTransaction().remove(fragment).commit();
                FloatingActionButton fab= Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
                FloatingActionButton tips= Objects.requireNonNull(getActivity()).findViewById(R.id.tips);
                Button button=getActivity().findViewById(R.id.culc);
                button.setClickable(true);
                button.setVisibility(View.VISIBLE);
                tips.setVisibility(View.VISIBLE);
                fab.setClickable(true);
                tips.setClickable(true);
                fab.setVisibility(View.VISIBLE);
            }
        });

    }




   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    } */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


