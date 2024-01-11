package com.app.inptreservationux;

import java.util.TimerTask;

public class ResTimer extends TimerTask {

    Reservation res ;

    public  ResTimer(Reservation r ){
        res = r ;
    }
    @Override
    public void run() {

        if(res.getEtat().equals("En cours")){
            res.ResTermine();
        }

    }
}
