package com.sigwalt.automobilista2Telemetry.udpListener.util;

public interface IPacketConverter<Output> {
	
	public Output convert(byte[] data);

}
