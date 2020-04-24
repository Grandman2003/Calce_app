package com.example.electricitymeal;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.security.auth.callback.Callback;

public class ResultFragment extends Fragment  {
    private Loader<String> mLoader;
    private EditText rubl;


    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // todo
        //if(getLoaderManager().getLoader(0)!=null){
        //}

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // setRetainInstance(true);
        return inflater.inflate(R.layout.resultfragmentlayout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rubl=(EditText) view.findViewById(R.id.rubl);
        rubl.setText("0");
    }
    public String getRubl(){
        return rubl.getText().toString();
    }

   /* @SuppressLint("SetTextI18n")
    public void setRusultation(int sum){
        if(rubl.getText().toString().equals("")){
            rubl.setText("0");
        }
        textView.setText(String.valueOf(Integer.parseInt(rubl.getText().toString())*sum)+ " руб.");
        if(Integer.parseInt(rubl.getText().toString())*sum>20000) {
            Log.d("gsonon","sum more");
            NotificationManager mgr = (NotificationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.NOTIFICATION_SERVICE);
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
        }
    } */


}
