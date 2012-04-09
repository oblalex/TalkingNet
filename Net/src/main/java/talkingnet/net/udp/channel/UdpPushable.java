package talkingnet.net.udp.channel;

import java.net.DatagramPacket;
import talkingnet.core.io.Pushable;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public interface UdpPushable extends Pushable {

    void push_in(DatagramPacket packet);
}
