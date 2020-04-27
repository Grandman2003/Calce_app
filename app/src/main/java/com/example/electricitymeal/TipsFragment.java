package com.example.electricitymeal;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TipsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TipsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TipsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public TextView textView;
    private Button backbutton;
    private TextView sovet;
    private TipsFragment fragment;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    String lightpower;
    String    lightcost;
    String applpower;
    String applcost;
    String comppower;
     String compcost;
    String otherpower;
   String othercost;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Toolbar toolbar;
    private String resultation;
    private int resultic;
    private OnFragmentInteractionListener mListener;

    public TipsFragment() {
        // Required empty public constructor
    }

    public void setFragment(TipsFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TipsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TipsFragment newInstance() {
        TipsFragment fragment = new TipsFragment();
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
        return inflater.inflate(R.layout.fragment_tips, container, false);
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
        textView=(TextView) view.findViewById(R.id.amount);
        toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        sovet=(TextView)view.findViewById(R.id.samsovet);
        if(resultic<20000){
            sovet.setText("Ваши затараты под контролем и беспокоиться не стоит, но если хотите ещё" +
                    " уменьшить свои расходы посмотрите наши советы," +
                    " нахжав на зелёную кнопку на главном экране");
        }else{
            sovet.setText("К сожалению, у вас повышенные расходы.Чтобы как-то поправить" +
                    " ситуацию обратитесь к разделу советы,который можно" +
                    " открыть,нажав на зелённую кнопку на главном углу");
        }
        textView.setText(resultation);

        //result fields
         TextView b1=(TextView)view.findViewById(R.id.svet_pow);
         TextView b2=(TextView)view.findViewById(R.id.svet_cost);
        TextView b3=(TextView)view.findViewById(R.id.appl_pow);
        TextView b4=(TextView)view.findViewById(R.id.appl_cost);
        TextView b5=(TextView)view.findViewById(R.id.comp_pow);
        TextView b6=(TextView)view.findViewById(R.id.comp_cost);
        TextView b7=(TextView)view.findViewById(R.id.other_pow);
        TextView b8=(TextView) view.findViewById(R.id.other_cost);
        b1.setText(lightpower);
        b2.setText(lightcost);
        b3.setText(applpower);
        b4.setText(applcost);
        b5.setText(comppower);
        b6.setText(compcost);
        b7.setText(otherpower);
        b8.setText(othercost);

        backbutton=(Button)view.findViewById(R.id.backbutton);
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
    public void setTextView(TextView textView){
        this.textView=textView;
    }

    public void setCounting(List<Informationcard> items, String rubl){
        int sumsvetpower = 0;
        int sumsvetcost=0;
        int sumapplpower = 0;
        int sumapplcost=0;
        int sumcomppower = 0;
        int sumcompcost=0;
        int sumotherpower = 0;
        int sumothercost=0;
        for(int i=0;i<items.size();i++){
            String s[]=items.get(i).mGoal.toLowerCase().split(" ");
            for (int j=0;j<s.length;j++){
                switch (s[j]){
                    case "освещение" :
                          sumsvetpower+=items.get(i).mPower;
                          sumsvetcost += items.get(i).mResult*Integer.parseInt(rubl);
                          //тоже самое и с другими элементами
                        break;
                    case "компьютеры" :
                        sumcomppower+=items.get(i).mPower;
                        sumcompcost += items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "бытовая" :
                        sumapplpower+=items.get(i).mPower;
                        sumapplcost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "электроинструметы" :
                        sumotherpower+=items.get(i).mPower;
                        sumothercost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "освещение," :
                        sumsvetpower+=items.get(i).mPower;
                        sumsvetcost += items.get(i).mResult*Integer.parseInt(rubl);
                        //тоже самое и с другими элементами
                        break;
                    case "компьютеры," :
                        sumcomppower+=items.get(i).mPower;
                        sumcompcost += items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "бытовая," :
                        sumapplpower+=items.get(i).mPower;
                        sumapplcost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "электроинструметы," :
                        sumotherpower+=items.get(i).mPower;
                        sumothercost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "lighting" :
                        sumsvetpower+=items.get(i).mPower;
                        sumsvetcost += items.get(i).mResult*Integer.parseInt(rubl);
                        //тоже самое и с другими элементами
                        break;
                    case "computers" :
                        sumcomppower+=items.get(i).mPower;
                        sumcompcost += items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "appliances" :
                        sumapplpower+=items.get(i).mPower;
                        sumapplcost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "power" :
                        sumotherpower+=items.get(i).mPower;
                        sumothercost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "lighting," :
                        sumsvetpower+=items.get(i).mPower;
                        sumsvetcost += items.get(i).mResult*Integer.parseInt(rubl);
                        //тоже самое и с другими элементами
                        break;
                    case "computers," :
                        sumcomppower+=items.get(i).mPower;
                        sumcompcost += items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "appliances," :
                        sumapplpower+=items.get(i).mPower;
                        sumapplcost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;
                    case "power," :
                        sumotherpower+=items.get(i).mPower;
                        sumothercost+= items.get(i).mResult*Integer.parseInt(rubl);
                        break;


                        default:
                            sumotherpower+=items.get(i).mPower;
                            sumothercost+= items.get(i).mResult*Integer.parseInt(rubl);
                            break;

                }
            }
            lightpower= String.valueOf(sumsvetpower);
            lightcost= String.valueOf(sumsvetcost);
            applpower= String.valueOf(sumapplpower);
            applcost= String.valueOf(sumapplcost);
            comppower= String.valueOf(sumcomppower);
            compcost= String.valueOf(sumcompcost);
            otherpower= String.valueOf(sumotherpower);
            othercost= String.valueOf(sumothercost);

        }

    }

    @SuppressLint("SetTextI18n")
    public void setRusultation(int sum, String rubl){
        if(rubl.equals("")){
            rubl="0";
        }
        //textView.setText(Integer.parseInt(rubl) * sum + " руб.");
        resultic=Integer.parseInt(rubl) * sum;
        resultation=Integer.parseInt(rubl) * sum + " руб.";

        /*if(Integer.parseInt(rubl)*sum>20000) {
            Log.d("gsonon","sum more");
            NotificationManager mgr = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            assert mgr != null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mgr.getNotificationChannel("spendings") == null) {
                mgr.createNotificationChannel(new NotificationChannel("spendings", "Intervention", NotificationManager.IMPORTANCE_DEFAULT));
            }
            NotificationCompat.Builder b = new NotificationCompat.Builder(Objects.requireNonNull(getContext()), "spendings");
            b.setAutoCancel(true);
            Log.d("gsonon","notification created");
            b.setContentTitle("Слишком большие затраты")
                    .setContentText("Удалите некоторые элементы или уменьшите затраты")
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            Intent outbound = new Intent(Intent.ACTION_VIEW);
            PendingIntent pi = PendingIntent.getActivity(getContext(), 0, outbound, PendingIntent.FLAG_UPDATE_CURRENT);
            b.setContentIntent(pi);
            mgr.notify(0,b.build());
        } */
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
