package Comunicacion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Multicast extends Thread {

	private InetAddress DIRECCION;
	private int PUERTO;
	private MulticastSocket multicastConection;
	private int id;
	private boolean identified;

	public Multicast() {

	}

	@Override
	public void run() {
		inicializar();
		id();
		while (true) {
			try {
				recibir();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

	}

	public void id() {
		id = -1;

		try {
			multicastConection.setSoTimeout(500);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		enviar("Identify");
		while (identified == false) {

			try {
				recibir();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				identified = true;
				if (id == -1) {
					id = 0;
				}
				try {
					multicastConection.setSoTimeout(0);
				} catch (SocketException e2) {
					// TODO Auto-generated catch block

				}
				System.out.println("Im player: " + id);
			}

		}

	}

	public void inicializar() {
		try {

			PUERTO = 5500;
			DIRECCION = InetAddress.getByName("224.0.0.0");
			multicastConection = new MulticastSocket(PUERTO);
			multicastConection.joinGroup(DIRECCION);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void enviar(String message) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				DatagramPacket envio = new DatagramPacket(message.getBytes(), message.length(), DIRECCION, PUERTO);

				try {
					multicastConection.send(envio);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void recibir() throws IOException {
		byte[] buffer = new byte[100];

		DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
		multicastConection.receive(recibo);

		String recibido = new String(recibo.getData()).trim();
		recibirMensajes(recibido);
	}

	public void recibirMensajes(String msgReceived) {
		if (msgReceived.equals("Identify")) {
			enviar("MyIDIs:" + this.id);
		}

		if (identified == false) {
			if (msgReceived.contains("MyIDIs:")) {
				String[] separate = msgReceived.split(":");
				int id = Integer.parseInt(separate[1]);
				if (id >= this.id) {
					this.id = id + 1;
				}
			}

		}

	}

}
