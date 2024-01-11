package com.app.inptreservationux;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.Timer;

public class Reservation {

    private int id ;
    private Timestamp dateDebut ;
    private Timestamp dateRes ;
    private Timestamp dateFin ;

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public void setCinEtud(String cinEtud) {
        this.cinEtud = cinEtud;
    }


    private String cinEtud ;
    private String etat ;
    private String terrain ;
    private static int nbrRes = 0  ;

    public Reservation(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateRes() {
        return dateRes;
    }

    public void setDateRes(Timestamp dateRes) {
        this.dateRes = dateRes;
    }




    public String  getCinEtud() {
        return cinEtud;
    }

    public void setEtud(String etud) {
        this.cinEtud = etud;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public static int getNbrRes() {
        return nbrRes;
    }

    public static void setNbrRes(int nbrRes) {
        Reservation.nbrRes = nbrRes;
    }

    public Reservation(Timestamp d , Timestamp f , String et  , String ter){
        id = ++nbrRes ;
        dateDebut = d ;
        dateRes = new Timestamp(System.currentTimeMillis()) ;
        cinEtud = et ;
        etat ="En cours"  ;
        terrain = ter ;
        dateFin = f ;
        Timer timer = new Timer();

        //Use this if you want to execute it once
        timer.schedule( new ResTimer(this) , dateDebut);

    }
    public Reservation(Timestamp d ,Timestamp f , String et  , String ter ,String eta ){
        id = ++nbrRes ;
        dateDebut = d ;
        dateRes = new Timestamp(System.currentTimeMillis()) ;
        cinEtud = et ;
        etat =eta ;
        terrain = ter ;
        dateFin = f ;


    }


    void AnnulerReservation(){
        this.etat="Annulé" ;
    }

    void ResTermine(){
        this.etat = "Terminé" ;
    }




}
