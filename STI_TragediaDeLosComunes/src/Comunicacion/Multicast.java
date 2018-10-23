package Comunicacion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Multicast extends Player {

	private InetAddress DIRECCION;
	private int PUERTO;
	private MulticastSocket multicastConection;
	private int id;
	private boolean identified;
	public int turnoAlServidor;

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
				enviar("ImInicial:" + id + ":" + poblacion + ":" + demanda + ":" + energiaGeneral);

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
		if (identified == true) {
			if (msgReceived.equals("Identify")) {
				enviar("MyIDIs:" + this.id);
			}
			if (msgReceived.equals("IMCanPlay:true")) {
				canPlay = true;
			}
			if (msgReceived.contains("AsignTurn")) {

				 String[] separated = msgReceived.split(":");

				 if (id == Integer.parseInt(separated[1])) {

					 turno = Integer.parseInt(separated[2]);
					 System.out.println("El turno asignado fue: "+ Integer.parseInt(separated[2]) );

				 }

			}

			if (msgReceived.contains("cambioTurno")) {
				String[] separated = msgReceived.split(":");
				int turno = Integer.parseInt(separated[1]);
				turnoAlServidor = turno;
			}
			
			if (msgReceived.equals("autumn")) {
				season = 2;
			}
			if (msgReceived.equals("winter")) {
				season = 3;
			}
			if (msgReceived.equals("spring")) {
				season = 4;
			}
			if (msgReceived.equals("juegoTerminado")) {
				season = 0;
			}
			if (msgReceived.contains("EnergyByRound")) {
				String[] separate = msgReceived.split(":");
				pasiveEnergy = Integer.parseInt(separate [1]);
			}
			if (msgReceived.contains("EnergyByRound")) {
				String[] separate = msgReceived.split(":");
				pasiveEnergy = Integer.parseInt(separate [1]);
			}			
			if (msgReceived.contains("notif")) {
				String[] separate = msgReceived.split(":");
				String name = separate[1];
				String notif = separate[2];
				String newNotif = name + " : " + notif;
				notif = newNotif;
			}
			

		} else {
			if (msgReceived.contains("MyIDIs:")) {
				String[] separate = msgReceived.split(":");
				int id = Integer.parseInt(separate[1]);
				if (id >= this.id) {
					this.id = id + 1;
				}
			}

		}

	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
		enviar("IMNombre:" + id + ":" + nombre);
	}

	@Override
	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
		enviar("IMPob:" + id + ":" + poblacion);
	}

	@Override
	public void setDemanda(int demanda) {
		this.demanda = demanda;
		enviar("IMDem:" + id + ":" + demanda);
	}

	@Override
	public void setEnergy(int energy) {
		this.energiaGeneral = energy;
		enviar("IMEne:" + id + ":" + energy);
	}
	
	public void notifyPlayers(String notif) {
		enviar("notif:" + nombre + ":" + notif);
	}

	@Override
	public void setSeason(int season) {
		// TODO Auto-generated method stub
		
	}

	public int getTurnoAlServidor() {
		return turnoAlServidor;
	}

	public void setTurnoAlServidor(int turnoAlServidor) {
		this.turnoAlServidor = turnoAlServidor;
	}
	
	@Override
	public void setEnvironmental(int environmental) {
		this.environmental = environmental;
		enviar("IMEnv:" + id + ":" + environmental);
	}
	
	@Override
	public void setFelicidad(int felicidad) {
		this.felicidad = felicidad;
		enviar("IMFel:" + id + ":" + felicidad);
	}
	

}
