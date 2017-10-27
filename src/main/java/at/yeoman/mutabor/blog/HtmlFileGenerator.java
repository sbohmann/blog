
package at.yeoman.mutabor.blog;

import at.yeoman.mutabor.simpleTemplates.SimpleTemplateEngine;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

class HtmlFileGenerator
{
    private String template;
    private File outputDirectory;
    private File markdownFile;
    private List<String> relativePath;
    
    HtmlFileGenerator setTemplate(String template)
    {
        this.template = template;
        return this;
    }
    
    HtmlFileGenerator setOutputDirectory(File outputDirectory)
    {
        this.outputDirectory = outputDirectory;
        return this;
    }
    
    HtmlFileGenerator setMarkdownFile(File markdownFile)
    {
        this.markdownFile = markdownFile;
        return this;
    }
    
    HtmlFileGenerator setRelativePath(List<String> relativePath)
    {
        this.relativePath = relativePath;
        return this;
    }
    
    private File createOutputFile()
    {
        String suffix = ".md";
        String name = markdownFile.getName();
        if (!name.endsWith(suffix))
        {
            throw new IllegalArgumentException(
                "Not a markdown file: [" + markdownFile.getAbsolutePath() + "]");
        }
        return new File(createOutputSubdirectory(),
            name.substring(0, name.length() - suffix.length()) + ".html");
    }
    
    private File createOutputSubdirectory()
    {
        File result = outputDirectory;
        for (String subdirectory : relativePath)
        {
            result = new File(result, subdirectory);
            
            if (!result.isDirectory())
            {
                throw new IllegalArgumentException(
                    "Sub-path of output directory [" + outputDirectory + "]" +
                        " is not a directory: [" + result.getAbsolutePath() + "]" +
                        " for input file " + markdownFile);
            }
        }
        return result;
    }
    
    void generateHtmlFile()
    {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(readFile(markdownFile));
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        
        String result = SimpleTemplateEngine.replaceVariables(template,
            Map.of("content", renderer.render(document)));
    
        File outputFile = createOutputFile();
    
        try
        {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);
            writer.write(result);
            writer.close();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }
    
    private String readFile(File markdownFile)
    {
        try
        {
            return new String(new FileInputStream(markdownFile).readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }
}
