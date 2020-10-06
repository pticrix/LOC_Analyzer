//Classe pour créer un fichier CSV

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FichierCSV {

    public static void exportToCSV (LOC_Analyzer analyzer, File file) throws IOException {

        File classCSV = new File(System.getProperty("user.dir") + File.separator + "classes.csv");
        File methodCSV = new File(System.getProperty("user.dir") + File.separator + "methodes.csv");

        FileWriter classWriter = new FileWriter(classCSV);
        FileWriter methodWriter = new FileWriter(methodCSV);

        classWriter.write("chemin, class, classe_LOC, classe_CLOC, classe_DC");
        methodWriter.write("chemin, class, methode, methode_LOC, methode_CLOC, methode_DC");

        String currentPath = file.getAbsolutePath();

        analyzer.getListClasses().forEach(classe -> {
            try {
                classWriter.write(currentPath+", "+ classe.getName() + ", " + classe.getLOC() +", " + classe.getCLOC() + ", " + classe.getDC());
            } catch (IOException e) {
                e.printStackTrace();
            }
            classe.getClass_methods().forEach((methodName, method) -> {
                try {
                    methodWriter.write(currentPath + ", " + classe.getName() + ", " + methodName + ", " + method.getLOC() +", " + method.getCLOC() + ", " + method.getDC());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

        classWriter.close();
        methodWriter.close();
    }

}