package talkingnet.core.adder;

import java.io.IOException;
import java.util.Collection;
import talkingnet.core.Element;
import talkingnet.core.io.Multipushable;
import talkingnet.core.io.Pushing;
import talkingnet.core.io.channel.PushChannel;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public class SimpleAdder extends Element implements Multipushable, Pushing {

    private PushChannel channel_out;
    byte[] buffer;
    private int bufferSize;

    public SimpleAdder(int bufferSize, PushChannel channel_out, String title) {
        super(title);
        this.bufferSize = bufferSize;
        this.channel_out = channel_out;
    }

    public void multipush_in(Collection<byte[]> data) {
        buffer = new byte[bufferSize];
        multiadd(data);
        push_out(buffer, buffer.length);
    }

    private void multiadd(Collection<byte[]> dataList) {
        for (byte[] data : dataList) {
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] += data[i];
            }
        }
    }

    public void push_out(byte[] data, int size) {
        try {
            channel_out.write(data, data.length);
        } catch (IOException ex) {
            System.out.println(title + ": " + ex);
        }
    }
}