import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class serverForMultiClientTCP extends ServerSocket {
    private static serverGameControl serverGameControl;

    public serverForMultiClientTCP(int SERVER_PORT)throws IOException {
        super(SERVER_PORT);
        serverGameControl = new serverGameControl();
    }

    public void runThread()throws IOException {
        try {
            while (true) {
//                System.out.println("waiting");
                Socket socket = accept();
                new CreateServerThread(socket);
                Thread.sleep(1);
            }
        }catch (IOException e) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    static class CreateServerThread extends Thread {
        private Socket client;
        static Data dataclass;
        // private BufferedReader bufferedReader;
        // private PrintWriter printWriter;

        public CreateServerThread(Socket s)throws IOException {
            if(dataclass == null){
                dataclass = new Data();
            }
            client = s;
            start();
        }

        public void run() {
            try {
                // System.out.println("Remote IP:" + client.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(client.getInputStream());

                String inputString = in.readUTF();
                System.out.println(inputString);

                List<String> myList = new ArrayList<>(Arrays.asList(inputString.split(",")));
                String pureIP = myList.get(0).split(":")[0];
                switch (String.valueOf(myList.get(1))){
                    case "move":
                        switch (myList.get(2)){
                            case "0":
                                dataclass.Lmove(pureIP);
                                break;
                            case "1":
                                dataclass.Rmove(pureIP);
                                break;
                            case "2":
                                dataclass.Umove(pureIP);
                                break;
                            case "3":
                                dataclass.Dmove(pureIP);
                                break;
                        }
                        break;
                    case "init" :
                        serverGameControl.addPlayer(pureIP);
                }

                System.out.println(dataclass.getUpdateList().get(pureIP)[0] + ", " + dataclass.getUpdateList().get(pureIP)[1]);

                client.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startTCP()throws IOException {
//        serverForMultiClientTCP a = new serverForMultiClientTCP(66);
        runThread();
    }
}

