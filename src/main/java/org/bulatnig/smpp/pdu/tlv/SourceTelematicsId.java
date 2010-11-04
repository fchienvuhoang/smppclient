package org.bulatnig.smpp.pdu.tlv;

import org.bulatnig.smpp.pdu.EsmClass;
import org.bulatnig.smpp.util.SmppByteBuffer;

/**
 * The source_telematics_id parameter indicates the type of telematics interface
 * over which the message originated.
 * 
 * @author Bulat Nigmatullin
 * 
 */
public class SourceTelematicsId extends TLV {
	/**
	 * Длина значения параметра.
	 */
	private static final int LENGTH = 1;
	/**
	 * Значение параметра.
	 */
	private int value;

	/**
	 * Constructor.
	 * 
	 * @param telematicsId
	 *            значение параметра
	 */
	public SourceTelematicsId(final short telematicsId) {
		super(ParameterTag.SOURCE_TELEMATICS_ID);
		value = telematicsId;
	}

	/**
	 * Constructor.
	 * 
	 * @param bytes
	 *            bytecode of TLV
     * @throws TLVException ошибка разбора TLV
	 */
	public SourceTelematicsId(final byte[] bytes) throws TLVException {
		super(bytes);
	}

    @Override
    protected void parseValue(byte[] bytes, final EsmClass esmClass, final int dataCoding) throws TLVException {
		if (getTag() != ParameterTag.SOURCE_TELEMATICS_ID) {
			throw new ClassCastException();
		}
		if (bytes.length == LENGTH) {
                value = new SmppByteBuffer(bytes).removeByte();
		} else {
            throw new TLVException("Value has wrong length: " + bytes.length + " but expected " + LENGTH);
        }
    }

    @Override
    protected byte[] getValueBytes(final EsmClass esmClass, final short dataCoding) throws TLVException {
        return new SmppByteBuffer().appendByte(value).array();
    }

    /**
	 * @return значение параметра
	 */
	public final int getValue() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return getClass().getName() + " Object {" + "\nvalue : " + value
				+ "\n}";
	}

}
