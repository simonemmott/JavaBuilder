package com.k2.JavaBuilder.io.impl;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.k2.JavaBuilder.MetaClass;
import com.k2.JavaBuilder.MetaField;
import com.k2.JavaBuilder.MetaInterface;
import com.k2.JavaBuilder.io.ClassReflector;
import com.k2.JavaBuilder.io.ClassWriter;
import com.k2.JavaBuilder.io.FieldReflector;
import com.k2.JavaBuilder.io.FieldWriter;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;

public class BasicFieldIO implements FieldWriter, FieldReflector{
	
	private MetaField metaField;
	private boolean pretty = false;
	
	
	public BasicFieldIO pretty() {
		pretty = true;
		return this;
	}
	
	private void cr(PrintWriter pw) {
		if (pretty) pw.println(); else pw.print(" ");
	}

	public BasicFieldIO() {}
	public BasicFieldIO(MetaField metaField) {
		this.metaField = metaField;
	}
		
	@Override
	public void write(Writer w) {

		PrintWriter pw;
		if (w instanceof PrintWriter) {
			pw = (PrintWriter)w;
		} else {
			pw = new PrintWriter(w);
		}

		cr(pw);
		pw.print(StringUtil.replaceAll("\tprivate {} {};", "{}", 
				metaField.getType().getSimpleName(), 
				StringUtil.aliasCase(metaField.getName())));
		cr(pw);
		pw.print(StringUtil.replaceAll("\tpublic {} get{}() { return {}; }", "{}", 
				metaField.getType().getSimpleName(), 
				StringUtil.initialUpperCase(metaField.getName()),
				StringUtil.aliasCase(metaField.getName())));
		cr(pw);
		pw.print(StringUtil.replaceAll("\tpublic void set{}({} {}) { this.{} = {}; }", "{}", 
				StringUtil.initialUpperCase(metaField.getName()),
				metaField.getType().getSimpleName(), 
				StringUtil.aliasCase(metaField.getName()),
				StringUtil.aliasCase(metaField.getName()),
				StringUtil.aliasCase(metaField.getName())
				));
		cr(pw);
		
	}

	@Override
	public List<MetaField> reflect(Class<?> cls) {
		
		return null;
	}

}
