
public class Gene {

    public String code;
    public int cost;

    /**
     * Create Gene class with default values:
     * cost = 9999
     * code = ""
     */
    public Gene() {
        this.cost = 9999;
        this.code = "";
    }

    /**
     * Create Gene class with default cost = 9999, but specified code.
     * @param code the String of the Gene
     */
    public Gene(String code) {
        this.code = code.length() > 0 ? code : "";
        this.cost = 9999;
    }

    /**
     * Creates a new random code with length.
     * @param length specifies the length of the random code to be generated
     */
    public void random(int length) {
        for(int i = length; i > 0; i--) {
            this.code += (char) (int)((Math.random() * 95)+32);
        }
    }

    /**
     * Mutate the gene with a given chance. The mutation will only occur if a randomly selected double is higher than the given chance.
     * @param chance the chance threshold that a mutation will occur
     */
    public void mutate(double chance) {
        if(Math.random() > chance) return;

        int index = (int) Math.floor(Math.random() * this.code.length());
        int upOrDown = Math.random() <= 0.5 ? -1 : 1;
        char newChar = (char) (this.code.charAt(index) + upOrDown);
        String newString = "";
        for(int i = 0; i < this.code.length(); i++) {
            newString += i == index ? newChar : this.code.charAt(i);
        }

        this.code = newString;
    }

    /**
     * Mates a given Gene gene with the Gene held in this class. Splices the two of them together at the middle (AA + BB = AB + BA).
     * @param  gene the Gene to mate with
     * @return a two Gene array
     */
    public Gene[] mate(Gene gene) {
        int pivot = (this.code.length() / 2) -1;

        String child1 = this.code.substring(0, pivot) + gene.code.substring(pivot);
        String child2 = gene.code.substring(0, pivot) + this.code.substring(pivot);

        return new Gene[]{ new Gene(child1), new Gene(child2) };
    }

    /**
     * Finds the cost of this Gene squaring the differences between the code and comp, on a character-level basis
     * @param comp the basis to calculate the cost from, usually the goal
     */
    public void calcCost(String comp) {
        int total = 0;
        for(int i = 0; i < this.code.length(); i++) {
            total += (this.code.charAt(i) - comp.charAt(i)) * (this.code.charAt(i) - comp.charAt(i));
        }

        this.cost = total;
    }

    public String toString(){
        return "" + code + " " + cost;
    }

}
