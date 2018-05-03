package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class PowerOutages implements Comparable<PowerOutages>{
	
	private int id ;
	private int eventTypeId ;
	private int tagId ;
	private int areaId ;
	private Nerc nerc ;
	private int ResponsibleId ;
	private int custumersAffected ;
	private LocalDateTime dateEventBegan ;
	private LocalDateTime dateEventFinished ;
	private int demandLoss ;
	
	public PowerOutages(int id, int eventTypeId, int tagId, int areaId, Nerc nerc, int responsibleId,
			int custumersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished, int demandLoss) {
		super();
		this.id = id;
		this.eventTypeId = eventTypeId;
		this.tagId = tagId;
		this.areaId = areaId;
		this.nerc = nerc;
		ResponsibleId = responsibleId;
		this.custumersAffected = custumersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.demandLoss = demandLoss;
	}
	
	public PowerOutages() {
		
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getResponsibleId() {
		return ResponsibleId;
	}

	public void setResponsibleId(int responsibleId) {
		ResponsibleId = responsibleId;
	}

	public int getCustumersAffected() {
		return custumersAffected;
	}

	public void setCustumersAffected(int custumersAffected) {
		this.custumersAffected = custumersAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
	}

	@Override
	public String toString() {
		return "PowerOutages [id=" + id + ", eventTypeId=" + eventTypeId + ", tagId=" + tagId + ", areaId=" + areaId
				+ ", nerc=" + nerc + ", ResponsibleId=" + ResponsibleId + ", custumersAffected=" + custumersAffected
				+ ", dateEventBegan=" + dateEventBegan + ", dateEventFinished=" + dateEventFinished + ", demandLoss="
				+ demandLoss + "]";
	}

	@Override
	public int compareTo(PowerOutages arg0) {
		return this.dateEventBegan.compareTo(arg0.getDateEventBegan());
	}
	
	
	

}
