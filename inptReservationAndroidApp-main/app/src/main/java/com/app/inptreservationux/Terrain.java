package com.app.inptreservationux;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class Terrain implements Parcelable , Serializable {
  private String id , sport ;

  public Terrain(String id, String sport) {
    this.id = id;
    this.sport = sport;
  }

  protected Terrain(Parcel in) {
    id = in.readString();
    sport = in.readString();
  }

  public static final Creator<Terrain> CREATOR = new Creator<Terrain>() {
    @Override
    public Terrain createFromParcel(Parcel in) {
      return new Terrain(in);
    }

    @Override
    public Terrain[] newArray(int size) {
      return new Terrain[size];
    }
  };

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Terrain terrain = (Terrain) o;
    return id.equals(terrain.id) && sport.equals(terrain.sport);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sport);
  }

  public void setSport(String sport) {
    this.sport = sport;
  }

  public String getId() {
    return id;
  }

  public String getSport() {
    return sport;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(id);
    parcel.writeString(sport);

  }
}
