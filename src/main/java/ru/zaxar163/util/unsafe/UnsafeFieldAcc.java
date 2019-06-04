package ru.zaxar163.util.unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ru.zaxar163.util.proxies.ProxyList;

public final class UnsafeFieldAcc {
	private final Object base;
	private final long offset;

	public UnsafeFieldAcc(final Field f) {
		if ((f.getModifiers() & Modifier.STATIC) == 0) {
			offset = ProxyList.UNSAFE.objectFieldOffset(f);
			base = null;
		} else {
			offset = ProxyList.UNSAFE.staticFieldOffset(f);
			base = ProxyList.UNSAFE.staticFieldBase(f);
		}
	}

	public byte getAndSetByte(final Object inst, final byte to) {
		if (base == null) {
			final byte ret = ProxyList.UNSAFE.getByteVolatile(inst, offset);
			ProxyList.UNSAFE.putByteVolatile(inst, offset, to);
			return ret;
		} else {
			final byte ret = ProxyList.UNSAFE.getByte(base, offset);
			ProxyList.UNSAFE.putByteVolatile(base, offset, to);
			return ret;
		}
	}

	public double getAndSetDouble(final Object inst, final double to) {
		return Double.longBitsToDouble(getAndSetLong(inst, Double.doubleToLongBits(to)));
	}

	public float getAndSetFloat(final Object inst, final float to) {
		return Float.intBitsToFloat(getAndSetInt(inst, Float.floatToIntBits(to)));
	}

	public int getAndSetInt(final Object inst, final int to) {
		return base == null ? ProxyList.UNSAFE.getAndSetInt(inst, offset, to)
				: ProxyList.UNSAFE.getAndSetInt(base, offset, to);
	}

	public long getAndSetLong(final Object inst, final long to) {
		return base == null ? ProxyList.UNSAFE.getAndSetLong(inst, offset, to)
				: ProxyList.UNSAFE.getAndSetLong(base, offset, to);
	}

	public Object getAndSetObject(final Object inst, final Object to) {
		return base == null ? ProxyList.UNSAFE.getAndSetObject(inst, offset, to)
				: ProxyList.UNSAFE.getAndSetObject(base, offset, to);
	}

	public short getAndSetShort(final Object inst, final short to) {
		if (base == null) {
			final short ret = ProxyList.UNSAFE.getShortVolatile(inst, offset);
			ProxyList.UNSAFE.putShortVolatile(inst, offset, to);
			return ret;
		} else {
			final short ret = ProxyList.UNSAFE.getShortVolatile(base, offset);
			ProxyList.UNSAFE.putShortVolatile(base, offset, to);
			return ret;
		}
	}

	public byte getByte(final Object inst) {
		return base == null ? ProxyList.UNSAFE.getByteVolatile(inst, offset)
				: ProxyList.UNSAFE.getByteVolatile(base, offset);
	}

	public double getDouble(final Object inst) {
		return base == null ? ProxyList.UNSAFE.getDoubleVolatile(inst, offset)
				: ProxyList.UNSAFE.getDoubleVolatile(base, offset);
	}

	public float getFloat(final Object inst) {
		return base == null ? ProxyList.UNSAFE.getFloatVolatile(inst, offset)
				: ProxyList.UNSAFE.getFloatVolatile(base, offset);
	}

	public int getInt(final Object inst) {
		return base == null ? ProxyList.UNSAFE.getIntVolatile(inst, offset)
				: ProxyList.UNSAFE.getIntVolatile(base, offset);
	}

	public long getLong(final Object inst) {
		return base == null ? ProxyList.UNSAFE.getLongVolatile(inst, offset)
				: ProxyList.UNSAFE.getLongVolatile(base, offset);
	}

	public Object getObject(final Object inst) {
		return base == null ? ProxyList.UNSAFE.getObjectVolatile(inst, offset)
				: ProxyList.UNSAFE.getObjectVolatile(base, offset);
	}

	public short getShort(final Object inst) {
		return base == null ? ProxyList.UNSAFE.getShortVolatile(inst, offset)
				: ProxyList.UNSAFE.getShortVolatile(base, offset);
	}

	public void setByte(final Object inst, final byte to) {
		if (base == null)
			ProxyList.UNSAFE.putByteVolatile(inst, offset, to);
		else
			ProxyList.UNSAFE.putByteVolatile(base, offset, to);
	}

	public void setDouble(final Object inst, final double to) {
		if (base == null)
			ProxyList.UNSAFE.putDoubleVolatile(inst, offset, to);
		else
			ProxyList.UNSAFE.putDoubleVolatile(base, offset, to);
	}

	public void setFloat(final Object inst, final float to) {
		if (base == null)
			ProxyList.UNSAFE.putFloatVolatile(inst, offset, to);
		else
			ProxyList.UNSAFE.putFloatVolatile(base, offset, to);
	}

	public void setInt(final Object inst, final int to) {
		if (base == null)
			ProxyList.UNSAFE.putIntVolatile(inst, offset, to);
		else
			ProxyList.UNSAFE.putIntVolatile(base, offset, to);
	}

	public void setLong(final Object inst, final long to) {
		if (base == null)
			ProxyList.UNSAFE.putLongVolatile(inst, offset, to);
		else
			ProxyList.UNSAFE.putLongVolatile(base, offset, to);
	}

	public void setObject(final Object inst, final Object to) {
		if (base == null)
			ProxyList.UNSAFE.putObjectVolatile(inst, offset, to);
		else
			ProxyList.UNSAFE.putObjectVolatile(base, offset, to);

	}

	public void setShort(final Object inst, final short to) {
		if (base == null)
			ProxyList.UNSAFE.putShortVolatile(inst, offset, to);
		else
			ProxyList.UNSAFE.putShortVolatile(base, offset, to);
	}
}
