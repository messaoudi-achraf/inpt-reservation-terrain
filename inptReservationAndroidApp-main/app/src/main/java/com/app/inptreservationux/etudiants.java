package com.app.inptreservationux;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class etudiants implements Parcelable , Serializable {
    private String nom ;
    private String prenom ;
    private String cin ;
    private String niv ;
    private String filiere ;
    private String pass ;
    private String numTel , email ;


    protected etudiants(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        cin = in.readString();
        niv = in.readString();
        filiere = in.readString();
        numTel = in.readString();
        email = in.readString();
        pass = in.readString() ;
    }


    public static final Creator<etudiants> CREATOR = new Creator<etudiants>() {
        @Override
        public etudiants createFromParcel(Parcel in) {
            return new etudiants(in);
        }

        @Override
        public etudiants[] newArray(int size) {
            return new etudiants[size];
        }
    };

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public etudiants(String nom, String prenom, String cin, String niv, String filiere , String num , String email , String p ) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.niv = niv;
        this.filiere = filiere;
        this.numTel = num ;
        this.email = email ;
        this.pass = p ;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNiv(String niv) {
        this.niv = niv;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }


    public String getNomPre(){
        return nom + " " + prenom ;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCin() {
        return cin;
    }

    public String getNiv() {
        return niv;
    }

    public String getPass(){ return pass ; }
    public Boolean isValidData(String email , String pass){
        return email.equals(this.email) && pass.equals(this.pass) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        etudiants etudiants = (etudiants) o;
        return nom.equals(etudiants.nom) && prenom.equals(etudiants.prenom) && cin.equals(etudiants.cin) && niv.equals(etudiants.niv) && filiere.equals(etudiants.filiere) && numTel.equals(etudiants.numTel) && email.equals(etudiants.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, cin, niv, filiere, numTel, email);
    }

    public String getFiliere() {
        return filiere;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + " CIN : " + cin + " " + niv + " " + filiere + " " + email + " " + numTel ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(nom);
    parcel.writeString(prenom);
    parcel.writeString(cin);
    parcel.writeString(niv);
    parcel.writeString(filiere);
    parcel.writeString(numTel);
    parcel.writeString(email);
    parcel.writeString(pass);



    }
}

