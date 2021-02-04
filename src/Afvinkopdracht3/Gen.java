package Afvinkopdracht3;

public class Gen {
    private int id;
    private String name;
    private String alternative;
    private char chromosome;
    private String location;
    private String function;
    private String type;

    public Gen(int id, String name, String alternative, char chromosome, String location, String function, String type) {
        this.id=id;
        this.name=name;
        this.alternative=alternative;
        this.chromosome=chromosome;
        this.location=location;
        this.function=function;
        this.type=type;
    }
}
