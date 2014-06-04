package WADL;

public class POSTAccountHandler
{
    private AccountModel oAccount;
    private SQLITEController oSQLITEController;

    POSTAccountHandler( AccountModel oAccount)
    {
        this.oAccount = oAccount;
        oSQLITEController = new SQLITEController();
    }

    public AccountModel postAccount()
    {
        //TODO add authentication if needed

        return oSQLITEController.postAccount(oAccount);
    }
}
