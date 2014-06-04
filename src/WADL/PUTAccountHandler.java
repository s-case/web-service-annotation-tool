package WADL;

public class PUTAccountHandler
{
    private AccountModel oAccount;
    private SQLITEController oSQLITEController;

    PUTAccountHandler(Integer accountId, AccountModel oAccount)
    {
        this.oAccount = oAccount;
        this.oAccount.setAccountId(accountId);
        oSQLITEController = new SQLITEController();
    }

    public AccountModel putAccount()
    {
        //TODO add authentication if needed

        return oSQLITEController.putAccount(oAccount);
    }

}
