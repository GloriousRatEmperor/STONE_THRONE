<<<<<<< HEAD
import Multiplayer.Client;
import Multiplayer.ClientData;
import Multiplayer.ServerData;
=======
>>>>>>> 244325ca06514a2b144c81d9ea80a7fe7b3a59ac
import Multiplayer.TechnicalClient;
import jade.Window;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ClientBooter {

    public static void main(String[] args) throws Exception {
        BlockingQueue<ClientData> requests=new ArrayBlockingQueue<>(15);
        BlockingQueue<ServerData> responses=new ArrayBlockingQueue<>(150);
        Thread.UncaughtExceptionHandler h = (th, ex) -> System.out.println("Uncaught exception: " + ex);
        String adress=args[0];
<<<<<<< HEAD

        Thread clientThread= new Thread(new TechnicalClient(adress,requests,responses));
        clientThread.setUncaughtExceptionHandler(h);
        clientThread.start();
=======
        new TechnicalClient(adress);
>>>>>>> 244325ca06514a2b144c81d9ea80a7fe7b3a59ac

        Window window = Window.get();
        window.clientThread=clientThread;
        window.requests=requests;
        window.responses=responses;
        window.run();




    }
}