package singpaulee.com.haversinealgorythm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseTubblesModel {
    @SerializedName("kodePesan")
    @Expose
    private Integer kodePesan;
    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("kode")
    @Expose
    private Boolean kode;
    @SerializedName("tambal_ban")
    @Expose
    private ArrayList<TubblesModel> tambalBan = null;

    public Boolean getKode() {
        return kode;
    }

    public void setKode(Boolean kode) {
        this.kode = kode;
    }

    public ArrayList<TubblesModel> getTambalBan() {
        return tambalBan;
    }

    public void setTambalBan(ArrayList<TubblesModel> tambalBan) {
        this.tambalBan = tambalBan;
    }

    public Integer getKodePesan() {
        return kodePesan;
    }

    public void setKodePesan(Integer kodePesan) {
        this.kodePesan = kodePesan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
