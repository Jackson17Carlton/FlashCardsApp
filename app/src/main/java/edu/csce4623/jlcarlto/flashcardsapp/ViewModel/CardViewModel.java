package edu.csce4623.jlcarlto.flashcardsapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;
import edu.csce4623.jlcarlto.flashcardsapp.Model.CardRepository;

/**
 * This ViewModel has access to the Repository methods over the Card table. Plug View components
 * into this ViewModel to access data in the database.
 */
public class CardViewModel extends AndroidViewModel {

    // ViewModel object will have an instance of the repository and a list of all cards in the database
    private CardRepository mCardRepository;
    private final LiveData<List<Card>> mCards;

    /**
     * Constructor for CardViewModel objects
     * @param application - applicaton context
     */
    public CardViewModel(@NonNull Application application) {
        super(application);
        mCardRepository = new CardRepository(application);
        mCards = mCardRepository.getAllCards();
    }

    /**
     * @return LiveData list of all cards in the database
     */
    public LiveData<List<Card>> getAllCards() {
        return mCards;
    }

    /**
     * Call the repository method for inserting cards
     * @param card - the card to insert into the database
     * @returns - nothing
     */
    public void insert(final Card card) {
        mCardRepository.insert(card);
    }

    /**
     * Call the repository method for deleting cards
     * @param - card - the card to be deleted
     * @returns - nothing
     */
    public void delete(final Card card) {
        mCardRepository.delete(card);
    }

    /**
     * Call the repository method for updating cards
     * @param - card - the card to be updated
     * @returns - nothing
     */
    public void update(final Card card) {
        mCardRepository.update(card);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("CardViewModel", "ViewModel Destroyed");
    }
}
