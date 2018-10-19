package Comunicacion;

public abstract class Player extends Thread {

	protected int poblacion, felicidad, demanda;
	protected String nombre;
	protected int season, energiaGeneral, turno, pasiveEnergy;
	protected String notificacion;
	protected boolean canPlay = false;

	protected String notif;
	public Player () {
		
	}
	
	public void getData ( int poblacion, int demanda, int energiaGeneral ) {
		this.poblacion = poblacion;
		this.demanda = demanda;
		this.energiaGeneral = energiaGeneral;
		
		season = 1;
	}
	@Override
	public abstract void run();
	
	public abstract void setNombre(String nombre);
	public abstract void setPoblacion(int poblacion);
	public abstract void setDemanda(int demanda);
	public abstract void setSeason(int season);
	public abstract void setEnergy (int energy);
	public abstract void notifyPlayers(String notif) ;
	
	public boolean isCanPlay() {
		return canPlay;
	}

	public int getTurno() {
		return turno;
	}

	public int getSeason () {
		return season;
	}

	public int getPasiveEnergy () {
		return pasiveEnergy;
	}

	public String getNotif() {
		return notif;
	}

	
	
	
}
