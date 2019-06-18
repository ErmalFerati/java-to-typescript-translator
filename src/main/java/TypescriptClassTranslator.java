import annotations.TypescriptClass;
import org.reflections.Reflections;
import shaded.parquet.org.slf4j.Logger;
import shaded.parquet.org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class TypescriptClassTranslator {

    private static final Logger logger = LoggerFactory.getLogger(TypescriptClassTranslator.class);

    public static void main(String[] args) {
        Set<Class<?>> annotatedClasses = getAnnotatedClasses();
        annotatedClasses.forEach(TypescriptClassTranslator::createClassFile);

        logger.info("Finished translating {} classes.", annotatedClasses.size());
    }

    private static Set<Class<?>> getAnnotatedClasses() {
        Reflections reflections = new Reflections();
        return reflections.getTypesAnnotatedWith(TypescriptClass.class);
    }

    private static void createClassFile(Class clazz) {
        ClassContentProcessor classContentProcessor = new ClassContentProcessor(clazz);

        String classPath = classContentProcessor.getClassPath();
        File file = new File(classPath);

        writeClassCode(classContentProcessor, file);
    }

    private static void writeClassCode(ClassContentProcessor classContentProcessor, File file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
             PrintWriter writer = new PrintWriter(bufferedWriter)) {
            String classCode = classContentProcessor.getClassContent();
            writer.println(classCode);
            writer.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
