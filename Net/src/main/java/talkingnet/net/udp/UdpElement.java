package talkingnet.net.udp;

import java.net.DatagramSocket;
import talkingnet.core.Element;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public abstract class UdpElement extends Element {
    protected DatagramSocket socket;

    public UdpElement(DatagramSocket socket, String title) {
        super(title);
        this.socket = socket;
    }
    
}
