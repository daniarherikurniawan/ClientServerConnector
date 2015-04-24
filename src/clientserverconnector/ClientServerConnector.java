/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientserverconnector;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author daniar heri
 */
public class ClientServerConnector {

    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os ;
    private PrintWriter pw ;
    private BufferedReader in;
    private String dataStr;
    public ClientServerConnector(String prm_socket, int prm_port){
        try {
            socket = new Socket(InetAddress.getByName(prm_socket), prm_port);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            pw = new PrintWriter(os);
            dataStr = new String();
            in = new BufferedReader(new InputStreamReader(is));
            System.out.print("Client and Server Connected\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void send(){
        pw.flush();
    }
    
    public void setString(String str){
        dataStr = str;
        pw.println(dataStr);
    }
    
    public void printMsg() throws IOException{
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);

    }
    
    public void close() throws IOException{
        
        is.close();
        os.close();
    }
    
    public static void main(String[] args) throws IOException {
        ClientServerConnector CSConnector = new ClientServerConnector("127.0.0.1",8000);
        CSConnector.setString("{\"id\":1,\"method\":\"object.deleteAll\",\"params\":[\"subscriber\"]}\n");
        CSConnector.send();
        CSConnector.printMsg();
        CSConnector.close();
        System.out.print("Success1");
        
    }
    
}
