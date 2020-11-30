package edu.csce4623.jlcarlto.flashcardsapp.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Provides API for data access to the ViewModel. These methods call CardDAO methods
 */
public class CardRepository {
    private CardDao mCardDao;
    private LiveData<List<Card>> mCardsList;

    public CardRepository(Application application) {
        //Get instance of database and get access to DAO
        CardDatabase database = CardDatabase.getInstance(application);
        mCardDao = database.cardDao();
        mCardsList = mCardDao.getAllCards();
    }

    /**
     * @return LiveData list of all cards in the database
     */
    public LiveData<List<Card>> getAllCards() {
        return mCardsList;
    }

    /**
     * Insert a Card object into the database
     * @param card - the card to insert into the database
     * @returns - nothing
     */
    public void insert(final Card card) {
        CardDatabase.databaseWriteExecutor.execute( new Runnable() {
            @Override
            public void run() {
                mCardDao.insert(card);
            }
        });
    }

    /**
    * Delete a card from the database based on it's ID
     * @param - card - the card to be deleted
     * @returns - nothing
     */
    public void delete(final Card card) {
        CardDatabase.databaseWriteExecutor.execute( new Runnable() {
            @Override
            public void run() {
                mCardDao.delete(card.getId());
            }
        });
    }

    /**
     * Update an existing card in the database
     * @param - card - the card to be updated
     * @returns - nothing
     */
     public void update(final Card card) {
        CardDatabase.databaseWriteExecutor.execute( new Runnable() {
            @Override
            public void run() {
                mCardDao.update(card);
            }
    });
    }
}
