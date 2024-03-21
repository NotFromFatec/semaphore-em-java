package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Armas {
    private final Semaphore semaforo;
    
    public Armas() {
        this.semaforo = new Semaphore(5); 
    }
    public void usar() throws InterruptedException {
        semaforo.acquire();

        Thread.sleep(gerarTempo(300, 3000)); 

        semaforo.release();    
    }
    private int gerarTempo(int minMillis, int maxMillis) {
        return minMillis + new Random().nextInt(maxMillis - minMillis + 1);    
    }
}
        
