package com.programmeren4.turnahead.shared.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Karakter DTO
 */
public class KarakterDTO implements Serializable {
	// attributen
	private static final long serialVersionUID = 1L;
	private Long karakterId; // int karakterId;
	private String karakterName;
	private String currentLocation;
	private Long userId;
	private Long locationId;
	private Date creationDate;
	private Date lastUseDate;

	// constructor
	/**
	 * Karakter Constructor met KarakterId
	 * @param karakterId
	 * @param karakterName
	 * @param currentLocation
	 * @param userId
	 * @param locationId
	 */
	public KarakterDTO(Long karakterId, String karakterName,
			String currentLocation, Long userId, Long locationId) {
		this.karakterId = karakterId;
		this.karakterName = karakterName;
		this.currentLocation = currentLocation;
		this.userId = userId;
		this.locationId = locationId;
	}
	
	/**
	 * Karakter Constructor zonder KarakterId
	 * @param karakterName
	 * @param currentLocation
	 * @param userId
	 * @param locationId
	 */
	public KarakterDTO(String karakterName, String currentLocation,
			Long userId, Long locationId) {
		this.karakterName = karakterName;
		this.currentLocation = currentLocation;
		this.userId = userId;
		this.locationId = locationId;
	}
	
	public KarakterDTO(String karakterName, Long userId) {
		this.karakterName = karakterName;
		this.userId = userId;
	}

	public KarakterDTO() {
		super();
	}

	// getters en setters
	public Long getKarakterId() {
		return karakterId;
	}

	public void setKarakterId(Long karakterId) {
		this.karakterId = karakterId;
	}

	public String getKarakterName() {
		return karakterName;
	}

	public void setKarakterName(String karakterName) {
		this.karakterName = karakterName;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUseDate() {
		return lastUseDate;
	}

	public void setLastUseDate(Date lastUseDate) {
		this.lastUseDate = lastUseDate;
	}

	// methoden
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KarakterDTO other = (KarakterDTO) obj;
		if (karakterId == null) {
			if (other.karakterId != null)
				return false;
		} else if (!karakterId.equals(other.karakterId))
			return false;
		return true;
	}

}
