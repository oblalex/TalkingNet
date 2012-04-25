package talkingnet.net.udp;

import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.net.SocketException;
import talkingnet.core.Element;
import talkingnet.core.io.Pushable;
import talkingnet.net.udp.io.UdpPushable;
import talkingnet.net.udp.io.UdpPushing;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public class UdpDataWrapper extends Element implements Pushable, UdpPushing{

    private UdpPushable sink;
    private SocketAddress dstAddress;
    
    public UdpDataWrapper(
            SocketAddress dstAddress, UdpPushable sink, String title) {
        
        super(title);
        this.dstAddress  = dstAddress;
        this.sink = sink;
    }

    public void push_in(byte[] data, int size) {
        try {
            DatagramPacket packet = new DatagramPacket(data, size, dstAddress);
            push_out(packet);
        } catch (SocketException ex) {
            System.out.println(title+": "+ex);
        }
    }

    public void push_out(DatagramPacket packet) {
        sink.push_in(packet);
    }    
}
