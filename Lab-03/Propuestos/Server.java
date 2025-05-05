import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {
    private static int uniqueId;
    private ArrayList<ClientThread> al;
    private SimpleDateFormat sdf;
    private int port;
    private boolean keepGoing;
    private String notif = " *** ";

    public Server(int port) {
        this.port = port;
        sdf = new SimpleDateFormat("HH:mm:ss");
        al = new ArrayList<ClientThread>();
    }

    public void start() {
    keepGoing = true;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (keepGoing) {
                display("Server waiting for Clients on port " + port + ".");
                Socket socket = serverSocket.accept();

                if (!keepGoing) break;

                ClientThread t = new ClientThread(socket);
                al.add(t);
                t.start();
            }

            try {
                serverSocket.close();
                for (int i = 0; i < al.size(); ++i) {
                    ClientThread tc = al.get(i);
                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    } catch (IOException ioE) {}
                }
            } catch (Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        } catch (IOException e) {
            String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
            display(msg);
        }
    }

    protected void stop() {
    keepGoing = false;
        try {
            new Socket("localhost", port);
        } catch (Exception e) {}
    }

    private synchronized boolean broadcast(String message) {
        String time = sdf.format(new Date());
        String[] w = message.split(" ", 3);
        boolean isPrivate = false;
        if (w[1].charAt(0) == '@') isPrivate = true;

        if (isPrivate) {
            String tocheck = w[1].substring(1);
            message = w[0] + w[2];
            String messageLf = time + " " + message + "\n";
            boolean found = false;

            for (int y = al.size(); --y >= 0;) {
                ClientThread ct1 = al.get(y);
                String check = ct1.getUsername();
                if (check.equals(tocheck)) {
                    if (!ct1.writeMsg(messageLf)) {
                        al.remove(y);
                        display("Disconnected Client " + ct1.username + " removed from list.");
                    }
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        } else {
            String messageLf = time + " " + message + "\n";
            System.out.print(messageLf);
            for (int i = al.size(); --i >= 0;) {
                ClientThread ct = al.get(i);
                if (!ct.writeMsg(messageLf)) {
                    al.remove(i);
                    display("Disconnected Client " + ct.username + " removed from list.");
                }
            }
        }
        return true;
    }



}
