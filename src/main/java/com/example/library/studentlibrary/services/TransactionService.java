package com.example.library.studentlibrary.services;

import com.example.library.studentlibrary.models.*;
import com.example.library.studentlibrary.repositories.BookRepository;
import com.example.library.studentlibrary.repositories.CardRepository;
import com.example.library.studentlibrary.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Component
public class TransactionService {

    @Autowired
    BookRepository bookRepository5;

    @Autowired
    CardRepository cardRepository5;

    @Autowired
    TransactionRepository transactionRepository5;

    @Value("${books.max_allowed}")
    int max_allowed_books;

    @Value("${books.max_allowed_days}")
    int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    int fine_per_day;

    public String issueBook(int cardId, int bookId) throws Exception {
        Transaction t = new Transaction();
        //check whether bookId and cardId already exist
        //conditions required for successful transaction of issue book:
        //1. book is present and available
        // If it fails: throw new Exception("Book is either unavailable or not present");
        Book b = bookRepository5.findById(bookId).get();
        try {
            if (!bookRepository5.existsById(bookId) || !b.isAvailable()) {
                throw new Exception("Book is either unavailable or not present");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        // Book b = bookRepository5.findById(bookId).get();
        t.setBook(b);
        //2. card is present and activated
        // If it fails: throw new Exception("Card is invalid");
        Card c = cardRepository5.findById(cardId).get();
        try {
            if (!cardRepository5.existsById(cardId) || c.getCardStatus().toString().equals("Deactivated")) {
                throw new Exception("Card is invalid");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        // Card c = cardRepository5.findById(cardId).get();
        t.setCard(c);
        //3. number of books issued against the card is strictly less than max_allowed_books
        // If it fails: throw new Exception("Book limit has reached for this card");
        try {
            if (c.getBooks().size() > max_allowed_books) {
                throw new Exception("Book limit has reached for this card");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        //If the transaction is successful, save the transaction to the list of transactions and return the id
        t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        t.setIssueOperation(true);
        transactionRepository5.save(t);
        //Note that the error message should match exactly in all cases

        // return null; //return transactionId instead
        return t.getTransactionId();
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception{

        List<Transaction> transactions = transactionRepository5.find(cardId, bookId,TransactionStatus.SUCCESSFUL, true);
        Transaction transaction = transactions.get(transactions.size() - 1);

        //for the given transaction calculate the fine amount considering the book has been returned exactly when this function is called

        //make the book available for other users
        //make a new transaction for return book which contains the fine amount as well

        Transaction returnBookTransaction  = null;
        return returnBookTransaction; //return the transaction after updating all details
    }
}