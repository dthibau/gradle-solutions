package org.formation

import java.io.File;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

class UpdateVersionTask extends DefaultTask {

  @Input ProjectVersion projectVersion
  @OutputFile File destFile

  UpdateVersionTask() {
    group = 'build'
    description = 'Update the version file.'
  }
  @TaskAction
  void start() {

  	print "Input properties : ${inputs.properties} "
    ant.propertyfile(file: destFile) {
      entry(key: 'major', type: 'string', operation: '=', value: projectVersion.majorNumber)
      entry(key: 'minor', type: 'string', operation: '=', value: projectVersion.minorNumber)
      entry(key: 'classifier', type: 'string', operation: '=', value: projectVersion.classifier)
    }
  }
}


