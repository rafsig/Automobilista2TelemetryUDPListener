package com.sigwalt.automobilista2Telemetry.udpListener.util;

import com.sigwalt.automobilista2Telemetry.models.PacketBase;
import com.sigwalt.automobilista2Telemetry.models.PacketType;
import com.sigwalt.automobilista2Telemetry.udpListener.util.parser.DataParser;

public class PacketBaseConverter implements IPacketConverter<PacketBase> {

	@Override
	public PacketBase convert(byte[] data) {
		PacketBase packetBase = new PacketBase();
		packetBase.setPacketNumber(DataParser.parseLong(0, 4, data));
		packetBase.setCategoryPacketNumber(DataParser.parseLong(4, 4, data));
		packetBase.setPartialPacketIndex(DataParser.parseShort(8, 1, data));
		packetBase.setPartialPacketNumber(DataParser.parseShort(9, 1, data));
		packetBase.setPacketType(PacketType.valueOf(DataParser.parseShort(10, 1, data)));
		packetBase.setPacketVersion(DataParser.parseShort(11, 1, data));
		return packetBase;
	}

}
