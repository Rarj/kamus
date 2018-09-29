package dev.arjuna.ri0.projetckamus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class KamusModel implements Parcelable {
    private int id;
    private String kata;
    private String translate;

    public KamusModel() {
    }

    public KamusModel(String kata, String translate) {
        this.kata = kata;
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public KamusModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getKata() {
        return kata;
    }

    public KamusModel setKata(String kata) {
        this.kata = kata;
        return this;
    }

    public String getTranslate() {
        return translate;
    }

    public KamusModel setTranslate(String translate) {
        this.translate = translate;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kata);
        dest.writeString(this.translate);
    }

    private KamusModel(Parcel in) {
        this.id = in.readInt();
        this.kata = in.readString();
        this.translate = in.readString();
    }

    public static final Parcelable.Creator<KamusModel> CREATOR = new Parcelable.Creator<KamusModel>() {
        @Override
        public KamusModel createFromParcel(Parcel source) {
            return new KamusModel(source);
        }

        @Override
        public KamusModel[] newArray(int size) {
            return new KamusModel[size];
        }
    };
}
