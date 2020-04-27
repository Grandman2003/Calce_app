package com.example.electricitymeal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Elements extends Fragment {
    private Button backbutton;
    private ListView listView;
    public int state;
    private SimpleAdapter simpleAdapter;
    private Elements fragment;
    // TODO: Rename and change types of parameters
    private Toolbar toolbar;
    private SovetsFragment.OnFragmentInteractionListener mListener;

    public Elements() {
        // Required empty public constructor
    }

    public void setFragment(Elements fragment) {
        this.fragment = fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TipsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Elements newInstance() {
        Elements fragment = new Elements();
        fragment.setFragment(fragment);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.elements_layout, container, false);
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
        LinkedList<Element> listBook=new LinkedList<Element>();
        listBook.add(new Element("Освещение"));
        listBook.add(new Element("Бытовая техника"));
        listBook.add(new Element("Компьютеры"));
        listBook.add(new Element("Электроинструменты и прочее"));
        ArrayList<HashMap<String,Object>> sovetlist=new ArrayList<>();
        HashMap<String,Object> map;
        for(int i=0;i<listBook.size();i++){
            map=new HashMap<String,Object>();
            map.put("text",listBook.get(i).text);
            sovetlist.add(map);
            if(simpleAdapter!=null){
                simpleAdapter.notifyDataSetChanged();
            }
            //sdb.execSQL("INSERT INTO"+ null+" " );
            //sdb.insert(OpenHelper.DATABASE_TABLE,null,contents);
        }
        String id[]={"text"};
        int to[]={R.id.element_text};

        simpleAdapter=new SimpleAdapter(getContext(),sovetlist,R.layout.element,id,to);
        simpleAdapter.notifyDataSetChanged();
        listView=(ListView) Objects.requireNonNull(getView()).findViewById(R.id.elements_list);
        listView.setAdapter(simpleAdapter);
        listView.setDivider(null);
        setRetainInstance(true);


    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}






class Element {
    String text;
    public Element(String text ) {
        this.text = text;
    }
}


