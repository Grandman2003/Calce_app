package com.example.electricitymeal;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RemoteException;
import android.print.PrintAttributes;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //private CardCounter.CustomAdapter mAdapter;
    private CustomAdapter mAdapter;
    private RecyclerView recyclerView;
    private TextView rexult;
    private RecyclerView.LayoutManager layoutManager;
    private List<Informationcard> items = new ArrayList<>();
    private Button button;
    private FloatingActionButton fab;
    private FloatingActionButton tips;


    TipsFragment tipsFragment;
    SovetsFragment sovetsFragment;
    ResultFragment fragment;
    public List<CustomHolder> mPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment =ResultFragment.newInstance();
        sovetsFragment=SovetsFragment.newInstance();
        tipsFragment=TipsFragment.newInstance();;
        setContentView(R.layout.activity_main);
        //Display display=getWindowManager().getDefaultDisplay();
       // Point size=new Point();
        //display.getSize(size);
       // int mHeight=size.y;
        final Toolbar toolbar = findViewById(R.id.toolbar);
        final Button delete=findViewById(R.id.action_settings);
        Button change=findViewById(R.id.change_settings);
        mPoints=new ArrayList<>();
        rexult=(TextView)findViewById(R.id.amount);
        setSupportActionBar(toolbar);
        button=(Button)findViewById(R.id.culc);
        //listView = (ListView) findViewById(R.id.lister);
        recyclerView = (RecyclerView) findViewById(R.id.lister);
        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CustomAdapter(items);
        recyclerView.setAdapter(mAdapter);



        Toast.makeText(this,"Чтобы добавить объект затрат нажмите на плюс",Toast.LENGTH_LONG).show();
        fab = findViewById(R.id.fab);
        tips=findViewById(R.id.tips);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                if(findViewById(R.id.fragmentContainer)==null){
                fragmentTransaction.add(R.id.fragmentContainer,fragment).commit();}
                else{
                    fragmentTransaction.replace(R.id.fragmentContainer,fragment).commit();
                }
                    items.add(new Informationcard(0,0,0,0));
                    mAdapter = new CustomAdapter(items);
                    recyclerView.setAdapter(mAdapter);
                        //addContact(contactName, contactPhone);
                        Toast.makeText(getApplicationContext(), "Карточка добавлена", Toast.LENGTH_SHORT).show();
            }
        });
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.allres,sovetsFragment).commit();
                fab.setVisibility(View.INVISIBLE);
                tips.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                int sum=0;
                toolbar.getMenu().getItem(1).setVisible(false);
                if(items.size()!=0){
                    Log.d("gsonon","counter clicked");
                    //CustomHolder holder=(CustomHolder) Objects.requireNonNull(recyclerView.getAdapter()).onCreateViewHolder(recyclerView,1);
                    for (int i=0;i<items.size();i++){
                        Log.d("gsonon","array entered");
                        Log.d("gsonon","мощность "+mPoints.get(i).power.getText().toString());
                       items.set(i, mPoints.get(i).InsertModel(items.get(i)));
                       sum=sum+items.get(i).mResult;
                    }
                    mAdapter=new CustomAdapter(items);
                    recyclerView.setAdapter(mAdapter);
                    getSupportFragmentManager().beginTransaction().add(R.id.allres,tipsFragment).commit();
                    tipsFragment.setRusultation(sum,fragment.getRubl());
                    fab.setVisibility(View.INVISIBLE);
                    tips.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    fab.setClickable(false);

                    button.setClickable(false);
                    if(Integer.parseInt(fragment.getRubl())*sum>20000) {
                        Log.d("gsonon","sum more");
                        NotificationManager mgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        assert mgr != null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mgr.getNotificationChannel("spendings") == null) {
                            mgr.createNotificationChannel(new NotificationChannel("spendings", "Intervention", NotificationManager.IMPORTANCE_DEFAULT));
                        }
                        NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext(), "spendings");
                        b.setAutoCancel(true);
                        Log.d("gsonon","notification created");
                        b.setContentTitle("Слишком большие затраты")
                                .setContentText("Удалите некоторые элементы или уменьшите затраты")
                                .setSmallIcon(android.R.drawable.stat_notify_more)
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
                        Intent outbound = new Intent(Intent.ACTION_VIEW);
                        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, outbound, PendingIntent.FLAG_UPDATE_CURRENT);
                        b.setContentIntent(pi);
                        mgr.notify(0,b.build());
                    }
                }else{
                    //fragment.setRusultation(0);
                    Log.d("gsonon","size =0");
                }
            }
        });
    }
    public void setInteractivity(){
        fab.setClickable(true);
        button.setClickable(true);
        tips.setClickable(true);
        tips.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            items.clear();
            recyclerView.setAdapter(new CustomAdapter(items));
            //button.setClickable(false);
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            button.setVisibility(View.INVISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }



    public class CustomAdapter extends RecyclerView.Adapter<CustomHolder> {
        @Override
        public int getItemViewType(int position) {
            return position % 4;
        }
        private List<Informationcard> mItems;
        public List<Informationcard> getmItems() {
            return mItems;
        }


        public CustomAdapter(List<Informationcard> mItems) {
            this.mItems = mItems;
        }


        @NonNull
        @Override
        public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("gsononviewtype", viewType + " ");
            if (viewType == 0 || viewType==1)
                return new CustomHolder(getLayoutInflater().inflate(R.layout.item_layout, parent, false));
            else
                return new CustomHolder(getLayoutInflater().inflate(R.layout.item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final CustomHolder holder, final int position) {
            holder.bindModel(mItems.get(position));
            if(mPoints.contains(holder)){
            mPoints.set(position,holder);}
            else{
                mPoints.add(position,holder);
            }
            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(mItems.get(position));
                    mPoints.remove(holder);
                    for (int i=0;i<items.size();i++){
                        Log.d("gsonon","array entered");
                        Log.d("gsonon","мощность "+mPoints.get(i).power.getText().toString());
                        items.set(i, mPoints.get(i).EditModel(items.get(i)));
                    }
                    recyclerView.setAdapter(new CustomAdapter(items));
                }
            });
            /*holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    NotificationManager mgr = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mgr.getNotificationChannel("socialism")==null){
                        mgr.createNotificationChannel(new NotificationChannel("socialism", "Intervention", NotificationManager.IMPORTANCE_DEFAULT));
                    }
                    NotificationCompat.Builder b = new NotificationCompat.Builder(view.getContext(), "socialism");
                    b.setAutoCancel(true);
                    b.setContentTitle("The element was successfully deleted")
                            .setContentText(mItems.get(position))
                            .setSmallIcon(android.R.drawable.stat_notify_more)
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
                    Intent outbound = new Intent(Intent.ACTION_VIEW);
                    PendingIntent pi = PendingIntent.getActivity(view.getContext(), 0, outbound, PendingIntent.FLAG_UPDATE_CURRENT);
                    b.setContentIntent(pi);

                    mgr.notify(position, b.build());

                    // BaseClass base = new Decorator4(new Decorator3(new Decorator2(new Decorator1(new BaseClass()))));




                    mItems.remove(position);
                    notifyItemRemoved(position);
                    //notifyDataSetChanged();

                }
            }); */
        }

        @Override
        public int getItemCount() {
            if (items == null) {
                return (0);
            }
            return (items.size());
        }
    }
}
