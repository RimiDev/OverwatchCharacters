package cs.dawson.entities;

import java.util.Arrays;

/**
 * Created by Zaza on 2017-11-01.
 */

public class OverwatchCharacter {

    // List<String> or nah?
    private String[] quotes;
    private String nameId;
    private String birthName;
    private String blurb;
    private String dateOfBirth;
    private String description;
    private String infoUrl;

    public OverwatchCharacter(){
        this(null, "", "", "", "","","");
    }

    public OverwatchCharacter(String[] quotes, String nameId, String birthName, String blurb, String dateOfBirth, String description, String infoUrl){
        // TODO maybe we should use ArrayList in order to be able to add more quotes later?
        // default is 5
        this.quotes = new String[5];
        this.nameId = nameId;
        this.birthName = birthName;
        this.blurb = blurb;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.infoUrl = infoUrl;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    @Override
    public String toString() {
        return "Character{" +
                "quotes=" + Arrays.toString(quotes) +
                ", birthName='" + birthName + '\'' +
                ", blurb='" + blurb + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", description='" + description + '\'' +
                ", infoUrl='" + infoUrl + '\'' +
                '}';
    }
}
