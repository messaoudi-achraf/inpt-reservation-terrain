package com.app.inptreservationux;

import android.util.Log;
import android.widget.Switch;
import android.window.SplashScreen;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Session {
    private static ArrayList<etudiants> etudiantsData = new ArrayList<>() ;
    private static ArrayList<Terrain> TerrainData = new ArrayList<>() ;
    private static ArrayList<Reservation> resData  = new ArrayList<>() ;
    private static  etudiants LoggedEtud ;

    public static ArrayList<etudiants> getetudiantsData() {
        return etudiantsData;
    }

    public static void setetudiantsData(ArrayList<etudiants> etudiantsData) {
        Session.etudiantsData = new ArrayList<>(etudiantsData);
    }
    public  static void setTerrainData( ArrayList<Terrain> TerrData){
        Session.TerrainData = new ArrayList<>(TerrData)  ;
    }
    public  static void setResData(ArrayList<Reservation> resDat){
        Session.resData = new ArrayList<>(resDat) ;
    }
    public  static ArrayList<Terrain> getTerData(){
        return TerrainData ;
    }
    public static ArrayList<String> getTerNames(){
        ArrayList<String> names = new ArrayList<>() ;
        for (Terrain t : TerrainData
             ) { names.add(t.getId());

        }
        return names ;
    }
    public static void removeHisto(String cin){
        for (Reservation r : resData
             ) {
            if (r.getCinEtud().equals(cin)) resData.remove(r) ;
        }
    }
    public static boolean checkDate(Timestamp deb , Timestamp fin){
        for (Reservation r: resData
             ) {
            if( (deb.after(r.getDateDebut()) && deb.before(r.getDateFin())) ||fin.after(r.getDateDebut()) && fin.before(r.getDateFin()))  return false ;
        }
        return true ;
    }

    public static etudiants getLoggedEtud() {
        return LoggedEtud;
    }

    public static void setLoggedEtud(etudiants loggedEtud) {
        LoggedEtud = loggedEtud;
    }


    static public  void addEtud(etudiants e ){
        etudiantsData.add(e );
    }

    static public void removeEtud(etudiants e ){
        etudiantsData.remove(e) ;
    }
    static public void addTerrain(Terrain t ){
        TerrainData.add(t) ;
    }
    static  public void removeTer(Terrain t ){
        TerrainData.remove(t) ;
    }
    static  public void addRes(Reservation r){ resData.add(r) ;}
    static public void removeRes(Reservation r ){ resData.remove(r) ; }
    static public HashMap<String , List<Reservation>> getresDataEncours(etudiants e ){

        HashMap<String , List<Reservation>> resMap = new HashMap<>() ;
        ArrayList<Reservation> resAnnule = new ArrayList<>() ;
        ArrayList<Reservation> resTermine = new ArrayList<>() ;
        ArrayList<Reservation> resEncours = new ArrayList<>() ;

        for (Reservation r: resData
             ) {
            if(Session.getLoggedEtud().getCin().equals(r.getCinEtud()) && r.getEtat().equals("En cours")){


                        resEncours.add(r) ;



            }






        }
        resMap.put("En cours" , resEncours) ;

        return resMap ;

    }

    static public HashMap<String , List<Reservation>> getresDataTermine(etudiants e ){

        HashMap<String , List<Reservation>> resMap = new HashMap<>() ;
        ArrayList<Reservation> resAnnule = new ArrayList<>() ;
        ArrayList<Reservation> resTermine = new ArrayList<>() ;
        ArrayList<Reservation> resEncours = new ArrayList<>() ;

        for (Reservation r: resData
        ) {
            if(Session.getLoggedEtud().getCin().equals(r.getCinEtud()) && r.getEtat().equals("Terminé")){


                resEncours.add(r) ;



            }






        }
        resMap.put("Terminé" , resEncours) ;

        return resMap ;

    }

    static public HashMap<String , List<Reservation>> getresDataAnnule(etudiants e ){

        HashMap<String , List<Reservation>> resMap = new HashMap<>() ;
        ArrayList<Reservation> resAnnule = new ArrayList<>() ;
        ArrayList<Reservation> resTermine = new ArrayList<>() ;
        ArrayList<Reservation> resEncours = new ArrayList<>() ;

        for (Reservation r: resData
        ) {
            if(Session.getLoggedEtud().getCin().equals(r.getCinEtud()) && r.getEtat().equals("Annulé")){


                resEncours.add(r) ;



            }






        }
        resMap.put("Annulé" , resEncours) ;

        return resMap ;



    }

    public static void AnnulerRes(int pos){
        resData.get(pos).AnnulerReservation();
    }

    public static Boolean checkData(String email , String pass ){
        for (etudiants e : etudiantsData
             ) {
            if (e.isValidData(email , pass)) {
                Session.setLoggedEtud(e);
                return true ;
            }
        }

        return false ;
    }
}
