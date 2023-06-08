import java.net.*;
import java.rmi.server.SocketSecurityException;
import java.io.*;
import java.lang.Thread;

class Client{
    public static void main(String args[]) throws InterruptedException{

        String hostname = "localhost";
        int port = 8000;
 
        try (Socket socket = new Socket(hostname, port)) {

            byte array[] = {(byte)0x78, (byte)0x78, (byte)0x11, (byte)0x12, (byte)0x03, (byte)0x55, (byte)0x17, (byte)0x21, (byte)0x08, (byte)0x74, (byte)0x40, (byte)0x77, (byte)0x80, (byte)0x66, (byte)0x21, (byte)0x21, (byte)0x00, (byte)0x04, (byte)0x59, (byte)0xE3, (byte)0x0D, (byte)0x0A};

            OutputStream out = socket.getOutputStream();
            out.write(array);
            out.flush();
            System.out.println("Send login data"); 
            
            while(true){
                InputStream input = socket.getInputStream();
                while(input.available() > 0){
                    byte[] bytes = new byte[100];
                    int d = input.read(bytes);
                    System.out.println(d);
                     
                    out.write(array);
                    out.flush();
                    for(int i = 0; i < d; i++){
                        System.out.print(String.format("%02X ", bytes[i]));
                    }
                    
                }
                Thread.sleep(1000);
                out.write(array);
                out.flush();
                System.out.println("1");
            }

            


 
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}