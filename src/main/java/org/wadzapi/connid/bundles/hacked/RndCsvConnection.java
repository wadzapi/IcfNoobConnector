package org.wadzapi.connid.bundles.hacked;

import org.identityconnectors.common.logging.Log;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

class RndCsvConnection {

    private static final Log LOG = Log.getLog(RndCsvConnection.class);

    private static final int RND_LEN = 20;

    private final String RND_ALGORITHM = "SHA1PRNG";

    private final SecureRandom secureRandom;

    private HackedCSVFileBundleConfiguration cvsConfig;

    private final CSVFileHelper csvFileHelper;

    RndCsvConnection(HackedCSVFileBundleConfiguration configuration) {
        this.secureRandom = initRndGenerator();
        cvsConfig = configuration;
        csvFileHelper = new CSVFileHelper(cvsConfig);
        csvFileHelper.initReader();
    }

    private SecureRandom initRndGenerator() {
        try {
            final SecureRandom secureRandom = SecureRandom.getInstance(RND_ALGORITHM);
            secureRandom.setSeed(new Random().nextLong());
            return secureRandom;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[Secure " + RND_ALGORITHM + " init error occured]: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    String generateRnd() {
        try {
            final byte[] rndBytes = new byte[RND_LEN];
            secureRandom.nextBytes(rndBytes);
            BigInteger rndValue = new BigInteger(secureRandom.generateSeed(RND_LEN));
            System.out.println("RND bigint: " + rndValue);
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] md5Sum = md5Digest.digest(rndBytes);
            String output01 = String.format("%032X", new BigInteger(1, md5Sum));
            System.out.println("out01: " + output01);
            String output02 = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(rndBytes));
            System.out.println("return out02: " + output02);
            return output02;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    void setCvsConfig(HackedCSVFileBundleConfiguration cvsConfig) {
        this.cvsConfig = cvsConfig;
    }

    void disconnect() {
        System.out.println("Session is disconnected.");
        LOG.info("Trying to reinit rndConnection");
        initRndGenerator();
        csvFileHelper.closeReader();
        LOG.info("Session is disconnected.");
    }
}