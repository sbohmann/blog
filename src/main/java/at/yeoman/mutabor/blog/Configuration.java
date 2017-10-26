
package at.yeoman.mutabor.blog;

import java.io.File;

class Configuration
{
    final String template;
    final File outputDirectory;
    
    Configuration(String template, File outputDirectory)
    {
        this.template = template;
        this.outputDirectory = outputDirectory;
    }
}
