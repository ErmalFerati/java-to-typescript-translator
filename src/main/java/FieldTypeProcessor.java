import annotations.CustomField;
import annotations.Numerical;
import annotations.Stringified;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class FieldTypeProcessor {


    private FieldTypeProcessor() {
    }

    public static String processJavaClassName(Field field) {
        Class clazz = field.getType();

        if (isCustomField(field)) {
            return getCustomFieldName(field);
        }

        if (isStringField(field)) {
            return "string";
        }

        if (isFieldNumerical(field)) {
            return "number";
        }

        return clazz.getSimpleName();
    }

    private static boolean isStringField(Field field) {
        Class fieldType = field.getType();
        String fieldTypeName = fieldType.getSimpleName();
        return isFieldAnnotatedAs(field, Stringified.class) || fieldTypeName.equalsIgnoreCase("string");
    }

    private static boolean isFieldNumerical(Field field) {
        String className = field.getType().getSimpleName();

        List<String> numericalFieldNames = getNumericalFieldNames();

        return isFieldAnnotatedAs(field, Numerical.class) ||
                numericalFieldNames.stream().anyMatch(type -> type.equalsIgnoreCase(className));
    }

    private static boolean isCustomField(Field field) {
        return isFieldAnnotatedAs(field, CustomField.class);
    }

    private static boolean isFieldAnnotatedAs(Field field, Class type) {
        return field.isAnnotationPresent(type);
    }

    private static String getCustomFieldName(Field field) {
        CustomField customField = field.getAnnotation(CustomField.class);
        return customField.name();
    }

    private static List<String> getNumericalFieldNames() {
        NumericalFieldType[] numericalFieldTypes = NumericalFieldType.values();
        List<NumericalFieldType> numericalFieldTypesValues = Arrays.asList(numericalFieldTypes);
        return numericalFieldTypesValues.stream().map(NumericalFieldType::getName).collect(Collectors.toList());
    }
}
