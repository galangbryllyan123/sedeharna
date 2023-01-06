package singpaulee.com.haversinealgorythm.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TubblesModel implements Comparable, Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("fitur_mobile")
    @Expose
    private String fiturMobile;
    @SerializedName("fitur_motor")
    @Expose
    private String fiturMotor;
    @SerializedName("fitur_fullday")
    @Expose
    private String fiturFullday;
    @SerializedName("fitur_tubbles")
    @Expose
    private String fiturTubbles;
    @SerializedName("fitur_nitrogen")
    @Expose
    private String fiturNitrogen;
    private Double jarak;

    public TubblesModel(String id, String nama, String alamat, String telepon, String latitude, String longitude, String gambar, String fiturMobile, String fiturMotor, String fiturFullday, String fiturTubbles, String fiturNitrogen, Double jarak) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gambar = gambar;
        this.fiturMobile = fiturMobile;
        this.fiturMotor = fiturMotor;
        this.fiturFullday = fiturFullday;
        this.fiturTubbles = fiturTubbles;
        this.fiturNitrogen = fiturNitrogen;
        this.jarak = jarak;
    }

    protected TubblesModel(Parcel in) {
        id = in.readString();
        nama = in.readString();
        alamat = in.readString();
        telepon = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        gambar = in.readString();
        fiturMobile = in.readString();
        fiturMotor = in.readString();
        fiturFullday = in.readString();
        fiturTubbles = in.readString();
        fiturNitrogen = in.readString();
        if (in.readByte() == 0) {
            jarak = null;
        } else {
            jarak = in.readDouble();
        }
    }

    public static final Creator<TubblesModel> CREATOR = new Creator<TubblesModel>() {
        @Override
        public TubblesModel createFromParcel(Parcel in) {
            return new TubblesModel(in);
        }

        @Override
        public TubblesModel[] newArray(int size) {
            return new TubblesModel[size];
        }
    };

    public Double getJarak() {
        return jarak;
    }

    public void setJarak(Double jarak) {
        this.jarak = jarak;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getFiturMobile() {
        return fiturMobile;
    }

    public void setFiturMobile(String fiturMobile) {
        this.fiturMobile = fiturMobile;
    }

    public String getFiturMotor() {
        return fiturMotor;
    }

    public void setFiturMotor(String fiturMotor) {
        this.fiturMotor = fiturMotor;
    }

    public String getFiturFullday() {
        return fiturFullday;
    }

    public void setFiturFullday(String fiturFullday) {
        this.fiturFullday = fiturFullday;
    }

    public String getFiturTubbles() {
        return fiturTubbles;
    }

    public void setFiturTubbles(String fiturTubbles) {
        this.fiturTubbles = fiturTubbles;
    }

    public String getFiturNitrogen() {
        return fiturNitrogen;
    }

    public void setFiturNitrogen(String fiturNitrogen) {
        this.fiturNitrogen = fiturNitrogen;
    }



    @Override
    public int compareTo(@NonNull Object o) {
        String j1 = String.format("%.2f",((TubblesModel)o).getJarak()).replace(",",".");
        String j2 = String.format("%.2f",this.getJarak()).replace(",",".");
        Double j11 = new Double(Double.valueOf(j1)*1000);
        Double j12 = new Double(Double.valueOf(j2)*1000);
        int hasilJ1 = j11.intValue();
        int hasilJ2 = j12.intValue();
        return hasilJ2-hasilJ1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nama);
        parcel.writeString(alamat);
        parcel.writeString(telepon);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(gambar);
        parcel.writeString(fiturMobile);
        parcel.writeString(fiturMotor);
        parcel.writeString(fiturFullday);
        parcel.writeString(fiturTubbles);
        parcel.writeString(fiturNitrogen);
        if (jarak == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(jarak);
        }
    }
}
