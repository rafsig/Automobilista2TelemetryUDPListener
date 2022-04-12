package com.sigwalt.automobilista2Telemetry.udpListener;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.sigwalt.automobilista2Telemetry.models.GameState;
import com.sigwalt.automobilista2Telemetry.models.GameStateData;
import com.sigwalt.automobilista2Telemetry.models.PacketBase;
import com.sigwalt.automobilista2Telemetry.models.PacketType;
import com.sigwalt.automobilista2Telemetry.models.TelemetryData;
import com.sigwalt.automobilista2Telemetry.udpListener.util.GameStateConverter;
import com.sigwalt.automobilista2Telemetry.udpListener.util.IPacketConverter;
import com.sigwalt.automobilista2Telemetry.udpListener.util.PacketBaseConverter;
import com.sigwalt.automobilista2Telemetry.udpListener.util.TelemetryDataConverter;

public class UDPListener implements Runnable {

		int port;
		byte[] receiveData;
		DatagramSocket udpListeningSocket;
		
		public UDPListener(int localPort) {
			port = localPort;
			receiveData = new byte[2048];
			try {
				udpListeningSocket = new DatagramSocket(localPort);
			}catch(SocketException e) {
				System.out.println("Socket bing error in port: " + port);
				e.printStackTrace();
			}
		}

		public void run() {
			
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				try {
					udpListeningSocket.receive(receivePacket);
					byte[] data = receivePacket.getData();

					IPacketConverter<PacketBase> converter = new PacketBaseConverter();
					PacketBase packetBase = converter.convert(data);
					if(packetBase.getPacketType() == null) {
						throw new Exception();
					}
					if(packetBase.getPacketType().equals(PacketType.GAME_STATE)) {
						IPacketConverter<GameStateData> gameStateConverter = new GameStateConverter();
						GameStateData gameStateData = gameStateConverter.convert(data);
					}
					else if(packetBase.getPacketType().equals(PacketType.CAR_PHYSICS)) {
						IPacketConverter<TelemetryData> telemetryDataConverter = new TelemetryDataConverter();
						TelemetryData telemetryData = telemetryDataConverter.convert(data);
						System.out.println("numGears: " + telemetryData.getNumGears());
					}
					
				}catch(Exception e) {

				}
			}
			
		}
}
