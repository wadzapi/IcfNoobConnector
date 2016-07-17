package org.wadzapi.connid.bundles.hacked;

import java.util.Set;

import org.codehaus.groovy.classgen.asm.OperandStack;

import org.identityconnectors.common.logging.Log;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.ResultsHandler;
import org.identityconnectors.framework.common.objects.Uid;
import org.identityconnectors.framework.common.objects.filter.FilterTranslator;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.Connector;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.operations.AuthenticateOp;
import org.identityconnectors.framework.spi.operations.CreateOp;
import org.identityconnectors.framework.spi.operations.DeleteOp;
import org.identityconnectors.framework.spi.operations.SearchOp;
import org.identityconnectors.framework.spi.operations.TestOp;
import org.identityconnectors.framework.spi.operations.UpdateOp;


@ConnectorClass(configurationClass = HackedCSVFileBundleConfiguration.class,
        displayNameKey = "hacked.connector.display")
@SuppressWarnings("unchecked")
public class HackedConnector implements Connector, CreateOp, UpdateOp,
        DeleteOp, TestOp, SearchOp<OperandStack>, AuthenticateOp {

    private static final Log LOG = Log.getLog(HackedConnector.class);

    private String currentRndString;

    private HackedCSVFileBundleConfiguration csvConfig;

    private RndCsvConnection rndCsvConnection;

    private static RndCsvConnection doConnect(HackedCSVFileBundleConfiguration csvConfig) {
        try {
            LOG.info("Session is connected.");
            return new RndCsvConnection(csvConfig);
        } catch (RuntimeException e) {
            System.out.println("Ошибка соединения с псевдослучатором: " + e);
            LOG.error("Ошибка соединения с псевдослучатором: ", e);
            throw new RuntimeException("Ошибка соединения с псевдослучатором: ", e);
        }
    }

    @Override
    public Configuration getConfiguration() {
        return csvConfig;
    }

    @Override
    public void init(Configuration configuration) {
        if (configuration instanceof HackedCSVFileBundleConfiguration) {
            csvConfig = (HackedCSVFileBundleConfiguration) configuration;
            rndCsvConnection = new RndCsvConnection(csvConfig);

        } else
            throw new ConfigurationException("Передан недопустимый тип конфигуации");
    }

    @Override
    public void dispose() {
        if (rndCsvConnection != null) {
            rndCsvConnection.disconnect();
        } else {
            LOG.warn("Connection is NULL");
        }
    }

    @Override
    public Uid create(ObjectClass objectClass, Set<Attribute> set, OperationOptions operationOptions) {
        LOG.info("creating hackedConnection on HAckedConnector.....");
        test();
        currentRndString = rndCsvConnection.generateRnd();
        return new Uid(currentRndString);
    }

    @Override
    public Uid update(ObjectClass objectClass, Uid uid, Set<Attribute> set, OperationOptions operationOptions) {
        return create(objectClass, set, operationOptions);
    }

    @Override
    public void delete(ObjectClass objectClass, Uid uid, OperationOptions operationOptions) {
        System.out.println("Nothing TODO!!!");
    }

    @Override
    public void test() {
        if (rndCsvConnection == null) {
            rndCsvConnection = doConnect(csvConfig);
        } else {
            rndCsvConnection.setCvsConfig(csvConfig);
        }
    }

    @Override
    public FilterTranslator<OperandStack> createFilterTranslator(ObjectClass objectClass,
                                                                 OperationOptions operationOptions) {
        System.out.printf("Filtering not implemented on HackedConnector");
        return null;
    }

    @Override
    public void executeQuery(ObjectClass objectClass, OperandStack operandStack, ResultsHandler resultsHandler,
                             OperationOptions operationOptions) {
        System.out.println("DO NOTHING on executeQuery method call");
        //throw new UnsupportedOperationException("executeQuery not implemented in HackedConnector");
    }

    String getCurrentRndString() {
        return currentRndString;
    }

    @Override
    public Uid authenticate(ObjectClass objectClass, String string, GuardedString guardedString,
                            OperationOptions operationOptions) {
        return new Uid(currentRndString);
    }
}
