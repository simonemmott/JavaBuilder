package com.k2.JavaBuilder.io;

import java.io.Writer;

import com.k2.JavaBuilder.AMetaType;

public interface TypeWriter {

	public void write(AMetaType type, Writer w);
}
