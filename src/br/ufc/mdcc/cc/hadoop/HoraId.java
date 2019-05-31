package br.ufc.mdcc.cc.hadoop;

public class HoraId {
	
	private String hora;
	private String id;
		
		
	public HoraId(String hora, String id) {
		super();
		this.hora = hora;
		this.id = id;
		
	}
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getId() {
		return id;
	}
	public void setDia(String id) {
		this.id = id;
	}
	
	
	

}
