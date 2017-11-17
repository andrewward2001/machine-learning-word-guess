public class Main {

    static int rip;

    public static void main(String[] args) {
        Population pop = new Population("Hello, world!", 6);
        while(!pop.generation()) {}
    }

}
