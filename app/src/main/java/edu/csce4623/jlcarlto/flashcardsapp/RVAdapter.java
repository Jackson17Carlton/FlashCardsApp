package edu.csce4623.jlcarlto.flashcardsapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {
    List<Card> cardList;
    List<Card> cards;

    RVAdapter(List<Card> cardList){
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_list_item, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int i) {

        Card card = cardList.get(i);
        Log.d("adapter", Integer.toString(i));
        holder.front.setText(card.getCardFront());
        holder.back.setText(card.getCardBack());
    }

    @Override
    public int getItemCount() {
            //Log.d("debug", String.valueOf(cardList.size()));
            return cardList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView front;
        TextView back;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            front = (TextView) itemView.findViewById(R.id.card_front);
            back = (TextView) itemView.findViewById(R.id.card_back);
        }
    }

}