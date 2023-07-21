package com.zaka.utils;

import com.zaka.annotation.Excel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

@Slf4j
public class ExcelUtils {

    public static void export(final Object obj) throws IllegalAccessException {
        Assert.notNull(obj, "not null please.");

        final Class<?> c = obj.getClass();
        log.info("{}", c);

        for (final Field field : c.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Excel.class)) {
                continue;
            }

            field.setAccessible(true);

            final Excel a = field.getAnnotation(Excel.class);
            var aa = a.type();
            var ab = a.columnWidth();
            String columnName = StringUtils.defaultIfBlank(a.columnName(), field.getName());
            log.info("{}, {}, {}", field.getName(), field.get(obj), columnName);
        }
    }
}
