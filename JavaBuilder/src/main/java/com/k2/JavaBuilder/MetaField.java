package com.k2.JavaBuilder;

import java.util.HashSet;
import java.util.Set;

public class MetaField {
	
	public MetaField(MetaClass metaClass) {
		this.metaClass = metaClass;
	}

	private MetaClass metaClass;
	public MetaClass getMetaClass() {
		return metaClass;
	}

	private AMetaType dataType;
	public AMetaType getType() { return dataType; }
	public void setType(AMetaType datatype) { this.dataType = datatype; }

	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metaClass == null) ? 0 : metaClass.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		MetaField other = (MetaField) obj;
		if (metaClass == null) {
			if (other.metaClass != null)
				return false;
		} else if (!metaClass.equals(other.metaClass))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	


}
