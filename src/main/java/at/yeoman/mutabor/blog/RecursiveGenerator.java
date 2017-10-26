
package at.yeoman.mutabor.blog;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static at.yeoman.mutabor.functional.Currying.applyLast;

class RecursiveGenerator
{
    private final File sourceDirectory;
    private final FileProcessor fileProcessor;
    
    private RecursiveGenerator(File sourceDirectory, Configuration configuration)
    {
        this.sourceDirectory = sourceDirectory;
        fileProcessor = new FileProcessor(configuration);
    }
    
    static void generateBlog(File sourceDirectory, Configuration configuration)
    {
        new RecursiveGenerator(sourceDirectory, configuration).generateBlog();
    }
    
    private void generateBlog()
    {
        processDirectory(sourceDirectory, Collections.emptyList());
    }
    
    @SuppressWarnings("ConstantConditions")
    private void processDirectory(File sourceDirectory, List<String> relativePath)
    {
        File[] files = sourceDirectory.listFiles();
        
        Arrays.stream(files)
            .filter(File::isFile)
            .filter(file -> file.getName().endsWith(".md"))
            .forEach(applyLast(fileProcessor::processFile, relativePath));
        
        Arrays.stream(files)
            .filter(File::isDirectory)
            .forEach(applyLast(this::processDirectory, relativePath));
    }
}
