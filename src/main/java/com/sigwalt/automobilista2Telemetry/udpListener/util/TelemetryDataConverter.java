package com.sigwalt.automobilista2Telemetry.udpListener.util;

import com.sigwalt.automobilista2Telemetry.models.TelemetryData;
import com.sigwalt.automobilista2Telemetry.udpListener.util.parser.DataParser;

public class TelemetryDataConverter implements IPacketConverter<TelemetryData> {

	@Override
	public TelemetryData convert(byte[] data) {
		TelemetryData telemetryData = new TelemetryData();

		telemetryData.setNumGears(DataParser.parseShortLastBits(45, 1, data));
		
		return telemetryData;
	}

}
