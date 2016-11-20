package lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;

/**
 * Created by PubuduC on 20-Nov-16.
 */

public class PersistentDemoExpenseManager extends ExpenseManager {
    private Context context;
    public PersistentDemoExpenseManager(Context context){
        this.context=context;
        setup();
    }
    @Override
    public void setup()  {
        SQLiteDatabase mydatabase = context.openOrCreateDatabase("140088L",context.MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                "Account_no VARCHAR PRIMARY KEY," +
                "Bank VARCHAR," +
                "Holder VARCHAR," +
                "Initial_amt REAL" +
                " );");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS TransactionLog(" +
                "Transaction_id INTEGER PRIMARY KEY," +
                "Account_no VARCHAR," +
                "Type INT," +
                "Amt REAL," +
                "Log_date DATE," +
                "FOREIGN KEY (Account_no) REFERENCES Account(Account_no)" +
                ");");
        PersistentAccountDAO accountDAO = new PersistentAccountDAO(mydatabase);
        setAccountsDAO(accountDAO);

        setTransactionsDAO(new PersistentTransactionDAO(mydatabase));
    }
}
