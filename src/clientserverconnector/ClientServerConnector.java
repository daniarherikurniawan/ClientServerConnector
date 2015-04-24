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

 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    private JSONObject jsonMsg ;
    private JSONObject jsonResponse ;
    
    public ClientServerConnector(String prm_socket, int prm_port){
        try {
            jsonMsg = new JSONObject();
            socket = new Socket(InetAddress.getByName(prm_socket), prm_port);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            pw = new PrintWriter(os);
            dataStr = new String();
            in = new BufferedReader(new InputStreamReader(is));
            System.out.print("Client and Server Connected\n");
        } catch (IOException e) {
            System.out.print("Client and Server Failed to Connect\n");
            e.printStackTrace();
        }
    }
    
    private String getResponse() throws IOException{
        //send packet JSON
        pw.println(jsonMsg.toJSONString());
        pw.flush();

        //get response
        String inputLine = new String();
        String response = new String();
        while ((inputLine = in.readLine()) != null){ 
            response += inputLine;
        }
        //close connection
        is.close();
        os.close();
        
        return response;
    }
    
    public String actionJoin(String IP, String Port) throws IOException{
        jsonMsg.put("ip", IP);
        jsonMsg.put("port", Port);
        return getResponse();
    }
    
    public String actionSignup(String username, String password) throws IOException{
        jsonMsg.put("method","signup");
        jsonMsg.put("username",username);
        jsonMsg.put("password", password);       
        return getResponse();
    }
    
    public String actionLogin(String username,  String password) throws IOException{
        jsonMsg.put("method","login");
        jsonMsg.put("username", username);
        jsonMsg.put("password", password);
        return getResponse();
    }
    
    public String actionInventory(String token) throws IOException{
        jsonMsg.put("method","inventory");
        jsonMsg.put("token", token);
        return getResponse();
    }
    
    public String actionMixItem(String token, String item1, String item2) throws IOException{
        jsonMsg.put("method","mixitem");
        jsonMsg.put("token", token);
        jsonMsg.put("item1", item1);
        jsonMsg.put("item2", item2);
        return getResponse();
    }
    
    public String actionMap(String token) throws IOException{
        jsonMsg.put("method","map");
        jsonMsg.put("token", token);
        return getResponse();
    }
    
    public String actionMove(String token, String x,  String y) throws IOException{
        jsonMsg.put("method","move");
        jsonMsg.put("token", token);
        jsonMsg.put("x", x);
        jsonMsg.put("y", y);
        return getResponse();
    }
    
    public String actionField(String token) throws IOException{
        jsonMsg.put("method","field");
        jsonMsg.put("token", token);
        return getResponse();
    }
    
    public String actionOffer(String token, String offered_item, String n1, String demanded_item, String n2) throws IOException{
        jsonMsg.put("method","offer");
        jsonMsg.put("token", token);
        jsonMsg.put("offered_item", offered_item);
        jsonMsg.put("n1", n1);        
        jsonMsg.put("demanded_item", demanded_item);
        jsonMsg.put("n2", n2);
        return getResponse();
    }
    
    public String actionTradebox(String token) throws IOException{
        jsonMsg.put("method","tradebox");
        jsonMsg.put("token", token);
        return getResponse();
    }
    
    public String actionSendFind(String token, String item) throws IOException{
        jsonMsg.put("method","sendfind");
        jsonMsg.put("token", token);
        jsonMsg.put("item", item);
        return getResponse();
    }
    
    public String actionFindOffer(String item) throws IOException{
        jsonMsg.put("method","findoffer");
        jsonMsg.put("item", item);
        return getResponse();
    }
    
    public String actionSendAccept(String token, String offer_token) throws IOException{
        jsonMsg.put("method","sendaccept");
        jsonMsg.put("token", token);
        jsonMsg.put("offer_token", offer_token);
        return getResponse();
    }
    
    public String actionAccept(String offer_token) throws IOException{
        jsonMsg.put("method","accept");
        jsonMsg.put("offer_token", offer_token);
        return getResponse();
    }
    
    public String actionFetchItem(String token, String offer_token) throws IOException{
        jsonMsg.put("method","fetchitem");
        jsonMsg.put("token", token);
        jsonMsg.put("offer_token", offer_token);
        return getResponse();
    }
    
    public String actionCancelOffer(String token, String offer_token) throws IOException{
        jsonMsg.put("method","canceloffer");
        jsonMsg.put("token", token);
        jsonMsg.put("offer_token", offer_token);
        return getResponse();
    }
    
    public static void main(String[] args) throws IOException {
        ClientServerConnector CSConnector = new ClientServerConnector("127.0.0.1",8000);
        
        System.out.println("Response = "+CSConnector.actionCancelOffer("0495835803958035324242","R12"));
                
    }
    
}
