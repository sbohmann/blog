
package at.yeoman.mutabor.blog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

class BlogGenerator
{
    private final File sourceDirectory;
    private final File outputDirectory;
    private final String template;
    
    private BlogGenerator(String[] args) throws IOException
    {
        fail(() -> args.length != 2, () -> "Expected exactly two arguments: <source directory> <outputDirectory>");
        
        sourceDirectory = getDirectory("Input", args[0]);
        outputDirectory = getDirectory("Output", args[1]);
        template = readTemplate();
    }
    
    public static void main(String[] args)
    {
        try
        {
            new BlogGenerator(args).run();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            System.exit(1);
        }
    }
    
    private void run()
    {
        RecursiveGenerator.generateBlog(sourceDirectory,
            new Configuration(template, outputDirectory));
    }
    
    private File getDirectory(String role, String path)
    {
        File sourceDirectory = new File(path);
        
        if (sourceDirectory.exists())
        {
            fail(() -> !sourceDirectory.isDirectory(), () -> "Not a directory: [" + sourceDirectory.getAbsolutePath() + "]");
        }
        else
        {
            fail(() -> true, () -> role + " directory [" + sourceDirectory.getAbsolutePath() + "] does not exist.");
        }
        
        return sourceDirectory;
    }
    
    private String readTemplate() throws IOException
    {
        File templateFile = new File(sourceDirectory, "template.html");
        fail(() -> !templateFile.isFile(), () -> "Missing or non-file template: [" + templateFile.getAbsolutePath() + "]");
        return new String(new FileInputStream(templateFile).readAllBytes(), StandardCharsets.UTF_8);
    }
    
    private static void fail(Supplier<Boolean> errorCondition, Supplier<String> errorMessage)
    {
        if (errorCondition.get())
        {
            System.err.println(errorMessage.get());
            System.exit(1);
        }
    }
}
