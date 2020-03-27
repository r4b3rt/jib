/*
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.jib.plugins.api.maven;

import com.google.cloud.tools.jib.api.buildplan.ContainerBuildPlan;
import java.util.function.BiConsumer;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

/**
 * Jib Maven plugin extension API. If a class implementing the interface is visible on the classpath
 * of the Jib Maven plugin, the Jib plugin extension framework calls the interface method of the
 * class.
 */
public interface JibMavenPluginExtension {

  /** Log levels, in order of verbosity. */
  enum LogLevel {
    ERROR,
    WARN,
    LIFECYCLE,
    INFO,
    DEBUG
  }

  ContainerBuildPlan extendContainerBuildPlan(
      ContainerBuildPlan buildPlan,
      MavenProject project,
      MavenSession session,
      BiConsumer<LogLevel, String> consoleLogger)
      throws JibPluginExtensionException;
}
