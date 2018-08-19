package com.k2.JavaBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.k2.JavaBuilder.io.ClassReflector;
import com.k2.JavaBuilder.io.ClassWriter;
import com.k2.JavaBuilder.io.FieldWriter;
import com.k2.JavaBuilder.io.impl.BasicClassIO;
import com.k2.JavaBuilder.io.impl.BasicFieldIO;

public class MetaTypeIOFactory {
	
	Class<? extends ClassWriter> classWriterType = BasicClassIO.class;
	public void setClassWriterType(Class<? extends ClassWriter> classWriterType) {
		this.classWriterType = classWriterType;
	}
	
	Class<? extends FieldWriter> fieldWriterType = BasicFieldIO.class;
	public void setFieldWriterType(Class<? extends FieldWriter> fieldWriterType) {
		this.fieldWriterType = fieldWriterType;
	}
	
	public ClassWriter getWriter(MetaClass metaClass) {
		
		Constructor<? extends ClassWriter> c;
		try {
			c = classWriterType.getConstructor(MetaClass.class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new JavaBuilderError("The given class writer {} has no constructor for a parameter of type {}", e, classWriterType.getName(), MetaClass.class.getName());
		}
		ClassWriter cw;
		try {
			cw = c.newInstance(metaClass);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new JavaBuilderError("The given class writer {} failed to instantiate with a parameter of type {}", e, classWriterType.getName(), MetaClass.class.getName());
		}
		cw.setFieldWriterType(fieldWriterType);
		return cw;
	}

	public static ClassReflector getReflector(Class<?> cls) {
		if (! cls.isAnnotationPresent(com.k2.JavaBuilder.annotation.ClassReflector.class))
			throw new JavaBuilderError("The given class {} is not annotated with '@ClassReflector'", cls.getName());
		com.k2.JavaBuilder.annotation.ClassReflector mr = cls.getAnnotation(com.k2.JavaBuilder.annotation.ClassReflector.class);
		Class<? extends ClassReflector> classReflectorType = mr.reflector();
		try {
			return classReflectorType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new JavaBuilderError("The class reflector {} defined by class {} does not have a public zero arg constructor");
		}
		
		
	}
}
