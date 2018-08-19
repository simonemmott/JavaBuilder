package com.k2.JavaBuilder;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.JavaBuilder.io.ClassReflector;
import com.k2.JavaBuilder.io.ClassWriter;
import com.k2.Util.classes.ClassUtil;



public class BasicClassIOTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	
	@Test
	public void basicClassWriteReflectTest() {
		
		MetaTypeIOFactory mtf = new MetaTypeIOFactory();
		
		MetaClass mc = new MetaClass("com.k2.example.Foo");
		
		StringWriter sw = new StringWriter();
		
		ClassWriter cw = mtf.getWriter(mc);
		
		cw.write(sw);
		
		String expectedResult = ""
				+ "package com.k2.example; "
				+ " "
				+ "import com.k2.JavaBuilder.annotation.ClassReflector; "
				+ " "
				+ "@ClassReflector(reflector=com.k2.JavaBuilder.io.impl.BasicClassIO.class) "
				+ "public class Foo { "
				+ "} ";
		
		assertEquals(expectedResult, sw.toString());
		
		Class<? extends Empty> cls = ClassUtil.createClassFromString(Empty.class, "com.k2.example", "Foo", sw.toString());
		
		ClassReflector cr = mtf.getReflector(cls);
		
		MetaClass reflection = cr.reflect(cls);
		
		assertEquals(mc.getName(), reflection.getName());
		assertEquals(mc.getType(), reflection.getType());
		assertEquals(mc.getClassType(), reflection.getClassType());
		
		
	}

	@Test
	public void prettyClassWriteReflectTest() {
		
		MetaTypeIOFactory mtf = new MetaTypeIOFactory();
		
		MetaClass mc = new MetaClass("com.k2.example.Bar");
		
		StringWriter sw = new StringWriter();
		
		ClassWriter cw = mtf.getWriter(mc).pretty();
		
		cw.write(sw);
		
		String expectedResult = ""
				+ "package com.k2.example;\n"
				+ "\n"
				+ "import com.k2.JavaBuilder.annotation.ClassReflector;\n"
				+ "\n"
				+ "@ClassReflector(reflector=com.k2.JavaBuilder.io.impl.BasicClassIO.class)\n"
				+ "public class Bar {\n"
				+ "}\n";
		
		assertEquals(expectedResult, sw.toString());
		
		Class<? extends Empty> cls = ClassUtil.createClassFromString(Empty.class, "com.k2.example", "Bar", sw.toString());
		
		ClassReflector cr = mtf.getReflector(cls);
		
		MetaClass reflection = cr.reflect(cls);
		
		assertEquals(mc.getName(), reflection.getName());
		assertEquals(mc.getType(), reflection.getType());
		assertEquals(mc.getClassType(), reflection.getClassType());
		
		
	}

	@Test
	public void extendedClassWriteReflectTest() {
		
		MetaTypeIOFactory mtf = new MetaTypeIOFactory();
		
		MetaClass mc = new MetaClass("com.k2.example.Extended");
		MetaClass ec = new MetaClass("com.k2.JavaBuilder.Empty");
		mc.setExtendsClass(ec);
		
		
		StringWriter sw = new StringWriter();
		
		ClassWriter cw = mtf.getWriter(mc).pretty();
		
		cw.write(sw);
		
		String expectedResult = ""
				+ "package com.k2.example;\n"
				+ "\n"
				+ "import com.k2.JavaBuilder.Empty;\n"
				+ "import com.k2.JavaBuilder.annotation.ClassReflector;\n"
				+ "\n"
				+ "@ClassReflector(reflector=com.k2.JavaBuilder.io.impl.BasicClassIO.class)\n"
				+ "public class Extended extends Empty {\n"
				+ "}\n";
		
		assertEquals(expectedResult, sw.toString());
		
		Class<? extends Empty> cls = ClassUtil.createClassFromString(Empty.class, "com.k2.example", "Extended", sw.toString());
		
		ClassReflector cr = mtf.getReflector(cls);
		
		MetaClass reflection = cr.reflect(cls);
		
		assertEquals(mc.getName(), reflection.getName());
		assertEquals(mc.getType(), reflection.getType());
		assertEquals(mc.getClassType(), reflection.getClassType());
		assertEquals(mc.getExtendsClass(), reflection.getExtendsClass());
		
		
	}

	@Test
	public void classImplementsWriteReflectTest() {
		
		MetaTypeIOFactory mtf = new MetaTypeIOFactory();
		
		MetaClass mc = new MetaClass("com.k2.example.Implementor");
		MetaClass ec = new MetaClass("com.k2.JavaBuilder.Empty");
		MetaInterface nothing1 = new MetaInterface("com.k2.JavaBuilder.Nothing1");
		MetaInterface nothing2 = new MetaInterface("com.k2.JavaBuilder.Nothing2");
		mc.setExtendsClass(ec);
		mc.addImplementedInterface(nothing1, nothing2);
		
		
		StringWriter sw = new StringWriter();
		
		ClassWriter cw = mtf.getWriter(mc).pretty();
		
		cw.write(sw);
		
		String expectedResult = ""
				+ "package com.k2.example;\n"
				+ "\n"
				+ "import com.k2.JavaBuilder.Empty;\n"
				+ "import com.k2.JavaBuilder.Nothing1;\n"
				+ "import com.k2.JavaBuilder.Nothing2;\n"
				+ "import com.k2.JavaBuilder.annotation.ClassReflector;\n"
				+ "\n"
				+ "@ClassReflector(reflector=com.k2.JavaBuilder.io.impl.BasicClassIO.class)\n"
				+ "public class Implementor extends Empty implements Nothing1, Nothing2 {\n"
				+ "}\n";
		
		assertEquals(expectedResult, sw.toString());
		
		Class<? extends Empty> cls = ClassUtil.createClassFromString(Empty.class, "com.k2.example", "Implementor", sw.toString());
		
		ClassReflector cr = mtf.getReflector(cls);
		
		MetaClass reflection = cr.reflect(cls);
		
		assertEquals(mc.getName(), reflection.getName());
		assertEquals(mc.getType(), reflection.getType());
		assertEquals(mc.getClassType(), reflection.getClassType());
		assertEquals(mc.getExtendsClass(), reflection.getExtendsClass());
		if (mc.getImplementedInterfaces() != null) {
			assertEquals(mc.getImplementedInterfaces().size(), reflection.getImplementedInterfaces().size());
			List<MetaInterface> reflectedInterfaces = reflection.getImplementedInterfaces();
			for (MetaInterface mi : mc.getImplementedInterfaces()) {
				assertTrue(reflectedInterfaces.contains(mi));
			}
		}
		
		
	}

	@Test
	public void classWithFieldsWriteReflectTest() {
		
		MetaTypeIOFactory mtf = new MetaTypeIOFactory();
		
		MetaClass mc = new MetaClass("com.k2.example.WithFields");
		
		MetaField nameField = mc.newRecordInListFields();
		nameField.setName("name");
		nameField.setType(MetaTypeFactory.nativeString);
		
		MetaField emptyField = mc.newRecordInListFields();
		emptyField.setName("empty");
		emptyField.setType(MetaTypeFactory.getMetaType(Empty.class));
		
		StringWriter sw = new StringWriter();
		
		ClassWriter cw = mtf.getWriter(mc).pretty();
		
		cw.write(sw);
		
		String expectedResult = ""
				+ "package com.k2.example;\n"
				+ "\n"
				+ "import com.k2.JavaBuilder.Empty;\n"
				+ "import com.k2.JavaBuilder.annotation.ClassReflector;\n"
				+ "\n"
				+ "@ClassReflector(reflector=com.k2.JavaBuilder.io.impl.BasicClassIO.class)\n"
				+ "public class WithFields {\n"
				+ "\n"
				+ "\tprivate String name;\n"
				+ "\tpublic String getName() { return name; }\n"
				+ "\tpublic void setName(String name) { this.name = name; }\n"
				+ "\n"
				+ "\tprivate Empty empty;\n"
				+ "\tpublic Empty getEmpty() { return empty; }\n"
				+ "\tpublic void setEmpty(Empty empty) { this.empty = empty; }\n"
				+ "}\n";
		
		assertEquals(expectedResult, sw.toString());
		
		Class<? extends Empty> cls = ClassUtil.createClassFromString(Empty.class, "com.k2.example", "WithFields", sw.toString());
		
		ClassReflector cr = mtf.getReflector(cls);
		
		MetaClass reflection = cr.reflect(cls);
		
		assertEquals(mc.getName(), reflection.getName());
		assertEquals(mc.getType(), reflection.getType());
		assertEquals(mc.getClassType(), reflection.getClassType());
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
