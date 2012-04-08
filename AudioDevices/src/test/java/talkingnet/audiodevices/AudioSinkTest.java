package talkingnet.audiodevices;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Mixer;
import org.junit.Test;
import talkingnet.audiodevices.util.FooAudioFormat;
import talkingnet.audiodevices.util.FooMixerHolder;
import talkingnet.core.io.Pullable;
import talkingnet.core.io.channel.PullChannel;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public class AudioSinkTest {
    
    private Pullable source;
    private PullChannel channel;
    private AudioSink sink;
    
    private Mixer mixer;
    private AudioFormat format;
    
    private int bufferLength = 1000;
    
    public AudioSinkTest() {        
        source = new SineGenerator();        
        mixer = FooMixerHolder.getMixer();
        format = new FooAudioFormat();
        channel = new PullChannel(source);        
        sink = new AudioSink(mixer, format, bufferLength, channel, "audioSink");        
    }

    @Test
    public void testAudioSink() throws Exception {
        sink.open();        
        sink.start();
        
        Thread.sleep(3000l);
        
        sink.close(); 
    }

    private class SineGenerator implements Pullable {

        byte[] buffer;
        float scale = 0.125f*2;
        int range = (Byte.MAX_VALUE+1)*(Byte.MAX_VALUE+1) - 1;

        public SineGenerator() {
            buffer = new byte[bufferLength];
            fillSine();
        }
        
        private void fillSine(){
            int sampleNumber = 0;
            for (int i = 0; i < buffer.length; i += 2) {
                int sample = (int) (Math.sin(sampleNumber*scale) * range);
                buffer[i+1]   = (byte) (sample & 0xFF);
                buffer[i] = (byte) ((sample >> Byte.SIZE) & 0xFF);
                sampleNumber++;
            }
        }
        
        public void pull_out(byte[] data, int size) {
            System.arraycopy(buffer, 0, data, 0, size);
        }
    }
}
