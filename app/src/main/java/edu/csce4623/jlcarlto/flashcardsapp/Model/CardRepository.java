package edu.csce4623.jlcarlto.flashcardsapp.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides API for data access to the ViewModel. These methods call CardDAO methods. All DB ops
 * ran on seprate thread from UI thread, therefore creating runnable objects and overriding
 * run is necessary.
 */
public class CardRepository {
    private CardDao mCardDao;
    private LiveData<List<Card>> mCardsList;
    private LiveData<List<Deck>> mDecksList;

    public CardRepository(Application application) {
        //Get instance of database and get access to DAO
        CardDatabase database = CardDatabase.getInstance(application);
        mCardDao = database.cardDao();
        mCardsList = mCardDao.getAllCards();
        mDecksList = mCardDao.getAllDecks();
    }

    /**
     * @return LiveData list of all cards in the database
     */
    public LiveData<List<Card>> getAllCards() {
        return mCardsList;
    }

    /**
     * Get all decks in the database
     * @return LiveData list of all cards in the database
     */
    public LiveData<List<Deck>> getAllDecks() { return mDecksList; }

    /**
     * Insert a Card object into the database
     * @param card - the card to insert into the database
     * @returns - nothing
     */
    public void insertCard(final Card card) {
        CardDatabase.databaseWriteExecutor.execute( new Runnable() {
            @Override
            public void run() {
                mCardDao.insertCard(card);
            }
        });
    }

    /**
    * Delete a card from the database based on it's ID
     * @param - card - the card to be deleted
     * @returns - nothing
     */
    public void deleteCard(final Card card) {
        CardDatabase.databaseWriteExecutor.execute( new Runnable() {
            @Override
            public void run() {
                mCardDao.deleteCard(card.getId());
            }
        });
    }

    /**
     * Update an existing card in the database
     * @param - card - the card to be updated
     * @returns - nothing
     */
     public void updateCard(final Card card) {
        CardDatabase.databaseWriteExecutor.execute( new Runnable() {
            @Override
            public void run() {
                mCardDao.updateCard(card);
            }
    });
    }
}