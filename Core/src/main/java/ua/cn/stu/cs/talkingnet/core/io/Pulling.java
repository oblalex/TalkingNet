package ua.cn.stu.cs.talkingnet.core.io;

/**
 *
 * @author Alexander Oblovatniy <oblovatniy@gmail.com>
 */
public interface Pulling {

    int pull_in(byte[] data, int size);
    int pull_in(byte[] data, int offset, int size);
}
