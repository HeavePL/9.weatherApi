package com.example.pogodaapi;

public class WindDirection {
    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    private String kierunek;

    public WindDirection(int deg) {
        int kier = (int) Math.ceil((double) deg / 45);
        // 0 45 90 135 180 225 270 315 360
        // 0  1  2   3   4   5   6   7   8
        // N NE E  SE  S   SW  W   NW   N
        if (kier==0 || kier==8){
             setKierunek("N");
        } else if (kier==1) {
            setKierunek("NE");
        } else if (kier==2) {
            setKierunek("E");
        } else if (kier==3) {
            setKierunek("SE");
        } else if (kier==4) {
            setKierunek("S");
        } else if (kier==5) {
            setKierunek("SW");
        } else if (kier==6) {
            setKierunek("W");
        } else if (kier==7) {
            setKierunek("NW");
        }


    }
}
