package org.wadzapi.connid.bundles.hacked;

import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.Uid;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NoobMain {


    private final HackedConnector hackedConnector;


    private NoobMain() {
        hackedConnector = new HackedConnector();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Main METHOD SUCCESSFULLY called!!");
        final NoobMain noobMain = new NoobMain();
        System.out.println("generating Obj attrs and other staff");
        HackedConnector hackedConnector = noobMain.getHackedConnector();
        hackedConnector.init(new HackedCSVFileBundleConfiguration());
        ObjectClass objectClass = new ObjectClass("hackedType");
        OperationOptions operationOptions = new OperationOptions(new HashMap<String, Object>());
        Set<Attribute> objectAttributes = new HashSet<Attribute>();
        System.out.println("test create");
        hackedConnector.create(objectClass, objectAttributes, operationOptions);
        System.out.println("test test");
        hackedConnector.test();
        System.out.println("test update");
        String currRnd = hackedConnector.getCurrentRndString();
        Uid currentUid = currRnd == null ? null : new Uid(currRnd);
        hackedConnector.update(objectClass, currentUid, objectAttributes, operationOptions);
        System.out.println("disposing hackedConnector Instance");
        hackedConnector.dispose();
        System.out.println("Main METHOD SUCCESSFULLY finished!!");
    }

    private HackedConnector getHackedConnector() {
        return hackedConnector;
    }
}
