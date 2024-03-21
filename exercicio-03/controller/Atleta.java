package controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Atleta extends Thread {    
    private final int id;    
    private int pontuacao;    
    Armas armas;    
    AtomicInteger posicao;

    public Atleta(int id, Armas armas, AtomicInteger posicao) {        
        this.id = id;        
        this.pontuacao = 0;        
        this.posicao = posicao;
        this.armas = armas;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();

            double velocidadeMinima = 20; 
            double velocidadeMaxima = 25; 
            double distanciaPercorrida = 0;

            double distanciaPercorridaRef = 0;

            while (distanciaPercorrida < 3000) {
                double distanciaCaminhada = velocidadeMinima + (velocidadeMaxima - velocidadeMinima) * random.nextDouble();
                distanciaPercorrida += distanciaCaminhada;
                
                distanciaPercorridaRef += distanciaCaminhada;
                if (distanciaPercorridaRef > 500) {
                    distanciaPercorridaRef = 0;
                    System.out.println("Atleta " + id + " caminhou " + (int)distanciaPercorrida + " metros.");
                }

                sleep(30); 
            }

            for (int i = 0; i < 3; i++) {
                int pontosTiro = random.nextInt(11); 
                pontuacao += pontosTiro; 
                           
                System.out.println("Atleta " + id + " fez " + pontosTiro + " pontos no tiro."); 

                armas.usar();  
            }            

            velocidadeMinima =30; 
            velocidadeMaxima = 40; 
            distanciaPercorrida = 0;
            distanciaPercorridaRef = 0;

            while (distanciaPercorrida < 5000) {
                double distanciaCaminhada = velocidadeMinima + (velocidadeMaxima - velocidadeMinima) * random.nextDouble();
                distanciaPercorrida += distanciaCaminhada;
                
                distanciaPercorridaRef += distanciaCaminhada;
                if (distanciaPercorridaRef > 500) {
                    distanciaPercorridaRef = 0;
                    System.out.println("Atleta " + id + " caminhou " + (int)distanciaPercorrida + " metros.");
                }

                sleep(40); 
            }

            int posicaoAtual = posicao.getAndIncrement();

            pontuacao += 250 - ((25 - posicaoAtual + 1) * 10);

            System.out.println("Atleta " + id + " chegou em " + posicaoAtual + " e fez " + pontuacao + " pontos!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sleep(int millis) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(millis);
    }

    public int getPontuacao() {        
        return pontuacao;    
    }

    @Override    
    public String toString() {        
        return "Atleta " + id + " - Pontuação: " + pontuacao;    
    }
}
