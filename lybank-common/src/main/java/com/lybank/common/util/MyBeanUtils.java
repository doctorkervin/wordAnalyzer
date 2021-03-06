package com.lybank.common.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * @author qilizhi
 * @date 2016年7月13日 下午4:49:56
 */
public abstract class MyBeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * Copy the property values of the given source bean into the target bean
     * without null value.
     * <p>
     * Note: The source and target classes do not have to match or even be
     * derived from each other, as long as the properties match. Any bean
     * properties that the source bean exposes but the target bean does not will
     * silently be ignored.
     * <p>
     * This is just a convenience method. For more complex transfer needs,
     * consider using a full BeanWrapper.
     *
     * @param source the source bean
     * @param target the target bean
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) throws BeansException {
        copyPropertiesIgnoreNull(source, target, null, (String[]) null);
    }

    /**
     * Copy the property values of the given source bean into the given target
     * bean without null value, only setting properties defined in the given
     * "editable" class (or interface).
     * <p>
     * Note: The source and target classes do not have to match or even be
     * derived from each other, as long as the properties match. Any bean
     * properties that the source bean exposes but the target bean does not will
     * silently be ignored.
     * <p>
     * This is just a convenience method. For more complex transfer needs,
     * consider using a full BeanWrapper.
     *
     * @param source   the source bean
     * @param target   the target bean
     * @param editable the class (or interface) to restrict property setting to
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target, Class<?> editable) throws BeansException {
        copyPropertiesIgnoreNull(source, target, editable, (String[]) null);
    }

    private static void copyPropertiesIgnoreNull(Object source, Object target, Class<?> editable,
                                                 String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName()
                        + "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0],
                            readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);

                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            if (value != null) {
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

}