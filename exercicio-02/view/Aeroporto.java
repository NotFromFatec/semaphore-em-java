// package view;

import controller.Pista;
import controller.Aeronave;

public class Aeroporto {
    public static void main(String[] args) {
        int numAeronaves = 12;

        Pista pistaNorte = new Pista("Norte");
        Pista pistaSul = new Pista("Sul");

        for (int i = 1; i <= numAeronaves; i++) {
            Aeronave aeronave = new Aeronave(i, pistaNorte, pistaSul);
            aeronave.start();
        }    
    }
}
