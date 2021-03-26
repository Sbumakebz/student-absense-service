package com.sibusiso.cgsi.model;

public class StudentAbsense {
	private String fullName;
	private String className;
	private String gradeName;
	private Boolean isPresent;
	private String dateTime;

	public StudentAbsense() {
		super();
	}

	public StudentAbsense(String fullName, String className, String gradeName, 
							Boolean isPresent, String dateTime) {
		super();
		this.fullName = fullName;
		this.className = className;
		this.gradeName = gradeName;
		this.isPresent = isPresent;
		this.dateTime = dateTime;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public Boolean getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass())
			return false;
		
		StudentAbsense other = (StudentAbsense) obj;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;

		return true;
	}
}
