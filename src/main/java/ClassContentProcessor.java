import annotations.Import;
import annotations.TypescriptClass;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassContentProcessor {

    private static final String CONSTRUCTOR_CODE = "  public constructor() {\n  }\n\n";
    private static final String GENERATED_CLASS_LOCAL_PATH = System.getProperty("user.dir") + "/src/main/webapp/src/model/%s.ts";
    private static final String CLASS_DECLARATION_FORMAT = "export class %s {\n\n";
    private static final String CLASS_IMPORT_FORMAT = "import {%s} from \"./%s\";\n";
    private static final String FIELDS_DECLARATION_FORMAT = "  public %s: %s;\n";
    private static final String CLASS_CLOSING_TAG = "}";
    private static final String NEW_LINE_TAG = "\n";

    private Class targetClass;

    private String classPath;
    private String classContent;

    public ClassContentProcessor(Class targetClass) {
        this.targetClass = targetClass;

        updateClassPath();
        updateClassContent();
    }

    public String getClassPath() {
        return classPath;
    }

    public String getClassContent() {
        return classContent;
    }

    private void updateClassPath() {
        classPath = String.format(GENERATED_CLASS_LOCAL_PATH, targetClass.getSimpleName());
    }

    private void updateClassContent() {
        String classImportsCode = getClassImports();
        String classDeclarationCode = getClassDeclarationCode();
        String classFieldsCode = generateFieldsCode();

        StringBuilder classCodeBuilder = new StringBuilder();
        classCodeBuilder.append(classImportsCode);
        classCodeBuilder.append(classDeclarationCode);
        classCodeBuilder.append(classFieldsCode);

        appendConstructorCodeIfNeeded(classCodeBuilder);
        classCodeBuilder.append(CLASS_CLOSING_TAG);

        classContent = classCodeBuilder.toString();
    }

    private String getClassImports() {
        Set<Class> classesToImport = getClassesToImport();

        StringBuilder imports = new StringBuilder();
        classesToImport.forEach(classToImport -> {
            String importForClass = getImportForClass(classToImport);
            imports.append(importForClass);
        });

        if (!classesToImport.isEmpty()) {
            imports.append(NEW_LINE_TAG);
        }
        return imports.toString();
    }

    private Set<Class> getClassesToImport() {
        List<Field> fields = getFieldsForClass();
        return fields.stream()
                .filter(this::shouldImportForField)
                .map(Field::getType)
                .collect(Collectors.toSet());
    }

    private List<Field> getFieldsForClass() {
        Field[] classFields = targetClass.getDeclaredFields();
        return Arrays.asList(classFields);
    }

    private boolean shouldImportForField(Field field) {
        return field.isAnnotationPresent(Import.class);
    }

    private String getClassDeclarationCode() {
        return String.format(CLASS_DECLARATION_FORMAT, targetClass.getSimpleName());
    }

    private String generateFieldsCode() {
        List<Field> classFields = getFieldsForClass();
        StringBuilder fieldsCode = new StringBuilder();

        classFields.forEach(field -> {
            String currentFieldCode = generateFieldCode(field);
            fieldsCode.append(currentFieldCode);
        });

        fieldsCode.append(NEW_LINE_TAG);
        return fieldsCode.toString();
    }

    private void appendConstructorCodeIfNeeded(StringBuilder stringBuilder) {
        TypescriptClass typescriptClass = (TypescriptClass) targetClass.getAnnotation(TypescriptClass.class);
        if (typescriptClass.withConstructor()) {
            stringBuilder.append(CONSTRUCTOR_CODE);
        }
    }

    private String getImportForClass(Class clazz) {
        String className = clazz.getSimpleName();
        return String.format(CLASS_IMPORT_FORMAT, className, className);
    }

    private String generateFieldCode(Field field) {
        String fieldTypeName = FieldTypeProcessor.processJavaClassName(field);
        return String.format(FIELDS_DECLARATION_FORMAT, field.getName(), fieldTypeName);
    }

}
