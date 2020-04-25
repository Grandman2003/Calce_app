package com.example.electricitymeal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class SovetsFragment extends Fragment {
    private Button backbutton;
    private ListView listView;
    private SimpleAdapter simpleAdapter;
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
        LinkedList<Sovet> listBook=new LinkedList<Sovet>();
        listBook.add(new Sovet(1,"Чтобы добавить карточку нажмите плюс в нижнем правом углу"));
        listBook.add(new Sovet(2,"При нажатии на минус на карточке, она удаляется"));
        listBook.add(new Sovet(3,"Чтобы скоратить затраты уменьшите кол-во используемого оборудования"));
        listBook.add(new Sovet(4,"Данное приложение имеет 4 раздела: освещение, бытовая техника, компьютерная и оргтехника, электроинструменты"));
        listBook.add(new Sovet(5,"Каждый раздел имеет свой цвет, чтобы назначить цвет раздела и определить карточку к нему в зоне устройство " +
                "карточки запишите сперва название раздела, а потом через запятую название устройства,например:компьютеры, персональный компьютер " ));
        setRetainInstance(true);
        ArrayList<HashMap<String,Object>> sovetlist=new ArrayList<>();
        HashMap<String,Object> map;
        for(int i=0;i<listBook.size();i++){
            map=new HashMap<String,Object>();
            map.put("id",listBook.get(i).id);
            map.put("text",listBook.get(i).text);
            sovetlist.add(map);
            if(simpleAdapter!=null){
                simpleAdapter.notifyDataSetChanged();
            }
            //sdb.execSQL("INSERT INTO"+ null+" " );
            //sdb.insert(OpenHelper.DATABASE_TABLE,null,contents);
        }
        String id[]={"id","text"};
        int to[]={R.id.sovet_id,R.id.sovet_text};

        simpleAdapter=new SimpleAdapter(getContext(),sovetlist,R.layout.sovet_item,id,to);
        simpleAdapter.notifyDataSetChanged();
        listView=(ListView)getView().findViewById(R.id.tipslist);
        listView.setAdapter(simpleAdapter);
        listView.setDivider(null);
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
class Sovet {
    String text;
    int id;

    public Sovet(int id,String text ) {
        this.id = id;
        this.text = text;
    }
}


