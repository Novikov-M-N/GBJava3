
public class BreakTime {
    static String i = "";
    static String template = " жду приказаний";
    static String outPut = null;

    public static void main(String[] args) {
        i.concat("Я, джун, ").concat(template);
        i.concat("Я, мидл, ").concat(template);
        outPut=i;
        outPut = outPut.concat(i).concat("Я, сеньер, ").concat(template);
        System.out.println(i + outPut);
    }
}
