package InterfaceElements;

public class Timer implements Runnable {

	private int  ms, s, sg;
	private boolean stoped;
	private String time;

	
	public Timer() {
		ms = 0;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
				if (stoped!=true) {
					
					ms++;
					if(ms==100){
						s++;
						sg++;
						ms=0;
					}
					
				}
				
				time = sg+ " : "+ ms;

			} catch (Exception e) {
				

			}

		}
	}

	public void restart(){
		s=0;
		sg=0;
		ms=0;
	}
	
	public int getMs() {
		return ms;
	}

	public void setMs(int ms) {
		this.ms = ms;
	}

	public int getSg() {
		return sg;
	}

	public void setSg(int sg) {
		this.sg = sg;
	}

	public boolean isStop() {
		return stoped;
	}

	public void setStop(boolean detener) {
		this.stoped = detener;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}
	
	
	
}
