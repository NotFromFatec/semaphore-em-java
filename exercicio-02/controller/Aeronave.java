package controller;

import java.util.Random;

public class Aeronave extends Thread {    
    private final int id;    
    private final Pista pistaNorte;    
    private final Pista pistaSul;    

    public Aeronave(int id, Pista pistaNorte, Pista pistaSul) {
        this.id = id;
        this.pistaNorte = pistaNorte;
        this.pistaSul = pistaSul;
    }

    @Override    
    public void run() {
        try {
            System.out.println("Aeronave " + id + " está em manobra.");
            Thread.sleep(gerarTempo(300, 700)); // Tempo de manobra

            if (new Random().nextBoolean()) {
                pistaNorte.usarPista(this);
            } else {
                pistaSul.usarPista(this);
            }

            Thread.sleep(gerarTempo(300, 800)); // Tempo de afastamento da área
            System.out.println("Aeronave " + id + " afastou-se da área.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }    
    }

    @Override    
    public String toString() {
        return "Aeronave " + id;    
    }

    private int gerarTempo(int minMillis, int maxMillis) {
        return minMillis + new Random().nextInt(maxMillis - minMillis + 1);    
    }
}
