import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import controller.Armas;
import controller.Atleta;

public class Triatlo {
    public static void main(String[] args) {       
        try {
            int numAtletas = 25;        

            List<Atleta> atletas = new ArrayList<>();        
            AtomicInteger posicao = new AtomicInteger(1);
            Armas armas = new Armas();
            
            for (int i = 1; i <= numAtletas; i++) { 
                Atleta a = new Atleta(i, armas, posicao);          
                atletas.add(a);        
            }

            for (Atleta atleta : atletas) {
                atleta.start();
            }

            for (Atleta atleta : atletas) {
                atleta.join();
            }

            atletas.sort(Comparator.comparingInt(Atleta::getPontuacao).reversed());

            System.out.println("Resultado final:");        
            for (Atleta atleta : atletas) {            
                System.out.println(atleta);        
            }    
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
