package com.k2.JavaBuilder.io;

import java.util.List;

import com.k2.JavaBuilder.MetaField;

public interface FieldReflector {

	public List<MetaField> reflect(Class<?> cls);
}
