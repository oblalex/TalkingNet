package talkingnet.audiodevices;

import org.junit.Test;
import talkingnet.audiodevices.defaults.DefaultAudioSink;
import talkingnet.audiodevices.defaults.DefaultAudioSource;
import talkingnet.codecs.ulaw.ULawCompressor;
import talkingnet.codecs.ulaw.ULawDecompressor;
import talkingnet.core.Pool;
import talkingnet.utils.audio.DefaultAudioFormat;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public class ULawDoubleCompressedEchoTest {

    private int bufferLengthInMillis = 20;
    private int bufferLength;
    
    private AudioSource source;
    private ULawCompressor compresssor1;
    private ULawDecompressor decompresssor1;
    private ULawCompressor compresssor2;
    private ULawDecompressor decompresssor2;
    private Pool pool;    
    private AudioSink sink;

    public ULawDoubleCompressedEchoTest() {

        bufferLength = DefaultAudioFormat.SAMPLING_RATE * DefaultAudioFormat.FRAME_SIZE;
        bufferLength /= (1000 / bufferLengthInMillis);

        pool = new Pool(5, "pool");

        decompresssor2 = new ULawDecompressor(pool, "decompressor2");
        compresssor2 = new ULawCompressor(decompresssor2, "compressor2");
        
        decompresssor1 = new ULawDecompressor(compresssor2, "decompressor1");
        compresssor1 = new ULawCompressor(decompresssor1, "compressor1");

        source = new DefaultAudioSource(bufferLength, compresssor1, "src");
        sink = new DefaultAudioSink(bufferLength, pool, "sink");
    }

    @Test
    public void testEcho() throws Exception {
        sink.open();
        source.open();
        sink.start();
        source.start();

        Thread.sleep(25000l);

        source.close();
        sink.close();
    }
}
