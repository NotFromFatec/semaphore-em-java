package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Pista {
    private final String nome;    
    private final Semaphore semaforo;
    
    public Pista(String nome) {
        this.nome = nome;
        this.semaforo = new Semaphore(1); 
    }
    public void usarPista(Aeronave aeronave) throws InterruptedException {
        semaforo.acquire();
        System.out.println(aeronave + " está usando a pista " + nome);
        Thread.sleep(gerarTempo(600, 800)); // Tempo de decolagem
        System.out.println(aeronave + " decolou da pista " + nome);
        semaforo.release();    
    }
    private int gerarTempo(int minMillis, int maxMillis) {
        return minMillis + new Random().nextInt(maxMillis - minMillis + 1);    
    }
}
        