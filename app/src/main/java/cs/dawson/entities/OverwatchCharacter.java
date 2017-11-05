package cs.dawson.entities;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Zaza on 2017-11-01.
 */

public class OverwatchCharacter implements Serializable{

    // List<String> or nah?
    private String[] quotes;
    private String nameId;
    private String birthName;
    private String blurb;
    private String dateOfBirth;
    private String infoUrl;
    private String dateAdded;

    public OverwatchCharacter(){
        this(null, "", "", "", "","","");
    }

    public OverwatchCharacter(String[] quotes, String nameId, String birthName, String blurb, String dateOfBirth, String infoUrl, String dateAdded){
        // default is 5
        this.quotes = new String[5];
        this.nameId = nameId;
        this.birthName = birthName;
        this.blurb = blurb;
        this.dateOfBirth = dateOfBirth;
        this.infoUrl = infoUrl;
        this.dateAdded = dateAdded;

    }


    public String[] getQuotes() {
        return quotes;
    }

    public void setQuotes(String[] quotes) {
        this.quotes = quotes;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "OverwatchCharacter{" +
                "quotes=" + Arrays.toString(quotes) +
                ", nameId='" + nameId + '\'' +
                ", birthName='" + birthName + '\'' +
                ", blurb='" + blurb + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", infoUrl='" + infoUrl + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }
}
