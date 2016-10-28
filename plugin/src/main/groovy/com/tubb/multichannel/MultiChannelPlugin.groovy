import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiChannelPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.extensions.create('multichannel', MultiChannelExtension)

    }
}