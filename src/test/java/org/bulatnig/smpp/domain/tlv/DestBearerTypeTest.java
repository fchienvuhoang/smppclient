package org.bulatnig.smpp.domain.tlv;

import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.assertEquals;

import org.bulatnig.smpp.util.SmppByteBuffer;
import org.junit.Test;
import org.bulatnig.smpp.pdu.tlv.*;
import org.bulatnig.smpp.util.WrongParameterException;

public class DestBearerTypeTest {

	// Used for backward compatibility (IDEs, Ant and JUnit 3 text runner)
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DestBearerTypeTest.class);
	}
	
	@Test
	public void testDBTConstructor1() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0007);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x05);
		DestBearerType dbt = new DestBearerType(bb.array());
		assertEquals(ParameterTag.DEST_BEARER_TYPE, dbt.getTag());
		assertEquals(5, dbt.getBytes().length);
		assertEquals(BearerType.CDPD, dbt.getValue());
		assertEquals("0007000105", new SmppByteBuffer(dbt.getBytes()).getHexDump());
	}

    @Test(expected = TLVNotFoundException.class)
	public void testDBTConstructor2() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0000);
		bb.appendShort(0x0002);
		bb.appendByte((byte)0x05);
		new DestBearerType(bb.array());
	}
	
	@Test(expected= TLVException.class)
	public void testDBTConstructor3() throws WrongParameterException, TLVException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0007);
		bb.appendShort(0x0001);
		bb.appendShort(0x0003);
		new DestBearerType(bb.array());
	}
	
	@Test
	public void testDBTConstructor4() throws TLVException, WrongParameterException {
		DestBearerType dnt = new DestBearerType(BearerType.CDPD);
		assertEquals(ParameterTag.DEST_BEARER_TYPE, dnt.getTag());
		assertEquals(5, dnt.getBytes().length);
		assertEquals(BearerType.CDPD, dnt.getValue());
		assertEquals("0007000105", new SmppByteBuffer(dnt.getBytes()).getHexDump());
	}
	
	@Test(expected= TLVException.class)
	public void testDBTConstructor5() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0007);
		bb.appendShort(0x0001);
		bb.appendShort(0x0014);
		new DestBearerType(bb.array());
	}
	
	@Test(expected=ClassCastException.class)
	public void testDBTConstructor6() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0005);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x00);
		new DestBearerType(bb.array());
	}
	
	@Test
	public void testDBTConstructor7() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0007);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x14);
		DestBearerType dbt = new DestBearerType(bb.array());
		assertEquals(ParameterTag.DEST_BEARER_TYPE, dbt.getTag());
		assertEquals(5, dbt.getBytes().length);
		assertEquals(BearerType.RESERVED, dbt.getValue());
		assertEquals((short) 20, dbt.getIntValue());
		assertEquals("0007000114", new SmppByteBuffer(dbt.getBytes()).getHexDump());
	}
	
}
