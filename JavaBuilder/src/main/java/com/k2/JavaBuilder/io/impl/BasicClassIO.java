package com.k2.JavaBuilder.io.impl;

import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.k2.JavaBuilder.JavaBuilderError;
import com.k2.JavaBuilder.MetaClass;
import com.k2.JavaBuilder.MetaField;
import com.k2.JavaBuilder.MetaInterface;
import com.k2.JavaBuilder.io.ClassReflector;
import com.k2.JavaBuilder.io.ClassWriter;
import com.k2.JavaBuilder.io.FieldWriter;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;

public class BasicClassIO implements ClassWriter, ClassReflector{
	
	private MetaClass metaClass;
	private boolean pretty = false;
	private Dependencies dependencies;
	
	
	public BasicClassIO pretty() {
		pretty = true;
		return this;
	}
	
	Class<? extends FieldWriter> fieldWriterType;
	@Override
	public void setFieldWriterType(Class<? extends FieldWriter> fieldWriterType) {
		this.fieldWriterType = fieldWriterType;
		
	}

	private void cr(PrintWriter pw) {
		if (pretty) pw.println(); else pw.print(" ");
	}

	public BasicClassIO() {}
	public BasicClassIO(MetaClass metaClass) {
		this.metaClass = metaClass;
		this.dependencies = Dependencies.forName(metaClass.getName());
		dependencies.add(com.k2.JavaBuilder.annotation.ClassReflector.class);
		if (metaClass.getExtendsClass() != null)
			dependencies.add(new Dependency(metaClass.getExtendsClass().getPackageName(), metaClass.getExtendsClass().getSimpleName()));
		if (metaClass.getImplementedInterfaces() != null) {
			for (MetaInterface mi : metaClass.getImplementedInterfaces()) {
				dependencies.add(new Dependency(mi.getPackageName(), mi.getSimpleName()));
			}
		}
		if (metaClass.getFields() != null) {
			for (MetaField field : metaClass.getFields()) {
				dependencies.add(new Dependency(field.getType().getPackageName(), field.getType().getSimpleName()));
			}
		}
		
	}
	
	private String extendsClause() {
		if (metaClass.getExtendsClass() == null) {
			return "";
		} else {
			return " extends "+metaClass.getExtendsClass().getSimpleName();
		}
	}
	
	private String implementsClause() {
		List<MetaInterface> implementedInterfaces = metaClass.getImplementedInterfaces();
		if (implementedInterfaces == null || implementedInterfaces.size() == 0) {
			return "";
		} else {
			StringBuilder clause = new StringBuilder(" implements ");
			implementedInterfaces.forEach(new Consumer<MetaInterface>() {

				private boolean added = false;
				
				@Override
				public void accept(MetaInterface mi) {
					if (added) clause.append(", ");
					clause.append(mi.getSimpleName());
					added = true;
					
				}
				
			});
				
			return clause.toString();
		}
	}
	
	private void writeFields(PrintWriter pw) {
		if (fieldWriterType == null) return;
		Constructor<? extends FieldWriter> c;
		try {
			c = fieldWriterType.getConstructor(MetaField.class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new JavaBuilderError("The field writer {} does not have a constructor which takes a MetaField argument", fieldWriterType.getName());
		}
		if (metaClass.getFields() != null) {
			for (MetaField field : metaClass.getFields()) {
				FieldWriter fw;
				try {
					fw = c.newInstance(field);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new JavaBuilderError("Inable to create a new instance of the field writer {} for the field {}", fieldWriterType.getName(), field.toString());
				}
				if (pretty) fw.pretty();
				fw.write(pw);
			}
		}
		
	}
	
	@Override
	public void write(Writer w) {

		PrintWriter pw;
		if (w instanceof PrintWriter) {
			pw = (PrintWriter)w;
		} else {
			pw = new PrintWriter(w);
		}

		pw.print(StringUtil.replaceAll("package {};", "{}", metaClass.getPackageName()));
		cr(pw);
		cr(pw);
		for (Dependency d : dependencies.getDependencies()) {
			pw.print(d.getImportClause());
			cr(pw);
		}
		cr(pw);
		pw.print("@ClassReflector(reflector=com.k2.JavaBuilder.io.impl.BasicClassIO.class)");
		cr(pw);
		pw.print(StringUtil.replaceAll(
				"public class {}{}{} {", 
				"{}", 
				metaClass.getSimpleName(),
				extendsClause(),
				implementsClause()));
		cr(pw);
		writeFields(pw);
		pw.print("}");
		cr(pw);
		
	}

	@Override
	public MetaClass reflect(Class<?> cls) {
		MetaClass mc = new MetaClass(cls.getName());
		
		if (cls.getSuperclass() != null) {
			mc.setExtendsClass(new MetaClass(cls.getSuperclass().getName()));
		}
		
		for (Class<?> i : cls.getInterfaces()) 
			mc.addImplementedInterface(new MetaInterface(i.getName()));
		
		return mc;
	}

}
