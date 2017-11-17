
public class Gene {

    public String code;
    public int cost;

    public Gene() {
        this.cost = 9999;
        this.code = "";
    }

    public Gene(String code) {
        this.code = code.length() > 0 ? code : "";
        this.cost = 9999;
    }

    public void random(int length) {
        for(int i = length; i > 0; i--) {
            this.code += (char) (int)((Math.random() * 95)+32);
        }
    }

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

    public Gene[] mate(Gene gene) {
        int pivot = (this.code.length() / 2) -1;

        String child1 = this.code.substring(0, pivot) + gene.code.substring(pivot);
        String child2 = gene.code.substring(0, pivot) + this.code.substring(pivot);

        return new Gene[]{ new Gene(child1), new Gene(child2) };
    }

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
