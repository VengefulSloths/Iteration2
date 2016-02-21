/**
 * Created by alexs on 2/20/2016.
 */
public class driver {
    public static void main(String ... args) {
        System.out.println("Starting driver");
        AreaView av = new AreaView();
        ViewEngine viewEngine = new ViewEngine(av);
        viewEngine.start();
        System.out.print("Finished with driver");
    }
}
