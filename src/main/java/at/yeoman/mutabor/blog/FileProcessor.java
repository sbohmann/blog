
package at.yeoman.mutabor.blog;
    
    import java.io.File;
    import java.util.List;

class FileProcessor
{
    private final String template;
    private final File outputDirectory;
    
    FileProcessor(String template, File outputDirectory)
    {
        if (!outputDirectory.isDirectory())
        {
            throw new IllegalArgumentException("Not a directory: [" + outputDirectory + "]");
        }
        
        this.template = template;
        this.outputDirectory = outputDirectory;
    }
    
    void processFile(File markdownFile, List<String> relativePath)
    {
        new HtmlFileGenerator(template, outputDirectory, markdownFile, relativePath).
            generateHtmlFile();
    }
}
