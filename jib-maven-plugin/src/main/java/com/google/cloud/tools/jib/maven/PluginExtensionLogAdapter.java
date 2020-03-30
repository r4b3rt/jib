package com.google.cloud.tools.jib.maven;

import com.google.cloud.tools.jib.api.LogEvent;
import com.google.cloud.tools.jib.plugins.api.maven.JibMavenPluginExtension;
import java.util.function.Consumer;

class PluginExtensionLogAdapter {

  private final Consumer<LogEvent> logger;

  PluginExtensionLogAdapter(Consumer<LogEvent> logger) {
    this.logger = logger;
  }

  void log(JibMavenPluginExtension.LogLevel logLevel, String message) {
    switch (logLevel) {
      case ERROR:
        logger.accept(LogEvent.error(message));
        break;
      case WARN:
        logger.accept(LogEvent.warn(message));
        break;
      case LIFECYCLE:
        logger.accept(LogEvent.lifecycle(message));
        break;
      case INFO:
        logger.accept(LogEvent.info(message));
        break;
      case DEBUG:
        logger.accept(LogEvent.debug(message));
        break;
      default:
        throw new RuntimeException();
    }
  }
}
