package com.kwk.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.Charset;

public class BufTest {

    @Test
    public void basicTest() {
        ByteBuf byteBuf = Unpooled.buffer(16);
        byteBuf.maxCapacity();
        byteBuf.writeLong(1);
        System.out.println(byteBuf.toString());
        byteBuf.writeLong(2);
        byteBuf.writeLong(3);
        System.out.println(byteBuf.toString());

        long l1 = byteBuf.readLong();
        System.out.println(byteBuf.toString());
    }

    @Test
    public void test1() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 14);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) == sliced.getByte(0);
    }

    @Test
    public void test2() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.copy(0, 14);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) != sliced.getByte(0);
    }

    @Test
    public void test3() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char) buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, (byte) 'B');
        System.out.println((char) buf.getByte(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }
}
