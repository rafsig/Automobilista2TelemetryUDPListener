package com.sigwalt.automobilista2Telemetry.udpListener.util.parser;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.BitSet;

public class DataParser {
	
	public static int parseInt(int start, int length, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data, start, length);
		return (int) (byteBuffer.getShort() & 0xFF);
	}
	
	public static short parseShort(int start, int length, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data, start, length);
		return (short) (byteBuffer.get(0) & 0xF);
	}
	
	public static long parseLong(int start, int length, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data, start, length);
		return (long) (byteBuffer.getInt() & 0xFFFFFFFF);
	}
	
	public static String parseString(int start, int length, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data, start, length);
		return null;
	}
	
	public static short parseShortLastBits(int start, int length, byte[] data) {
		
		int result = (int) (Arrays.copyOfRange(data, 45, 46)[0] & 0x0F);
		return (short) result;
	}
	
	private static void printBits(BitSet bitSet) {
		System.out.println("\n\n Bits received:\n");
		for(int i = 0; i < bitSet.length(); i++) {
			System.out.print(bitSet.get(i) + " ");
		}
		System.out.println("\n\n");
	}
	
	private static short convertBitSet(BitSet bitSet) {
		short value=0;
		for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i+1)) {
			value += (1 << i);
		}
		return value;
	}
}
