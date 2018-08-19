package com.k2.JavaBuilder.io;

import java.io.Writer;

import com.k2.JavaBuilder.MetaClass;

public interface ClassWriter {

	public void write(Writer w);
	public ClassWriter pretty();
	public void setFieldWriterType(Class<? extends FieldWriter> fieldWriterType);
}
