package org.bulatnig.smpp.domain.tlv;

import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.assertEquals;

import org.bulatnig.smpp.util.SmppByteBuffer;
import org.junit.Test;
import org.bulatnig.smpp.pdu.tlv.ParameterTag;
import org.bulatnig.smpp.pdu.tlv.QosTimeToLive;
import org.bulatnig.smpp.pdu.tlv.TLVException;
import org.bulatnig.smpp.pdu.tlv.TLVNotFoundException;
import org.bulatnig.smpp.util.WrongParameterException;

public class QosTimeToLiveTest {

	// Used for backward compatibility (IDEs, Ant and JUnit 3 text runner)
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(QosTimeToLiveTest.class);
	}
	
	@Test
	public void testQTTLConstructor1() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0017);
		bb.appendShort(0x0004);
		bb.appendInt(0x1111);
		QosTimeToLive qttl = new QosTimeToLive(bb.array());
		assertEquals(ParameterTag.QOS_TIME_TO_LIVE, qttl.getTag());
		assertEquals(8, qttl.getBytes().length);
		assertEquals(4369L, qttl.getValue());
		assertEquals("0017000400001111", new SmppByteBuffer(qttl.getBytes()).getHexDump());
	}

    @Test(expected = TLVNotFoundException.class)
	public void testQTTLConstructor2() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0000);
		bb.appendShort(0x0004);
		bb.appendInt(0x00001111);
		new QosTimeToLive(bb.array());
	}
	
	@Test(expected= TLVException.class)
	public void testQTTLConstructor3() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0017);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x11);
		new QosTimeToLive(bb.array());
	}
	
	@Test
	public void testQTTLConstructor4() throws WrongParameterException, TLVException {
		QosTimeToLive qttl = new QosTimeToLive(112);
		assertEquals(ParameterTag.QOS_TIME_TO_LIVE, qttl.getTag());
		assertEquals(8, qttl.getBytes().length);
		assertEquals(112L, qttl.getValue());
		assertEquals("0017000400000070", new SmppByteBuffer(qttl.getBytes()).getHexDump());
	}
	
	@Test(expected= TLVException.class)
	public void testQTTLConstructor5() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0008);
		bb.appendShort(0x0004);
		bb.appendByte((byte)0x11);
		new QosTimeToLive(bb.array());
	}
	
	@Test(expected=ClassCastException.class)
	public void testQTTLConstructor6() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0005);
		bb.appendShort(0x0004);
		bb.appendInt(0x00007fff);
		new QosTimeToLive(bb.array());
	}

    @Test(expected = TLVException.class)
    public void testQTTLConstructor7() throws TLVException {
        new QosTimeToLive(90000000000000L).getBytes();
    }
	
}
