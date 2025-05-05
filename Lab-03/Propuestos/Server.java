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
}
