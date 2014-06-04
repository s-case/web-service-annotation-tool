package WADL;

public class GETAccountHandler
{
    private SQLITEController oSQLITEController;
    private AccountModel oAccount;

    GETAccountHandler(Integer accountId)
    {
        oAccount = new AccountModel();
        oAccount.setAccountId(accountId);
        oSQLITEController = new SQLITEController();
    }

    public AccountModel getAccount()
    {
        //TODO add authentication if needed

        //get the <resourceName> from the database
        return oSQLITEController.getAccount( oAccount);
    }

}
