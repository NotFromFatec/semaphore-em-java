package controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Cavaleiro extends Thread {
    private static final int DISTANCIA_TOTAL = 2000; 
    private static final int TOCHA_POSICAO = 500;
    private static final int PEDRA_POSICAO = 1500;

    private final int cavaleiroId;

    AtomicBoolean tocha;
    AtomicBoolean pedra;
    AtomicInteger porta;
    int portaDaVitoria;

    public Cavaleiro(int cavaleiroId, AtomicBoolean tocha, AtomicBoolean pedra, AtomicInteger porta, int portaDaVitoria) {
        this.cavaleiroId = cavaleiroId;
        this.tocha = tocha;
        this.pedra = pedra;
        this.porta = porta;
        this.portaDaVitoria = portaDaVitoria;
    }
    
    @Override
    public void run() {
        try {
            Random random = new Random();

            double velocidadeMinima = 2; 
            double velocidadeMaxima = 4; 
            double distanciaPercorrida = 0;

            double distanciaPercorridaRef = 0;

            while (distanciaPercorrida < DISTANCIA_TOTAL) {
                double distanciaCaminhada = velocidadeMinima + (velocidadeMaxima - velocidadeMinima) * random.nextDouble();
                distanciaPercorrida += distanciaCaminhada;
                
                distanciaPercorridaRef += distanciaCaminhada;
                if (distanciaPercorridaRef > 100) {
                    distanciaPercorridaRef = 0;
                    System.out.println("Cavaleiro " + cavaleiroId + " caminhou " + (int)distanciaPercorrida + " metros.");
                }

                if (distanciaPercorrida >= TOCHA_POSICAO && tocha.get() == true) {
                    tocha.set(false);
                    System.out.println("Cavaleiro " + cavaleiroId + " pegou a tocha!");
                    velocidadeMaxima += 2.0;
                }

                if (distanciaPercorrida >= PEDRA_POSICAO && pedra.get() == true) {
                    pedra.set(false);
                    System.out.println("Cavaleiro " + cavaleiroId + " pegou a pedra!");
                    velocidadeMaxima += 2.0;
                }

                sleep(50); 
            }

            int portaEscolhida = porta.getAndIncrement();
            System.out.println("Cavaleiro " + cavaleiroId + " escolheu a porta " + portaEscolhida);

            double distanciaDoNovoCorredor = 500 + (1000 - 500) * random.nextDouble();
            distanciaPercorrida = 0;

            distanciaPercorridaRef = 0;

            while (distanciaPercorrida < distanciaDoNovoCorredor) {
                double distanciaCaminhada = velocidadeMinima + (velocidadeMaxima - velocidadeMinima) * random.nextDouble();
                distanciaPercorrida += distanciaCaminhada;
                
                distanciaPercorridaRef += distanciaCaminhada;
                if (distanciaPercorridaRef > 100) {
                    distanciaPercorridaRef = 0;
                    System.out.println("Cavaleiro " + cavaleiroId + " caminhou " + (int)distanciaPercorrida + " metros.");
                }

                sleep(50); 
            }

            if (portaEscolhida == portaDaVitoria) {
                System.out.println("Cavaleiro " + cavaleiroId + " ganhou !!!"); 
            } else {
                System.out.println("Cavaleiro " + cavaleiroId + " se ferrou !!!"); 
            }
        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    private void sleep(int millis) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(millis);
    }
}

