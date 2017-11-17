import java.util.ArrayList;
import java.util.Comparator;

public class Population {

    public String goal;
    public int size, generationNumber;
    public ArrayList<Gene> members;

    /**
     * Creates a new Population with a goal and a size.
     * @param goal the target String to teach to the computer
     * @param size the size of the Gene pool that will be kept alive and allowed to mutate
     */
    public Population(String goal, int size) {
        this.goal = goal;
        this.size = size;
        this.members = new ArrayList<>();
        this.generationNumber = 0;

        for(int i = size; i >= 0; i--) {
            Gene gene = new Gene();
            gene.random(this.goal.length());
            this.members.add(gene);
        }
    }

    /**
     * Prints information about the given gene: "Generation ${generationNumber}. '${gene.code}' (cost: ${gene.cost})
     * @param gene the Gene to be printed
     * @see Gene
     */
    public void display(Gene gene) {
        System.out.println("Generation " + generationNumber + ". \"" + gene.code + "\" (cost: " + gene.cost + ")");
    }

    /**
     * Sorts ArrayList members by cost
     */
    public void sort() {
        this.members.sort((a, b) -> a.cost - b.cost);
    }

    /**
     * Performs one genetic generation
     * @return true if a gene matches the goal; else false
     */
    public boolean generation() {
        for(int i = 0; i < members.size(); i++) {
            this.members.get(i).calcCost(this.goal);
        }

        sort();
        display(members.get(0));
        Gene[] children = this.members.get(0).mate(this.members.get(1));
        members.set(members.size() -2, children[0]);
        members.set(members.size() -1, children[1]);

        for(int i = 0; i < members.size(); i++) {
            members.get(i).mutate(0.5);
            members.get(i).calcCost(this.goal);
            if(members.get(i).code.equals(this.goal)) {
                sort();
                display(members.get(i));
                return true;
            }
        }


        this.generationNumber++;
        return false;
    }

}
