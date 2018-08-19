package com.k2.JavaBuilder.io;

import java.io.Writer;

import com.k2.JavaBuilder.MetaClass;

public interface FieldWriter {

	public void write(Writer w);
	public FieldWriter pretty();
}
