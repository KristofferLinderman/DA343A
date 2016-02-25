package laboration11;
import java.net.*;
import java.util.*;
import l8games.TextWindow;

public class UDPServer implements Runnable {
    private ArrayList<String> words = new ArrayList<String>();
    private Thread server = new Thread(this);
    private DatagramSocket socket;
    
    public UDPServer(int requestPort) {
        TextWindow.println("Server: Start av UDPServer, Lyssnar på "+requestPort);
        try {
            socket = new DatagramSocket(requestPort);
            server.start();
        } catch(Exception e) { System.out.println(e);}
    }
    
    public void run() {
        String message,response;
        DatagramPacket packet;
        byte[] readBuffer = new byte[256];
        byte[] outData;
        try {
            while(true) {
                packet = new DatagramPacket(readBuffer,readBuffer.length);
                
                socket.receive(packet);
                message = new String(packet.getData(),0,packet.getLength());
                if(message.equals("GET")) {
                	TextWindow.println("Server: GET from "+packet.getAddress().getHostAddress()+":"+packet.getPort());
                    response="";
                    for(int i=0; i<words.size(); i++) {
                        response += (String)words.get(i) + "\n";
                    }
                    if(response.length()>1024)
                        response = response.substring(0,1024);
                    outData = response.getBytes();
                    packet = new DatagramPacket(outData,outData.length,packet.getAddress(),packet.getPort());
                    socket.send(packet);
                } else if(message.equals("CLR")) {
                	TextWindow.println("Server: CLR from "+packet.getAddress().getHostAddress());
                    words.clear();
                } else if(message.startsWith("PUT")) {
                	TextWindow.println("Server: PUT from "+packet.getAddress().getHostAddress());
                    words.add(message.substring(3,message.length()));
                } else
                    TextWindow.println("Server: Okänd förfrågan: "+message);
            }
        } catch(Exception e) { 
        	System.err.println(e);
        }
    }
}
