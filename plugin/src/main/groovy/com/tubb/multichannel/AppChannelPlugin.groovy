package com.tubb.multichannel

import org.gradle.api.Plugin
import org.gradle.api.Project

public class AppChannelPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.extensions.create('appchannel', AppChannelExtension)
        project.ext {
            buildChannel = this.&buildChannel
        }
        project.afterEvaluate { // tasks is already fine
            boolean hasChannelProperty = project.hasProperty('channel')
            boolean disableDebugTask = project['appchannel'].disableDebugTask
            boolean disableLintTask = project['appchannel'].disableLintTask
            boolean disableTestTask = project['appchannel'].disableTestTask
            if (hasChannelProperty) {
                def targetTasks = project.tasks.findAll{ task ->
                    def taskName = task.name
                    if (disableDebugTask && taskName.contains("Debug"))
                        return true
                    if (disableLintTask && (taskName.contains("lint") || taskName.contains("Lint")))
                        return true
                    if (disableTestTask && taskName.contains("Test"))
                        return true
                    return false
                }
                targetTasks.each{
                    println "disable task ${it.name} by AppChannelPlugin"
                    it.setEnabled false
                }
            }
            customOutput(project)
        }
    }

    private void customOutput(Project project) {
        String outputDir = project['appchannel'].outputDir
        def buildOutputFileName = project['appchannel'].buildOutputFileName
        project.android.applicationVariants.all { variant ->
            variant.outputs.each { output ->
                def sourceFile = output.outputFile
                String targetDir = outputDir != null ? outputDir : sourceFile.parent
                String targetFileName = sourceFile.name
                if (buildOutputFileName != null) {
                    targetFileName = buildOutputFileName(project, variant)
                }
                output.outputFile = new File(targetDir, targetFileName)
            }
        }
    }

    public void buildChannel(Project project) {
        def channelFilePath = project['appchannel'].channelFilePath
        def buildProductFlavor = project['appchannel'].buildProductFlavor
        File channelFile = new File(channelFilePath)
        if (channelFile.isDirectory() || !channelFile.exists()) {
            throw new IllegalArgumentException('channelFilePath is not a valid file path!')
        }
        if (buildProductFlavor == null) {
            throw new IllegalArgumentException('customProductFlavor cant not be null!')
        }
        boolean hasChannelProperty = project.hasProperty('channel')
        if (hasChannelProperty) {
            project.configure(project.android.productFlavors, {
                channelFile.eachLine { name ->
                    if(!name.startsWith("#")){
                        project.android.productFlavors.create(name, buildProductFlavor(name))
                    }
                }
            })
        }
    }
}