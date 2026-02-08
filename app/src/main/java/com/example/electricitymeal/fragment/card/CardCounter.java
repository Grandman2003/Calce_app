package com.example.electricitymeal.fragment.card;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricitymeal.R;

import java.util.ArrayList;
import java.util.List;

public class CardCounter extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Informationcard> items = new ArrayList<>();
    private ListView listView;
    View veiwer;

    public void setVeiwer(View veiwer) {
        this.veiwer = veiwer;
    }


    public View getVeiwer() {
        return veiwer;
    }

    public  void addItem(Informationcard item) {
        items.add(item);
    }

    public static Fragment newInstance(int columnCount) {
       CardCounter fragment = new CardCounter();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listcard, container, false);
        CardCounter fragment = new CardCounter();
        fragment.setVeiwer(view);

        // Set the adapter
      /*  if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            if (items.size() != 0) {
                recyclerView.setAdapter(new CustomAdapter(items));
            }
        } */
       // return view;
    }


