package Utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import WADL.AccountModel;
import WADL.RESTMethodModel;
import WADL.RESTParameterModel;
import WADL.RESTServiceModel;
import WADL.ResourceModel;
import WSDL.InputMessageModel;
import WSDL.InputParameterModel;
import WSDL.OutputMessageModel;
import WSDL.OutputParameterModel;
import WSDL.SOAPOperationModel;
import WSDL.SOAPServiceModel;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new AnnotationConfiguration().configure()
            		.addAnnotatedClass(AccountModel.class)
            		.addAnnotatedClass(RESTServiceModel.class)
            		.addAnnotatedClass(ResourceModel.class)
            		.addAnnotatedClass(RESTMethodModel.class)
            		.addAnnotatedClass(RESTParameterModel.class)
            		.addAnnotatedClass(InputMessageModel.class)
            		.addAnnotatedClass(InputParameterModel.class)
            		.addAnnotatedClass(OutputMessageModel.class)
            		.addAnnotatedClass(OutputParameterModel.class)
            		.addAnnotatedClass(SOAPOperationModel.class)
            		.addAnnotatedClass(SOAPServiceModel.class)
            		.buildSessionFactory();//TODO add the rest models here
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}