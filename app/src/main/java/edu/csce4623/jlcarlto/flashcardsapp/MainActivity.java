package edu.csce4623.jlcarlto.flashcardsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;
import edu.csce4623.jlcarlto.flashcardsapp.Model.CardRepository;
import edu.csce4623.jlcarlto.flashcardsapp.ViewModel.CardViewModel;

public class MainActivity extends AppCompatActivity {

    CardViewModel mViewModel;
    private LiveData<List<Card>> cardList;
    private List<Card> cards;
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        mViewModel = new CardViewModel(getApplication());
        cardList = mViewModel.getAllCards();

        cards = new ArrayList<>();
        cardList.observe(this, new Observer<List<Card>>() {
                    @Override
                    public void onChanged(List<Card> c) {
                        Log.d("onChanged", "cards cleared");
                        cards.clear();
                        cards.addAll(c);
                    }
        });
        rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        Card card = new Card("Front text", "Back text");
        mViewModel.insert(card);

        //Log.d("debug", Integer.toString(cards.size()));
        if(cards == null) {
            Log.d("debug", "cards is NULL");
        }
        initializeAdapter();
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(cards);
        rv.setAdapter(adapter);
    }
}
