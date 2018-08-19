package com.k2.JavaBuilder.io;

import com.k2.JavaBuilder.AMetaType;

public interface TypeReflector {

	public AMetaType reflect(Class<?> cls);
}
