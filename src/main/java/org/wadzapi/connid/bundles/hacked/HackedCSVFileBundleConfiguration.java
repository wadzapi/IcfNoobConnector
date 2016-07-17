package org.wadzapi.connid.bundles.hacked;

import org.apache.commons.lang3.StringUtils;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.math.BigInteger;

public class HackedCSVFileBundleConfiguration extends AbstractConfiguration {
    private String propertyStranger;

    private BigInteger propertyConfigId;

    private int rndIters;

    private String csvFileName;
    private String csvDelimiter;

    public HackedCSVFileBundleConfiguration() {
        super();
    }


    @ConfigurationProperty(displayMessageKey = "hacked.propertyStranger.display",
            helpMessageKey = "hacked.propertyStranger.help", order = 1)
    public String getPropertyStranger() {
        return propertyStranger;
    }

    public void setPropertyStranger(String propertyStranger) {
        this.propertyStranger = propertyStranger;
    }

    @ConfigurationProperty(displayMessageKey = "hacked.propertyConfigId.display",
            helpMessageKey = "hacked.propertyConfigId.help", order = 2)
    public BigInteger getPropertyConfigId() {
        return propertyConfigId;
    }

    public void setPropertyConfigId(BigInteger propertyConfigId) {
        this.propertyConfigId = propertyConfigId;
    }

    @ConfigurationProperty(displayMessageKey = "hacked.csvFileName.display",
            helpMessageKey = "hacked.csvFileName.help", order = 3)
    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    @ConfigurationProperty(displayMessageKey = "hacked.csvDelimiter.display",
            helpMessageKey = "hacked.csvDelimiter.help", order = 4)
    public String getCsvDelimiter() {
        return csvDelimiter;
    }

    public void setCsvDelimiter(String csvDelimiter) {
        this.csvDelimiter = csvDelimiter;
    }


    @ConfigurationProperty(displayMessageKey = "hacked.rndIters.display",
            helpMessageKey = "hacked.rndIters.help", order = 5)
    public int getRndIters() {
        return rndIters;
    }

    public void setRndIters(int rndIters) {
        this.rndIters = rndIters;
    }

    @Override
    public void validate() {
        System.out.println("validating property values: ");
        if (StringUtils.isEmpty(csvFileName) || StringUtils.isEmpty(csvDelimiter)) {
            throw new ConfigurationException("csv filename and delimeter should be defined!");
        }
    }
}
