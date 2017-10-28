
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
    
    HtmlFileGenerator(String template, File outputDirectory, File markdownFile, List<String> relativePath)
    {
        this.template = template;
        this.outputDirectory = outputDirectory;
        this.markdownFile = markdownFile;
        this.relativePath = relativePath;
    }
    
    private File createOutputFile()
    {
        String name = markdownFile.getName();
        String outputFileName = getMarkdownFileBaseName(name) + ".html";
        return new File(createOutputSubdirectory(), outputFileName);
    }
    
    private String getMarkdownFileBaseName(String name)
    {
        String suffix = ".md";
        if (!name.endsWith(suffix))
        {
            throw new IllegalArgumentException(
                "Not a markdown file: [" + markdownFile.getAbsolutePath() + "]");
        }
        return name.substring(0, name.length() - suffix.length());
    }
    
    private File createOutputSubdirectory()
    {
        File result = outputDirectory;
        for (String subdirectory : relativePath)
        {
            result = createSubdirectory(result, subdirectory);
        }
        return result;
    }
    
    private File createSubdirectory(File result, String subdirectory)
    {
        result = new File(result, subdirectory);
        if (!result.isDirectory())
        {
            throw new IllegalArgumentException(
                "Sub-path of output directory [" + outputDirectory + "]" +
                    " is not a directory: [" + result.getAbsolutePath() + "]" +
                    " for input file [" + markdownFile.getAbsolutePath() + "]");
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
            OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(outputFile),
                StandardCharsets.UTF_8);
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
            return new String(
                new FileInputStream(markdownFile).readAllBytes(),
                StandardCharsets.UTF_8);
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }
}
