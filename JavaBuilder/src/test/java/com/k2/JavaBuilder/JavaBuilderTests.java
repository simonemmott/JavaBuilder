package com.k2.JavaBuilder;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JavaBuilderTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	@Test
	public void metaPrimitivesTest()
    {
		AMetaType mt;
		
		mt = MetaTypeFactory.getPrimitive(int.class);
		assertEquals("", mt.getPackageName());
		assertEquals("int", mt.getName());
		assertEquals("int", mt.getSimpleName());
		assertEquals("int", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(long.class);
		assertEquals("", mt.getPackageName());
		assertEquals("long", mt.getName());
		assertEquals("long", mt.getSimpleName());
		assertEquals("long", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(double.class);
		assertEquals("", mt.getPackageName());
		assertEquals("double", mt.getName());
		assertEquals("double", mt.getSimpleName());
		assertEquals("double", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(float.class);
		assertEquals("", mt.getPackageName());
		assertEquals("float", mt.getName());
		assertEquals("float", mt.getSimpleName());
		assertEquals("float", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(boolean.class);
		assertEquals("", mt.getPackageName());
		assertEquals("boolean", mt.getName());
		assertEquals("boolean", mt.getSimpleName());
		assertEquals("boolean", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(char.class);
		assertEquals("", mt.getPackageName());
		assertEquals("char", mt.getName());
		assertEquals("char", mt.getSimpleName());
		assertEquals("char", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(short.class);
		assertEquals("", mt.getPackageName());
		assertEquals("short", mt.getName());
		assertEquals("short", mt.getSimpleName());
		assertEquals("short", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(byte.class);
		assertEquals("", mt.getPackageName());
		assertEquals("byte", mt.getName());
		assertEquals("byte", mt.getSimpleName());
		assertEquals("byte", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		mt = MetaTypeFactory.getPrimitive(void.class);
		assertEquals("", mt.getPackageName());
		assertEquals("void", mt.getName());
		assertEquals("void", mt.getSimpleName());
		assertEquals("void", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());
		
		try {
			mt = MetaTypeFactory.getPrimitive(String.class);
			fail("Should not get here");
		} catch (JavaBuilderError e) {
			assertEquals("The class java.lang.String is not a java primitive type", e.getMessage());
		}
		
	}
	
	@Test
	public void metaNativesTest()
    {
		MetaClass mc;
		
		mc = MetaTypeFactory.getNative(Integer.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Integer", mc.getName());
		assertEquals("Integer", mc.getSimpleName());
		assertEquals("java.lang.Integer", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Long.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Long", mc.getName());
		assertEquals("Long", mc.getSimpleName());
		assertEquals("java.lang.Long", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Float.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Float", mc.getName());
		assertEquals("Float", mc.getSimpleName());
		assertEquals("java.lang.Float", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Double.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Double", mc.getName());
		assertEquals("Double", mc.getSimpleName());
		assertEquals("java.lang.Double", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Boolean.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Boolean", mc.getName());
		assertEquals("Boolean", mc.getSimpleName());
		assertEquals("java.lang.Boolean", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(String.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.String", mc.getName());
		assertEquals("String", mc.getSimpleName());
		assertEquals("java.lang.String", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Date.class);
		assertEquals("java.util", mc.getPackageName());
		assertEquals("java.util.Date", mc.getName());
		assertEquals("Date", mc.getSimpleName());
		assertEquals("java.util.Date", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Character.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Character", mc.getName());
		assertEquals("Character", mc.getSimpleName());
		assertEquals("java.lang.Character", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Short.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Short", mc.getName());
		assertEquals("Short", mc.getSimpleName());
		assertEquals("java.lang.Short", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Byte.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Byte", mc.getName());
		assertEquals("Byte", mc.getSimpleName());
		assertEquals("java.lang.Byte", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		mc = MetaTypeFactory.getNative(Character.class);
		assertEquals("java.lang", mc.getPackageName());
		assertEquals("java.lang.Character", mc.getName());
		assertEquals("Character", mc.getSimpleName());
		assertEquals("java.lang.Character", mc.toString());
		assertEquals(JavaCoreType.CLASS, mc.getType());
		assertEquals(ClassType.NATIVE, mc.getClassType());
		
		try {
			mc = MetaTypeFactory.getNative(int.class);
			fail("Should not get here");
		} catch (JavaBuilderError e) {
			assertEquals("The class int is not a java native type", e.getMessage());
		}
		

	}
	
	
	@Test
	public void metaTypeTest()
    {
		AMetaType mt;
		
		mt = MetaTypeFactory.getMetaType(double.class);
		assertEquals("", mt.getPackageName());
		assertEquals("double", mt.getName());
		assertEquals("double", mt.getSimpleName());
		assertEquals("double", mt.toString());
		assertEquals(JavaCoreType.PRIMITIVE, mt.getType());

		mt = MetaTypeFactory.getMetaType(Integer.class);
		assertEquals("java.lang", mt.getPackageName());
		assertEquals("java.lang.Integer", mt.getName());
		assertEquals("Integer", mt.getSimpleName());
		assertEquals("java.lang.Integer", mt.toString());
		assertEquals(JavaCoreType.CLASS, mt.getType());

		mt = MetaTypeFactory.getMetaType(MetaClass.class);
		assertEquals("com.k2.JavaBuilder", mt.getPackageName());
		assertEquals("com.k2.JavaBuilder.MetaClass", mt.getName());
		assertEquals("MetaClass", mt.getSimpleName());
		assertEquals("com.k2.JavaBuilder.MetaClass", mt.toString());
		assertEquals(JavaCoreType.CLASS, mt.getType());
		

    }
	

}
