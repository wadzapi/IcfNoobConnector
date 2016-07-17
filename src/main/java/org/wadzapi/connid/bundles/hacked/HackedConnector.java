package org.wadzapi.connid.bundles.hacked;

import java.util.Set;

import org.codehaus.groovy.classgen.asm.OperandStack;

import org.identityconnectors.common.security.GuardedString;
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


@ConnectorClass(configurationClass = HackedConfiguration.class,
        displayNameKey = "unix.connector.display")
@SuppressWarnings("unchecked")
public class HackedConnector implements Connector, CreateOp, UpdateOp,
               DeleteOp, TestOp, SearchOp<OperandStack>, AuthenticateOp {
    @Override
    public Configuration getConfiguration() {
        // TODO Implement this method
        return null;
    }

    @Override
    public void init(Configuration configuration) {
        // TODO Implement this method
    }

    @Override
    public void dispose() {
        // TODO Implement this method
    }

    @Override
    public Uid create(ObjectClass objectClass, Set<Attribute> set, OperationOptions operationOptions) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Uid update(ObjectClass objectClass, Uid uid, Set<Attribute> set, OperationOptions operationOptions) {
        // TODO Implement this method
        return null;
    }

    @Override
    public void delete(ObjectClass objectClass, Uid uid, OperationOptions operationOptions) {
        // TODO Implement this method

    }

    @Override
    public void test() {
        // TODO Implement this method
    }

    @Override
    public FilterTranslator<OperandStack> createFilterTranslator(ObjectClass objectClass,
                                                                 OperationOptions operationOptions) {
        // TODO Implement this method
        return null;
    }

    @Override
    public void executeQuery(ObjectClass objectClass, OperandStack operandStack, ResultsHandler resultsHandler,
                             OperationOptions operationOptions) {
        // TODO Implement this method

    }

    @Override
    public Uid authenticate(ObjectClass objectClass, String string, GuardedString guardedString,
                            OperationOptions operationOptions) {
        // TODO Implement this method
        return null;
    }
}
