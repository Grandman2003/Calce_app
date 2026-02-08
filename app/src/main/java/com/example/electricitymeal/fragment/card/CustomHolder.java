package com.example.electricitymeal.fragment.card;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricitymeal.R;

import java.util.List;

public class CustomHolder extends RecyclerView.ViewHolder{
        TextView result;
        CardView cardView;
        EditText power;
        EditText count;
        EditText hours;
        EditText goal;
        public Button del;
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
                    if (s[i].equals("электроинструменты") ||s[i].equals("электроинструмент")|| s[i].equals("power")||s[i].equals("электроинструменты,")
                            ||s[i].equals("электроинструмент,")|| s[i].equals("power,")||s[i].equals("другое,")||s[i].equals("другое")) {
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
