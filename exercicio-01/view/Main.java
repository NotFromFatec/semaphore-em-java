package view;

import controller.Cavaleiro;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        AtomicBoolean tocha = new AtomicBoolean(true);
        AtomicBoolean pedra = new AtomicBoolean(true);
        AtomicInteger porta = new AtomicInteger(1);

        int portaDaVitoria = random.nextInt(4) + 1;

        for (int i = 1; i < 5; i++) {
            Cavaleiro cavaleiro = new Cavaleiro(i, tocha, pedra, porta, portaDaVitoria);
            cavaleiro.start();
        }
    }
}
