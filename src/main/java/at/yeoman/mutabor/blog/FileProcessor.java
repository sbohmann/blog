
package at.yeoman.mutabor.blog;

import java.io.File;
import java.util.List;

class FileProcessor
{
    private final String template;
    private final File outputDirectory;
    
    FileProcessor(Configuration configuration)
    {
        this.template = configuration.template;
        this.outputDirectory = configuration.outputDirectory;
    
        if (!outputDirectory.isDirectory())
        {
            throw new IllegalArgumentException("Not a directory: [" + outputDirectory + "]");
        }
    }
    
    void processFile(File markdownFile, List<String> relativePath)
    {
        new HtmlFileGenerator()
            .setTemplate(template)
            .setOutputDirectory(outputDirectory)
            .setMarkdownFile(markdownFile)
            .setRelativePath(relativePath)
            .generateHtmlFile();
    }
}
