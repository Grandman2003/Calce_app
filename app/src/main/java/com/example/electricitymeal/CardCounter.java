package com.example.electricitymeal;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


    class CustomHolder extends RecyclerView.ViewHolder{
        TextView result;
        CardView cardView;
        EditText power;
        EditText count;
        EditText hours;
        EditText goal;
        Button del;
        View itemView;
        public Informationcard item;
        public List<CustomHolder> mPoints;
        EditText minutes;
        int chet;


        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView=(CardView)itemView.findViewById(R.id.itemlayout);
            this.itemView=itemView;
            this.goal=(EditText) itemView.findViewById(R.id.goal);
            this.del=(Button) itemView.findViewById(R.id.itemdeletion);
            this.result = (TextView) itemView.findViewById(R.id.result);
            this.power=(EditText) itemView.findViewById(R.id.power);
            this.count=(EditText) itemView.findViewById(R.id.count);
            this.hours=(EditText) itemView.findViewById(R.id.hours);
            this.minutes=(EditText) itemView.findViewById(R.id.minutes);
            this.chet=0;
        }
        public void bindModel(Informationcard info){
            power.setText(String.valueOf(info.mPower));
            goal.setText(info.mGoal);
            String []s=info.mGoal.toLowerCase().split(" ");
            for(int i=0;i<s.length;i++){
                    if (s[i].equals("освещение") || s[i].equals("lighting")||s[i].equals("освещение,") || s[i].equals("lighting,")) {
                        cardView.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
                        del.setBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
                    }
                    if (s[i].equals("бытовая") ||s[i].equals("прочее") ||  s[i].equals("appliances")||s[i].equals("бытовая,") ||s[i].equals("прочее,") ||  s[i].equals("appliances,")) {
                    cardView.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorAppliances));
                    del.setBackgroundColor(itemView.getResources().getColor(R.color.colorAppliances));
                    }
                    if (s[i].equals("компьютеры") || s[i].equals("компьютерная")||s[i].equals("электроника")||s[i].equals("компьютерный")||s[i].equals("компьютерные")||
                            s[i].equals("оргтехника")||s[i].equals("computers")||s[i].equals("компьютеры,") || s[i].equals("компьютерная,")||s[i].equals("электроника,")||s[i].equals("компьютерный,")||s[i].equals("компьютерные,")||
                            s[i].equals("оргтехника,")||s[i].equals("computers,")) {
                        cardView.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorComputers));
                        del.setBackgroundColor(itemView.getResources().getColor(R.color.colorComputers));
                    }
                    if (s[i].equals("электроинструменты") ||s[i].equals("электроинструмент")|| s[i].equals("power")||s[i].equals("электроинструменты,") ||s[i].equals("электроинструмент,")|| s[i].equals("power,")) {
                        cardView.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorTools));
                        del.setBackgroundColor(itemView.getResources().getColor(R.color.colorTools));
                }
            }
            hours.setText(String.valueOf(info.mHours));
            minutes.setText(String.valueOf(info.mMinutes));
            count.setText(String.valueOf(info.mCount));
            result.setText(String.valueOf(info.mResult)+" кВт/ч");
        }
        public Informationcard InsertModel(Informationcard info){
            Log.d("gsonon","Ты зашёл по адресу"+power.getText().toString());
            if((power.getText()).toString().equals("")){
                info.mPower=0;
            }else{
                info.mPower=Integer.parseInt((power.getText()).toString());}

            if((hours.getText()).toString().equals("")){
                info.mHours=0;
            }else{
                info.mHours=Integer.parseInt((hours.getText()).toString());}

            if((count.getText()).toString().equals("")){
                info.mCount=0;
            }else{
                info.mCount=Integer.parseInt((count.getText()).toString());}

            if((minutes.getText()).toString().equals("")){
                info.mMinutes=0;
            }else{
            info.mMinutes=Integer.parseInt((minutes.getText()).toString());}
            info.mResult=info.mPower*info.mCount*(info.mHours+(info.mMinutes)/60);
            info.mGoal=goal.getText().toString();
            return info;
        }
        public Informationcard EditModel(Informationcard info){
            Log.d("gsonon","Ты зашёл по адресу"+power.getText().toString());
            if((power.getText()).toString().equals("")){
                info.mPower=0;
            }else{
                info.mPower=Integer.parseInt((power.getText()).toString());}

            if((hours.getText()).toString().equals("")){
                info.mHours=0;
            }else{
                info.mHours=Integer.parseInt((hours.getText()).toString());}

            if((count.getText()).toString().equals("")){
                info.mCount=0;
            }else{
                info.mCount=Integer.parseInt((count.getText()).toString());}

            if((minutes.getText()).toString().equals("")){
                info.mMinutes=0;
            }else{
                info.mMinutes=Integer.parseInt((minutes.getText()).toString());}
            info.mGoal=goal.getText().toString();
            return info;
        }

        public EditText getCount() {
            return count;
        }

        public void setResult(TextView result) {
            this.result = result;
        }

        public TextView getResult() {
            return result;
        }

        public EditText getPower() {
            return power;
        }

        public EditText getHours() {
            return hours;
        }

        public EditText getMinutes() {
            return minutes;
        }

    }

//}
class Informationcard{
public int mResult;
public int mPower;
public int mCount;
public int mHours;
public String mGoal;
public int mMinutes;

    public Informationcard( int mCount, int mHours, int mMinutes,int mPower) {
        this.mPower=mPower;
        this.mCount = mCount;
        this.mHours = mHours;
        this.mMinutes = mMinutes;
        this.mGoal="";
        this.mResult = (int) Math.ceil(mPower*mCount*(mHours+((mMinutes*60)/100)));
    }
}


