package com.k2.JavaBuilder;

import java.util.ArrayList;
import java.util.List;

import com.k2.JavaBuilder.annotation.ClassReflector;

@ClassReflector(reflector=com.k2.JavaBuilder.io.impl.BasicClassIO.class)
public class MetaClass extends AMetaType{
	
	public MetaClass(String name) {
		super(name, JavaCoreType.CLASS);
	}

	public MetaClass(String name, ClassType classType) {
		super(name, JavaCoreType.CLASS);
		this.classType = classType;
	}

	private ClassType classType;
	public ClassType getClassType() { return classType; }

	private MetaClass extendsClass;
	public MetaClass getExtendsClass() { return extendsClass; }
	public void setExtendsClass(MetaClass extendsClass) { this.extendsClass = extendsClass; }
	
	public List<MetaInterface> implementedInterfaces;
	public List<MetaInterface> getImplementedInterfaces() { return implementedInterfaces; }
	public void setImplementedInterfaces(List<MetaInterface> implementedInterfaces) { this.implementedInterfaces = implementedInterfaces;}
	public void addImplementedInterface(MetaInterface ... implementedInterfaces) {
		if (this.implementedInterfaces == null) this.implementedInterfaces = new ArrayList<MetaInterface>();
		for (MetaInterface item : implementedInterfaces) {
			this.implementedInterfaces.add(item);
		}
	}

	public List<MetaField> fields;
	public List<MetaField> getFields() { return fields; }
	public void setFields(List<MetaField> fields) { this.fields = fields;}
	public void addField(MetaField ... fields) {
		if (this.fields == null) this.fields = new ArrayList<MetaField>();
		for (MetaField item : fields) {
			this.fields.add(item);
		}
	}
	public MetaField newRecordInListFields() {
		if (fields == null) fields = new ArrayList<MetaField>();
		MetaField item = new MetaField(this);
		fields.add(item);
		return item;
	}
}
