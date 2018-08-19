package com.k2.JavaBuilder;

public class MetaInterface extends AMetaType{
	
	public MetaInterface(String name) {
		super(name, JavaCoreType.INTERFACE);
	}
	
	private MetaInterface extendsInterface;
	public MetaInterface getExtendsInterface() {
		return extendsInterface;
	}
	public void setExtendsInterface(MetaInterface extendsInterface) {
		this.extendsInterface = extendsInterface;
	}
	
}
