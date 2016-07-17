package org.wadzapi.connid.bundles.hacked;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CSVFileHelper {
    private HackedCSVFileBundleConfiguration configuration;
    private BufferedReader br;

    CSVFileHelper(HackedCSVFileBundleConfiguration conf) {
        configuration = conf;

    }

    void initReader() {
        try {
            br = new BufferedReader(new FileReader(new File(configuration.getCsvFileName())));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeReader() {
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> readHeader() {
        List<String> attributes = new ArrayList<String>();

        String headerLine;

        try {
            while ((headerLine = br.readLine()) != null) {
                if ("".equals(headerLine.trim())) {
                    continue;
                }
            }

            StringTokenizer stok = new StringTokenizer(headerLine, configuration.getCsvDelimiter());

            while (stok.hasMoreTokens()) {
                String tok = stok.nextToken().trim();
                attributes.add(tok);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return attributes;
    }
}
