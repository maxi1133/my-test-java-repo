package com.zaka.annotation;

import com.zaka.enums.ExcelExportType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface Excel {
    ExcelExportType type() default ExcelExportType.DEFAULT;

    int columnWidth() default 30;

    String columnName() default "";

}
