import Multiplayer.TechnicalClient;
import jade.Window;

public class ClientBooter {
    public static void main(String[] args) throws Exception {

        String adress=args[0];
        new TechnicalClient(adress);

        Window window = Window.get();
        window.run();


    }
}