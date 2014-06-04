package WADL;


public class DELETEAccountHandler
{

    private SQLITEController oSQLITEController;
    private AccountModel oAccount;

    DELETEAccountHandler(Integer accountId)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        oSQLITEController = new SQLITEController();
    }

    public void deleteAccount()
    {
        //TODO add authentication if needed

        //delete the <resourceName> from the database
        oSQLITEController.deleteAccount( oAccount);
    }
}