package com.k2.JavaBuilder;

import java.util.Date;

import com.k2.JavaBuilder.io.ClassReflector;

public class MetaTypeFactory {
	
	public static MetaPrimitive primitiveInt = new MetaPrimitive("int");
	public static MetaPrimitive primitiveLong = new MetaPrimitive("long");
	public static MetaPrimitive primitiveChar = new MetaPrimitive("char");
	public static MetaPrimitive primitiveBoolean = new MetaPrimitive("boolean");
	public static MetaPrimitive primitiveFloat = new MetaPrimitive("float");
	public static MetaPrimitive primitiveDouble = new MetaPrimitive("double");
	public static MetaPrimitive primitiveShort = new MetaPrimitive("short");
	public static MetaPrimitive primitiveByte = new MetaPrimitive("byte");
	public static MetaPrimitive primitiveVoid = new MetaPrimitive("void");
	
	public static MetaNative nativeInteger = new MetaNative("java.lang.Integer");
	public static MetaNative nativeLong = new MetaNative("java.lang.Long");
	public static MetaNative nativeCharacter = new MetaNative("java.lang.Character");
	public static MetaNative nativeFloat = new MetaNative("java.lang.Float");
	public static MetaNative nativeDouble = new MetaNative("java.lang.Double");
	public static MetaNative nativeString = new MetaNative("java.lang.String");
	public static MetaNative nativeDate = new MetaNative("java.util.Date");
	public static MetaNative nativeBoolean = new MetaNative("java.lang.Boolean");
	public static MetaNative nativeByte = new MetaNative("java.lang.Byte");
	public static MetaNative nativeShort = new MetaNative("java.lang.Short");
	
	public static MetaPrimitive getPrimitive(Class<?> cls) {
		if (cls == int.class) return primitiveInt;
		if (cls == long.class) return primitiveLong;
		if (cls == char.class) return primitiveChar;
		if (cls == float.class) return primitiveFloat;
		if (cls == double.class) return primitiveDouble;
		if (cls == boolean.class) return primitiveBoolean;
		if (cls == short.class) return primitiveShort;
		if (cls == byte.class) return primitiveByte;
		if (cls == void.class) return primitiveVoid;
		
		throw new JavaBuilderError("The class {} is not a java primitive type", cls.getName());
	}

	public static MetaNative getNative(Class<?> cls) {
		if (cls == Integer.class) return nativeInteger;
		if (cls == Long.class) return nativeLong;
		if (cls == Character.class) return nativeCharacter;
		if (cls == Float.class) return nativeFloat;
		if (cls == Double.class) return nativeDouble;
		if (cls == String.class) return nativeString;
		if (cls == Date.class) return nativeDate;
		if (cls == Boolean.class) return nativeBoolean;
		if (cls == Byte.class) return nativeByte;
		if (cls == Short.class) return nativeShort;
		
		throw new JavaBuilderError("The class {} is not a java native type", cls.getName());
	}
	
	public static AMetaType getMetaType(Class<?> cls) {
		try {
			return getPrimitive(cls);
		} catch (JavaBuilderError e) {
			try {
				return getNative(cls);
			} catch (JavaBuilderError e2) {
				
				ClassReflector cr = MetaTypeIOFactory.getReflector(cls);
				
				return cr.reflect(cls);
			}
		}
	}

}
