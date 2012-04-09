package talkingnet.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import talkingnet.core.Element;
import talkingnet.core.io.Pushable;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public class UdpSink extends Element implements Pushable {

    private DatagramSocket socket;
    
    public UdpSink(DatagramSocket socket, String title) {
        super(title);
    }

    /**
     * If socket is connected to some peer.
     */
    public void push_in(byte[] data, int size) {
        DatagramPacket packet = new DatagramPacket(data, size);
        push_in(packet);
    }
    
    /**
     * If socket is not connected to any peer.
     */
    public void push_in(DatagramPacket packet) {
        try {
            socket.send(packet);
        } catch (IOException ex) {
            System.out.println(title+": "+ex);
        }
    }
}
