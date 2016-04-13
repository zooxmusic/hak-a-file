package com.aeg.partner;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PartnerHolder {

    private static PartnerHolder INSTANCE = null;

    public static PartnerHolder getInstance() throws IOException, URISyntaxException {
        if (null == INSTANCE) {
            INSTANCE = new PartnerHolder();
        }
        return INSTANCE;
    }

    private String partnersFilePath = "C:/AEG/config/partners.json";
    //private Path partnersJson;

    private PartnerHolder() throws IOException, URISyntaxException {
        read();
    }

    private Partners partners = null;

    public Partner find(String name) throws IOException, URISyntaxException {
        for (Partner partner : partners.getPartners()) {
            if (partner.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase())) return partner;
        }
        return null;
    }

    /*public static void main(String[] args) {
        PartnerHolder p = new PartnerHolder();
        try {
            p.read();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    private void read() throws URISyntaxException, IOException {
        //URL url = PartnerHolder.class.getResource(partnersFilePath);


        File file = new File(partnersFilePath);
        URL url = PartnerHolder.class.getResource(partnersFilePath);
        List<String> lines = Files.readAllLines(Paths.get(file.toURI()), Charset.forName("UTF-8"));
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {
            builder.append(line);
        }

        String json = sanitize(builder.toString());
        partners = new Gson().fromJson(json, Partners.class);
    }

    /*public void init() throws Exception {
        URL url = PartnerHolder.class.getResource(partnersFilePath);
        partnersJson = Paths.get(url.toURI());
        read();
    }*/
    private String sanitize(String original) {
        String aegHome = System.getProperty("AEG_HOME");
        if (null == aegHome || "".equalsIgnoreCase(aegHome.trim())) {
            aegHome = "C:/AEG";
        }
        return original.replaceAll("#AEG_HOME", aegHome);
    }

    public Partners getPartners() {
        return partners;
    }

    public List<Partner> getPartnerList() {
        return getPartners().getPartners();
    }
}
